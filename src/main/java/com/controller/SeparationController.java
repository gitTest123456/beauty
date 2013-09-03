package com.controller;

import com.model.Separation;
import com.service.SeparationI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: nkravchenko
 * Date: 9/3/13
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SeparationController {
    static Logger log = Logger.getLogger(SeparationController.class.getName());

    @Autowired
    private SeparationI service;


    @PostConstruct
    public void init() {
        log.info("Inside method SeparationController init");
    }

    public SeparationI getService() {
        return service;
    }

    public void setService(SeparationI service) {
        this.service = service;
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        ServiceController.log = log;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/separations")
    public
    @ResponseBody
    Collection<Separation> getSeparations() {
        return service.getListSeparation();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/separations/{id}")
    public
    @ResponseBody
    Separation getService(@PathVariable Integer id) {
        return service.getSeparationById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/separations/add")
    public
    @ResponseBody
    Separation addSeparation(@RequestBody Separation separation) {
        System.out.println("add separation " + separation);
        service.addSeparation(separation);
        return separation;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/separations/delete")
    public String deleteClient(@RequestBody Separation separation) {
        System.out.println("delete separation " + separation.toString());
        service.deleteSeparation(separation);
        return "redirect:/1";
    }
}
