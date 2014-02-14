package com.dao;

import com.model.Client;
import com.model.InvestEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 10.08.13
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */

public interface InvestDao {
    public List<InvestEntity> getAllInvest();

    public void deleteInvest(InvestEntity invest);

    public void updateInvest(InvestEntity invest);

    public void addInvest(InvestEntity invest);
}
