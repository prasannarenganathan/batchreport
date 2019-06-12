package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.KeyValue;

@Repository
public interface KeyValueRepository extends JpaRepository<KeyValue, String> {

    @Query("SELECT kv  FROM KeyValue kv where kv.lastModifiedDate < :lastExecutedDate")
    List<KeyValue> findAllByTimeStamp(@Param("lastExecutedDate") Timestamp lastExecutedDate);
}
