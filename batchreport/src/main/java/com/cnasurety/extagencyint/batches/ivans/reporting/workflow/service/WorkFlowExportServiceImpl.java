package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader.IvansMessageItemReader;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader.KeyValueItemReader;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader.NotificationItemReader;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.writer.WriteProcessor;

@Component
public class WorkFlowExportServiceImpl implements WorkFlowExportService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    WriteProcessor writeProcessor;
    
    
    
    @Autowired
    KeyValueItemReader keyValueReader;
    
    @Autowired
    NotificationItemReader notificationReader;
    
    @Autowired
    IvansMessageItemReader ivansMessageReader;
    
    
    
    private static final String EVENT_AUDIT_TABLE = "EVENTAUDITTABLE_";
   
  
    



	

	
}
