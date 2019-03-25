package com.harri.invoicesspring.repositories;

import com.harri.invoicesspring.models.AttachmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentHistoryRepository extends JpaRepository<AttachmentHistory, Integer> {

    AttachmentHistory findByTitle(@Param("title") String title);

}
