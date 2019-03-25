package com.harri.invoicesspring.DTOs;

public class AttachmentDTO {


    private Integer id;

    private String title;

    private String attachmentPath;

    public AttachmentDTO(){

    }

    public AttachmentDTO(Integer id, String title, String attachmentPath) {
        this.id = id;
        this.title = title;
        this.attachmentPath = attachmentPath;
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

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
}
