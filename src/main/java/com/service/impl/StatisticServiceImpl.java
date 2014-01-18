package com.service.impl;

import com.dao.StatisticDao;
import com.model.Statistic;
import com.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 01.09.13
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    StatisticDao dao;

    public List<Statistic> getStatisticList() {
        return dao.getStatisticList();
    }

    public Statistic getStatisticById(int statisticId) {
        return dao.getStatisticById(statisticId);
    }

    public void deleteStatistic(Statistic statistic) {
        dao.deleteStatistic(statistic);
    }

    public void printReport(Integer empId) {
        dao.printReport(empId);
    }

    public void updateStatistic(Statistic statistic) {
        dao.updateStatistic(statistic);
    }

    public void addService(Statistic statistic) {
        dao.updateStatistic(statistic);
    }
}
