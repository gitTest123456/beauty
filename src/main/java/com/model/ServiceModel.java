package com.model;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "service", schema = "", catalog = "beauty_salon")
@Entity
public class ServiceModel {
    private int serviceId;
    private String naming;
    private Date data;
    private int cost;
    private Separation separation_;


    @javax.persistence.Column(name = "service_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @javax.persistence.Column(name = "naming", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    public String getNaming() {
        return naming;
    }

    public void setNaming(String naming) {
        this.naming = naming;
    }

    @javax.persistence.Column(name = "data", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @javax.persistence.Column(name = "cost", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceModel that = (ServiceModel) o;

        if (cost != that.cost) return false;
        if (serviceId != that.serviceId) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (naming != null ? !naming.equals(that.naming) : that.naming != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceId;
        result = 31 * result + (naming != null ? naming.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }

    @ManyToOne
    @javax.persistence.JoinColumn(name = "separation_id", referencedColumnName = "separation_id", nullable = false)
    public Separation getSeparation_() {
        return separation_;
    }

    public void setSeparation_(Separation separation_) {
        this.separation_ = separation_;
    }


    @Override
    public String toString() {

        JSONObject json = new JSONObject();
        try {
            json.put("serviceId", serviceId);
            json.put("naming", naming);
            json.put("data", data);
            json.put("cost", cost);
            json.put("separation_", separation_);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return json.toString();
    }
}
