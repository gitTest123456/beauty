package com.dao;

import com.model.Statistic;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 01.09.13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public interface StatisticDao {
    public List<Statistic> getStatisticList();

    public Statistic getStatisticById(int serviceId);

    public void deleteStatistic(Statistic statistic);

    public void updateStatistic(Statistic statistic);

    public void addService(Statistic statistic);

    public void printReport(Integer empId);

    public void printDayReport(Integer empId);
}
