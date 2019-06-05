package com.cnasurety.extagencyint.batches.ivans.reporting.workflow.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cnasurety.extagencyint.batches.ivans.reporting.workflow.model.IvansMessageAttachment;

public interface IvansMessageAttachmentRepository extends JpaRepository<IvansMessageAttachment, String> {
	
	 @Query("SELECT ima  FROM IvansMessageAttachment ima where ima.lastModifiedDate > :lastExecutedDate")
	    List<IvansMessageAttachment> findAllByTimeStamp(@Param("lastExecutedDate") Timestamp lastExecutedDate);
	 
	 public List<IvansMessageAttachment> findByIvansMessageKey(UUID key);

}
