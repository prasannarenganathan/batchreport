package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader;

import java.sql.Timestamp;

public class BaseItemReader {

	Timestamp lastExecutedJobTimeStamp;

	public Timestamp getLastExecutedJobTimeStamp() {
		return lastExecutedJobTimeStamp;
	}

	public void setLastExecutedJobTimeStamp(Timestamp lastExecutedJobTimeStamp) {
		this.lastExecutedJobTimeStamp = lastExecutedJobTimeStamp;
	}
	
	
}
