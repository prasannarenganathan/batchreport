package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.Notification;

@Service
public class NotificationItemProcessor implements ItemProcessor<Notification, Notification> {
	
	
	@Override
	public Notification process(Notification e) throws Exception {
		return e;
	}
}
