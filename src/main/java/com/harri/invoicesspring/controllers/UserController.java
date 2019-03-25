package com.harri.invoicesspring.controllers;

import com.harri.invoicesspring.DTOs.Dto;
import com.harri.invoicesspring.DTOs.UserDTO;
import com.harri.invoicesspring.exceptions.DataViolationException;
import com.harri.invoicesspring.exceptions.UserNotFoundException;
import com.harri.invoicesspring.models.User;
import com.harri.invoicesspring.services.InvoiceService;
import com.harri.invoicesspring.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    InvoiceService invoiceService;


//    @RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
//    public ModelAndView index(){
//        List <User> users = userService.getAllUsers();
//        HashMap params = new HashMap<String,Object>();
//        params.put("users",users);
//
//        ModelAndView modelAndView = new ModelAndView("users");
//        modelAndView.addObject("users", users);
//
//        return modelAndView;
//    }


//    @RequestMapping(value = "/users/search", produces = "application/json", method = RequestMethod.GET)
//    public User getUserByUsername(@RequestParam("username") String username){
//        User user = userService.getUserByUsername(username);
//
//        return user;
//    }


    @Dto(UserDTO.class)
    @RequestMapping(value = "/api/users",  produces = "application/json", method = RequestMethod.GET)
    public List<User> getUsers(){
        List <User> users = userService.getAllUsers();

        return users;
    }

    @Dto(UserDTO.class)
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    public User createUser(@Valid @RequestBody User user) throws DataViolationException {

        return userService.createUser(user);
    }

    @Dto(UserDTO.class)
    @RequestMapping(value = "/api/users/{id}", produces = "application/json", method = RequestMethod.GET)
    public Optional <User> getUserById(@PathVariable Integer id) throws UserNotFoundException{

        Optional<User> user = userService.getUser(id);
        if (!user.isPresent()) throw UserNotFoundException.createWith(id.toString());
        return user;
    }

    @Dto(UserDTO.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/api/users/{id}", produces = "application/json", method = RequestMethod.PUT)
    public User updateUserById(@RequestBody User user, @PathVariable Integer id){ //there's some problem here - pass id

        return userService.editUser(user);
    }

    @Dto(UserDTO.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable Integer id){
        userService.deleteUser(id);
    }


    @Dto(UserDTO.class)
    @RequestMapping(value = "/api/users", params = "username", produces = "application/json", method = RequestMethod.GET) ////doesn't throw exception when param is null
    public User getUserByUsername(@Param("username") String username) throws UserNotFoundException {

        User user = userService.getUserByUsername(username);
        if(user == null) throw UserNotFoundException.createWith(username);
        return user;
    }

    @Dto(UserDTO.class)
    @RequestMapping(value = "/api/users", params = {"size", "page"}, produces = "application/json", method = RequestMethod.GET) //throws exception when param is null
    public Page <User> getUsersPaginated(@Param("page") Integer page, @Param("size") Integer size){ //add sort here
        Page <User> users = userService.getAllUsers(page, size);

        return users;
    }

//    @RequestMapping(value = "/api/users/{id}/invoices", produces = "application/json", method = RequestMethod.GET) //throws exception when param is null
//    public List <Invoice> getInvoicesCreatedByUser(@PathVariable Integer id){
//        List <Invoice> invoices = invoiceService.getInvoicesByUserId(id);
//
//        return invoices;
//    }



}
