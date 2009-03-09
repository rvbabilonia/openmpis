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
package com.googlecode.openmpis.dto;

/**
 * The Report class is used to represent an investigator's report.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class Report {

    /**
     * The report ID
     */
    private int id;
    /**
     * The person to whom this report is attributed
     */
    private int personId;
    /**
     * The investigator ID
     */
    private int investigatorId;
    /**
     * The investigator's report
     */
    private String report;
    /**
     * The date this report was created
     */
    private String date;

    /**
     * Gets the ID of the report.
     *
     * @return              the ID of the report
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the report.
     *
     * @param id            the ID of the report
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the person.
     *
     * @return              the ID of the person
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * Sets the ID of the person.
     *
     * @param personId      the ID of the person
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * Gets the ID of the investigator.
     *
     * @return              the ID of the investigator
     */
    public int getInvestigatorId() {
        return investigatorId;
    }

    /**
     * Sets the ID of the investigator.
     *
     * @param investigatorId the ID of the investigator
     */
    public void setInvestigatorId(int investigatorId) {
        this.investigatorId = investigatorId;
    }

    /**
     * Gets the investigator's report.
     *
     * @return              the investigator's report
     */
    public String getReport() {
        return report;
    }

    /**
     * Sets the investigator's report.
     *
     * @param report        the investigator's report
     */
    public void setReport(String report) {
        this.report = report;
    }

    /**
     * Gets the date this report was created.
     *
     * @return              the date this report was created
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date this report was created.
     *
     * @param date          the date this report was created
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns a String representation of this data transfer object.
     *
     * @return              the String representation of this data transfer object
     */
    @Override
    public String toString() {
        String content = "";
        content += "ID: " + id;
        content += "Person ID: " + personId;
        content += "Investigator ID: " + investigatorId;
        content += "Date: " + date;
        content += "Report: " + report;
        return content;
    }
}