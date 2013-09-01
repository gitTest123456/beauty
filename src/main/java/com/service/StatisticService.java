package com.service;

import com.model.Statistic;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 01.09.13
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public interface StatisticService {
    public List<Statistic> getStatisticList();

    public Statistic getStatisticById(int serviceId);

    public void deleteStatistic(Statistic statistic);

    public void updateStatistic(Statistic statistic);

    public void addService(Statistic statistic);
}
