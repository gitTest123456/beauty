package com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "separation", schema = "", catalog = "beauty_salon")
@Entity
public class Separation {
    private int separationId;
    private String separationName;
    private Collection<Employer> employers;
    private Collection<ServiceModel> serviceModels;

    @javax.persistence.Column(name = "separation_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getSeparationId() {
        return separationId;
    }

    public void setSeparationId(int separationId) {
        this.separationId = separationId;
    }

    @javax.persistence.Column(name = "separation_name", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    public String getSeparationName() {
        return separationName;
    }

    public void setSeparationName(String separationName) {
        this.separationName = separationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Separation that = (Separation) o;

        if (separationId != that.separationId) return false;
        if (separationName != null ? !separationName.equals(that.separationName) : that.separationName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = separationId;
        result = 31 * result + (separationName != null ? separationName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "separation")
    public Collection<Employer> getEmployersBySeparationId() {
        return employers;
    }

    public void setEmployersBySeparationId(Collection<Employer> employersBySeparationId) {
        this.employers = employers;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "separation_", cascade = CascadeType.ALL)
    @JsonManagedReference
    public Collection<ServiceModel> getServiceModels() {
        return serviceModels;
    }

    public void setServiceModels(Collection<ServiceModel> serviceModels) {
        this.serviceModels = serviceModels;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("separationId", separationId);
            json.put("separationName", separationName);
            json.put("employers", employers);
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return json.toString();
    }
}
