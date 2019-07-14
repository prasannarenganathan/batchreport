package com.cnasurety.extagencyint.batches.ivans.reporting.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the batch_job_execution database table.
 * 
 */
@Entity
@Table(name = "batch_job_execution")
public class BatchJobExecution implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "job_execution_id")
    private Long jobExecutionId;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "exit_code")
    private String exitCode;

    @Column(name = "exit_message")
    private String exitMessage;

    @Column(name = "job_configuration_location")
    private String jobConfigurationLocation;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    @Column(name = "start_time")
    private Timestamp startTime;

    private String status;

    private Long version;

    // bi-directional many-to-one association to BatchJobInstance
    @ManyToOne
    @JoinColumn(name = "job_instance_id")
    private BatchJobInstance batchJobInstance;

    public BatchJobExecution() {
    }

    public Long getJobExecutionId() {
        return this.jobExecutionId;
    }

    public void setJobExecutionId(Long jobExecutionId) {
        this.jobExecutionId = jobExecutionId;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getExitCode() {
        return this.exitCode;
    }

    public void setExitCode(String exitCode) {
        this.exitCode = exitCode;
    }

    public String getExitMessage() {
        return this.exitMessage;
    }

    public void setExitMessage(String exitMessage) {
        this.exitMessage = exitMessage;
    }

    public String getJobConfigurationLocation() {
        return this.jobConfigurationLocation;
    }

    public void setJobConfigurationLocation(String jobConfigurationLocation) {
        this.jobConfigurationLocation = jobConfigurationLocation;
    }

    public Timestamp getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Timestamp getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public BatchJobInstance getBatchJobInstance() {
        return this.batchJobInstance;
    }

    public void setBatchJobInstance(BatchJobInstance batchJobInstance) {
        this.batchJobInstance = batchJobInstance;
    }

}