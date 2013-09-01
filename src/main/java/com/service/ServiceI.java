package com.service;

import com.model.ServiceModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 01.09.13
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */
public interface ServiceI {

    List<ServiceModel> getAllServiceList();

    void deleteService(ServiceModel serviceModel);

    void addService(ServiceModel newServiceModel);

    ServiceModel getServiceById(int serviceId);
}
