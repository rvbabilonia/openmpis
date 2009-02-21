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
     * Inserts a new message.
     * 
     * @param message       the new message
     * @return              <code>true</code> if the message was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertMessage(Message message) throws SQLException {
        return messageDAO.insertMessage(message);
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
}