package com.service;

import com.model.Client;
import com.model.InvestEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public interface InvestService {
    public List<InvestEntity> getAllInvest();

    public void deleteInvest(InvestEntity invest);

    public void addInvest(InvestEntity invest);

    void printReport(int investId);

    void printInvestReport(int investId);
}
