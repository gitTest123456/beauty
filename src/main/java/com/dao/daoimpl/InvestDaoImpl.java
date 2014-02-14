package com.dao.daoimpl;

import com.dao.ClientDao;
import com.dao.InvestDao;
import com.model.Client;
import com.model.InvestEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 10.08.13
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
@Repository
@Service
public class InvestDaoImpl implements InvestDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static Logger log = Logger.getLogger(InvestDaoImpl.class.getName());

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<InvestEntity> getAllInvest() {
        List<InvestEntity> invest = sessionFactory.getCurrentSession().createQuery("from com.model.InvestEntity").list();
        return invest;
    }


    public void deleteInvest(InvestEntity investEntity) {
        sessionFactory.getCurrentSession().delete(investEntity);
    }


    public void updateInvest(InvestEntity investEntity) {
        sessionFactory.getCurrentSession().merge(investEntity);
    }

    public void addInvest(InvestEntity invest) {
        sessionFactory.getCurrentSession().merge(invest);
    }


}
