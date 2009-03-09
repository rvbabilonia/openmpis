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
 * The Log class is used to represent events pertaining to the system's operations.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class Log {

    /**
     * The log ID
     */
    private int id;
    /**
     * The logged event
     */
    private String log;
    /**
     * The date this log was created
     */
    private String date;

    /**
     * Gets the log ID.
     * 
     * @return              the ID of the log
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the log ID.
     * 
     * @param id            the ID of the log
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the logged event.
     * 
     * @return              the logged event
     */
    public String getLog() {
        return log;
    }

    /**
     * Sets the logged event.
     * 
     * @param log           the logged event
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * Gets the date this log was created.
     * 
     * @return              the date this log was created
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date this log was created.
     * 
     * @param date          the date this log was created
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
        content += "\nLog: " + log;
        content += "\nDate: " + date;
        return content;
    }
}