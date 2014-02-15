package com.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 15.02.14
 * Time: 11:37
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "invest", schema = "", catalog = "beauty_salon")
@Entity
public class InvestEntity {
    private Integer investId;
    private Integer arenda;
    private Integer commonReqEnv;
    private Date dateReq;

    @javax.persistence.Column(name = "invest_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    @javax.persistence.Column(name = "arenda", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getArenda() {
        return arenda;
    }

    public void setArenda(Integer arenda) {
        this.arenda = arenda;
    }

    @javax.persistence.Column(name = "common_req_env", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getCommonReqEnv() {
        return commonReqEnv;
    }

    public void setCommonReqEnv(Integer commonReqEnv) {
        this.commonReqEnv = commonReqEnv;
    }

    @javax.persistence.Column(name = "date_req", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getDateReq() {
        return dateReq;
    }

    public void setDateReq(Date dateReq) {
        this.dateReq = dateReq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvestEntity that = (InvestEntity) o;

        if (arenda != null ? !arenda.equals(that.arenda) : that.arenda != null) return false;
        if (commonReqEnv != null ? !commonReqEnv.equals(that.commonReqEnv) : that.commonReqEnv != null) return false;
        if (dateReq != null ? !dateReq.equals(that.dateReq) : that.dateReq != null) return false;
        if (investId != null ? !investId.equals(that.investId) : that.investId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = investId != null ? investId.hashCode() : 0;
        result = 31 * result + (arenda != null ? arenda.hashCode() : 0);
        result = 31 * result + (commonReqEnv != null ? commonReqEnv.hashCode() : 0);
        result = 31 * result + (dateReq != null ? dateReq.hashCode() : 0);
        return result;
    }
}
