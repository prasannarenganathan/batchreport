package com.workflow.report.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"EVENT_AUDIT_TBL\"") // "event_audit_tbl")
public class EventAudit {

    @Id
    @Column(name = "\"EVENT_AUDIT_KEY\"")
    private String eventAuditKey;

    @Column(name = "\"NOTIFICATION_EVENT_FROM_STATUS\"")
    private String notificationEventFromStatus;

    @Column(name = "\"NOTIFICATION_EVENT_TO_STATUS\"")
    private String notificationEventToStatus;

    @Column(name = "\"PACKAGE_EVENT_FROM_STATUS\"")
    private String packageEventFromStatus;

    @Column(name = "\"PACKAGE_EVENT_TO_STATUS\"")
    private String packageEventToStatus;

    @Column(name = "\"LAST_MODIFIED_DATE\"")
    Date lastModifiedDate;

    @Column(name = "\"NOTIFICATION_KEY\"")
    private String notificationKey;

    @Column(name = "\"PACKAGE_KEY\"")
    private String packageKey;

    public String getEventAuditKey() {
        return eventAuditKey;
    }

    public void setEventAuditKey(String eventAuditKey) {
        this.eventAuditKey = eventAuditKey;
    }

    public String getNotificationEventFromStatus() {
        return notificationEventFromStatus;
    }

    public void setNotificationEventFromStatus(String notificationEventFromStatus) {
        this.notificationEventFromStatus = notificationEventFromStatus;
    }

    public String getNotificationEventToStatus() {
        return notificationEventToStatus;
    }

    public void setNotificationEventToStatus(String notificationEventToStatus) {
        this.notificationEventToStatus = notificationEventToStatus;
    }

    public String getPackageEventFromStatus() {
        return packageEventFromStatus;
    }

    public void setPackageEventFromStatus(String packageEventFromStatus) {
        this.packageEventFromStatus = packageEventFromStatus;
    }

    public String getPackageEventToStatus() {
        return packageEventToStatus;
    }

    public void setPackageEventToStatus(String packageEventToStatus) {
        this.packageEventToStatus = packageEventToStatus;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
    }

    public String getPackageKey() {
        return packageKey;
    }

    public void setPackageKey(String packageKey) {
        this.packageKey = packageKey;
    }

}
