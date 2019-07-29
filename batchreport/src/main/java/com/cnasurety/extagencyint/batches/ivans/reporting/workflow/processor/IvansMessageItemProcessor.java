package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.util.ReportingUtil;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessage;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessageAttachment;
@Service
public class IvansMessageItemProcessor implements ItemProcessor<IvansMessage, IvansMessage> {

	@Override
	public IvansMessage process(IvansMessage ivansMessage) throws Exception {
		 
		
         	
         	String[] ivansMessageData = null; 
         	if(!ivansMessage.getIvansMessageAttaments().isEmpty()) {
	            	for(IvansMessageAttachment ivansMessageAttachment:ivansMessage.getIvansMessageAttaments()) {
	            		ivansMessageData =  new String[] {
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
	            		};
		            }
         	}else {
         		ivansMessageData =  new String[] {
	                		ivansMessage.getIvansMessageKey().toString(),ivansMessage.getAgencyStateCode(),ivansMessage.getAgencyCode(),
	                		ivansMessage.getNaicsCode(),	ivansMessage.getBondNumber(),ReportingUtil.convertToString(ivansMessage.getTermEffectiveDate()),
	                		ReportingUtil.convertToString(ivansMessage.getTermExpiryDate()),
	                		ReportingUtil.convertToString(ivansMessage.getTransactionDate()),
	                		ivansMessage.getPrincipalName(),
	                		ReportingUtil.convertToString(ivansMessage.getEventDate()),
	                		ivansMessage.getBusinessPurposeTypeCode(),ivansMessage.getRemarkText(),
	                		ReportingUtil.convertToString(ivansMessage.getLastModifiedDate()),
	                		String.valueOf(ivansMessage.getDeliveryFailureCount()),
	                				
	                		"","","","","","",""};
	            }
         
 		 
 		
      
        
         	ivansMessage.setEntitydataString(ivansMessageData);
       
		return ivansMessage;
	}
}
