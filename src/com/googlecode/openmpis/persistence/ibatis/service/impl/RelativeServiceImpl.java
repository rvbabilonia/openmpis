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

import com.googlecode.openmpis.dto.Relative;
import com.googlecode.openmpis.persistence.ibatis.dao.RelativeDAO;
import com.googlecode.openmpis.persistence.ibatis.service.RelativeService;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The RelativeServiceImpl interface provides the ability to add, edit, delete, update and
 * retrieve relatives.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class RelativeServiceImpl implements RelativeService {

    /**
     * The relative DAO
     */
    private RelativeDAO relativeDAO = null;

    /**
     * Creates a new relative service implementation.
     *
     * @param relativeDAO   the relative DAO
     */
    public RelativeServiceImpl(RelativeDAO relativeDAO) {
        this.relativeDAO = relativeDAO;
    }

    /**
     * Retrieves all relatives.
     *
     * @param pagination    the pagination context
     * @return              the list of relatives
     * @throws java.sql.SQLException
     */
    @Override
    public List<Relative> getAllRelatives(Pagination pagination) throws SQLException {
        return relativeDAO.getAllRelatives(pagination);
    }

    /**
     * Retrieves a relative given his ID.
     * 
     * @param id            the relative ID
     * @return              the relative
     * @throws java.sql.SQLException
     */
    @Override
    public Relative getRelativeById(Integer id) throws SQLException {
        return relativeDAO.getRelativeById(id);
    }

    /**
     * Inserts a new relative.
     * 
     * @param relative      the new relative
     * @return              <code>true</code> if the relative was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertRelative(Relative relative) throws SQLException {
        return relativeDAO.insertRelative(relative);
    }

    /**
     * Updates an existing relative.
     * 
     * @param relative      the existing relative
     * @return              <code>true</code> if the relative was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updateRelative(Relative relative) throws SQLException {
        return relativeDAO.updateRelative(relative);
    }

    /**
     * Deletes a relative with the specified ID.
     * 
     * @param id            the ID of the relative to be deleted
     * @return              <code>true</code> if the relative was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deleteRelative(Integer id) throws SQLException {
        return relativeDAO.deleteRelative(id);
    }

    /**
     * Checks if a relative is unique.
     * 
     * @param relative      the existing relative
     * @return              <code>true</code> if the relative is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isUniqueRelative(Relative relative) throws SQLException {
        return relativeDAO.isUniqueRelative(relative);
    }

    /**
     * Returns the total number of relatives.
     *
     * @return              the total number of relatives
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllRelatives() throws SQLException {
        return relativeDAO.countAllRelatives();
    }
}