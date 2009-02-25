/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.googlecode.openmpis.persistence.ibatis.service.impl;

import com.googlecode.openmpis.dto.Abductor;
import com.googlecode.openmpis.persistence.ibatis.dao.AbductorDAO;
import com.googlecode.openmpis.persistence.ibatis.service.AbductorService;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The AbductorServiceImpl interface provides the ability to add, edit, delete, update and
 * retrieve abductors.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class AbductorServiceImpl implements AbductorService {

    /**
     * The abductor DAO
     */
    private AbductorDAO abductorDAO = null;

    /**
     * Creates a new abductor service implementation.
     *
     * @param abductorDAO   the abductor DAO
     */
    public AbductorServiceImpl(AbductorDAO abductorDAO) {
        this.abductorDAO = abductorDAO;
    }

    /**
     * Retrieves all abductors.
     *
     * @param pagination    the pagination context
     * @return              the list of abductors
     * @throws java.sql.SQLException
     */
    @Override
    public List<Abductor> getAllAbductors(Pagination pagination) throws SQLException {
        return abductorDAO.getAllAbductors(pagination);
    }

    /**
     * Retrieves an abductor given his ID.
     * 
     * @param id            the abductor ID
     * @return              the abductor
     * @throws java.sql.SQLException
     */
    @Override
    public Abductor getAbductorById(Integer id) throws SQLException {
        return abductorDAO.getAbductorById(id);
    }

    /**
     * Inserts a new abductor.
     * 
     * @param abductor      the new abductor
     * @return              the last index
     * @throws java.sql.SQLException
     */
    @Override
    public int insertAbductor(Abductor abductor) throws SQLException {
        return abductorDAO.insertAbductor(abductor);
    }

    /**
     * Updates an existing abductor.
     * 
     * @param abductor      the existing abductor
     * @return              <code>true</code> if the abductor was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updateAbductor(Abductor abductor) throws SQLException {
        return abductorDAO.updateAbductor(abductor);
    }

    /**
     * Deletes an abductor with the specified ID.
     * 
     * @param id            the ID of the abductor to be deleted
     * @return              <code>true</code> if the abductor was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deleteAbductor(Integer id) throws SQLException {
        return abductorDAO.deleteAbductor(id);
    }

    /**
     * Checks if an abductor is unique.
     *
     * @param abductor      the existing abductor
     * @return              <code>true</code> if the abductor is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isUniqueAbductor(Abductor abductor) throws SQLException {
        return abductorDAO.isUniqueAbductor(abductor);
    }

    /**
     * Returns the total number of abductors.
     *
     * @return              the total number of abductors
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllAbductors() throws SQLException {
        return abductorDAO.countAllAbductors();
    }
}