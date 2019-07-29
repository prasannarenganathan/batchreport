package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.KeyValue;
@Service
public class KeyValueItemWriter implements ItemWriter<KeyValue> {

	@Autowired
	WriteProcessor writeProcessor;
	
	private static final String EVENT_AUDIT_TABLE = "EVENTAUDITTABLE_";
	
	@Override
	public void write(List<? extends KeyValue> keyValues) throws Exception {
		 List<String[]> data = new ArrayList<String[]>();
 		 
 		for (KeyValue e : keyValues) {
            data.add(e.getEntityDataString());
        }
 		writeProcessor.writeFile(data, EVENT_AUDIT_TABLE);
 		
		
	}

}
