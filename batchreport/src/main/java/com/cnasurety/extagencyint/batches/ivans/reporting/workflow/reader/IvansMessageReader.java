package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cnasurety.extagencyint.batches.ivans.reporting.util.ReportingUtil;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessage;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessageAttachment;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.IvansMessageAttachmentRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.IvansMessageRepository;

public class IvansMessageReader implements BaseReader{



	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	    
	    @Autowired
	    IvansMessageRepository ivansMessageRepository;
	    
	    @Autowired
	    IvansMessageAttachmentRepository ivansMessageAttachmentRepository;
	    
 	
 	@Override
 	public List<String[]> readTable(Timestamp lastExecutedTimeStamp){
 		 
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
 		return data;
 	}

}
