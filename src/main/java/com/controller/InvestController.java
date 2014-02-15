package com.controller;

import com.model.InvestEntity;
import com.service.InvestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class InvestController {
    static Logger log = Logger.getLogger(InvestController.class.getName());

    @PostConstruct
    public void init() {
        log.info("Inside method InvestController init");
    }


    @Autowired
    private InvestService service;


    @RequestMapping(method = RequestMethod.GET, value = "/invest")
    public
    @ResponseBody
    List<InvestEntity> getClients() {
        return service.getAllInvest();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/invest/add")
    public
    @ResponseBody
    InvestEntity addClient(@RequestBody InvestEntity investEntity) {
        service.addInvest(investEntity);
        return investEntity;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/invest/delete")
    public String deleteClient(@RequestBody InvestEntity investEntity) {
        service.deleteInvest(investEntity);
        return "redirect:/1";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/invest/investReport")
    public String generateInvestReport() {
        service.printInvestReport();
        return "redirect:/1";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/invest/report")
    public String generateReport() {
        service.printReport();
        return "redirect:/1";
    }

}
