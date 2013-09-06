package com.dao;

import com.model.Separation;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nkravchenko
 * Date: 9/3/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SeparationDao {
    public List<Separation> getAllSeparations();

    public Separation getSeparationById(int separationId);

    public void deleteSeparation(Separation separation);

    public void updateSeparation(Separation separation);

    public void addSeparation(Separation separation);
}
