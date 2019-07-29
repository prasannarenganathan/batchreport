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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.util.ReportingUtil;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.dto.KeyValueDTO;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.KeyValue;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.DocumentRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.EventAuditRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.KeyValueRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.NotificationRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.PackageRepository;

public class KeyValueItemReader extends BaseItemReader implements ItemReader<KeyValue>{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ItemReader<KeyValue> delegate;
	@SuppressWarnings("unchecked")
	@Override
	public KeyValue read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (delegate == null) {
            delegate = new IteratorItemReader<>(readTable(getLastExecutedJobTimeStamp()));
        }
        return  delegate.read();
	}
	
	@Autowired
	KeyValueRepository keyValueRepository;
 	
 	public List<KeyValue> readTable(Timestamp lastExecutedTimeStamp){
 		 List<KeyValue> keyValues = null;
 		 List<String[]> data = new ArrayList<String[]>();
 		 if (Objects.isNull(lastExecutedTimeStamp)) {
 			keyValues = keyValueRepository.findAll();
            } else {
            	keyValues = keyValueRepository.findAllByTimeStamp(lastExecutedTimeStamp);
            }
 		keyValues.forEach(keyValue -> {
            KeyValueDTO keyValueDTO = new KeyValueDTO();
            BeanUtils.copyProperties(keyValue, keyValueDTO);
            
            if (keyValue.getKeyValuePairTypeCode().equals("NOTIFICATION_TBL")) {
            	keyValue.setForiegnKeyId(
                        notificationRepository.findNotificationIdByKeyValuePairId(keyValue.getKeyValuePairId()));
            } else if (keyValue.getKeyValuePairTypeCode().equals("PACKAGE_TBL")) {
            	keyValue.setForiegnKeyId(
                        packageRepository.findPackageIdByKeyValuePairId(keyValue.getKeyValuePairId()));
            } else { // (keyValue.getKeyValuePairTypeCode().equals("DOCUMENT_TBL")) {
            	keyValue.setForiegnKeyId(
                        documentRepository.findDocumentIdByKeyValuePairId(keyValue.getKeyValuePairId()));
            }

        });
 		
 		return keyValues;
 	}
	
	
 	
	  @Autowired
	    NotificationRepository notificationRepository;
	  
	  @Autowired
	    PackageRepository packageRepository;
	  
	  @Autowired
	    DocumentRepository documentRepository;
	  
	 
 
}
