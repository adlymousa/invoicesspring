package com.harri.invoicesspring.repositories;

import com.harri.invoicesspring.models.InvoiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceHistoryRepository extends JpaRepository<InvoiceHistory, Integer> {
    InvoiceHistory findByTitle(@Param("title") String title);
    List<InvoiceHistory> findInvoicesByUserId(@Param("userId") Integer userId);
}
