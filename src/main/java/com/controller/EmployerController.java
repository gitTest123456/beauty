package com.controller;

import com.model.Employer;
import com.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 31.08.13
 * Time: 21:14
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class EmployerController {
    @Autowired
    EmployerService service;

    private static Logger log = Logger.getLogger(EmployerController.class.getName());

    @PostConstruct
    public void init() {
        log.info("Inside method EmployerController init");
    }

    public EmployerService getService() {
        return service;
    }

    public void setService(EmployerService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/employers")
    public
    @ResponseBody
    List<Employer> getEmployers() {
        return service.getAllEmployerList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employers/{id}")
    public
    @ResponseBody
    Employer getEmployer(@PathVariable Integer id) {
        return service.getEmployerById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/employers/add")
    public
    @ResponseBody
    Employer addEmployer(@RequestBody Employer employer) {
        System.out.println("add employer " + employer);
        service.addEmployer(employer);
        return employer;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/employers/delete")
    public String deleteEmployer(@RequestBody Employer employer) {
        System.out.println("delete employer " + employer.toString());
        service.deleteEmployer(employer);
        return "redirect:/1";
    }

}
