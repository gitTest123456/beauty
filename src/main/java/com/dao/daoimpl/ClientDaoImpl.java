package com.dao.daoimpl;

import com.dao.ClientDao;
import com.model.Client;
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
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static Logger log = Logger.getLogger(ClientDaoImpl.class.getName());

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Client> getAllClients() {
        List<Client> clients = sessionFactory.getCurrentSession().createQuery("from com.model.Client").list();
        return clients;
    }


    public Client getClientById(Long clientId) {
        Client client = (Client) sessionFactory.getCurrentSession().get(Client.class, clientId);
        return client;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteClient(Client client) {
        sessionFactory.getCurrentSession().delete(client);
    }


    public void updateClient(Client client) {
        sessionFactory.getCurrentSession().merge(client);
    }

    public void addClient(Client client) {
        sessionFactory.getCurrentSession().persist(client);
    }
}
