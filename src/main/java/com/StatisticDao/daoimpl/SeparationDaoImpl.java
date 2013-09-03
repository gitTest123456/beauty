package com.StatisticDao.daoimpl;

import com.StatisticDao.SeparationDao;
import com.model.Separation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nkravchenko
 * Date: 9/3/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Repository
public class SeparationDaoImpl implements SeparationDao {
    @Autowired
    SessionFactory sessionFactory;

    public void deleteSeparation(Separation separation) {
        sessionFactory.getCurrentSession().delete(separation);
    }

    public void updateSeparation(Separation separation) {
        sessionFactory.getCurrentSession().merge(separation);
    }

    public void addSeparation(Separation newSeparation) {
        sessionFactory.getCurrentSession().merge(newSeparation);
    }

    public List<Separation> getAllSeparations() {
        return sessionFactory.getCurrentSession().createQuery("from com.model.Separation").list();
    }

    public Separation getSeparationById(int separation) {
        Separation separation1 = (Separation) sessionFactory.getCurrentSession().get(Separation.class, separation);
        return separation1;
    }

}
