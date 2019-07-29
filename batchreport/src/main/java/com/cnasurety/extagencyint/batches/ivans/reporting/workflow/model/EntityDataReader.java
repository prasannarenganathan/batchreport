package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model;

import java.util.List;

public class EntityDataReader {

	String[] entityDataString;

	public String[] getEntityDataString() {
		return entityDataString;
	}

	public void setEntitydataString(String[] entityDataString) {
		this.entityDataString = entityDataString;
	}
	
	private List<IvansMessageAttachment> ivansMessageAttaments;
	
	public List<IvansMessageAttachment> getIvansMessageAttaments() {
		return ivansMessageAttaments;
	}

	public void setIvansMessageAttaments(List<IvansMessageAttachment> ivansMessageAttaments) {
		this.ivansMessageAttaments = ivansMessageAttaments;
	}
	
	
	NotificationAgencyExtension notificationAgencyExtension;

	public NotificationAgencyExtension getNotificationAgencyExtension() {
		return notificationAgencyExtension;
	}

	public void setNotificationAgencyExtension(NotificationAgencyExtension notificationAgencyExtension) {
		this.notificationAgencyExtension = notificationAgencyExtension;
	}	
	
}
