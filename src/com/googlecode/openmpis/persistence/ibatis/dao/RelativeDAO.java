/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.googlecode.openmpis.persistence.ibatis.dao;

import com.googlecode.openmpis.dto.Relative;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The RelativeDAO interface provides the ability to add, edit, delete, update and
 * retrieve relatives.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public interface RelativeDAO {

    /**
     * Retrieves all relatives.
     *
     * @param pagination    the pagination context
     * @return              the list of relatives
     * @throws java.sql.SQLException
     */
    public List<Relative> getAllRelatives(Pagination pagination) throws SQLException;

    /**
     * Retrieves all relatives according to last name.
     *
     * @return              the list of relatives
     * @throws java.sql.SQLException
     */
    public List<Relative> listAllRelatives() throws SQLException;

    /**
     * Retrieves a relative given his ID.
     * 
     * @param id            the relative ID
     * @return              the relative
     * @throws java.sql.SQLException
     */
    public Relative getRelativeById(Integer id) throws SQLException;

    /**
     * Inserts a new relative.
     * 
     * @param relative      the new relative
     * @return              the last index
     * @throws java.sql.SQLException
     */
    public int insertRelative(Relative relative) throws SQLException;

    /**
     * Updates an existing relative.
     * 
     * @param relative      the existing relative
     * @return              <code>true</code> if the relative was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean updateRelative(Relative relative) throws SQLException;

    /**
     * Deletes a relative with the specified ID.
     * 
     * @param id            the ID of the relative to be deleted
     * @return              <code>true</code> if the relative was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean deleteRelative(Integer id) throws SQLException;

    /**
     * Checks if a relative is unique.
     * 
     * @param relative      the existing relative
     * @return              <code>true</code> if the relative is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean isUniqueRelative(Relative relative) throws SQLException;

    /**
     * Returns the total number of relatives.
     *
     * @return              the total number of relatives
     * @throws java.sql.SQLException
     */
    public int countAllRelatives() throws SQLException;
}