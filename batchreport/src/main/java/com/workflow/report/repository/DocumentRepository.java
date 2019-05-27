package com.workflow.report.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentRepository {
    @Query("SELECT d.documentKey  FROM Document d where d.keyValuePairId = keyValuePairId")
    String findDocumentIdByKeyValuePairId(@Param("keyValuePairId") UUID keyValuePairId);
}
