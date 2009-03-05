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
package com.googlecode.openmpis.persistence.ibatis.dao.impl;

import com.googlecode.openmpis.dto.Relative;
import com.googlecode.openmpis.persistence.ibatis.dao.RelativeDAO;
import com.googlecode.openmpis.util.Pagination;
import com.googlecode.openmpis.util.SqlMapConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The RelativeDAOImpl class implements the RelativeDAO interface.
 * This provides the ability to add, edit, delete, update and
 * retrieve relatives.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class RelativeDAOImpl implements RelativeDAO {

    /**
     * The SQL map client
     */
    private SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();

    /**
     * Retrieves all relatives.
     *
     * @param pagination    the pagination context
     * @return              the list of relatives
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Relative> getAllRelatives(Pagination pagination) throws SQLException {
        List<Relative> relativeList = new ArrayList<Relative>();

        try {
            sqlMap.startTransaction();
            relativeList = sqlMap.queryForList("getAllRelatives", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllRelatives"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return relativeList;
    }

    /**
     * Retrieves all relatives according to last name.
     *
     * @return              the list of relatives
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Relative> listAllRelatives() throws SQLException {
        List<Relative> relativeList = new ArrayList<Relative>();

        try {
            sqlMap.startTransaction();
            relativeList = sqlMap.queryForList("listAllRelatives");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return relativeList;
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
        Relative relative = null;

        try {
            sqlMap.startTransaction();
            relative = (Relative) sqlMap.queryForObject("getRelativeById", id);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return relative;
    }

    /**
     * Inserts a new relative.
     * 
     * @param relative      the new relative
     * @return              the last index
     * @throws java.sql.SQLException
     */
    @Override
    public int insertRelative(Relative relative) throws SQLException {
        int index = 0;

        try {
            sqlMap.startTransaction();
            index = (Integer) sqlMap.insert("insertRelative", relative);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return index;
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
        try {
            sqlMap.startTransaction();
            sqlMap.update("updateRelative", relative);
            sqlMap.commitTransaction();

            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
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
        try {
            sqlMap.startTransaction();
            sqlMap.delete("deleteRelative", id);
            sqlMap.commitTransaction();

            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
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
        try {
            sqlMap.startTransaction();
            Relative r = (Relative) sqlMap.queryForObject("checkRelative", relative);
            sqlMap.commitTransaction();

            if (r == null) {
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }

    /**
     * Returns the total number of relatives.
     *
     * @return              the total number of relatives
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllRelatives() throws SQLException {
        int relativeCount = 0;

        try {
            sqlMap.startTransaction();
            relativeCount = (Integer) sqlMap.queryForObject("countAllRelatives");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return relativeCount;
    }
}