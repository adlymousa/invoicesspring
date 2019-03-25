package com.harri.invoicesspring.services;

import com.harri.invoicesspring.models.Action;
import com.harri.invoicesspring.models.Invoice;
import com.harri.invoicesspring.models.InvoiceHistory;
import com.harri.invoicesspring.models.User;
import com.harri.invoicesspring.repositories.InvoiceHistoryRepository;
import com.harri.invoicesspring.repositories.InvoiceRepository;
import com.harri.invoicesspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceHistoryService {

    @Autowired
    InvoiceHistoryRepository invoiceHistoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AttachmentHistoryService attachmentHistoryService;

    public InvoiceHistory createInvoiceHistory(Invoice invoice, Action action) {
        User author = userService.getCurrentAuthor();
        InvoiceHistory invoiceHistory = new InvoiceHistory(invoice, author, action);

        if(invoice.getAttachment() != null) attachmentHistoryService.createAttachmentHistory(invoice.getAttachment(), action);

        return invoiceHistoryRepository.save(invoiceHistory);
    }


    public Optional<InvoiceHistory> getInvoiceHistory(Integer id) {
        return invoiceHistoryRepository.findById(id);
    }


    public Page<InvoiceHistory> getAllInvoiceHostories(int pageNumber, int pageSize) {
        return invoiceHistoryRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"))); //, Sort.by(access Invoice id here)
    }


    public List<InvoiceHistory> getAllInvoiceHistories() {
        return invoiceHistoryRepository.findAll();
    }


    public long countInvoices() {
        return invoiceHistoryRepository.count();
    }


    //search will replace all the code below

    public InvoiceHistory getInvoiceHistoryByTitle(String title){
        return invoiceHistoryRepository.findByTitle(title);
    }


//    public List <InvoiceHistory> searchInvoices(Specification<InvoiceHistory> specification){
//        return invoiceHistoryRepository.findAll(specification);
//    }

    public List<InvoiceHistory> getInvoicesByUserId(Integer userId){ return invoiceHistoryRepository.findInvoicesByUserId(userId); }

}
