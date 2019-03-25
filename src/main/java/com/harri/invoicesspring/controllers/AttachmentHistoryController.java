package com.harri.invoicesspring.controllers;

import com.harri.invoicesspring.models.AttachmentHistory;
import com.harri.invoicesspring.services.AttachmentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AttachmentHistoryController {

    @Autowired
    AttachmentHistoryService attachmentHistoryService;

    @RequestMapping(value = "/api/audit/attachments", method = RequestMethod.GET)
    public List<AttachmentHistory> getAttachments(){
        List <AttachmentHistory> attachmentHistorys = attachmentHistoryService.getAllAttachmentHistories();
        return attachmentHistorys;
    }

    @RequestMapping(value = "/api/audit/attachments/{id}", produces = "application/json", method = RequestMethod.GET)
    public Optional<AttachmentHistory> getAttachmentById(@PathVariable Integer id){

        Optional<AttachmentHistory> attachmentHistory = attachmentHistoryService.getAttachemnt(id);
        return attachmentHistory;
    }

    @RequestMapping(value = "/api/audit/attachments", params = {"size", "page"},produces = "application/json", method = RequestMethod.GET)
    public Page<AttachmentHistory> getAttachmentHistoriesPaginated(@Param("page") Integer page, @Param("size") Integer size){

        Page<AttachmentHistory> attachmentHistory = attachmentHistoryService.getAllAttachmentHistories(page, size);
        return attachmentHistory;
    }


    @RequestMapping(value = "/api/audit/attachments", params = "title", method = RequestMethod.GET)
    public AttachmentHistory getAttachmentByTitle(@RequestParam("title") String title){

        AttachmentHistory attachmentHistory = attachmentHistoryService.getAttachmentHistoryByTitle(title);
        return attachmentHistory;
    }

}
