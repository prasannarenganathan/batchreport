package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.service;

import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnasurety.extagencyint.batches.ivans.reporting.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.reporting.util.ReportingUtil;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.dto.KeyValueDTO;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.DocumentEntity;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessage;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessageAttachment;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.KeyValue;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.Notification;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.NotificationAgencyExtension;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.PackageEntity;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.DocumentRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.EventAuditRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.IvansMessageAttachmentRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.IvansMessageRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.KeyValueRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.NotificationAgencyExtensionRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.NotificationRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.PackageRepository;
import com.opencsv.CSVWriter;

@Component
public class WorkFlowExportServiceImpl implements WorkFlowExportService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    ApplicationConfig applicationConfig;
    
    @Autowired
    EventAuditRepository eventAuditRepository;
    
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationAgencyExtensionRepository notificationAgencyExtensionRepository;
    
    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    KeyValueRepository keyValueRepository;
    
    @Autowired
    IvansMessageRepository ivansMessageRepository;
    
    @Autowired
    IvansMessageAttachmentRepository ivansMessageAttachmentRepository;
    
    

    @Override
    public String exportEventAuditTable(Timestamp lastExecutedTimeStamp) {
        try {
            logger.info("Exporting Event Auit Table");
            File file = new File(applicationConfig.getFilePath()+"EVENT_AUDIT_TABLE.csv");
            FileWriter outputfile = new FileWriter(file);
            List<EventAudit> eventAudits = null;
            CSVWriter writer = new CSVWriter(outputfile, '|', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            List<String[]> data = new ArrayList<String[]>();

            if (Objects.isNull(lastExecutedTimeStamp)) {
                eventAudits = eventAuditRepository.findAll();
            } else {
                eventAudits = eventAuditRepository.findAllByTimeStamp(lastExecutedTimeStamp);
            }
            logger.info("Number of Records Exported: {}", eventAudits.size());

            for (EventAudit e : eventAudits) {
                data.add(new String[] { e.getEventAuditKey(), e.getNotificationEventFromStatus(),
                        e.getNotificationEventToStatus(), e.getPackageEventFromStatus(), e.getPackageEventToStatus(),
                        ReportingUtil.convertToString(e.getLastModifiedDate()), e.getNotificationKey(), e.getPackageKey() });
            }
            writer.writeAll(data);
            writer.close();
        } catch (Exception e) {
            logger.error("Error while exporting EVENT_AUDIT_TBL {}", e);
        }
        return null;
    }

    @Override
    public String exportKeyValueTable(Timestamp lastExecutedTimeStamp) {
        try {
            logger.info("Exporting Key Value Table");

            File file = new File(applicationConfig.getFilePath()+"KEY_VALUE_TABLE.csv");
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile, '|', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            List<String[]> data = new ArrayList<String[]>();

            List<KeyValue> keyValues = null;
            if (Objects.isNull(lastExecutedTimeStamp)) {
                keyValues = keyValueRepository.findAll();
            } else {
                keyValues = keyValueRepository.findAllByTimeStamp(lastExecutedTimeStamp);
            }
            logger.info("Number of Records Exported: {}", keyValues.size());

            keyValues.forEach(keyValue -> {
                KeyValueDTO keyValueDTO = new KeyValueDTO();
                BeanUtils.copyProperties(keyValue, keyValueDTO);
                System.out.println(keyValue.getKeyValuePairId().toString());
                if (keyValue.getKeyValuePairTypeCode().equals("NOTIFICATION_TBL")) {
                    keyValueDTO.setForiegnKeyId(
                            notificationRepository.findNotificationIdByKeyValuePairId(keyValue.getKeyValuePairId()));
                } else if (keyValue.getKeyValuePairTypeCode().equals("PACKAGE_TBL")) {
                    keyValueDTO.setForiegnKeyId(
                            packageRepository.findPackageIdByKeyValuePairId(keyValue.getKeyValuePairId()));
                } else { // (keyValue.getKeyValuePairTypeCode().equals("DOCUMENT_TBL")) {
                    keyValueDTO.setForiegnKeyId(
                            documentRepository.findDocumentIdByKeyValuePairId(keyValue.getKeyValuePairId()));
                }

                data.add(new String[] { keyValueDTO.getKeyValueKey(), keyValueDTO.getKey(), keyValueDTO.getValue(),
                		ReportingUtil.convertToString(keyValueDTO.getLastModifiedDate()), ReportingUtil.convertToString(keyValueDTO.getKeyValuePairId()),
                        keyValueDTO.getKeyValuePairTypeCode(), keyValueDTO.getForiegnKeyId() });

            });
            writer.writeAll(data);
            writer.close();
        } catch (Exception e) {
        	logger.error("Error: ",e);
        }
        return null;
    }

	@Override
	public String exportIvansMessageTables(Timestamp lastExecutedTimeStamp) {
		 try {
	            logger.info("Exporting Ivans Message and Attachment Table");
	            
	            File file = new File(applicationConfig.getFilePath()+"IVANS_MESSAGE_TABLE.csv");
	            FileWriter outputfile = new FileWriter(file);

	            CSVWriter writer = new CSVWriter(outputfile, '|', CSVWriter.NO_QUOTE_CHARACTER,
	                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
	            List<String[]> data = new ArrayList<String[]>();

	            List<IvansMessage> ivansMessages = null;
	            List<IvansMessageAttachment> ivansMessageAttachments = null;
	            if (Objects.isNull(lastExecutedTimeStamp)) {
	            	ivansMessages = ivansMessageRepository.findAll();
	            
	            } else {
	            	ivansMessages = ivansMessageRepository.findAllByTimeStamp(lastExecutedTimeStamp);
	            }
	            
	            
	            for (IvansMessage ivansMessage : ivansMessages) {
	            	ivansMessageAttachments = ivansMessageAttachmentRepository.findByIvansMessageKey(ivansMessage.getIvansMessageKey());
	            	if(!ivansMessageAttachments.isEmpty()) {
		            	for(IvansMessageAttachment ivansMessageAttachment:ivansMessageAttachments) {
		            		data.add(new String[] {
		            				ReportingUtil.convertToString(ivansMessage.getIvansMessageKey()),
			                		ivansMessage.getAgencyStateCode(),ivansMessage.getAgencyCode(),
			                		ivansMessage.getNaicsCode(),	
			                		ivansMessage.getBondNumber(),
			                		ivansMessage.getTermEffectiveDate().toString(),
			                		ReportingUtil.convertToString(ivansMessage.getTermExpiryDate()),
			                		ReportingUtil.convertToString(ivansMessage.getTransactionDate()),
			                		ivansMessage.getLineOfBusiness(),
			                		ivansMessage.getPrincipalName(),
			                		ReportingUtil.convertToString(ivansMessage.getEventDate()),
			                		ivansMessage.getActivityNoteTypeCode(),
			                		ivansMessage.getBusinessPurposeTypeCode(),
			                		ivansMessage.getRemarkText(),
			                		ReportingUtil.convertToString(ivansMessage.getLastModifiedDate()),
			                		String.valueOf(ivansMessage.getDeliveryFailureCount()),
			                	
			                		ReportingUtil.convertToString(ivansMessageAttachment.getIvansMessgaeAttachmentKey()),
			                		ivansMessageAttachment.getAttachmentTypeCode(),
			            	        ivansMessageAttachment.getAttachmentDescription(),
			            	        ivansMessageAttachment.getAttachmentFileName(),
			            	        ivansMessageAttachment.getMimeTypeCode(),
			            	        ReportingUtil.convertToString(ivansMessageAttachment.getLastModifiedDate()),
			            	        ReportingUtil.convertToString(ivansMessageAttachment.getPackageKey())//,
			            	        
			            	        //ReportingUtil.convertToString(ivansMessageAttachment.getIvansMessageKey()
		            		});
			            }
	            	}else {
		            	data.add(new String[] {
		                		ivansMessage.getIvansMessageKey().toString(),ivansMessage.getAgencyStateCode(),ivansMessage.getAgencyCode(),
		                		ivansMessage.getNaicsCode(),	ivansMessage.getBondNumber(),ReportingUtil.convertToString(ivansMessage.getTermEffectiveDate()),
		                		ReportingUtil.convertToString(ivansMessage.getTermExpiryDate()),
		                		ReportingUtil.convertToString(ivansMessage.getTransactionDate()),
		                		ivansMessage.getPrincipalName(),
		                		ReportingUtil.convertToString(ivansMessage.getEventDate()),
		                		ivansMessage.getBusinessPurposeTypeCode(),ivansMessage.getRemarkText(),
		                		ReportingUtil.convertToString(ivansMessage.getLastModifiedDate()),
		                		String.valueOf(ivansMessage.getDeliveryFailureCount()),
		                				
		                		"","","","","","",""});
		            }
	            }
	                
	            
	            logger.info("Number of Records Exported: {}", data.size());
	            writer.writeAll(data);
	            writer.close();

	            
	        } catch (Exception e) {
	        	logger.error("Error: ",e);
	        }
	        return null;
	}

	@Override
	public String exportNotificationTables(Timestamp lastExecutedTimeStamp) {

		 try {
	            logger.info("Exporting Notification, Notification Agency Extension, Package and Document Table");
	            
	            File file = new File(applicationConfig.getFilePath()+"NOTIFICATION_TABLE.csv");
	            FileWriter outputfile = new FileWriter(file);

	            CSVWriter writer = new CSVWriter(outputfile, '|', CSVWriter.NO_QUOTE_CHARACTER,
	                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
	            List<String[]> data = new ArrayList<String[]>();

	            List<Notification> notifications = null;
	            NotificationAgencyExtension notificationAgencyExtension = null;
	            List<PackageEntity> packages = null;
	            List<DocumentEntity> documents = null;
	            if (Objects.isNull(lastExecutedTimeStamp)) {
	            	notifications = notificationRepository.findAll();
	            } else {
	            	notifications = notificationRepository.findAllByTimeStamp(lastExecutedTimeStamp);
	            }
	             	
	            
	            for (Notification notification : notifications) {
	            	notificationAgencyExtension = 	notificationAgencyExtensionRepository.findByNotificationKey(notification.getNotificationKey());
	            	if(notificationAgencyExtension!=null) {
	            	
		            	packages = packageRepository.findByNotificationKey(notification.getNotificationKey());
		            	if(!packages.isEmpty()) {
			            	for(PackageEntity packageEntity: packages) {
			            		documents = documentRepository.findByPackageKey(packageEntity.getPackageKey());
			            		if(!documents.isEmpty()) {
			            			for(DocumentEntity documentEntity: documents) {
			            				  data.add(new String[]{
			            						//NOTIFICATION TABLE		
			            						  /*"NOTIFICATION_TYPE_CODE","NOTIFICATION_GLOBAL_ID","AGENCY_STATE_CODE","AGENCY_CODE",
			            		    	            "PROCESSING_OFFICE_TYPE_CODE","PROCESSING_OFFICE_CODE","ACCOUNT_NUMBER","WRITING_COMPANY_CODE",	
			            		    	            "SUBMISSION_NUMBER","BOND_NUMBER","TERM_EFFECTIVE_DATE","TERM_EXPIRY_DATE",		
			            		    	            "TERM_NUMBER","TRANSACTION_DATE","LINE_OF_BUSINESS","PRINCIPAL_NAME", 				
			            		    	            "EVENT_DATE","EVENT_TYPE_CODE","EVENT_SUB_TYPE_CODE","SPECIAL_HANDLING_INDICATOR", 	
			            		    	            "REMARK_TEXT","NOTIFICATION_WORKFLOW_STATUS_TYPE_CODE","LAST_MODIFIED_DATE","NOTIFICATION_KEY", 				
			            		    	            "IVANS_MESSAGE_KEY","KEY_VALUE_PAIR_ID",
			            		    	          */  
							    		  notification.getNotificationTypeCode(), notification.getNotificationGlobalId(), notification.getAgencyStateCode(),notification.getAgencyCode(),
							    		  notification.getProcessingOfficeTypeCode(),notification.getProcessingOfficeCode(),notification.getAccountNumber(),notification.getWritingCompanyCode(),
							    		  notification.getSubmissionNumber(),notification.getBondNumber(),ReportingUtil.convertToString(notification.getTermEffectiveDate()),ReportingUtil.convertToString(notification.getTermExpiryDate()),
							    		  notification.getTermNumber(),notification.getTransactionDate(),notification.getLineOfBusiness(),notification.getPrincipalName(),
							    		  notification.getEventDate().toString(),notification.getEventTypeCode(),notification.getEventSubTypeCode(),notification.getSpecialHandlingIndicator(),
							    		  notification.getRemarkText(),notification.getNotificationWorkflowStatusTypeCode(),ReportingUtil.convertToString(notification.getLastModifiedDate()),ReportingUtil.convertToString(notification.getNotificationKey()),
							    		  notification.getIvansMessageKey(),notification.getKeyValuePairId(),String.valueOf(notification.getNotificationFailureCount()),
							    		  
							    		  
							    		//NOTIFICATION AGENCY EXTENSION TABLE
						    	            /*"NOTIFICATION_AGENCY_EXTENSION_KEY","IVANS_ENROLLMENT_IND","IVANS_PREF_DIRBILRPRTS", 			
						    	            "IVANS_PREF_AGCYBILSTMTS","IVANS_PREF_IBL","IVANS_PREF_SF_TX", 					
						    	            "IVANS_PREF_BR_TX","IVANS_YACCTNUM","IVANS_LASTUPDATE_DTTM"
						    	            */
							    		  notificationAgencyExtension.getNotificationAgencyExtensionKey(),ReportingUtil.convertToString(notificationAgencyExtension.getIvansEnrollmentInd()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefDirbilrprts()),
							    		  ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefAgcybilstmts()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefIbl()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefSfTx()),
							    		  ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefBrTx()),notificationAgencyExtension.getIvansYacctnum(),ReportingUtil.convertToString(notificationAgencyExtension.getIvansLastupdateDttm()),//ReportingUtil.convertToString(notificationAgencyExtension.getNotificationKey()),
							    		  
							    		  
							    		//PACKAGE TABLE
						    	            /*"PACKAGE_KEY","PACKAGE_TYPE_CODE","PACKAGE_ID", 				
						    	            "PACKAGE_DESCRIPTION","PACKAGE_OUTPUT_FILE_NAME","MIME_TYPE_CODE", 			
						    	            "KEY_VALUE_PAIR_ID","PACKAGE_WORKFLOW_STATUS_TYPE","LAST_MODIFIED_DATE"*/
							    		  ReportingUtil.convertToString(packageEntity.getPackageKey()), 	packageEntity.getPackageTypeCode(), 		packageEntity.getPackageId(),
							    		  packageEntity.getPackageDescription(),							packageEntity.getPackageOutputFileName(),	packageEntity.getMimeTypeCode(),
							    		  ReportingUtil.convertToString(packageEntity.getKeyValuePairId()),								packageEntity.getPackageWorkflowStatusType(),ReportingUtil.convertToString(packageEntity.getLastModifiedDate()),
							    		  String.valueOf(packageEntity.getPackageFailureCount()),
							    		  //ReportingUtil.convertToString(packageEntity.getNotificationKey()),
				
							    		//DOCUMENT TABLE
						    	            /*"DOCUMENT_KEY","DOCUMENT_TYPE_CODE","DOCUMENT_ID", 				
						    	            "MIME_TYPE_CODE","DOCUMENT_REPOSITORY_CODE","KEY_VALUE_PAIR_ID", 		
						    	            "LAST_MODIFIED_DATE"*/
							    		  documentEntity.getDocumentKey(),	documentEntity.getDocumentTypeCode(),	documentEntity.getDocumentId(),
							    		  documentEntity.getMimeTypeCode(),	documentEntity.getRepositoryCode(),		documentEntity.getKeyValuePairId(),
							    		  //ReportingUtil.convertToString(documentEntity.getPackageKey()),	
							    		  ReportingUtil.convertToString(documentEntity.getLastModifiedDate())
							    		  
			            				  });      				
			            			}
			            		}else {
			            			 data.add(new String[]{
			            					//export notification, notification agency extension and package  details
								    		  notification.getNotificationTypeCode(), notification.getNotificationGlobalId(), notification.getAgencyStateCode(),notification.getAgencyCode(),
								    		  notification.getProcessingOfficeTypeCode(),notification.getProcessingOfficeCode(),notification.getAccountNumber(),notification.getWritingCompanyCode(),
								    		  notification.getSubmissionNumber(),notification.getBondNumber(),ReportingUtil.convertToString(notification.getTermEffectiveDate()),ReportingUtil.convertToString(notification.getTermExpiryDate()),
								    		  notification.getTermNumber(),notification.getTransactionDate(),notification.getLineOfBusiness(),notification.getPrincipalName(),
								    		  notification.getEventDate().toString(),notification.getEventTypeCode(),notification.getEventSubTypeCode(),notification.getSpecialHandlingIndicator(),
								    		  notification.getRemarkText(),notification.getNotificationWorkflowStatusTypeCode(),ReportingUtil.convertToString(notification.getLastModifiedDate()),ReportingUtil.convertToString(notification.getNotificationKey()),
								    		  notification.getIvansMessageKey(),notification.getKeyValuePairId(),String.valueOf(notification.getNotificationFailureCount()),
								    		  
								    		  notificationAgencyExtension.getNotificationAgencyExtensionKey(),	ReportingUtil.convertToString(notificationAgencyExtension.getIvansEnrollmentInd()),	ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefDirbilrprts()),
								    		  ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefAgcybilstmts()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefIbl()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefSfTx()),
								    		  ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefBrTx()),notificationAgencyExtension.getIvansYacctnum(),ReportingUtil.convertToString(notificationAgencyExtension.getIvansLastupdateDttm()),ReportingUtil.convertToString(notificationAgencyExtension.getNotificationKey()),
								    		  
								    		  ReportingUtil.convertToString(packageEntity.getPackageKey()), packageEntity.getPackageTypeCode(), packageEntity.getPackageId(),
								    		  packageEntity.getPackageDescription(),packageEntity.getPackageOutputFileName(),packageEntity.getMimeTypeCode(),
								    		  ReportingUtil.convertToString(packageEntity.getKeyValuePairId()),packageEntity.getPackageWorkflowStatusType(),ReportingUtil.convertToString(packageEntity.getLastModifiedDate()),ReportingUtil.convertToString(packageEntity.getNotificationKey()),
								    		  String.valueOf(packageEntity.getPackageFailureCount()),
								    		  
								    		  "","","","","","","","" 
								    		  
			            			 });
			            		}
			            	}
		            	}else {
		            		//export notification and notifi agency extension details
		            		data.add(new String[]{
						    		  notification.getNotificationTypeCode(), notification.getNotificationGlobalId(), notification.getAgencyStateCode(),notification.getAgencyCode(),
						    		  notification.getProcessingOfficeTypeCode(),notification.getProcessingOfficeCode(),notification.getAccountNumber(),notification.getWritingCompanyCode(),
						    		  notification.getSubmissionNumber(),notification.getBondNumber(),ReportingUtil.convertToString(notification.getTermEffectiveDate()),ReportingUtil.convertToString(notification.getTermExpiryDate()),
						    		  notification.getTermNumber(),notification.getTransactionDate(),notification.getLineOfBusiness(),notification.getPrincipalName(),
						    		  notification.getEventDate().toString(),notification.getEventTypeCode(),notification.getEventSubTypeCode(),notification.getSpecialHandlingIndicator(),
						    		  notification.getRemarkText(),notification.getNotificationWorkflowStatusTypeCode(),ReportingUtil.convertToString(notification.getLastModifiedDate()),ReportingUtil.convertToString(notification.getNotificationKey()),
						    		  notification.getIvansMessageKey(),notification.getKeyValuePairId(),String.valueOf(notification.getNotificationFailureCount()),
						    		  
						    		  notificationAgencyExtension.getNotificationAgencyExtensionKey(),ReportingUtil.convertToString(notificationAgencyExtension.getIvansEnrollmentInd()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefDirbilrprts()),
						    		  ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefAgcybilstmts()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefIbl()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefSfTx()),
						    		  ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefBrTx()),notificationAgencyExtension.getIvansYacctnum(),ReportingUtil.convertToString(notificationAgencyExtension.getIvansLastupdateDttm()),ReportingUtil.convertToString(notificationAgencyExtension.getNotificationKey()),
						    		  
						    		  "","","","","","","","",
						    		  "","","","","","","","",""
		            		});
		            	}
	            	}else {
	            		data.add(new String[]{
					    		  notification.getNotificationTypeCode(), notification.getNotificationGlobalId(), notification.getAgencyStateCode(),notification.getAgencyCode(),
					    		  notification.getProcessingOfficeTypeCode(),notification.getProcessingOfficeCode(),notification.getAccountNumber(),notification.getWritingCompanyCode(),
					    		  notification.getSubmissionNumber(),notification.getBondNumber(),ReportingUtil.convertToString(notification.getTermEffectiveDate()),ReportingUtil.convertToString(notification.getTermExpiryDate()),
					    		  notification.getTermNumber(),notification.getTransactionDate(),notification.getLineOfBusiness(),notification.getPrincipalName(),
					    		  notification.getEventDate().toString(),notification.getEventTypeCode(),notification.getEventSubTypeCode(),notification.getSpecialHandlingIndicator(),
					    		  notification.getRemarkText(),notification.getNotificationWorkflowStatusTypeCode(),ReportingUtil.convertToString(notification.getLastModifiedDate()),ReportingUtil.convertToString(notification.getNotificationKey()),
					    		  notification.getIvansMessageKey(),notification.getKeyValuePairId(),String.valueOf(notification.getNotificationFailureCount()),
					    		  "","","","","","","","","",
					    		  "","","","","","","","",
					    		  "","","","","","","","",""
	            		});
	            	}
	            } 			
 
	            logger.info("Number of Records Exported: {}", data.size());
	            writer.writeAll(data);
	            writer.close();

	            
	        } catch (Exception e) {
	        	logger.error("Error: ",e);
	        }
	        return null;
	
	}

	@Override
	public void purgeTables() {
		notificationRepository.purgeTransactionTables();
		
	}
}
