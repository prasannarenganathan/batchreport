package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the "NOTIFICATION_AGENCY_EXTENSION_TBL" database table.
 * 
 */
@Entity
@Table(name="\"NOTIFICATION_AGENCY_EXTENSION_TBL\"", schema = "workflow")
public class NotificationAgencyExtension implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"NOTIFICATION_AGENCY_EXTENSION_KEY\"", unique=true, nullable=false)
	private String notificationAgencyExtensionKey;

	@Column(name="\"IVANS_ENROLLMENT_IND\"")
	private Boolean ivansEnrollmentInd;

	@Column(name="\"IVANS_LASTUPDATE_DTTM\"")
	private Timestamp ivansLastupdateDttm;

	@Column(name="\"IVANS_PREF_AGCYBILSTMTS\"")
	private Boolean ivansPrefAgcybilstmts;

	@Column(name="\"IVANS_PREF_BR_TX\"")
	private Boolean ivansPrefBrTx;

	@Column(name="\"IVANS_PREF_DIRBILRPRTS\"")
	private Boolean ivansPrefDirbilrprts;

	@Column(name="\"IVANS_PREF_IBL\"")
	private Boolean ivansPrefIbl;

	@Column(name="\"IVANS_PREF_SF_TX\"")
	private Boolean ivansPrefSfTx;

	@Column(name="\"IVANS_YACCTNUM\"", nullable=false)
	private String ivansYacctnum;
	
	@Column(name="\"NOTIFICATION_KEY\"", nullable=false)
	private UUID notificationKey;

	public NotificationAgencyExtension() {
	}

	public String getNotificationAgencyExtensionKey() {
		return this.notificationAgencyExtensionKey;
	}

	public void setNotificationAgencyExtensionKey(String notificationAgencyExtensionKey) {
		this.notificationAgencyExtensionKey = notificationAgencyExtensionKey;
	}

	public Boolean getIvansEnrollmentInd() {
		return this.ivansEnrollmentInd;
	}

	public void setIvansEnrollmentInd(Boolean ivansEnrollmentInd) {
		this.ivansEnrollmentInd = ivansEnrollmentInd;
	}

	public Timestamp getIvansLastupdateDttm() {
		return this.ivansLastupdateDttm;
	}

	public void setIvansLastupdateDttm(Timestamp ivansLastupdateDttm) {
		this.ivansLastupdateDttm = ivansLastupdateDttm;
	}

	public Boolean getIvansPrefAgcybilstmts() {
		return this.ivansPrefAgcybilstmts;
	}

	public void setIvansPrefAgcybilstmts(Boolean ivansPrefAgcybilstmts) {
		this.ivansPrefAgcybilstmts = ivansPrefAgcybilstmts;
	}

	public Boolean getIvansPrefBrTx() {
		return this.ivansPrefBrTx;
	}

	public void setIvansPrefBrTx(Boolean ivansPrefBrTx) {
		this.ivansPrefBrTx = ivansPrefBrTx;
	}

	public Boolean getIvansPrefDirbilrprts() {
		return this.ivansPrefDirbilrprts;
	}

	public void setIvansPrefDirbilrprts(Boolean ivansPrefDirbilrprts) {
		this.ivansPrefDirbilrprts = ivansPrefDirbilrprts;
	}

	public Boolean getIvansPrefIbl() {
		return this.ivansPrefIbl;
	}

	public void setIvansPrefIbl(Boolean ivansPrefIbl) {
		this.ivansPrefIbl = ivansPrefIbl;
	}

	public Boolean getIvansPrefSfTx() {
		return this.ivansPrefSfTx;
	}

	public void setIvansPrefSfTx(Boolean ivansPrefSfTx) {
		this.ivansPrefSfTx = ivansPrefSfTx;
	}

	public String getIvansYacctnum() {
		return ivansYacctnum;
	}

	public void setIvansYacctnum(String ivansYacctnum) {
		this.ivansYacctnum = ivansYacctnum;
	}

	public UUID getNotificationKey() {
		return notificationKey;
	}

	public void setNotificationKey(UUID notificationKey) {
		this.notificationKey = notificationKey;
	}
	
	

	

}