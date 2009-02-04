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

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.persistence.ibatis.dao.LogDAO;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;

import java.sql.SQLException;
import java.util.List;

/**
 * The LogServiceImpl class implements the LogService interface.
 * This provides the ability to add and retrieve logs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class LogServiceImpl implements LogService {

    /**
     * The LogDAO
     */
    private LogDAO logDAO = null;
    
    /**
     * Sole constructor.
     * 
     * @param logDAO   the log DAO
     */
    public LogServiceImpl(LogDAO logDAO) {
        this.logDAO = logDAO;
    }

    /**
     * Retrieves all logs.
     * 
     * @return      the list of logs
     * @throws java.sql.SQLException
     */
    @Override
    public List getAllLogs() throws SQLException {
        return logDAO.getAllLogs();
    }

    /**
     * Retrieves a log given its ID.
     * 
     * @param id    the log ID
     * @return      the log
     * @throws java.sql.SQLException
     */
    @Override
    public Log getLogById(Integer id) throws SQLException {
        return logDAO.getLogById(id);
    }

    /**
     * Inserts a new log.
     * 
     * @param log  the new log
     * @return      <code>true</code> if the log was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertLog(Log log) throws SQLException {
        return logDAO.insertLog(log);
    }
}