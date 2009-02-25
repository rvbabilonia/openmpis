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
import com.googlecode.openmpis.util.Pagination;
import com.googlecode.openmpis.util.SqlMapConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The MessageDAOImpl class implements the MessageDAO interface.
 * This provides the ability to add, delete and retrieve sightings and feedbacks.
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
     * @param id            the ID of the message to be deleted
     * @return              <code>true</code> if the message was successfully deleted; <code>false</code> otherwise
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
     * @param pagination    the pagination context
     * @return              the list of messages
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getAllMessages(Pagination pagination) throws SQLException {
        List<Message> messageList = new ArrayList<Message>();
        
        try {
            sqlMap.startTransaction();
            messageList = sqlMap.queryForList("getAllMessages", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllMessages"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }
        
        return messageList;
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
        List<Message> messageList = new ArrayList<Message>();

        try {
            sqlMap.startTransaction();
            messageList = sqlMap.queryForList("getAllFeedbacks", userId, pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllFeedbacks", userId));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return messageList;
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
        List<Message> messageList = new ArrayList<Message>();

        try {
            sqlMap.startTransaction();
            messageList = sqlMap.queryForList("getAllSightingsForPerson", sighting, pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllSightingsForPerson", sighting));
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
     * @param id            the message ID
     * @return              the message
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
     * Inserts a new sighting.
     * 
     * @param sighting      the new sighting
     * @return              <code>true</code> if the sighting was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertSighting(Message sighting) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.insert("insertSighting", sighting);
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
     * @param message       the new feedback
     * @return              <code>true</code> if the feedback was successfully inserted; <code>false</code> otherwise
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

    /**
     * Returns the total number of feedbacks for the administrator.
     *
     * @param userId        the user ID
     * @return              the total number of feedbacks for the administrator
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllFeedbacks(Integer userId) throws SQLException {
        int feedbackCount = 0;

        try {
            sqlMap.startTransaction();
            feedbackCount = (Integer) sqlMap.queryForObject("countFeedbacks", userId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return feedbackCount;
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
        int newFeedbackCount = 0;

        try {
            sqlMap.startTransaction();
            newFeedbackCount = (Integer) sqlMap.queryForObject("countAllNewFeedbacks", userId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return newFeedbackCount;
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
        int newSightingCount = 0;

        try {
            sqlMap.startTransaction();
            newSightingCount = (Integer) sqlMap.queryForObject("countAllNewSightings", userId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return newSightingCount;
    }

    /**
     * Returns the total number of sightings for a given person.
     *
     * @param userId        the user ID
     * @return              the total number of sightings for a given person
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllSightingsForPerson(Message sighting) throws SQLException {
        int sightingForPersonCount = 0;

        try {
            sqlMap.startTransaction();
            sightingForPersonCount = (Integer) sqlMap.queryForObject("countAllSightingsForPerson", sighting);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return sightingForPersonCount;
    }

    /**
     * Returns the total number of new sightings for a given person.
     *
     * @param userId        the user ID
     * @return              the total number of new sightings for a given person
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllNewSightingsForPerson(Message sighting) throws SQLException {
        int newSightingForPersonCount = 0;

        try {
            sqlMap.startTransaction();
            newSightingForPersonCount = (Integer) sqlMap.queryForObject("countAllNewSightingsForPerson", sighting);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return newSightingForPersonCount;
    }
}