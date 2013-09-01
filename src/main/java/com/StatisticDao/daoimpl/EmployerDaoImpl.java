package com.StatisticDao.daoimpl;

import com.StatisticDao.EmployerDao;
import com.model.Employer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 31.08.13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */

@Repository
@Service
public class EmployerDaoImpl implements EmployerDao {
    @Autowired
    SessionFactory sessionFactory;

    Logger logger = Logger.getLogger(EmployerDao.class.getName());

    public List<Employer> getAllEmployers() {
        return sessionFactory.getCurrentSession().createQuery("from com.model.Employer").list();
    }

    public Employer getEmployerById(int employerId) {
        Employer employer = (Employer) sessionFactory.getCurrentSession().get(Employer.class, employerId);
        return employer;
    }

    public void deleteEmployer(Employer employer) {
        sessionFactory.getCurrentSession().delete(employer);
    }

    public void updateEmployer(Employer employer) {
        sessionFactory.getCurrentSession().merge(employer);
    }

    public void addEmployer(Employer employer) {
        sessionFactory.getCurrentSession().merge(employer);
    }
}
