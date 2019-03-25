package com.harri.invoicesspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import com.harri.invoicesspring.models.Action;

@Entity
@Table(name = "attachment_history")
public class AttachmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "attachment_id", nullable = false)
    private Integer attachmentId;

    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String title;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "attachment_path", nullable = false)
    private String attachmentPath;

    @Column(nullable = false)
    private String updated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    public AttachmentHistory(){

    }

    public AttachmentHistory(Integer attachmentId, @Size(max = 100) String title, User user, String fileType, String attachmentPath, String updated, Action action) {
        this.attachmentId = attachmentId;
        this.title = title;
        this.userId = user.getId();
        this.fileType = fileType;
        this.attachmentPath = attachmentPath;
        this.updated = updated;
        this.action = action;
    }

    public AttachmentHistory(Attachment attachment, User author, Action action){

        this.attachmentId = attachment.getId();
        this.title = attachment.getTitle();
        this.userId = author.getId();
        this.fileType = attachment.getFileType();
        this.attachmentPath = attachment.getAttachmentPath();
        this.action = action;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
