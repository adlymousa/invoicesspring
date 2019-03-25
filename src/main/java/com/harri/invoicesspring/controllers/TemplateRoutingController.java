package com.harri.invoicesspring.controllers;


import com.harri.invoicesspring.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TemplateRoutingController {



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String routeIndex(){

        return "index";
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String routeIndex2(){

        return "index";
    }

    @RequestMapping(value = "/users.html", method = RequestMethod.GET)
    public String routeUsers(){

        return "users";
    }

    @RequestMapping(value = "/invoices-history.html", method = RequestMethod.GET)
    public String routeInvoicesAudits(){

        return "invoices-history";
    }

    @RequestMapping(value = "/attachments-history.html", method = RequestMethod.GET)
    public String routeAttachmentsAudits(){

        return "attachments-history";
    }

    @RequestMapping(value = "/forbidden.html", method = RequestMethod.GET)
    public String routeForbidden(){

        return "forbidden";
    }

    @RequestMapping(value = "/register.html", method = RequestMethod.GET)
    public String routeRegister(){

        return "register";
    }

    //login may be working now, it's a template

}
