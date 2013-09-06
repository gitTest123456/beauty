package com.service.impl;

import com.dao.EmployerDao;
import com.model.Employer;
import com.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 31.08.13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployerServiceImpl implements EmployerService {
    @Autowired
    EmployerDao employerDao;

    public List<Employer> getAllEmployerList() {
        return employerDao.getAllEmployers();
    }

    public void deleteEmployer(Employer employer) {
        employerDao.deleteEmployer(employer);
    }

    public void addEmployer(Employer newEmployer) {
        employerDao.addEmployer(newEmployer);
    }

    public Employer getEmployerById(int employerId) {
        return employerDao.getEmployerById(employerId);
    }
}
