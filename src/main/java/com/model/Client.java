package com.model;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "clients", schema = "", catalog = "beauty_salon")
@Entity
public class Client {
    private int clientId;
    private String firstName;
    private String surName;
    private String lastName;
    private String telephone;

    public Client(int id, String firstName, String surName, String lastName, String telephone) {
        this.clientId = id;
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.telephone = telephone;
    }

    public Client() {
    }

    @javax.persistence.Column(name = "client_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @javax.persistence.Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @javax.persistence.Column(name = "sur_name", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @javax.persistence.Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @javax.persistence.Column(name = "telephone", nullable = true, insertable = true, updatable = true, length = 13, precision = 0)
    @Basic
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;

        if (clientId != that.clientId) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (surName != null ? !surName.equals(that.surName) : that.surName != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;

        return true;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("clientId", clientId);
            json.put("firstName", firstName);
            json.put("surName", surName);
            json.put("lastName", lastName);
            json.put("telephone", telephone);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return json.toString();
    }
}
