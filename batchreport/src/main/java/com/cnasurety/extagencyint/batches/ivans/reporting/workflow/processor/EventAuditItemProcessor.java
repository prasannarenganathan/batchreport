package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.util.ReportingUtil;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;
@Service
public class EventAuditItemProcessor implements ItemProcessor<EventAudit, EventAudit> {

	@Override
	public EventAudit process(EventAudit e) throws Exception {
		 
 		 
 		 
 		
        String[] entityDataString = new String[] { e.getEventAuditKey(), e.getNotificationEventFromStatus(),
                    e.getNotificationEventToStatus(), e.getPackageEventFromStatus(), e.getPackageEventToStatus(),
                    ReportingUtil.convertToString(e.getLastModifiedDate()), e.getNotificationKey(), e.getPackageKey() };
        
        e.setEntitydataString(entityDataString);
        e = null;
        e.setEntitydataString(entityDataString);
		return e;
	}

	

}
