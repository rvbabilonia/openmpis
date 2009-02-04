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
package com.googlecode.openmpis.persistence.ibatis.dao.impl;

import com.googlecode.openmpis.dto.Message;
import com.googlecode.openmpis.persistence.ibatis.dao.MessageDAO;
import com.googlecode.openmpis.util.SqlMapConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.List;

/**
 * The MessageDAOImpl class implements the MessageDAO interface.
 * This provides the ability to add, delete and retrieve messages and feedbacks.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class MessageDAOImpl implements MessageDAO {

    /**
     * The SQL map client
     */
    private SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();

    /**
     * Deletes a message with the specified ID.
     * 
     * @param id    the ID of the message to be deleted
     * @return      <code>true</code> if the message was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deleteMessage(Integer id) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.delete("deleteMessage", id);
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
     * Retrieves all messages.
     * 
     * @return      the list of messages
     * @throws java.sql.SQLException
     */
    @Override
    public List getAllMessages() throws SQLException {
        List messageList = null;
        
        try {
            sqlMap.startTransaction();
            messageList = sqlMap.queryForList("getAllMessages");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }
        
        return messageList;
    }

    /**
     * Retrieves a message given its ID.
     * 
     * @param id    the message ID
     * @return      the message
     * @throws java.sql.SQLException
     */
    @Override
    public Message getMessageById(Integer id) throws SQLException {
        Message message = null;
        
        try {
            sqlMap.startTransaction();
            message = (Message) sqlMap.queryForObject("getMessageById", id);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return message;
    }

    /**
     * Inserts a new message.
     * 
     * @param message   the new message
     * @return          <code>true</code> if the message was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertMessage(Message message) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.insert("insertMessage", message);
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
     * Inserts a new feedback.
     * 
     * @param message   the new feedback
     * @return          <code>true</code> if the feedback was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertFeedback(Message message) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.insert("insertFeedback", message);
            sqlMap.commitTransaction();

            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }
}