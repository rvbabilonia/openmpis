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
package com.googlecode.openmpis.persistence.ibatis.dao;

import com.googlecode.openmpis.dto.Message;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The MessageDAO interface provides the ability to add, delete and
 * retrieve sightings and feedbacks.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public interface MessageDAO {

    /**
     * Retrieves all messages.
     *
     * @param pagination    the pagination context
     * @return              the list of messages
     * @throws java.sql.SQLException
     */
    public List<Message> getAllMessages(Pagination pagination) throws SQLException;

    /**
     * Retrieves all feedbacks.
     *
     * @param pagination    the pagination context
     * @param userId        the user ID
     * @return              the list of feedbacks
     * @throws java.sql.SQLException
     */
    public List<Message> getAllFeedbacks(Pagination pagination, Integer userId) throws SQLException;

    /**
     * Retrieves all sightings for a given person.
     *
     * @param pagination    the pagination context
     * @param sighting      the sighting
     * @return              the list of sightings for a given person
     * @throws java.sql.SQLException
     */
    public List<Message> getAllSightingsForPerson(Pagination pagination, Message sighting) throws SQLException;

    /**
     * Retrieves a message given his ID and sets its status to read.
     * 
     * @param id            the message ID
     * @return              the message
     * @throws java.sql.SQLException
     */
    public Message getMessageById(Integer id) throws SQLException;

    /**
     * Inserts a new sighting.
     * 
     * @param sighting      the new sighting
     * @return              <code>true</code> if the sighting was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean insertSighting(Message sighting) throws SQLException;

    /**
     * Inserts a new feedback.
     * 
     * @param feedback      the new feedback
     * @return              <code>true</code> if the feedback was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean insertFeedback(Message feedback) throws SQLException;

    /**
     * Deletes a message with the specified ID.
     * 
     * @param id            the ID of the message to be deleted
     * @return              <code>true</code> if the message was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean deleteMessage(Integer id) throws SQLException;

    /**
     * Returns the total number of feedbacks for the administrator.
     *
     * @param userId        the user ID
     * @return              the total number of feedbacks for the administrator
     * @throws java.sql.SQLException
     */
    public int countAllFeedbacks(Integer userId) throws SQLException;

    /**
     * Returns the total number of new feedbacks for the administrator.
     *
     * @param userId        the user ID
     * @return              the total number of new feedbacks for the administrator
     * @throws java.sql.SQLException
     */
    public int countAllNewFeedbacks(Integer userId) throws SQLException;

    /**
     * Returns the total number of new sightings attributed to the investigator.
     *
     * @param userId        the user ID
     * @return              the total number of new sightings attributed to the investigator
     * @throws java.sql.SQLException
     */
    public int countAllNewSightings(Integer userId) throws SQLException;

    /**
     * Returns the total number of sightings for a given person.
     *
     * @param sighting      the sighting
     * @return              the total number of sightings for a given person
     * @throws java.sql.SQLException
     */
    public int countAllSightingsForPerson(Message sighting) throws SQLException;

    /**
     * Returns the total number of new sightings for a given person.
     *
     * @param sighting      the sighting
     * @return              the total number of new sightings for a given person
     * @throws java.sql.SQLException
     */
    public int countAllNewSightingsForPerson(Message sighting) throws SQLException;
}