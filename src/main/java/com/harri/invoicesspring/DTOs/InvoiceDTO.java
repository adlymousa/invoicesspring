package com.harri.invoicesspring.DTOs;

import com.harri.invoicesspring.models.Attachment;
import com.harri.invoicesspring.models.User;

public class InvoiceDTO {


    private Integer id;

    private String title;

    private UserForInvoiceDTO user;

    private String date;

    private String created;

    private Double totalAmount;

    private CurrencyDTO currency;

    private String description;

    private AttachmentDTO attachment;


    public InvoiceDTO(){

    }

    public InvoiceDTO(Integer id, String title, String date, String created, Double totalAmount, CurrencyDTO currency, String description, UserForInvoiceDTO user, AttachmentDTO attachment) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.created = created;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.description = description;
        this.user = user;
        this.attachment = attachment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserForInvoiceDTO getUser() {
        return user;
    }

    public void setUser(UserForInvoiceDTO user) {
        this.user = user;
    }

    public AttachmentDTO getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentDTO attachment) {
        this.attachment = attachment;
    }

}
