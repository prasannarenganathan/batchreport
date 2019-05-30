package com.cnasurety.extagencyint.batches.ivans.workflow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.workflow.domain.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

    @Query("SELECT n.notificationKey  FROM Notification n where n.keyValuePairId = keyValuePairId")
    String findNotificationIdByKeyValuePairId(@Param("keyValuePairId") UUID keyValuePairId);
}
