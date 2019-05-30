package com.cnasurety.extagencyint.batches.ivans.reporting.model;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"KEY_VALUE_TBL\"")
public class KeyValue {
    @Id
    @Column(name = "\"KEY_VALUE_KEY\"")
    private String keyValueKey;

    @Column(name = "\"KEY\"")
    private String key;

    @Column(name = "\"VALUE\"")
    private String value;

    @Column(name = "\"LAST_MODIFIED_DATE\"")
    private Date lastModifiedDate;

    @Column(name = "\"KEY_VALUE_PAIR_ID\"")
    private UUID keyValuePairId;

    @Column(name = "\"KEY_VALUE_PAIR_TYPE_CODE\"")
    private String keyValuePairTypeCode;

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

    public UUID getKeyValuePairId() {
        return keyValuePairId;
    }

    public void setKeyValuePairId(UUID keyValuePairId) {
        this.keyValuePairId = keyValuePairId;
    }

    public String getKeyValuePairTypeCode() {
        return keyValuePairTypeCode;
    }

    public void setKeyValuePairTypeCode(String keyValuePairTypeCode) {
        this.keyValuePairTypeCode = keyValuePairTypeCode;
    }

}
