package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the batch_job_instance database table.
 * 
 */
@Entity
@Table(name = "batch_job_instance", schema = "reporting")
public class BatchJobInstance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "job_instance_id")
    private Long jobInstanceId;

    @Column(name = "job_key")
    private String jobKey;

    @Column(name = "job_name")
    private String jobName;

    private Long version;

    // bi-directional many-to-one association to BatchJobExecution
    @OneToMany(mappedBy = "batchJobInstance")
    private List<BatchJobExecution> batchJobExecutions;

    public BatchJobInstance() {
    }

    public Long getJobInstanceId() {
        return this.jobInstanceId;
    }

    public void setJobInstanceId(Long jobInstanceId) {
        this.jobInstanceId = jobInstanceId;
    }

    public String getJobKey() {
        return this.jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<BatchJobExecution> getBatchJobExecutions() {
        return this.batchJobExecutions;
    }

    public void setBatchJobExecutions(List<BatchJobExecution> batchJobExecutions) {
        this.batchJobExecutions = batchJobExecutions;
    }

    public BatchJobExecution addBatchJobExecution(BatchJobExecution batchJobExecution) {
        getBatchJobExecutions().add(batchJobExecution);
        batchJobExecution.setBatchJobInstance(this);

        return batchJobExecution;
    }

    public BatchJobExecution removeBatchJobExecution(BatchJobExecution batchJobExecution) {
        getBatchJobExecutions().remove(batchJobExecution);
        batchJobExecution.setBatchJobInstance(null);

        return batchJobExecution;
    }

}