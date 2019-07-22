package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader.EventAuditReader;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader.IvansMessageReader;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader.KeyValueReader;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader.NotificationReader;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.writer.WriteProcessor;

@Component
public class WorkFlowExportServiceImpl implements WorkFlowExportService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    WriteProcessor writeProcessor;
    
    @Autowired
    EventAuditReader eventAuditReader;
    
    @Autowired
    KeyValueReader keyValueReader;
    
    @Autowired
    NotificationReader notificationReader;
    
    @Autowired
    IvansMessageReader ivansMessageReader;
    
    
    
    private static final String EVENT_AUDIT_TABLE = "EVENTAUDITTABLE_";
    private static final String KEY_VALUE_TABLE = "KEYVALUETABLE_";
    private static final String IVANS_MESSAGE_TABLE = "IVANSMESSAGETABLE_";
    private static final String NOTIFICATION_TABLE = "NOTIFICATIONTABLE_";

    @Override
    public String exportEventAuditTable(Timestamp lastExecutedTimeStamp) {
		String result= null;
		 try { 
	            logger.info("Exporting Event Auit Table");
	            List<String[]> data = eventAuditReader.readTable(lastExecutedTimeStamp);
	            result = writeProcessor.writeFile( data,  EVENT_AUDIT_TABLE);
	            	            
	        } catch (Exception e) {
	        	logger.error("Error: ",e);
	        }
	        return result;
	
	}

    
    @Override
    public String exportKeyValueTable(Timestamp lastExecutedTimeStamp) {

		String result= null;
		 try { 
	            logger.info("Exporting Key Value Table");
	            List<String[]> data = keyValueReader.readTable(lastExecutedTimeStamp);
	            result = writeProcessor.writeFile( data,  KEY_VALUE_TABLE);
	            	            
	        } catch (Exception e) {
	        	logger.error("Error: ",e);
	        }
	        return result;
	
	
        }

	@Override
	public String exportIvansMessageTables(Timestamp lastExecutedTimeStamp) {

		String result= null;
		 try { 
	            logger.info("Exporting Ivans Message and Attachment Table");
	            List<String[]> data = ivansMessageReader.readTable(lastExecutedTimeStamp);
	            result = writeProcessor.writeFile( data,  IVANS_MESSAGE_TABLE);
	            	            
	        } catch (Exception e) {
	        	logger.error("Error: ",e);
	        }
	        return result;
	
	
        }

	@Override
	public String exportNotificationTables(Timestamp lastExecutedTimeStamp) {
		String result= null;
		 try { 
			 
	            logger.info("Exporting Notification, Notification Agency Extension, Package and Document Table");
	            List<String[]> data = notificationReader.readTable(lastExecutedTimeStamp);
	            result = writeProcessor.writeFile( data,  NOTIFICATION_TABLE);
	            	            
	        } catch (Exception e) {
	        	logger.error("Error: ",e);
	        }
	        return result;
	
	}
	
}
