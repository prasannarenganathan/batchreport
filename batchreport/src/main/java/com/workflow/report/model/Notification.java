package com.workflow.report.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"NOTIFICATION_TBL\"")
public class Notification {

    @Column(name = "\"NOTIFICATION_TYPE_CODE\"")
    private String notificationTypeCode;

    @Column(name = "\"NOTIFICATION_GLOBAL_ID\"")
    private String notificationGlobalId;

    @Column(name = "\"AGENCY_STATE_CODE\"")
    private String agencyStateCode;

    @Column(name = "\"AGENCY_CODE\"")
    private String agencyCode;

    @Column(name = "\"PROCESSING_OFFICE_TYPE_CODE\"")
    private String processingOfficeTypeCode;

    @Column(name = "\"PROCESSING_OFFICE_CODE\"")
    private String processingOfficeCode;

    @Column(name = "\"ACCOUNT_NUMBER\"")
    private String accountNumber;

    @Column(name = "\"WRITING_COMPANY_CODE\"")
    private String writingCompanyCode;

    @Column(name = "\"SUBMISSION_NUMBER\"")
    private String submissionNumber;

    @Column(name = "\"BOND_NUMBER\"")
    private String bondNumber;

    @Column(name = "\"TERM_EFFECTIVE_DATE\"")
    Date termEffectiveDate;

    @Column(name = "\"TERM_EXPIRY_DATE\"")
    Date termExpiryDate;

    @Column(name = "\"TERM_NUMBER\"")
    private String termNumber;

    @Column(name = "\"TRANSACTION_DATE\"")
    private String transactionDate;

    @Column(name = "\"LINE_OF_BUSINESS\"")
    private String lineOfBusiness;

    @Column(name = "\"PRINCIPAL_NAME\"")
    private String principalName;

    @Column(name = "\"EVENT_DATE\"")
    Date eventDate;

    @Column(name = "\"EVENT_TYPE_CODE\"")
    private String eventTypeCode;

    @Column(name = "\"EVENT_SUB_TYPE_CODE\"")
    private String eventSubTypeCode;

    @Column(name = "\"SPECIAL_HANDLING_INDICATOR\"")
    private String specialHandlingIndicator;

    @Column(name = "\"REMARK_TEXT\"")
    private String remarkText;

    @Column(name = "\"NOTIFICATION_WORKFLOW_STATUS_TYPE_CODE\"")
    private String notificationWorkflowStatusTypeCode;

    @Column(name = "\"LAST_MODIFIED_DATE\"")
    Date lastModifiedDate;

    @Id
    @Column(name = "\"NOTIFICATION_KEY\"")
    private String notificationKey;

    @Column(name = "\"IVANS_MESSAGE_KEY\"")
    private String ivansMessageKey;

    @Column(name = "\"KEY_VALUE_PAIR_ID\"")
    private String keyValuePairId;

    public String getNotificationTypeCode() {
        return notificationTypeCode;
    }

    public void setNotificationTypeCode(String notificationTypeCode) {
        this.notificationTypeCode = notificationTypeCode;
    }

    public String getNotificationGlobalId() {
        return notificationGlobalId;
    }

    public void setNotificationGlobalId(String notificationGlobalId) {
        this.notificationGlobalId = notificationGlobalId;
    }

    public String getAgencyStateCode() {
        return agencyStateCode;
    }

    public void setAgencyStateCode(String agencyStateCode) {
        this.agencyStateCode = agencyStateCode;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getProcessingOfficeTypeCode() {
        return processingOfficeTypeCode;
    }

    public void setProcessingOfficeTypeCode(String processingOfficeTypeCode) {
        this.processingOfficeTypeCode = processingOfficeTypeCode;
    }

    public String getProcessingOfficeCode() {
        return processingOfficeCode;
    }

    public void setProcessingOfficeCode(String processingOfficeCode) {
        this.processingOfficeCode = processingOfficeCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getWritingCompanyCode() {
        return writingCompanyCode;
    }

    public void setWritingCompanyCode(String writingCompanyCode) {
        this.writingCompanyCode = writingCompanyCode;
    }

    public String getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(String submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public String getBondNumber() {
        return bondNumber;
    }

    public void setBondNumber(String bondNumber) {
        this.bondNumber = bondNumber;
    }

    public Date getTermEffectiveDate() {
        return termEffectiveDate;
    }

    public void setTermEffectiveDate(Date termEffectiveDate) {
        this.termEffectiveDate = termEffectiveDate;
    }

    public Date getTermExpiryDate() {
        return termExpiryDate;
    }

    public void setTermExpiryDate(Date termExpiryDate) {
        this.termExpiryDate = termExpiryDate;
    }

    public String getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(String termNumber) {
        this.termNumber = termNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTypeCode() {
        return eventTypeCode;
    }

    public void setEventTypeCode(String eventTypeCode) {
        this.eventTypeCode = eventTypeCode;
    }

    public String getEventSubTypeCode() {
        return eventSubTypeCode;
    }

    public void setEventSubTypeCode(String eventSubTypeCode) {
        this.eventSubTypeCode = eventSubTypeCode;
    }

    public String getSpecialHandlingIndicator() {
        return specialHandlingIndicator;
    }

    public void setSpecialHandlingIndicator(String specialHandlingIndicator) {
        this.specialHandlingIndicator = specialHandlingIndicator;
    }

    public String getRemarkText() {
        return remarkText;
    }

    public void setRemarkText(String remarkText) {
        this.remarkText = remarkText;
    }

    public String getNotificationWorkflowStatusTypeCode() {
        return notificationWorkflowStatusTypeCode;
    }

    public void setNotificationWorkflowStatusTypeCode(String notificationWorkflowStatusTypeCode) {
        this.notificationWorkflowStatusTypeCode = notificationWorkflowStatusTypeCode;
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

    public String getIvansMessageKey() {
        return ivansMessageKey;
    }

    public void setIvansMessageKey(String ivansMessageKey) {
        this.ivansMessageKey = ivansMessageKey;
    }

    public String getKeyValuePairId() {
        return keyValuePairId;
    }

    public void setKeyValuePairId(String keyValuePairId) {
        this.keyValuePairId = keyValuePairId;
    }

}
