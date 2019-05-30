package com.cnasurety.extagencyint.batches.ivans.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.reporting.model.KeyValue;

@Repository
public interface KeyValueRepository extends JpaRepository<KeyValue, String> {

}
