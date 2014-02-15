package com.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 15.02.14
 * Time: 0:24
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

        if (arenda != that.arenda) return false;
        if (commonReqEnv != that.commonReqEnv) return false;
        if (investId != that.investId) return false;
        if (dateReq != null ? !dateReq.equals(that.dateReq) : that.dateReq != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = investId;
        result = 31 * result + arenda;
        result = 31 * result + commonReqEnv;
        result = 31 * result + (dateReq != null ? dateReq.hashCode() : 0);
        return result;
    }
}
