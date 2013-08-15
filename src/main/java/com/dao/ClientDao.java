package com.dao;

import com.model.Client;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 10.08.13
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */

public interface ClientDao {
    public List<Client> getAllClients();

    public Client getClientById(int clientId);

    public void deleteClient(Client client);

    public void updateClient(Client client);

    public void addClient(Client client);
}
