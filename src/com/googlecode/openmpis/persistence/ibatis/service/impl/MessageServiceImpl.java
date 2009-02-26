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

import com.googlecode.openmpis.dto.Message;
import com.googlecode.openmpis.persistence.ibatis.dao.MessageDAO;
import com.googlecode.openmpis.persistence.ibatis.service.MessageService;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The MessageServiceImpl class implements the MessageService interface.
 * This provides the ability to add, delete and retrieve sightings and feedbacks.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class MessageServiceImpl implements MessageService {

    /**
     * The message DAO
     */
    private MessageDAO messageDAO = null;
    
    /**
     * Creates a new message service implementation.
     * 
     * @param messageDAO   the message DAO
     */
    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    /**
     * Deletes a message with the specified ID.
     * 
     * @param id            the ID of the message to be deleted
     * @return              <code>true</code> if the message was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deleteMessage(Integer id) throws SQLException {
        return messageDAO.deleteMessage(id);
    }

    /**
     * Retrieves all messages.
     *
     * @param pagination    the pagination context
     * @return              the list of messages
     * @throws java.sql.SQLException
     */
    @Override
    public List<Message> getAllMessages(Pagination pagination) throws SQLException {
        return messageDAO.getAllMessages(pagination);
    }

    /**
     * Retrieves all feedbacks.
     *
     * @param pagination    the pagination context
     * @param userId        the user ID
     * @return              the list of feedbacks
     * @throws java.sql.SQLException
     */
    @Override
    public List<Message> getAllFeedbacks(Pagination pagination, Integer userId) throws SQLException {
        return messageDAO.getAllFeedbacks(pagination, userId);
    }

    /**
     * Retrieves all sightings for a given person.
     *
     * @param pagination    the pagination context
     * @param sighting      the sighting
     * @return              the list of sightings for a given person
     * @throws java.sql.SQLException
     */
    @Override
    public List<Message> getAllSightingsForPerson(Pagination pagination, Message sighting) throws SQLException {
        return messageDAO.getAllSightingsForPerson(pagination, sighting);
    }

    /**
     * Retrieves a message given his ID.
     * 
     * @param id            the message ID
     * @return              the message
     * @throws java.sql.SQLException
     */
    @Override
    public Message getMessageById(Integer id) throws SQLException {
        return messageDAO.getMessageById(id);
    }

    /**
     * Inserts a new sighting.
     * 
     * @param sighting      the new sighting
     * @return              <code>true</code> if the sighting was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertSighting(Message sighting) throws SQLException {
        return messageDAO.insertSighting(sighting);
    }

    /**
     * Inserts a new feedback.
     * 
     * @param message       the new feedback
     * @return              <code>true</code> if the message was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertFeedback(Message message) throws SQLException {
        return messageDAO.insertFeedback(message);
    }

    /**
     * Returns the total number of feedbacks for the administrator.
     *
     * @param userId        the user ID
     * @return              the total number of feedbacks for the administrator
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllFeedbacks(Integer userId) throws SQLException {
        return messageDAO.countAllFeedbacks(userId);
    }

    /**
     * Returns the total number of new feedbacks for the administrator.
     *
     * @param userId        the user ID
     * @return              the total number of new feedbacks for the administrator
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllNewFeedbacks(Integer userId) throws SQLException {
        return messageDAO.countAllNewFeedbacks(userId);
    }

    /**
     * Returns the total number of new sightings attributed to the investigator.
     *
     * @param userId        the user ID
     * @return              the total number of new sightings attributed to the investigator
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllNewSightings(Integer userId) throws SQLException {
        return messageDAO.countAllNewSightings(userId);
    }

    /**
     * Returns the total number of sightings for a given person.
     *
     * @param sighting      the sighting
     * @return              the total number of sightings for a given person
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllSightingsForPerson(Message sighting) throws SQLException {
        return messageDAO.countAllSightingsForPerson(sighting);
    }

    /**
     * Returns the total number of new sightings for a given person.
     *
     * @param sighting      the sighting
     * @return              the total number of new sightings for a given person
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllNewSightingsForPerson(Message sighting) throws SQLException {
        return messageDAO.countAllNewSightingsForPerson(sighting);
    }
}