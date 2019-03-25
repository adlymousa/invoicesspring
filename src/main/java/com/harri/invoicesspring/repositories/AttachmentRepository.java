package com.harri.invoicesspring.repositories;

import com.harri.invoicesspring.models.Attachment;                //Bean.User
import org.springframework.data.jpa.repository.JpaRepository;           // * thing
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository <Attachment, Integer> {

    Attachment findByTitle(@Param("title") String title);

}
