package com.service.impl;

import com.dao.ServiceDao;
import com.model.ServiceModel;
import com.service.ServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 01.09.13
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ServiceImpl implements ServiceI {

    @Autowired
    ServiceDao dao;

    public List<ServiceModel> getAllServiceList() {
        return dao.getAllServices();
    }

    public void deleteService(ServiceModel serviceModel) {
        dao.deleteService(serviceModel);
    }

    public void addService(ServiceModel newServiceModel) {
        dao.addService(newServiceModel);
    }

    public ServiceModel getServiceById(int serviceId) {
        return dao.getServiceById(serviceId);
    }
}
