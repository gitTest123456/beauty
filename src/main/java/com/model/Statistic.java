package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "statistic", schema = "", catalog = "beauty_salon")
@Entity
public class Statistic {
    private int visitId;
    private Date dateVisit;
    private Time timeVisit;
    private Employer employerByEmployerId;
    private Client clientByClientId;

    @javax.persistence.Column(name = "visit_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    @javax.persistence.Column(name = "date_visit", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getDateVisit() {
        return dateVisit;
    }

    public void setDateVisit(Date dateVisit) {
        this.dateVisit = dateVisit;
    }

    @javax.persistence.Column(name = "time_visit", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    @Basic
    public Time getTimeVisit() {
        return timeVisit;
    }

    public void setTimeVisit(Time timeVisit) {
        this.timeVisit = timeVisit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistic that = (Statistic) o;

        if (visitId != that.visitId) return false;
        if (dateVisit != null ? !dateVisit.equals(that.dateVisit) : that.dateVisit != null) return false;
        if (timeVisit != null ? !timeVisit.equals(that.timeVisit) : that.timeVisit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = visitId;
        result = 31 * result + (dateVisit != null ? dateVisit.hashCode() : 0);
        result = 31 * result + (timeVisit != null ? timeVisit.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JsonBackReference
    @javax.persistence.JoinColumn(name = "employeer_id", referencedColumnName = "employeer_id", nullable = false)
    public Employer getEmployerByEmployerId() {
        return employerByEmployerId;
    }

    public void setEmployerByEmployerId(Employer employerByEmployerId) {
        this.employerByEmployerId = employerByEmployerId;
    }

    @ManyToOne
    @JsonBackReference
    @javax.persistence.JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
    public Client getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(Client clientByClientId) {
        this.clientByClientId = clientByClientId;
    }
}
