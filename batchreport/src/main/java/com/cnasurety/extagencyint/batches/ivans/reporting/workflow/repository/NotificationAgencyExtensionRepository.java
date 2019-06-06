package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository;


import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.NotificationAgencyExtension;

@Repository
public interface NotificationAgencyExtensionRepository extends JpaRepository<NotificationAgencyExtension, String> {

	 public NotificationAgencyExtension findByNotificationKey(UUID key);

}
