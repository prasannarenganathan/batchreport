package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.util.ReportingUtil;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.EventAuditRepository;

@Service
public class EventAuditReader implements BaseReader{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 	@Autowired
	    EventAuditRepository eventAuditRepository;
	 	
	 	@Override
	 	public List<String[]> readTable(Timestamp lastExecutedTimeStamp){
	 		 List<EventAudit> eventAudits = null;
	 		 List<String[]> data = new ArrayList<String[]>();
	 		 if (Objects.isNull(lastExecutedTimeStamp)) {
	                eventAudits = eventAuditRepository.findAll();
	            } else {
	                eventAudits = eventAuditRepository.findAllByTimeStamp(lastExecutedTimeStamp);
	            }
	 		 
	 		for (EventAudit e : eventAudits) {
                data.add(new String[] { e.getEventAuditKey(), e.getNotificationEventFromStatus(),
                        e.getNotificationEventToStatus(), e.getPackageEventFromStatus(), e.getPackageEventToStatus(),
                        ReportingUtil.convertToString(e.getLastModifiedDate()), e.getNotificationKey(), e.getPackageKey() });
            }
	 		logger.info("Number of Records Exported: {}", data.size());
	 		return data;
	 	}
	 	
	
}
