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

import com.googlecode.openmpis.dto.Report;
import com.googlecode.openmpis.persistence.ibatis.dao.ReportDAO;
import com.googlecode.openmpis.util.Pagination;
import com.googlecode.openmpis.util.SqlMapConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The ReportDAOImpl class implements the ReportDAO interface.
 * This provides the ability to add, edit, delete, update and
 * retrieve investigator reports.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class ReportDAOImpl implements ReportDAO {

    /**
     * The SQL map client
     */
    private SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();

    /**
     * Retrieves all reports for a given person.
     *
     * @param pagination    the pagination context
     * @param id            the ID of the person
     * @return              the list of reports for a given person
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Report> getAllReportsForPerson(Pagination pagination, Integer id) throws SQLException {
        List<Report> reportList = new ArrayList<Report>();

        try {
            sqlMap.startTransaction();
            reportList = sqlMap.queryForList("getAllReportsForPerson", id, pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllReportsForPerson", id));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return reportList;
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
    @SuppressWarnings("unchecked")
    public List<Report> listAllReportsForPerson(Integer id) throws SQLException {
        List<Report> reportList = new ArrayList<Report>();

        try {
            sqlMap.startTransaction();
            reportList = sqlMap.queryForList("getAllReportsForPerson", id);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return reportList;
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
    @SuppressWarnings("unchecked")
    public List<Report> getAllReportsByInvestigatorId(Pagination pagination, Integer id) throws SQLException {
        List<Report> reportList = new ArrayList<Report>();

        try {
            sqlMap.startTransaction();
            reportList = sqlMap.queryForList("getAllReportsByInvestigatorId", id, pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllReportsByInvestigatorId", id));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return reportList;
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
        Report report = null;

        try {
            sqlMap.startTransaction();
            report = (Report) sqlMap.queryForObject("getReportById", id);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return report;
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
        try {
            sqlMap.startTransaction();
            sqlMap.insert("insertReport", report);
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
     * Updates an existing report.
     * 
     * @param report        the existing report
     * @return              <code>true</code> if the report was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updateReport(Report report) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.update("updateReport", report);
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
     * Deletes a report with the specified ID.
     * 
     * @param id            the ID of the report to be deleted
     * @return              <code>true</code> if the report was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deleteReport(Integer id) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.delete("deleteReport", id);
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
     * Deletes all reports for a given person.
     *
     * @param personId      the ID of the person
     * @return              <code>true</code> if all reports for a given person were successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deleteReportsForPerson(Integer personId) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.delete("deleteReportsForPerson", personId);
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
     * Returns the total number of reports for a given person.
     *
     * @param id            the ID of the person
     * @return              the total number of reports for a given person
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllReportsForPerson(Integer id) throws SQLException {
        int reportsForPersonCount = 0;

        try {
            sqlMap.startTransaction();
            reportsForPersonCount = (Integer) sqlMap.queryForObject("countAllReportsForPerson", id);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return reportsForPersonCount;
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
        int reportsForPersonCount = 0;

        try {
            sqlMap.startTransaction();
            reportsForPersonCount = (Integer) sqlMap.queryForObject("countAllReportsByInvestigatorId", investigatorId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return reportsForPersonCount;
    }
}