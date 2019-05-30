package com.cnasurety.extagencyint.batches.ivans.workflow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.workflow.domain.DocumentEntity;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, String> {
    @Query("SELECT d.documentKey  FROM DocumentEntity d where d.keyValuePairId = keyValuePairId")
    String findDocumentIdByKeyValuePairId(@Param("keyValuePairId") UUID keyValuePairId);
}
