package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cnasurety.extagencyint.batches.ivans.reporting.util.ReportingUtil;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.dto.KeyValueDTO;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.KeyValue;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.DocumentRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.KeyValueRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.NotificationRepository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository.PackageRepository;

public class KeyValueReader implements BaseReader{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    KeyValueRepository keyValueRepository;
 	
	  @Autowired
	    NotificationRepository notificationRepository;
	  
	  @Autowired
	    PackageRepository packageRepository;
	  
	  @Autowired
	    DocumentRepository documentRepository;
	  
	 @Override
 	public List<String[]> readTable(Timestamp lastExecutedTimeStamp){
 		
 		 
 		List<KeyValue> keyValues = null;
 		 List<String[]> data = new ArrayList<String[]>();
 		 
 		  if (Objects.isNull(lastExecutedTimeStamp)) {
              keyValues = keyValueRepository.findAll();
          } else {
              keyValues = keyValueRepository.findAllByTimeStamp(lastExecutedTimeStamp);
          }
          logger.info("Number of Records Exported: {}", keyValues.size());

          keyValues.forEach(keyValue -> {
              KeyValueDTO keyValueDTO = new KeyValueDTO();
              BeanUtils.copyProperties(keyValue, keyValueDTO);
              
              if (keyValue.getKeyValuePairTypeCode().equals("NOTIFICATION_TBL")) {
                  keyValueDTO.setForiegnKeyId(
                          notificationRepository.findNotificationIdByKeyValuePairId(keyValue.getKeyValuePairId()));
              } else if (keyValue.getKeyValuePairTypeCode().equals("PACKAGE_TBL")) {
                  keyValueDTO.setForiegnKeyId(
                          packageRepository.findPackageIdByKeyValuePairId(keyValue.getKeyValuePairId()));
              } else { // (keyValue.getKeyValuePairTypeCode().equals("DOCUMENT_TBL")) {
                  keyValueDTO.setForiegnKeyId(
                          documentRepository.findDocumentIdByKeyValuePairId(keyValue.getKeyValuePairId()));
              }

              data.add(new String[] { keyValueDTO.getKeyValueKey(), keyValueDTO.getKey(), keyValueDTO.getValue(),
              		ReportingUtil.convertToString(keyValueDTO.getLastModifiedDate()), ReportingUtil.convertToString(keyValueDTO.getKeyValuePairId()),
                      keyValueDTO.getKeyValuePairTypeCode(), keyValueDTO.getForiegnKeyId() });

          });
          logger.info("Number of Records Exported: {}", data.size());
 		return data;
 	}
}
