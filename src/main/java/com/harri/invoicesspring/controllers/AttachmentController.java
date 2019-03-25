package com.harri.invoicesspring.controllers;

import com.harri.invoicesspring.models.Attachment;
import com.harri.invoicesspring.services.AttachmentHistoryService;
import com.harri.invoicesspring.services.AttachmentService;

import com.harri.invoicesspring.services.AttachmentService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    AttachmentHistoryService attachmentHistoryService;

    @RequestMapping(value = "/api/attachments", method = RequestMethod.GET)
    public List <Attachment> getAttachments(){

        List <Attachment> attachments = attachmentService.getAllAttachments();
        return attachments;
    }

    @RequestMapping(value = "/api/attachments", method = RequestMethod.POST)
    public Attachment createAttachment(@RequestBody Attachment attachment){

        Attachment newAttachment = attachmentService.createAttachment(attachment);
        return newAttachment;
    }

    @RequestMapping(value = "/api/attachments/{id}", produces = "application/json", method = RequestMethod.GET)
    public Optional <Attachment> getAttachmentById(@PathVariable Integer id){

        Optional<Attachment> attachment = attachmentService.getAttachemnt(id);
        return attachment;
    }

    @RequestMapping(value = "/api/attachments/{id}", produces = "application/json", method = RequestMethod.PUT)
    public Attachment updateAttachmentById(@RequestBody Attachment attachment, @PathVariable Integer id){

        Attachment editedAttachment = attachmentService.editAttachment(attachment, id);
        return editedAttachment;
    }

    @RequestMapping(value = "/api/attachments/{id}", method = RequestMethod.DELETE)
    public void deleteAttachmentById(@PathVariable Integer id){

        attachmentService.deleteAttachment(id);
    }

    @RequestMapping(value = "/api/attachments", params = "title", method = RequestMethod.GET)
    public Attachment getAttachmentByTitle(@RequestParam("title") String title){

        Attachment attachment = attachmentService.getAttachmentByTitle(title);
        return attachment;
    }


}
