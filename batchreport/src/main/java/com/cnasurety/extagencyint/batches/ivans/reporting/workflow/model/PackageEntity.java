package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"PACKAGE_TBL\"")
public class PackageEntity {

    @Id
    @Column(name = "\"PACKAGE_KEY\"")
    UUID packageKey;

    @Column(name = "\"PACKAGE_TYPE_CODE\"")
    String packageTypeCode;

    @Column(name = "\"PACKAGE_ID\"")
    String packageId;

    @Column(name = "\"PACKAGE_DESCRIPTION\"")
    String packageDescription;

    @Column(name = "\"PACKAGE_OUTPUT_FILE_NAME\"")
    String packageOutputFileName;

    @Column(name = "\"MIME_TYPE_CODE\"")
    String mimeTypeCode;

    @Column(name = "\"KEY_VALUE_PAIR_ID\"")
    String keyValuePairId;

    @Column(name = "\"PACKAGE_WORKFLOW_STATUS_TYPE\"")
    String packageWorkflowStatusType;

    @Column(name = "\"LAST_MODIFIED_DATE\"")
    Timestamp lastModifiedDate;

    @Column(name = "\"NOTIFICATION_KEY\"")
    UUID notificationKey;
    
    @Column(name = "\"PACKAGE_FAILURE_COUNT\"")
    int packageFailureCount;



    public UUID getPackageKey() {
		return packageKey;
	}

	public void setPackageKey(UUID packageKey) {
		this.packageKey = packageKey;
	}

	public String getPackageTypeCode() {
        return packageTypeCode;
    }

    public void setPackageTypeCode(String packageTypeCode) {
        this.packageTypeCode = packageTypeCode;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getPackageOutputFileName() {
        return packageOutputFileName;
    }

    public void setPackageOutputFileName(String packageOutputFileName) {
        this.packageOutputFileName = packageOutputFileName;
    }

    public String getMimeTypeCode() {
        return mimeTypeCode;
    }

    public void setMimeTypeCode(String mimeTypeCode) {
        this.mimeTypeCode = mimeTypeCode;
    }

    public String getKeyValuePairId() {
        return keyValuePairId;
    }

    public void setKeyValuePairId(String keyValuePairId) {
        this.keyValuePairId = keyValuePairId;
    }

    public String getPackageWorkflowStatusType() {
        return packageWorkflowStatusType;
    }

    public void setPackageWorkflowStatusType(String packageWorkflowStatusType) {
        this.packageWorkflowStatusType = packageWorkflowStatusType;
    }

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public UUID getNotificationKey() {
		return notificationKey;
	}

	public void setNotificationKey(UUID notificationKey) {
		this.notificationKey = notificationKey;
	}

	public int getPackageFailureCount() {
		return packageFailureCount;
	}

	public void setPackageFailureCount(int packageFailureCount) {
		this.packageFailureCount = packageFailureCount;
	}



	
    

}
