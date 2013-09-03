package com.service;

import com.model.Separation;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: nkravchenko
 * Date: 9/3/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SeparationI {

    void deleteSeparation(Separation separation);

    void addSeparation(Separation newSeparation);

    Separation getSeparationById(int separation);

    Collection<Separation> getListSeparation();
}
