package com.cnasurety.extagencyint.batches.ivans.workflow.dto;

import java.sql.Date;

public class KeyValueDTO {

    private String keyValueKey;
    private String key;
    private String value;
    private Date lastModifiedDate;
    private String keyValuePairId;
    private String keyValuePairTypeCode;
    private String foriegnKeyId;

    public String getKeyValueKey() {
        return keyValueKey;
    }

    public void setKeyValueKey(String keyValueKey) {
        this.keyValueKey = keyValueKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getKeyValuePairId() {
        return keyValuePairId;
    }

    public void setKeyValuePairId(String keyValuePairId) {
        this.keyValuePairId = keyValuePairId;
    }

    public String getForiegnKeyId() {
        return foriegnKeyId;
    }

    public void setForiegnKeyId(String foriegnKeyId) {
        this.foriegnKeyId = foriegnKeyId;
    }

    public String getKeyValuePairTypeCode() {
        return keyValuePairTypeCode;
    }

    public void setKeyValuePairTypeCode(String keyValuePairTypeCode) {
        this.keyValuePairTypeCode = keyValuePairTypeCode;
    }

}
