package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.service;

import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnasurety.extagencyint.batches.ivans.reporting.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.dto.KeyValueDTO;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessage;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessageAttachment;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.KeyValue;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.DocumentRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.EventAuditRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.IvansMessageAttachmentRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.IvansMessageRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.KeyValueRepository;
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
                        e.getLastModifiedDate().toString(), e.getNotificationKey(), e.getPackageKey() });
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
                        keyValueDTO.getLastModifiedDate().toString(), keyValueDTO.getKeyValuePairId().toString(),
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
	            	if(ivansMessageAttachments.isEmpty()) {
		            	for(IvansMessageAttachment ivansMessageAttachment:ivansMessageAttachments) {
		            		data.add(new String[] {
			                		ivansMessage.getIvansMessageKey().toString(),
			                		ivansMessage.getAgencyStateCode(),ivansMessage.getAgencyCode(),
			                		ivansMessage.getNaicsCode(),	
			                		ivansMessage.getBondNumber(),
			                		ivansMessage.getTermEffectiveDate().toString(),
			                		ivansMessage.getTermExpiryDate()==null?"":ivansMessage.getTermExpiryDate().toString(),
			                		ivansMessage.getTransactionDate()==null?"":ivansMessage.getTransactionDate().toString(),
			                		ivansMessage.getLineOfBusiness(),
			                		ivansMessage.getPrincipalName(),
			                		ivansMessage.getEventDate()==null?"":ivansMessage.getEventDate().toString(),
			                		ivansMessage.getActivityNoteTypeCode(),
			                		ivansMessage.getBusinessPurposeTypeCode(),
			                		ivansMessage.getRemarkText(),
			                		ivansMessage.getLastModifiedDate()==null?"":ivansMessage.getLastModifiedDate().toString(),
			                	
			                		ivansMessageAttachment.getIvansMessgaeAttachmentKey()==null?"":ivansMessageAttachment.getIvansMessgaeAttachmentKey().toString(),
			                		ivansMessageAttachment.getAttachmentTypeCode(),
			            	        ivansMessageAttachment.getAttachmentDescription(),
			            	        ivansMessageAttachment.getAttachmentFileName(),
			            	        ivansMessageAttachment.getMimeTypeCode(),
			            	        ivansMessageAttachment.getLastModifiedDate()==null?"":ivansMessageAttachment.getLastModifiedDate().toString(),
			            	        ivansMessageAttachment.getPackageKey().toString(),
			            	        ivansMessageAttachment.getIvansMessageKey()==null?"":ivansMessageAttachment.getIvansMessageKey().toString()
		            		});
			            }
	            	}else {
		            	data.add(new String[] {
		                		ivansMessage.getIvansMessageKey().toString(),ivansMessage.getAgencyStateCode(),ivansMessage.getAgencyCode(),
		                		ivansMessage.getNaicsCode(),	ivansMessage.getBondNumber(),ivansMessage.getTermEffectiveDate().toString(),
		                		ivansMessage.getTermExpiryDate()==null?"":ivansMessage.getTermExpiryDate().toString(),
		                		ivansMessage.getTransactionDate()==null?"":ivansMessage.getTransactionDate().toString(),ivansMessage.getLineOfBusiness(),
		                		ivansMessage.getPrincipalName(),
		                		ivansMessage.getEventDate()==null?"":ivansMessage.getEventDate().toString(),ivansMessage.getActivityNoteTypeCode(),
		                		ivansMessage.getBusinessPurposeTypeCode(),ivansMessage.getRemarkText(),
		                		ivansMessage.getLastModifiedDate()==null?"":ivansMessage.getLastModifiedDate().toString(),
		                				
		                		"","","","","","","",""});
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

}
