package com.controller;

import com.model.Employer;
import com.model.Statistic;
import com.service.StatisticService;
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
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class StatisticController {
    static Logger log = Logger.getLogger(StatisticController.class.getName());

    @Autowired
    private StatisticService service;


    @PostConstruct
    public void init() {
        log.info("Inside method Statistic init");
    }

    public StatisticService getService() {
        return service;
    }

    public void setService(StatisticService service) {
        this.service = service;
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        ServiceController.log = log;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/statistics")
    public
    @ResponseBody
    List<Statistic> getServices() {
        return service.getStatisticList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/statistics/{id}")
    public
    @ResponseBody
    Statistic getService(@PathVariable Integer id) {
        return service.getStatisticById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/statistics/add")
    public
    @ResponseBody
    Statistic addService(@RequestBody Statistic statistic) {
        System.out.println("add statistic " + statistic.getClientByClientId());
        service.addService(statistic);
        return statistic;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/statistics/delete")
    public String deleteStatistic(@RequestBody Statistic statistic) {
        System.out.println("delete statistic " + statistic.getDescription());
        service.deleteStatistic(statistic);
        return "redirect:/1";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/statistics/report")
    public String generateReport(@RequestBody Employer employer) {
        System.out.println("report statistic " + employer.getBirthday());
        service.printReport(employer.getEmployerId());
        return "redirect:/1";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/statistics/dayReport")
    public String generateDayReport(@RequestBody Employer employer) {
        System.out.println("report statistic " + employer.getBirthday());
        service.printDayReport(employer.getEmployerId());
        return "redirect:/1";
    }
}
