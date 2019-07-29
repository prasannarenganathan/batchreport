package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.util.ReportingUtil;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessage;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessageAttachment;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.EventAuditRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.IvansMessageAttachmentRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.IvansMessageRepository;

public class IvansMessageItemReader extends BaseItemReader implements ItemReader<IvansMessage>{



	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	    
	    @Autowired
	    IvansMessageRepository ivansMessageRepository;
	    
	    @Autowired
	    IvansMessageAttachmentRepository ivansMessageAttachmentRepository;
	    
	    private ItemReader<IvansMessage> delegate;
		@SuppressWarnings("unchecked")
		@Override
		public IvansMessage read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
			if (delegate == null) {
	            delegate = new IteratorItemReader<>(readTable(getLastExecutedJobTimeStamp()));
	        }
	        return  delegate.read();
		}
		
		@Autowired
	    EventAuditRepository eventAuditRepository;
	 	
	 	public List<IvansMessage> readTable(Timestamp lastExecutedTimeStamp){
	 		 
	 		 List<IvansMessage> ivansMessages = null;
	         List<IvansMessageAttachment> ivansMessageAttachments = null;
	         if (Objects.isNull(lastExecutedTimeStamp)) {
	         	ivansMessages = ivansMessageRepository.findAll();
	         
	         } else {
	         	ivansMessages = ivansMessageRepository.findAllByTimeStamp(lastExecutedTimeStamp);
	         }
	         for (IvansMessage ivansMessage : ivansMessages) {
	          	ivansMessageAttachments = ivansMessageAttachmentRepository.findByIvansMessageKey(ivansMessage.getIvansMessageKey());
	          	ivansMessage.setIvansMessageAttaments(ivansMessageAttachments);
	         }
	         return ivansMessages;
	 	}
 	
 	

}
