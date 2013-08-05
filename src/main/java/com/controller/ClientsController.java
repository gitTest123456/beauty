package com.controller;

import com.model.ClientsEntity;
import com.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ClientsController {
    @Autowired
    private ClientsService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("client")
                             ClientsEntity client, BindingResult result) {

        service.getAllClientsList();

        return "redirect:/index";
    }

    @RequestMapping("/clients")
    public String listContacts() {

        service.getAllClientsList();

        return "index.html";
    }


}
