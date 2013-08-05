package com.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "employeer", schema = "", catalog = "beauty_salon")
@Entity
public class EmployeerEntity {
    private int employeerId;
    private String firstName;
    private String surName;
    private String lastName;
    private String address;
    private String birthday;
    private String telephone;
    private SeparationEntity separationBySeparationId;
    private Collection<StatisticEntity> statisticsByEmployeerId;

    @javax.persistence.Column(name = "employeer_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getEmployeerId() {
        return employeerId;
    }

    public void setEmployeerId(int employeerId) {
        this.employeerId = employeerId;
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

    @javax.persistence.Column(name = "last_name", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @javax.persistence.Column(name = "address", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @javax.persistence.Column(name = "birthday", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @javax.persistence.Column(name = "telephone", nullable = true, insertable = true, updatable = true, length = 12, precision = 0)
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

        EmployeerEntity that = (EmployeerEntity) o;

        if (employeerId != that.employeerId) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (surName != null ? !surName.equals(that.surName) : that.surName != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeerId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @javax.persistence.JoinColumn(name = "separation_id", referencedColumnName = "separation_id", nullable = false)
    public SeparationEntity getSeparationBySeparationId() {
        return separationBySeparationId;
    }

    public void setSeparationBySeparationId(SeparationEntity separationBySeparationId) {
        this.separationBySeparationId = separationBySeparationId;
    }

    @OneToMany(mappedBy = "employeerByEmployeerId")
    public Collection<StatisticEntity> getStatisticsByEmployeerId() {
        return statisticsByEmployeerId;
    }

    public void setStatisticsByEmployeerId(Collection<StatisticEntity> statisticsByEmployeerId) {
        this.statisticsByEmployeerId = statisticsByEmployeerId;
    }
}
