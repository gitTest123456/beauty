package com.controller;

import com.model.ServiceModel;
import com.service.ServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 01.09.13
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ServiceController {
    static Logger log = Logger.getLogger(ServiceController.class.getName());

    @PostConstruct
    public void init() {
        log.info("Inside method ClientsController init");
    }


    public ServiceI getService() {
        return service;
    }

    public void setService(ServiceI service) {
        this.service = service;
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        ServiceController.log = log;
    }

    @Autowired
    private ServiceI service;


    @RequestMapping(method = RequestMethod.GET, value = "/services")
    public
    @ResponseBody
    List<ServiceModel> getServices() {
        return service.getAllServiceList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/services/{id}")
    public
    @ResponseBody
    ServiceModel getService(@PathVariable Integer id) {
        return service.getServiceById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/services/add")
    public
    @ResponseBody
    ServiceModel addService(@RequestBody ServiceModel service_Model_) {
        System.out.println("add service " + service_Model_);
        service.addService(service_Model_);
        return service_Model_;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/services/delete")
    public String deleteClient(@RequestBody ServiceModel service_Model_) {
        System.out.println("delete service " + service_Model_.toString());
        service.deleteService(service_Model_);
        return "redirect:/1";
    }
}
