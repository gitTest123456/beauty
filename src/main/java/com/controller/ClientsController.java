package com.controller;

import com.model.Client;
import com.service.ClientsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/clients")
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

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Client> getContacts() {
        log.info("START get list of Contacts");
        List<Client> clients = service.getAllClientsList();
        return clients;

    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addContact(@RequestBody Client client) {

        service.addClient(client);

        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String removeContact(@RequestBody Client client) {

        service.deleteClient(client);

        return "redirect:/index";
    }

}
