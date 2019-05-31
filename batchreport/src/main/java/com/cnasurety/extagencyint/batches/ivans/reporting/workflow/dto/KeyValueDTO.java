package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.dto;

import java.sql.Timestamp;
import java.util.UUID;

public class KeyValueDTO {

    private String keyValueKey;
    private String key;
    private String value;
    private Timestamp lastModifiedDate;
    private UUID keyValuePairId;
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

    public UUID getKeyValuePairId() {
        return keyValuePairId;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setKeyValuePairId(UUID keyValuePairId) {
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
