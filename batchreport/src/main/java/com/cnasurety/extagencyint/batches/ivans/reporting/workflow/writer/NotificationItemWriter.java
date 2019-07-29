package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.Notification;
@Service
public class NotificationItemWriter implements ItemWriter<Notification> {

	@Autowired
	WriteProcessor writeProcessor;
	
	private static final String NOTIFICATION_TABLE = "NOTIFICATIONTABLE_";
	
	@Override
	public void write(List<? extends Notification> notifications) throws Exception {
		 List<String[]> data = new ArrayList<String[]>();
 		 
 		for (Notification e : notifications) {
            data.add(e.getEntityDataString());
        }
 		writeProcessor.writeFile(data, NOTIFICATION_TABLE);
	}
}
