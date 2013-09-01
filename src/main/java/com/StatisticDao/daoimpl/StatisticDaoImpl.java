package com.StatisticDao.daoimpl;

import com.StatisticDao.StatisticDao;
import com.model.Statistic;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 01.09.13
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */
@Service
@Repository
public class StatisticDaoImpl implements StatisticDao {
    @Autowired
    SessionFactory factory;

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public List<Statistic> getStatisticList() {
        return factory.getCurrentSession().createQuery("from com.model.Statistic").list();
    }

    public Statistic getStatisticById(int statisticId) {
        return (Statistic) factory.getCurrentSession().get(Statistic.class, statisticId);
    }

    public void deleteStatistic(Statistic statistic) {
        factory.getCurrentSession().merge(statistic);
    }

    public void updateStatistic(Statistic statistic) {
        factory.getCurrentSession().merge(statistic);
    }

    public void addService(Statistic statistic) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
