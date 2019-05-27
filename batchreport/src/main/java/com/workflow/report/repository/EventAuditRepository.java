package com.workflow.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.workflow.report.model.EventAudit;

@Repository
public interface EventAuditRepository extends JpaRepository<EventAudit, String> {

    @Query(value = "select * from workflow.\"EVENT_AUDIT_TBL\"", nativeQuery = true)
    List<EventAudit> findAllActiveUsers();
}
