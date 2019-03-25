package com.harri.invoicesspring.services;

import com.harri.invoicesspring.exceptions.DataViolationException;
import com.harri.invoicesspring.exceptions.InvoiceNotFoundException;
import com.harri.invoicesspring.models.Action;
import com.harri.invoicesspring.models.Attachment;
import com.harri.invoicesspring.models.Invoice;
import com.harri.invoicesspring.models.User;
import com.harri.invoicesspring.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceHistoryService invoiceHistoryService;

    @Autowired
    AttachmentHistoryService attachmentHistoryService;

    @Autowired
    UserService userService;

    @Autowired
    FileStorageService fileStorageService;

    @Transactional
    public Invoice createInvoice(Invoice invoice, @Nullable MultipartFile file) throws DataViolationException {

        Invoice checkInvoice = invoiceRepository.findByTitle(invoice.getTitle());
        if(checkInvoice != null) throw new DataViolationException("Title already exists");

        if(file != null){
            Attachment attachment = fileStorageService.storeFile(file);
            invoice.setAttachment(attachment);
        }
        User author = userService.getCurrentAuthor();
        invoice.setUser(author);
        Invoice newInvoice = invoiceRepository.save(invoice);
        invoiceHistoryService.createInvoiceHistory(newInvoice, Action.CREATION);
        return newInvoice;
    }


    public Optional<Invoice> getInvoice(Integer id) throws InvoiceNotFoundException{

        Optional <Invoice> invoice = invoiceRepository.findById(id);
        if(!invoice.isPresent()) throw InvoiceNotFoundException.createWith(id.toString());
        return invoice;
    }


    public Page<Invoice> getAllInvoices(int pageNumber, int pageSize) {

        return invoiceRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "created"))); //, Sort.by(access Invoice id here)
    }


    public List<Invoice> getAllInvoices() {

        return invoiceRepository.findAll();
    }

    public List<Invoice> getAllInvoicesWithAttachmentDetails() {

        return invoiceRepository.findAllWithAttachmentInfo();
    }


    @Transactional
    public Invoice editInvoice(Invoice invoice, @Nullable MultipartFile file, Integer id) throws InvoiceNotFoundException, DataViolationException {

        Optional <Invoice> oldInvoice = invoiceRepository.findById(id);
        if (!oldInvoice.isPresent()) throw InvoiceNotFoundException.createWith(id.toString());

        if(file != null){
            Attachment attachment = fileStorageService.storeFile(file);
            invoice.setAttachment(attachment);
        } else if(oldInvoice.get().getAttachment() != null){
            invoice.setAttachment(oldInvoice.get().getAttachment());
        }

        // check if new title isn't existing in case it changed
        Invoice checkInvoice = invoiceRepository.findByTitle(invoice.getTitle()); // get invoice with the same title
        if(checkInvoice != null && (oldInvoice.get().getId() != checkInvoice.getId())) throw new DataViolationException("This new title already in our Database, Please try another title or just get the old one back"); // if there's invoice with the same title, check if the same Id. Note that we are oldInvoice id and id is the same and can't be changed.

        invoice.setId(id);
        invoice.setUser(oldInvoice.get().getUser());
        Invoice newInvoice = invoiceRepository.save(invoice);
        invoiceHistoryService.createInvoiceHistory(oldInvoice.get(), Action.UPDATING);

        return newInvoice;
    }


    public long countInvoices() {
        return invoiceRepository.count();
    }


    @Transactional
    public void deleteInvoice(Integer id) throws InvoiceNotFoundException { // 3 requests, you can check if it's existById first but that will cost you 4 requests

        Optional <Invoice> invoice = invoiceRepository.findById(id);
        if(!invoice.isPresent()) throw InvoiceNotFoundException.createWith(id.toString());
        invoiceHistoryService.createInvoiceHistory(invoice.get(), Action.DELETION);

        invoiceRepository.deleteById(id);
    }

    @Transactional
    public void deleteInvoice(Invoice invoice) { // this method isn't used by any controller

        invoiceHistoryService.createInvoiceHistory(invoice, Action.DELETION);
        invoiceRepository.delete(invoice);
    }

    public Invoice getInvoiceByTitle(String title) throws InvoiceNotFoundException{

        Invoice invoice = invoiceRepository.findByTitle(title);
        if(invoice == null) throw InvoiceNotFoundException.createWith(title);
        return invoice;
    }

    public Page <Invoice> searchInvoices(Specification <Invoice> specification, Pageable pageable){
        return invoiceRepository.findAll(specification, pageable);
    }

    public List<Invoice> getInvoicesByUserId(Integer userId){ return invoiceRepository.findInvoicesByUserId(userId); }

}
