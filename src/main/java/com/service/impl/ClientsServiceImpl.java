package com.service.impl;

import com.model.ClientsEntity;
import com.service.ClientsService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ClientsServiceImpl implements ClientsService {
    static Logger log = Logger.getLogger(
            ClientsServiceImpl.class.getName());
    @Transactional
    public List<ClientsEntity> getAllClientsList() {
        log.info("getAllClients: ->");
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
