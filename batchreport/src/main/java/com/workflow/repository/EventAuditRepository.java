package com.workflow.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workflow.report.model.EventAudit;

@Repository
public interface EventAuditRepository extends JpaRepository<EventAudit, String> {

}
