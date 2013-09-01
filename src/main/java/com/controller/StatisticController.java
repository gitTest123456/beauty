package com.controller;

import com.model.ServiceModel;
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
        System.out.println("add statistic " + statistic);
        service.addService(statistic);
        return statistic;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/statistics/delete")
    public String deleteClient(@RequestBody Statistic statistic) {
        System.out.println("delete statistic " + statistic.toString());
        service.deleteStatistic(statistic);
        return "redirect:/1";
    }
}
