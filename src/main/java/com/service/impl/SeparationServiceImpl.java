package com.service.impl;

import com.StatisticDao.SeparationDao;
import com.model.Separation;
import com.service.SeparationI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: nkravchenko
 * Date: 9/3/13
 * Time: 1:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SeparationServiceImpl implements SeparationI {
    @Autowired
    SeparationDao separationDao;

    public void deleteSeparation(Separation separation) {
        separationDao.deleteSeparation(separation);
    }

    public void addSeparation(Separation newSeparation) {
        separationDao.addSeparation(newSeparation);
    }

    public Separation getSeparationById(int separation) {
        return separationDao.getSeparationById(separation);
    }

    public Collection<Separation> getListSeparation() {
        return separationDao.getAllSeparations();
    }
}
