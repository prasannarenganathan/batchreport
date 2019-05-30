package com.workflow.report.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"DOCUMENT_TBL\"")
public class Document {

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
    Date lastModifiedDate;
    @Column(name = "\"PACKAGE_KEY\"")
    String packageKey;

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

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getPackageKey() {
        return packageKey;
    }

    public void setPackageKey(String packageKey) {
        this.packageKey = packageKey;
    }

}
