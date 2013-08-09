package com.controller;

import com.model.Client;
import com.service.ClientsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ClientsController {
    static Logger log = Logger.getLogger(ClientsController.class.getName());

    @PostConstruct
    public void init() {
        log.info("Inside method ClientsController init");
    }


    @Autowired
    private ClientsService service;


    public ClientsService getService() {
        return service;
    }

    public void setService(ClientsService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clients")
    public
    @ResponseBody
    java.util.List<Client> getContacts() {
        ArrayList list = (ArrayList) service.getAllClientsList();
        log.info("START get list of Contacts");
        return list;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("client") Client client, BindingResult result) {

        service.getAllClientsList();

        return "redirect:/index";
    }

}
