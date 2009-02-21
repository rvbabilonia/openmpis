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

import com.googlecode.openmpis.dto.Report;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The ReportDAO interface provides the ability to add, edit, delete, update and
 * retrieve investigator reports.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public interface ReportDAO {

    /**
     * Retrieves all reports for a given person.
     *
     * @param id            the ID of the person
     * @param pagination    the pagination context
     * @return              the list of reports for a given person
     * @throws java.sql.SQLException
     */
    public List<Report> getAllReportsForPerson(Integer id, Pagination pagination) throws SQLException;

    /**
     * Retrieves a report given its ID.
     * 
     * @param id            the report ID
     * @return              the report
     * @throws java.sql.SQLException
     */
    public Report getReportById(Integer id) throws SQLException;

    /**
     * Inserts a new report.
     * 
     * @param report        the new report
     * @return              <code>true</code> if the report was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean insertReport(Report report) throws SQLException;

    /**
     * Updates an existing report.
     * 
     * @param report        the existing report
     * @return              <code>true</code> if the report was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean updateReport(Report report) throws SQLException;

    /**
     * Deletes a report with the specified ID.
     * 
     * @param id            the ID of the report to be deleted
     * @return              <code>true</code> if the report was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean deleteReport(Integer id) throws SQLException;

    /**
     * Returns the total number of reports for a given person.
     *
     * @param id            the ID of the person
     * @return              the total number of reports for a given person
     * @throws java.sql.SQLException
     */
    public int countAllReportsForPerson(Integer id) throws SQLException;
}