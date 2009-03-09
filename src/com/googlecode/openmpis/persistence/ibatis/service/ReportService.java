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

import com.googlecode.openmpis.dto.Report;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The ReportService interface provides the ability to add, edit, delete, update and
 * retrieve investigator reports.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public interface ReportService {

    /**
     * Retrieves all reports for a given person.
     *
     * @param pagination    the pagination context
     * @param id            the ID of the person
     * @return              the list of reports for a given person
     * @throws java.sql.SQLException
     */
    public List<Report> getAllReportsForPerson(Pagination pagination, Integer id) throws SQLException;

    /**
     * Retrieves all reports written by a given investigator.
     *
     * @param pagination    the pagination context
     * @param investigatorId the ID of the investigator
     * @return              the list of reports written by a given investigator
     * @throws java.sql.SQLException
     */
    public List<Report> getAllReportsByInvestigator(Pagination pagination, Integer investigatorId) throws SQLException;

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

    /**
     * Returns the total number of reports written by a given investigator.
     *
     * @param investigatorId the ID of the investigator
     * @return              the total number of reports written by a given investigator
     * @throws java.sql.SQLException
     */
    public int countAllReportsByInvestigator(Integer investigatorId) throws SQLException;
}