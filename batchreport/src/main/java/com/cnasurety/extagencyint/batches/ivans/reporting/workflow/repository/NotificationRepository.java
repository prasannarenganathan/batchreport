package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

    @Query("SELECT n.notificationKey  FROM Notification n where n.keyValuePairId = keyValuePairId")
    String findNotificationIdByKeyValuePairId(@Param("keyValuePairId") UUID keyValuePairId);
    
    @Query("SELECT n  FROM Notification n where n.lastModifiedDate > :lastExecutedDate")
    List<Notification> findAllByTimeStamp(@Param("lastExecutedDate") Timestamp lastExecutedDate);
   
}
