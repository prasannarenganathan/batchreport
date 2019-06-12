package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.service;

import java.sql.Timestamp;

public interface WorkFlowExportService {

    public String exportEventAuditTable(Timestamp lastExecutedTimeStamp);

    public String exportKeyValueTable(Timestamp lastExecutedTimeStamp);
    
    public String exportIvansMessageTables(Timestamp lastExecutedTimeStamp);
    
    public String exportNotificationTables(Timestamp lastExecutedTimeStamp);
    
    public void purgeTables();
}
