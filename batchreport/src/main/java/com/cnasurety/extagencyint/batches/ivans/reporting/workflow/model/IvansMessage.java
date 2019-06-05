package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the "IVANS_MESSAGE_TBL" database table.
 * 
 */
@Entity
@Table(name="\"IVANS_MESSAGE_TBL\"")
public class IvansMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"IVANS_MESSAGE_KEY\"", unique=true, nullable=false)
	private UUID ivansMessageKey;

	@Column(name="\"ACTIVITY_NOTE_TYPE_CODE\"", nullable=false, length=60)
	private String activityNoteTypeCode;

	@Column(name="\"AGENCY_CODE\"", nullable=false, length=5)
	private String agencyCode;

	@Column(name="\"AGENCY_STATE_CODE\"", nullable=false, length=2)
	private String agencyStateCode;

	@Column(name="\"BOND_NUMBER\"", length=11)
	private String bondNumber;

	@Column(name="\"BUSINESS_PURPOSE_TYPE_CODE\"", nullable=false, length=60)
	private String businessPurposeTypeCode;

	@Temporal(TemporalType.DATE)
	@Column(name="\"EVENT_DATE\"", nullable=false)
	private Date eventDate;

	@Column(name="\"LAST_MODIFIED_DATE\"")
	private Timestamp lastModifiedDate;

	@Column(name="\"LINE_OF_BUSINESS\"", length=40)
	private String lineOfBusiness;

	@Column(name="\"NAICS_CODE\"", nullable=false, length=4)
	private String naicsCode;

	@Column(name="\"PRINCIPAL_NAME\"", length=70)
	private String principalName;

	@Column(name="\"REMARK_TEXT\"", length=250)
	private String remarkText;

	@Temporal(TemporalType.DATE)
	@Column(name="\"TERM_EFFECTIVE_DATE\"")
	private Date termEffectiveDate;

	@Temporal(TemporalType.DATE)
	@Column(name="\"TERM_EXPIRY_DATE\"")
	private Date termExpiryDate;

	@Temporal(TemporalType.DATE)
	@Column(name="\"TRANSACTION_DATE\"", nullable=false)
	private Date transactionDate;

	

	public IvansMessage() {
	}

	public UUID getIvansMessageKey() {
		return ivansMessageKey;
	}

	public void setIvansMessageKey(UUID ivansMessageKey) {
		this.ivansMessageKey = ivansMessageKey;
	}



	public String getActivityNoteTypeCode() {
		return this.activityNoteTypeCode;
	}

	public void setActivityNoteTypeCode(String activityNoteTypeCode) {
		this.activityNoteTypeCode = activityNoteTypeCode;
	}

	public String getAgencyCode() {
		return this.agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getAgencyStateCode() {
		return this.agencyStateCode;
	}

	public void setAgencyStateCode(String agencyStateCode) {
		this.agencyStateCode = agencyStateCode;
	}

	public String getBondNumber() {
		return this.bondNumber;
	}

	public void setBondNumber(String bondNumber) {
		this.bondNumber = bondNumber;
	}

	public String getBusinessPurposeTypeCode() {
		return this.businessPurposeTypeCode;
	}

	public void setBusinessPurposeTypeCode(String businessPurposeTypeCode) {
		this.businessPurposeTypeCode = businessPurposeTypeCode;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Timestamp getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLineOfBusiness() {
		return this.lineOfBusiness;
	}

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}

	public String getNaicsCode() {
		return this.naicsCode;
	}

	public void setNaicsCode(String naicsCode) {
		this.naicsCode = naicsCode;
	}

	public String getPrincipalName() {
		return this.principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getRemarkText() {
		return this.remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	public Date getTermEffectiveDate() {
		return this.termEffectiveDate;
	}

	public void setTermEffectiveDate(Date termEffectiveDate) {
		this.termEffectiveDate = termEffectiveDate;
	}

	public Date getTermExpiryDate() {
		return this.termExpiryDate;
	}

	public void setTermExpiryDate(Date termExpiryDate) {
		this.termExpiryDate = termExpiryDate;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}