package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"DOCUMENT_TBL\"")
public class DocumentEntity {

    @Id
    @Column(name = "\"DOCUMENT_KEY\"")
    String documentKey;
    @Column(name = "\"DOCUMENT_TYPE_CODE\"")
    String documentTypeCode;
    @Column(name = "\"DOCUMENT_ID\"")
    String documentId;
    @Column(name = "\"MIME_TYPE_CODE\"")
    String mimeTypeCode;
    @Column(name = "\"DOCUMENT_REPOSITORY_CODE\"")
    String repositoryCode;
    @Column(name = "\"KEY_VALUE_PAIR_ID\"")
    String keyValuePairId;
    @Column(name = "\"LAST_MODIFIED_DATE\"")
    Timestamp lastModifiedDate;
    @Column(name = "\"PACKAGE_KEY\"")
    UUID packageKey;

    public String getDocumentKey() {
        return documentKey;
    }

    public void setDocumentKey(String documentKey) {
        this.documentKey = documentKey;
    }

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getMimeTypeCode() {
        return mimeTypeCode;
    }

    public void setMimeTypeCode(String mimeTypeCode) {
        this.mimeTypeCode = mimeTypeCode;
    }

    public String getRepositoryCode() {
        return repositoryCode;
    }

    public void setRepositoryCode(String repositoryCode) {
        this.repositoryCode = repositoryCode;
    }

    public String getKeyValuePairId() {
        return keyValuePairId;
    }

    public void setKeyValuePairId(String keyValuePairId) {
        this.keyValuePairId = keyValuePairId;
    }

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public UUID getPackageKey() {
		return packageKey;
	}

	public void setPackageKey(UUID packageKey) {
		this.packageKey = packageKey;
	}


}
