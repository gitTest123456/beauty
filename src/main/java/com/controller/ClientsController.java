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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public @ResponseBody List<Client> getClients() {
        return service.getAllClientsList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody Client getClient(@PathVariable Integer id) {
        return service.getClientById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/clients/add")
    public @ResponseBody Client addClient(@RequestBody Client client) {
        System.out.println("add client " + client);
        service.addClient(client);
        return client;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/clients/delete")
    public String deleteClient(@RequestBody Client client) {
        System.out.println("delete client " + client.toString());
        service.deleteClient(client);
        return "redirect:/1";
    }
}
