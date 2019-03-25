package com.harri.invoicesspring.services;

import com.harri.invoicesspring.exceptions.DataViolationException;
import com.harri.invoicesspring.models.Invoice;
import com.harri.invoicesspring.models.User;
import com.harri.invoicesspring.repositories.InvoiceRepository;
import com.harri.invoicesspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    //@Resource
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(User user) throws DataViolationException{

        User checkUser = userRepository.findByUsername(user.getUsername());
        if(checkUser != null) throw new DataViolationException("username aleady exists");

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }


    public Page <User> getAllUsers(int pageNumber, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"))); //, Sort.by(access user id here)
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User editUser(User user) {
        return userRepository.save(user);
    }


    public long countUsers() {
        return userRepository.count();
    }


    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


    public void deleteUser(User user) {
        userRepository.delete(user);
    }


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getCurrentAuthor(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = userRepository.findByUsername("user");
        }
        return user;
    }

    //public List<Invoice> getInvoicesByUserId(Integer userId){ return invoiceRepository.findInvoicesByUserId(userId); }

}
