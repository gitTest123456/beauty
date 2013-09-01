package com.service;

import com.model.Employer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 31.08.13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
public interface EmployerService {
    List<Employer> getAllEmployerList();

    void deleteEmployer(Employer employer);

    void addEmployer(Employer newEmployer);

    Employer getEmployerById(int employerId);
}
