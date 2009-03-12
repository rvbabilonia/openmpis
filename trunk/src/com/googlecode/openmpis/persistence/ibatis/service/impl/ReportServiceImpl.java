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
package com.googlecode.openmpis.persistence.ibatis.service.impl;

import com.googlecode.openmpis.dto.Report;
import com.googlecode.openmpis.persistence.ibatis.dao.ReportDAO;
import com.googlecode.openmpis.persistence.ibatis.service.ReportService;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The ReportServiceImpl interface provides the ability to add, edit, delete, update and
 * retrieve investigator reports.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class ReportServiceImpl implements ReportService {

    /**
     * The report DAO
     */
    private ReportDAO reportDAO = null;

    /**
     * Creates a new report service implementation.
     *
     * @param reportDAO   the report DAO
     */
    public ReportServiceImpl(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    /**
     * Retrieves all reports for a given person.
     *
     * @param pagination    the pagination context
     * @param id            the ID of the person
     * @return              the list of reports for a given person
     * @throws java.sql.SQLException
     */
    @Override
    public List<Report> getAllReportsForPerson(Pagination pagination, Integer id) throws SQLException {
        return reportDAO.getAllReportsForPerson(pagination, id);
    }

    /**
     * Retrieves all reports for a given person.
     *
     * @param id            the ID of the person
     * @return              the list of reports for a given person
     * @throws java.sql.SQLException
     */
    @Override
    public List<Report> listAllReportsForPerson(Integer id) throws SQLException {
        return reportDAO.listAllReportsForPerson(id);
    }

    /**
     * Retrieves all reports written by a given investigator.
     *
     * @param pagination    the pagination context
     * @param investigatorId the ID of the investigator
     * @return              the list of reports written by a given investigator
     * @throws java.sql.SQLException
     */
    @Override
    public List<Report> getAllReportsByInvestigatorId(Pagination pagination, Integer investigatorId) throws SQLException {
        return reportDAO.getAllReportsByInvestigatorId(pagination, investigatorId);
    }

    /**
     * Retrieves a report given its ID.
     * 
     * @param id            the report ID
     * @return              the report
     * @throws java.sql.SQLException
     */
    @Override
    public Report getReportById(Integer id) throws SQLException {
        return reportDAO.getReportById(id);
    }

    /**
     * Inserts a new report.
     * 
     * @param report        the new report
     * @return              <code>true</code> if the report was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertReport(Report report) throws SQLException {
        return reportDAO.insertReport(report);
    }

    /**
     * Updates an existing report.
     * 
     * @param report        the existing report
     * @return              <code>true</code> if the report was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updateReport(Report report) throws SQLException {
        return reportDAO.updateReport(report);
    }

    /**
     * Deletes a report with the specified ID.
     * 
     * @param id            the ID of the report to be deleted
     * @return              <code>true</code> if the report was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deleteReport(Integer id) throws SQLException {
        return reportDAO.deleteReport(id);
    }

    /**
     * Returns the total number of reports for a given person.
     *
     * @param id            the ID of the person
     * @return              the total number of reports for a given person
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllReportsForPerson(Integer id) throws SQLException {
        return reportDAO.countAllReportsForPerson(id);
    }

    /**
     * Returns the total number of reports written by a given investigator.
     *
     * @param investigatorId the ID of the investigator
     * @return              the total number of reports written by a given investigator
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllReportsByInvestigatorId(Integer investigatorId) throws SQLException {
        return reportDAO.countAllReportsByInvestigatorId(investigatorId);
    }
}