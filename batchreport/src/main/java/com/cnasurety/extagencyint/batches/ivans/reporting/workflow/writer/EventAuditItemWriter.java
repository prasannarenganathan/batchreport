package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;
@Service
public class EventAuditItemWriter implements ItemWriter<EventAudit> {

	@Autowired
	WriteProcessor writeProcessor;
	
	 private static final String KEY_VALUE_TABLE = "KEYVALUETABLE_";
	
	@Override
	public void write(List<? extends EventAudit> eventAudits) throws Exception {
		 List<String[]> data = new ArrayList<String[]>();
 		 
 		for (EventAudit e : eventAudits) {
            data.add(e.getEntityDataString());
        }
 		writeProcessor.writeFile(data, KEY_VALUE_TABLE);
 		
		
	}

	

	

	

}
