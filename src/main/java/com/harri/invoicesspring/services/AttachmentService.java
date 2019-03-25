package com.harri.invoicesspring.services;

import com.harri.invoicesspring.models.Action;
import com.harri.invoicesspring.models.Attachment;
import com.harri.invoicesspring.repositories.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentHistoryService attachmentHistoryService;

    @Transactional
    public Attachment createAttachment(Attachment attachment) {

        Attachment newAttachment = attachmentRepository.save(attachment);
        //attachmentHistoryService.createAttachmentHistory(newAttachment, Action.CREATION);
        return newAttachment;
    }


    public Optional<Attachment> getAttachemnt(Integer id) {
        return attachmentRepository.findById(id);
    }

    public Page<Attachment> getAllAttachments(int pageNumber, int pageSize) {
        return (Page<Attachment>) attachmentRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"))); //, Sort.by(access Invoice id here)
    }


    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }

    @Transactional
    public Attachment editAttachment(Attachment attachment, Integer id) {

        attachment.setId(id);
        Attachment editedAttachment = attachmentRepository.save(attachment);
        attachmentHistoryService.createAttachmentHistory(editedAttachment, Action.UPDATING);
        return editedAttachment;
    }


    public long countAttachments() {
        return attachmentRepository.count();
    }

    @Transactional
    public void deleteAttachment(Integer id) {

        Optional <Attachment> attachment = attachmentRepository.findById(id);
        attachmentHistoryService.createAttachmentHistory(attachment.get(), Action.DELETION);
        attachmentRepository.deleteById(id);
    }

    @Transactional
    public void deleteAttachment(Attachment attachment) {

        attachmentHistoryService.createAttachmentHistory(attachment, Action.DELETION);
        attachmentRepository.delete(attachment);
    }

    public Attachment getAttachmentByTitle(String title){
        return attachmentRepository.findByTitle(title);
    }

}
