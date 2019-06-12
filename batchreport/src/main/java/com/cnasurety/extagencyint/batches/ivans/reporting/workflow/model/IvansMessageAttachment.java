package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the "IVANS_MESSAGE_ATTACHMENT_TBL" database table.
 * 
 */
@Entity
@Table(name="\"IVANS_MESSAGE_ATTACHMENT_TBL\"", schema = "workflow")
public class IvansMessageAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"IVANS_MESSGAE_ATTACHMENT_KEY\"", unique=true, nullable=false)
	private String ivansMessgaeAttachmentKey;

	@Column(name="\"ATTACHMENT_DESCRIPTION\"", length=250)
	private String attachmentDescription;

	@Column(name="\"ATTACHMENT_FILE_NAME\"", length=75)
	private String attachmentFileName;

	@Column(name="\"ATTACHMENT_TYPE_CODE\"", nullable=false, length=60)
	private String attachmentTypeCode;

	@Column(name="\"IVANS_MESSAGE_KEY\"", nullable=false)
	private UUID ivansMessageKey;

	@Column(name="\"LAST_MODIFIED_DATE\"")
	private Timestamp lastModifiedDate;

	@Column(name="\"MIME_TYPE_CODE\"", nullable=false, length=60)
	private String mimeTypeCode;

	@Column(name="\"PACKAGE_KEY\"")
	private UUID packageKey;

	
	public IvansMessageAttachment() {
	}

	public String getIvansMessgaeAttachmentKey() {
		return this.ivansMessgaeAttachmentKey;
	}

	public void setIvansMessgaeAttachmentKey(String ivansMessgaeAttachmentKey) {
		this.ivansMessgaeAttachmentKey = ivansMessgaeAttachmentKey;
	}

	public String getAttachmentDescription() {
		return this.attachmentDescription;
	}

	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}

	public String getAttachmentFileName() {
		return this.attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String getAttachmentTypeCode() {
		return this.attachmentTypeCode;
	}

	public void setAttachmentTypeCode(String attachmentTypeCode) {
		this.attachmentTypeCode = attachmentTypeCode;
	}

	

	public UUID getIvansMessageKey() {
		return ivansMessageKey;
	}

	public void setIvansMessageKey(UUID ivansMessageKey) {
		this.ivansMessageKey = ivansMessageKey;
	}

	public Timestamp getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getMimeTypeCode() {
		return this.mimeTypeCode;
	}

	public void setMimeTypeCode(String mimeTypeCode) {
		this.mimeTypeCode = mimeTypeCode;
	}

	public UUID getPackageKey() {
		return packageKey;
	}

	public void setPackageKey(UUID packageKey) {
		this.packageKey = packageKey;
	}

}