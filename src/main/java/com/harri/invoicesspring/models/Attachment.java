package com.harri.invoicesspring.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String title;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Size (max=50)
    @Column(nullable = false, updatable = false)
    private String Created;

    @Column(name = "attachment_path", nullable = false)
    private String attachmentPath;

    //@JsonIgnore
    @OneToOne(mappedBy = "attachment")
    private Invoice invoice;

    public Attachment() {

    }

    public Attachment(@Size(max = 100) String title, String fileType, String attachmentPath) {
        this.title = title;
        this.fileType = fileType;
        this.attachmentPath = attachmentPath;
    }

    public Attachment(@Size(max = 100) String title, String fileType, String attachmentPath, Invoice invoice) {
        this.title = title;
        this.fileType = fileType;
        this.attachmentPath = attachmentPath;
        this.invoice = invoice;
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
