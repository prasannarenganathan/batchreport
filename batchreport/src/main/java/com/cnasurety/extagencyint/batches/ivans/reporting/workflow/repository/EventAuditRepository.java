package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.EventAudit;

@Repository
public interface EventAuditRepository extends JpaRepository<EventAudit, String> {

  
}
