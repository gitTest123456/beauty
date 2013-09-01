package com.service;

import com.model.Client;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public interface ClientsService {
    List<Client> getAllClientsList();

    void deleteClient(Client client);

    void addClient(Client newClient);

    Client getClientById(int clientId);
}
