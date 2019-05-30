package com.workflow.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workflow.report.model.KeyValue;

@Repository
public interface KeyValueRepository extends JpaRepository<KeyValue, String> {

}
