package com.workflow.report.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PackageRepository {
    @Query("SELECT p.packageKey  FROM Package p where p.keyValuePairID = keyValuePairId")
    String findPackageIdByKeyValuePairId(@Param("keyValuePairId") UUID keyValuePairId);
}
