package com.harri.invoicesspring.controllers;

import com.harri.invoicesspring.DTOs.Dto;
import com.harri.invoicesspring.DTOs.InvoiceDTO;
import com.harri.invoicesspring.exceptions.DataViolationException;
import com.harri.invoicesspring.exceptions.GlobalExceptionHandler;
import com.harri.invoicesspring.exceptions.InvoiceNotFoundException;
import com.harri.invoicesspring.models.*;
import com.harri.invoicesspring.services.*;
import com.harri.invoicesspring.repositories.InvoiceRepository;

import net.kaczmarzyk.spring.data.jpa.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


//specification Argument Resolver
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    InvoiceHistoryService invoiceHistoryService;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


//    @RequestMapping(value = "/api/invoices", method = RequestMethod.GET)
//    public List <Invoice> getInvoices(){
//
//        List <Invoice> invoices = invoiceService.getAllInvoices();
//        return invoices;
//    }

//    @Dto(InvoiceDTO.class) // you don't need this join, it doing it internally
//    @RequestMapping(value = "/api/invoicesWithAttachments", method = RequestMethod.GET)
//    public List <Invoice> getInvoicesWithAttachments(){
//
//        List <Invoice> invoices = invoiceService.getAllInvoicesWithAttachmentDetails();
//        return invoices;
//    }

    @Dto(InvoiceDTO.class)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/api/invoices", method = RequestMethod.POST, consumes = {"multipart/form-data", "application/json"})
    public Invoice createInvoice(
            @NotBlank(message = "Title is Mandatory") @RequestParam(value = "title") String title,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "totalAmount", required = false) Double totalAmount,
            @RequestParam(value = "currency", required = false) String currencyName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "file", required = false) MultipartFile file) throws DataViolationException {

        Currency currency = null;
        if (currencyName != null) currency = currencyService.getCurrencyByName(currencyName);

        Invoice invoice = new Invoice(title, date, totalAmount, currency, description);

        return invoiceService.createInvoice(invoice, file);
    }

    @Dto(InvoiceDTO.class)
    @RequestMapping(value = "/api/invoices/{id}", produces = "application/json", method = RequestMethod.GET)
    public Optional <Invoice> getInvoiceById(@PathVariable Integer id) throws InvoiceNotFoundException{

        Optional<Invoice> invoice = invoiceService.getInvoice(id);
        if (!invoice.isPresent()) throw InvoiceNotFoundException.createWith(id.toString());
        return invoice;
    }

    @Dto(InvoiceDTO.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/api/invoices/{id}", produces = "application/json", method = RequestMethod.PUT, consumes = {"multipart/form-data", "application/json"})
    public Invoice updateInvoiceById(@RequestParam(value = "title") String title,
                                     @RequestParam(value = "date", required = false) String date,
                                     @RequestParam(value = "totalAmount", required = false) Double totalAmount,
                                     @RequestParam(value = "currency", required = false) String currencyName,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "file", required = false) MultipartFile file,
                                     @PathVariable Integer id) throws InvoiceNotFoundException, DataViolationException{

        Currency currency = null;
        if (currencyName != null) currency = currencyService.getCurrencyByName(currencyName);

        Invoice invoice = new Invoice(title, date, totalAmount, currency, description);

        return invoiceService.editInvoice(invoice, file, id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/api/invoices/{id}", method = RequestMethod.DELETE)
    public void deleteInvoiceById(@PathVariable Integer id) throws InvoiceNotFoundException{

        invoiceService.deleteInvoice(id);
    }

    @Dto(InvoiceDTO.class)
    @RequestMapping(value = "/api/invoices", method = RequestMethod.GET)
    public Page<Invoice> search(
            @And({
                    @Spec(path = "id", params = "id", spec = LikeIgnoreCase.class),
                    @Spec(path = "title", params = "title", spec = LikeIgnoreCase.class),
                    @Spec(path = "user.username", params = "user", spec = LikeIgnoreCase.class),
                    @Spec(path = "user.firstName", params = "user", spec = LikeIgnoreCase.class),
                    @Spec(path = "user.lastName", params = "user", spec = LikeIgnoreCase.class),
                    @Spec(path = "attachment.title", params = "attachment", spec = LikeIgnoreCase.class),
                    @Spec(path = "date", params = "date", spec = Like.class),
                    @Spec(path = "created", params = "created", spec = Like.class),
                    @Spec(path = "totalAmount", params = "amount", spec = Equal.class),
                    @Spec(path = "currency.name", params = "currency", spec = Equal.class),
                    @Spec(path = "description", params = "description", spec = Like.class),
                    //@Spec(path = "user.role.name().toString()", params = "role", spec = EqualIgnoreCase.class)
            }) Specification<Invoice> invoiceSpecification,
            @PageableDefault(size = 10)
            @SortDefault(sort = "created", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Invoice> invoices = invoiceService.searchInvoices(invoiceSpecification, pageable);
        return invoices;
    }


//    @Dto(InvoiceDTO.class)
//    @RequestMapping(value = "/api/invoices", params = {"size", "page"}, produces = "application/json", method = RequestMethod.GET) //throws exception when param is null
//    public Page <Invoice> getInvoicesPaginated(@Param("page") Integer page, @Param("size") Integer size){ //add sort here
//
//        LOGGER.error("inside pagination api");
//        Page <Invoice> invoices = invoiceService.getAllInvoices(page, size);
//        return invoices;
//    }

//    @RequestMapping(value = "/api/invoices/{id}/attachment", produces = "application/json", method = RequestMethod.GET) //throws exception when param is null
//    public Attachment getAttachmentForInvoice(@PathVariable Integer id){
//        Optional<Invoice> invoice = invoiceService.getInvoice(id);
//        Attachment attachment = attachmentService.getAttachemnt();
//
//        return attachment;
//    }


}
