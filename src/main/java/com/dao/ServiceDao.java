package com.dao;

import com.model.ServiceModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 01.09.13
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public interface ServiceDao {
    public List<ServiceModel> getAllServices();

    public ServiceModel getServiceById(int serviceId);

    public void deleteService(ServiceModel serviceModel);

    public void updateService(ServiceModel serviceModel);

    public void addService(ServiceModel serviceModel);
}
