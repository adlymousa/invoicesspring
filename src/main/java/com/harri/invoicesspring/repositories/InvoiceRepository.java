package com.harri.invoicesspring.repositories;

import com.harri.invoicesspring.models.Attachment;
import com.harri.invoicesspring.models.Invoice;                //Bean.User
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;           // * thing
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RepositoryRestResource(collectionResourceRel = "invoices", path = "invoices")
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>, JpaSpecificationExecutor<Invoice> {

    Invoice findByTitle(@Param("title") String title);
    List<Invoice> findInvoicesByUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT i, a FROM Invoice i LEFT JOIN i.attachment a") //never used
    List<Invoice> findAllWithAttachmentInfo();
}
