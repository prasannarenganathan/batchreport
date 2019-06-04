package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.BatchJobExecution;

@Repository
public interface BatchJobExecutionRepository extends JpaRepository<BatchJobExecution, String> {
    @Query(nativeQuery = true, value = "select max(end_time) from reporting.batch_job_execution where status='COMPLETED'")
    Timestamp findLastExecutedTimeStamp();
}
