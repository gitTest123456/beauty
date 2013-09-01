package com.dao.daoimpl;

import com.dao.ServiceDao;
import com.model.ServiceModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 01.09.13
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
@Repository
@Service
public class ServiceDaoImpl implements ServiceDao {
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    SessionFactory sessionFactory;

    public List<ServiceModel> getAllServices() {
        return sessionFactory.getCurrentSession().createQuery("from com.model.ServiceModel").list();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ServiceModel getServiceById(int serviceId) {
        return (ServiceModel) sessionFactory.getCurrentSession().get(ServiceModel.class, serviceId);
    }

    public void deleteService(ServiceModel serviceModel) {
        sessionFactory.getCurrentSession().delete(serviceModel);
    }

    public void updateService(ServiceModel serviceModel) {
        sessionFactory.getCurrentSession().merge(serviceModel);
    }

    public void addService(ServiceModel serviceModel) {
        sessionFactory.getCurrentSession().merge(serviceModel);
    }
}
