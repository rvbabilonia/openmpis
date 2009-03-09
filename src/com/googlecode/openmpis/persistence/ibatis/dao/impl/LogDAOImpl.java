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
package com.googlecode.openmpis.persistence.ibatis.dao.impl;

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.persistence.ibatis.dao.LogDAO;
import com.googlecode.openmpis.util.Pagination;
import com.googlecode.openmpis.util.SqlMapConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The LogDAOImpl class implements the LogDAO interface.
 * This provides the ability to add and retrieve logs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class LogDAOImpl implements LogDAO {

    /**
     * The SQL map client
     */
    private SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();

    /**
     * Retrieves all logs.
     *
     * @param pagination    the pagination context
     * @return              the list of logs
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Log> getAllLogs(Pagination pagination) throws SQLException {
        List<Log> logList = new ArrayList<Log>();
        
        try {
            sqlMap.startTransaction();
            logList = sqlMap.queryForList("getAllLogs", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllLogs"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }
        
        return logList;
    }

    /**
     * Retrieves a log given its ID.
     * 
     * @param id            the log ID
     * @return              the log
     * @throws java.sql.SQLException
     */
    @Override
    public Log getLogById(Integer id) throws SQLException {
        Log log = null;
        
        try {
            sqlMap.startTransaction();
            log = (Log) sqlMap.queryForObject("getLogById", id);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return log;
    }

    /**
     * Inserts a new log.
     * 
     * @param log           the new log
     * @return              <code>true</code> if the log was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertLog(Log log) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.insert("insertLog", log);
            sqlMap.commitTransaction();

            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }
}