package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.util.ReportingUtil;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.EventAuditRepository;

public class EventAuditItemReader  extends BaseItemReader implements ItemReader<EventAudit>{

	private ItemReader<EventAudit> delegate;
	@SuppressWarnings("unchecked")
	@Override
	public EventAudit read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (delegate == null) {
            delegate = new IteratorItemReader<>(readTable(getLastExecutedJobTimeStamp()));
        }
        return  delegate.read();
	}
	
	@Autowired
    EventAuditRepository eventAuditRepository;
 	
 	public List<EventAudit> readTable(Timestamp lastExecutedTimeStamp){
 		 List<EventAudit> eventAudits = null;
 		 List<String[]> data = new ArrayList<String[]>();
 		 if (Objects.isNull(lastExecutedTimeStamp)) {
                eventAudits = eventAuditRepository.findAll();
            } else {
                eventAudits = eventAuditRepository.findAllByTimeStamp(lastExecutedTimeStamp);
            }
 		EventAudit e = new EventAudit();
 		e.setEventAuditKey("1");
 		eventAudits.add(e);
 		return eventAudits;
 	}
	

}
