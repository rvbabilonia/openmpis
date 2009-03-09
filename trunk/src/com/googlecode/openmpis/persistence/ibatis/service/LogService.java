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
package com.googlecode.openmpis.persistence.ibatis.service;

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The LogService interface provides the ability to add and retrieve logs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public interface LogService {

    /**
     * Retrieves all logs.
     *
     * @param pagination    the pagination context
     * @return              the list of logs
     * @throws java.sql.SQLException
     */
    public List<Log> getAllLogs(Pagination pagination) throws SQLException;

    /**
     * Retrieves a log given its ID.
     * 
     * @param id            the log ID
     * @return              the log
     * @throws java.sql.SQLException
     */
    public Log getLogById(Integer id) throws SQLException;

    /**
     * Inserts a new log.
     * 
     * @param log           the new log
     * @return              <code>true</code> if the log was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean insertLog(Log log) throws SQLException;
}