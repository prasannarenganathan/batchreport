package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.util.ReportingUtil;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.KeyValue;
@Service
public class KeyValueItemProcessor implements ItemProcessor<KeyValue, KeyValue> {
	
	
	@Override
	public KeyValue process(KeyValue e) throws Exception {
		
        String[] entityDataString = new String[] { e.getKeyValueKey(), e.getKey(), e.getValue(),
          		ReportingUtil.convertToString(e.getLastModifiedDate()), ReportingUtil.convertToString(e.getKeyValuePairId()),
                e.getKeyValuePairTypeCode(), e.getForiegnKeyId() };
        e.setEntitydataString(entityDataString);
		return e;
	}
    

}
