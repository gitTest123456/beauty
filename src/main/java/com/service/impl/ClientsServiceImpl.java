package com.service.impl;

import com.dao.ClientDao;
import com.model.Client;
import com.service.ClientsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional(rollbackFor = Exception.class)
public class ClientsServiceImpl implements ClientsService {
    static Logger log = Logger.getLogger(ClientsServiceImpl.class.getName());

    @Autowired
    ClientDao serviceDao;

    public List<Client> getAllClientsList() {
        log.info("[DAO] = " + serviceDao);
        return serviceDao.getAllClients();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteClient(Client client) {
        log.info("Remove client " + client.toString());
        serviceDao.deleteClient(client);
    }

    public void addClient(Client newClient) {
        log.info("Add client " + newClient.toString());
        serviceDao.addClient(newClient);
    }
}
