package com.harri.invoicesspring.models;

import org.hibernate.validator.constraints.Currency;
import com.harri.invoicesspring.models.Action;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "invoice_history")
public class InvoiceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "invoice_id", nullable = false)
    private Integer invoiceId;

    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private String date;

    @Column(nullable = false)
    private String updated;


    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "currency_id")
    private Integer currencyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @Size(max = 500)
    @Column
    private String description;

    @Column(name = "attachment_id")
    private Integer attachmentId;


    public  InvoiceHistory(){

    }

    public InvoiceHistory(User user, Integer invoiceId, @Size(max = 50) String title, String date, String updated, Double totalAmount, Integer currencyId, Action action, @Size(max = 500) String description, Integer attachmentId) {
        this.userId = user.getId();
        this.invoiceId = invoiceId;
        this.title = title;
        this.date = date;
        this.updated = updated;
        this.totalAmount = totalAmount;
        this.currencyId = currencyId;
        this.action = action;
        this.description = description;
        this.attachmentId = attachmentId;
    }

    public InvoiceHistory(Invoice invoice, User author, Action action){

        this.userId = author.getId();
        this.invoiceId = invoice.getId();
        this.title = invoice.getTitle();
        this.date = invoice.getDate();
        //updated date will be automatically set in the database
        this.totalAmount = invoice.getTotalAmount();
        if (invoice.getCurrency() != null) this.currencyId = invoice.getCurrency().getId();
        this.description = invoice.getDescription();
        if(invoice.getAttachment() != null) this.attachmentId = invoice.getAttachment().getId();
        this.action = action;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }


}
