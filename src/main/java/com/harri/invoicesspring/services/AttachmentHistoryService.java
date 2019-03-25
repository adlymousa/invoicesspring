package com.harri.invoicesspring.services;

import com.harri.invoicesspring.models.Action;
import com.harri.invoicesspring.models.Attachment;
import com.harri.invoicesspring.models.AttachmentHistory;
import com.harri.invoicesspring.models.User;
import com.harri.invoicesspring.repositories.AttachmentHistoryRepository;
import com.harri.invoicesspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentHistoryService {

    @Autowired
    AttachmentHistoryRepository attachmentHistoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public AttachmentHistory createAttachmentHistory(Attachment attachment, Action action) {

        User author = userService.getCurrentAuthor();
        AttachmentHistory attachmentHistory = new AttachmentHistory(attachment, author, action);
        return attachmentHistoryRepository.save(attachmentHistory);
    }


    public Optional<AttachmentHistory> getAttachemnt(Integer id) {
        return attachmentHistoryRepository.findById(id);
    }

    public Page<AttachmentHistory> getAllAttachmentHistories(int pageNumber, int pageSize) {
        return (Page<AttachmentHistory>) attachmentHistoryRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"))); //, Sort.by(access Invoice id here)
    }


    public List<AttachmentHistory> getAllAttachmentHistories() {
        return attachmentHistoryRepository.findAll();
    }


    public long countAttachmentHistories() {
        return attachmentHistoryRepository.count();
    }


    public AttachmentHistory getAttachmentHistoryByTitle(String title){
        return attachmentHistoryRepository.findByTitle(title);
    }

}
