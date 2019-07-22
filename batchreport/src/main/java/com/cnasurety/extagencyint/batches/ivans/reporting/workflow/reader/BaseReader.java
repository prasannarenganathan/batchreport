package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.reader;

import java.sql.Timestamp;
import java.util.List;

public interface BaseReader {

	public List<String[]> readTable(Timestamp lastExecutedTimeStamp);
}
