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
package com.googlecode.openmpis.persistence.ibatis.dao;

import com.googlecode.openmpis.dto.Abductor;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The AbductorDAO interface provides the ability to add, edit, delete, update and
 * retrieve abductors.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public interface AbductorDAO {

    /**
     * Retrieves all abductors.
     *
     * @param pagination    the pagination context
     * @return              the list of abductors
     * @throws java.sql.SQLException
     */
    public List<Abductor> getAllAbductors(Pagination pagination) throws SQLException;

    /**
     * Retrieves an abductor given his ID.
     * 
     * @param id            the abductor ID
     * @return              the abductor
     * @throws java.sql.SQLException
     */
    public Abductor getAbductorById(Integer id) throws SQLException;

    /**
     * Inserts a new abductor.
     * 
     * @param abductor      the new abductor
     * @return              <code>true</code> if the abductor was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean insertAbductor(Abductor abductor) throws SQLException;

    /**
     * Updates an existing abductor.
     * 
     * @param abductor      the existing abductor
     * @return              <code>true</code> if the abductor was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean updateAbductor(Abductor abductor) throws SQLException;

    /**
     * Deletes an abductor with the specified ID.
     * 
     * @param id            the ID of the abductor to be deleted
     * @return              <code>true</code> if the abductor was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean deleteAbductor(Integer id) throws SQLException;

    /**
     * Checks if an abductor is unique.
     *
     * @param abductor      the existing abductor
     * @return              <code>true</code> if the abductor is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean isUniqueAbductor(Abductor abductor) throws SQLException;

    /**
     * Returns the total number of abductors.
     *
     * @return              the total number of abductors
     * @throws java.sql.SQLException
     */
    public int countAllAbductors() throws SQLException;
}