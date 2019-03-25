package com.harri.invoicesspring.controllers;

import com.harri.invoicesspring.models.InvoiceHistory;
import com.harri.invoicesspring.services.InvoiceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceHistoryController {

    @Autowired
    InvoiceHistoryService invoiceHistoryService;

    @RequestMapping(value = "/api/audit/invoices", method = RequestMethod.GET)
    public List<InvoiceHistory> getInvoicesHistory(){
        List <InvoiceHistory> invoiceHistories = invoiceHistoryService.getAllInvoiceHistories();
        return invoiceHistories;
    }


    @RequestMapping(value = "/api/audit/invoices/{id}", produces = "application/json", method = RequestMethod.GET)
    public Optional <InvoiceHistory> getInvoiceHistoryById(@PathVariable Integer id){

        Optional<InvoiceHistory> invoiceHistory = invoiceHistoryService.getInvoiceHistory(id);
        return invoiceHistory;
    }

    @RequestMapping(value = "/api/audit/invoices", params = {"size", "page"}, produces = "application/json", method = RequestMethod.GET)
    public Page<InvoiceHistory> getInvoicesHistoryPaginated(@Param("page") Integer page, @Param("size") Integer size){

        Page<InvoiceHistory> invoiceHistory = invoiceHistoryService.getAllInvoiceHostories(page, size);
        return invoiceHistory;
    }


//    @RequestMapping(value = "/api/invoiceAudit/search", method = RequestMethod.GET)
//    public List<InvoiceHistory> search(InvoiceSearchSpecification invoiceSpecification){
//
//        List<InvoiceHistory> invoices = invoiceHistoryService.searchInvoices(invoiceSpecification);
//        return invoices;
//    }

}
