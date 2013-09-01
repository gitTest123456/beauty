package com.dao;

import com.model.Employer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 31.08.13
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public interface EmployerDao {
    public List<Employer> getAllEmployers();

    public Employer getEmployerById(int employerId);

    public void deleteEmployer(Employer employer);

    public void updateEmployer(Employer employer);

    public void addEmployer(Employer employer);
}
