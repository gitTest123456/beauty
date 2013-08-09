package com.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

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
    private Collection<StatisticEntity> statisticsByClientId;

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
    public int hashCode() {
        int result = clientId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "clientsByClientId")
    public Collection<StatisticEntity> getStatisticsByClientId() {
        return statisticsByClientId;
    }

    public void setStatisticsByClientId(Collection<StatisticEntity> statisticsByClientId) {
        this.statisticsByClientId = statisticsByClientId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
