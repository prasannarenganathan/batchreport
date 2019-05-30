package com.cnasurety.extagencyint.batches.ivans.workflow.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.workflow.domain.EventAudit;

@Repository
public interface EventAuditRepository extends JpaRepository<EventAudit, String> {

  
}
