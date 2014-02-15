package com.service.impl;

import com.dao.InvestDao;
import com.model.InvestEntity;
import com.service.InvestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 04.08.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvestServiceImpl implements InvestService {
    static Logger log = Logger.getLogger(InvestServiceImpl.class.getName());


    @Autowired
    InvestDao investDao;

    public List<InvestEntity> getAllInvest() {
        log.info("[DAO] = " + investDao);
        return investDao.getAllInvest();
    }

    public void deleteInvest(InvestEntity investEntity) {
        log.info("Remove invest " + investEntity.toString());
        investDao.deleteInvest(investEntity);
    }

    public void addInvest(InvestEntity investEntity) {
        investDao.addInvest(investEntity);
    }

    public void printReport() {
        investDao.printReport();
    }

    public void printInvestReport() {
        investDao.printInvestReport();
    }

}
