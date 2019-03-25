package com.harri.invoicesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @NotBlank(message = "Title for the invoice is mandatory")
    @Column(nullable = false, unique = true)
    private String title;

//    @Column(name = "user_id", nullable = false, updatable = false)
//    private Integer userId;
//
//    @Column(name = "attachment_id")
//    private Integer attachmentId;

    @Column
    private String date;

    @Column(nullable = false, updatable = false)
    private String created;


    @Column(name = "total_amount") // unique = true is preferred
    private Double totalAmount;

//    @Column(name = "currency_id") //  unique = true is preferred
//    private Integer currencyId;

    @Size(max = 500)
    @Column
    private String description;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    public Invoice(){

    }

    public Invoice(@Size(max = 50) String title, User user){
        this.title = title;
        this.user = user;
    }

    public Invoice(@Size(max = 50) String title, @Nullable String date, @ Nullable Double totalAmount, @Nullable Currency currency, @Nullable @Size(max = 500) String description) {
        this.title = title;
        this.date = date;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.description = description;
    }


    public Invoice(@Size(max = 50) String title, String date, String created, Double totalAmount, @Size(max = 500) String description, User user, Attachment attachment, Currency currency) {
        this.title = title;
        this.date = date;
        this.created = created;
        this.totalAmount = totalAmount;
        this.description = description;
        this.user = user;
        this.attachment = attachment;
        this.currency = currency;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

}
