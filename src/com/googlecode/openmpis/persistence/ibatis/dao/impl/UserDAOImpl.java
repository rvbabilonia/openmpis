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

import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.persistence.ibatis.dao.UserDAO;
import com.googlecode.openmpis.util.Pagination;
import com.googlecode.openmpis.util.SqlMapConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The UserDAOImpl class implements the UserDAO interface.
 * This provides the ability to add, edit, delete, update and
 * retrieve administrators, encoders and investigators.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class UserDAOImpl implements UserDAO {

    /**
     * The SQL map client
     */
    private SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();

    /**
     * Deletes a user with the specified ID.
     * 
     * @param id            the ID of the user to be deleted
     * @return              <code>true</code> if the user was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deleteUser(Integer id) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.delete("deleteUser", id);
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
     * Retrieves all users.
     *
     * @param pagination    the pagination context
     * @return              the list of users
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers(Pagination pagination) throws SQLException {
        List<User> userList = new ArrayList<User>();
        
        try {
            sqlMap.startTransaction();
            userList = sqlMap.queryForList("getAllUsers", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllUsers"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }
        
        return userList;
    }

    /**
     * Retrieves all investigators according to agency then last name.
     *
     * @return              the list of investigators
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listInvestigators() throws SQLException {
        List<User> investigatorList = new ArrayList<User>();

        try {
            sqlMap.startTransaction();
            investigatorList = sqlMap.queryForList("getInvestigators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorList;
    }

    /**
     * Retrieves all active investigators according to agency then last name.
     *
     * @return              the list of active investigators
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listActiveInvestigators() throws SQLException {
        List<User> investigatorList = new ArrayList<User>();

        try {
            sqlMap.startTransaction();
            investigatorList = sqlMap.queryForList("getActiveInvestigators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorList;
    }

    /**
     * Retrieves all suspended investigators according to agency then last name.
     *
     * @return              the list of suspended investigators
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listSuspendedInvestigators() throws SQLException {
        List<User> investigatorList = new ArrayList<User>();

        try {
            sqlMap.startTransaction();
            investigatorList = sqlMap.queryForList("getSuspendedInvestigators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorList;
    }

    /**
     * Retrieves all administrators according to agency then last name.
     *
     * @return              the list of administrators
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listAdministrators() throws SQLException {
        List<User> investigatorList = new ArrayList<User>();

        try {
            sqlMap.startTransaction();
            investigatorList = sqlMap.queryForList("getAdministrators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorList;
    }

    /**
     * Retrieves all active administrators according to agency then last name.
     *
     * @return              the list of active administrators
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listActiveAdministrators() throws SQLException {
        List<User> investigatorList = new ArrayList<User>();

        try {
            sqlMap.startTransaction();
            investigatorList = sqlMap.queryForList("getActiveAdministrators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorList;
    }

    /**
     * Retrieves all suspended administrators according to agency then last name.
     *
     * @return              the list of suspended administrators
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listSuspendedAdministrators() throws SQLException {
        List<User> investigatorList = new ArrayList<User>();

        try {
            sqlMap.startTransaction();
            investigatorList = sqlMap.queryForList("getSuspendedAdministrators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorList;
    }

    /**
     * Retrieves all encoders according to agency then last name.
     *
     * @return              the list of encoders
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listEncoders() throws SQLException {
        List<User> investigatorList = new ArrayList<User>();

        try {
            sqlMap.startTransaction();
            investigatorList = sqlMap.queryForList("getEncoders");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorList;
    }

    /**
     * Retrieves all active encoders according to agency then last name.
     *
     * @return              the list of active encoders
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listActiveEncoders() throws SQLException {
        List<User> investigatorList = new ArrayList<User>();

        try {
            sqlMap.startTransaction();
            investigatorList = sqlMap.queryForList("getActiveEncoders");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorList;
    }

    /**
     * Retrieves all suspended encoders according to agency then last name.
     *
     * @return              the list of suspended encoders
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listSuspendedEncoders() throws SQLException {
        List<User> investigatorList = new ArrayList<User>();

        try {
            sqlMap.startTransaction();
            investigatorList = sqlMap.queryForList("getSuspendedEncoders");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorList;
    }

    /**
     * Retrieves a user given his ID.
     * 
     * @param id            the user ID
     * @return              the user
     * @throws java.sql.SQLException
     */
    @Override
    public User getUserById(Integer id) throws SQLException {
        User user = null;
        
        try {
            sqlMap.startTransaction();
            user = (User) sqlMap.queryForObject("getUserById", id);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return user;
    }

    /**
     * Retrieves a user given his username.
     * 
     * @param username      the username
     * @return              the user
     * @throws java.sql.SQLException
     */
    @Override
    public User getUserByUsername(String username) throws SQLException {
        User user = null;
        
        try {
            sqlMap.startTransaction();
            user = (User) sqlMap.queryForObject("getUserByUsername", username);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return user;
    }

    /**
     * Inserts a new user.
     * 
     * @param user          the new user
     * @return              <code>true</code> if the user was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertUser(User user) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.insert("insertUser", user);
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
     * Checks if an email address is unique.
     * 
     * @param user          the existing user
     * @return              <code>true</code> if the email address is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isUniqueEmail(User user) throws SQLException {
        try {
            sqlMap.startTransaction();
            User u = (User) sqlMap.queryForObject("checkEmail", user);
            sqlMap.commitTransaction();

            if (u == null) {
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }

    /**
     * Checks if a username is unique.
     * 
     * @param user          the existing user
     * @return              <code>true</code> if the username is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isUniqueUsername(User user) throws SQLException {
        try {
            sqlMap.startTransaction();
            User u = (User) sqlMap.queryForObject("checkUsername", user);
            sqlMap.commitTransaction();

            if (u == null) {
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }

    /**
     * Updates a user's last log in date and IP address.
     * 
     * @param user          the user who logged in
     * @return              <code>true</code> if the log in date and IP address were successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updateLogin(User user) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.update("updateLogin", user);
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
     * Resets a user's password.
     * 
     * @param user          the user who supplied the correct security question and answer
     * @return              <code>true</code> if the password was successfully reset; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updatePassword(User user) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.update("updatePassword", user);
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
     * Updates an existing user.
     * 
     * @param user          the existing user
     * @return              <code>true</code> if the user was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updateUser(User user) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.update("updateUser", user);
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
     * Returns the total number of users.
     *
     * @return              the total number of users
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllUsers() throws SQLException {
        int userCount = 0;

        try {
            sqlMap.startTransaction();
            userCount = (Integer) sqlMap.queryForObject("countAllUsers");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return userCount;
    }

    /**
     * Returns the total number of administrators.
     *
     * @return              the total number of administrators
     * @throws java.sql.SQLException
     */
    @Override
    public int countAdministrators() throws SQLException {
        int userCount = 0;

        try {
            sqlMap.startTransaction();
            userCount = (Integer) sqlMap.queryForObject("countAdministrators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return userCount;
    }

    /**
     * Returns the total number of active administrators.
     *
     * @return              the total number of active administrators
     * @throws java.sql.SQLException
     */
    @Override
    public int countActiveAdministrators() throws SQLException {
        int userCount = 0;

        try {
            sqlMap.startTransaction();
            userCount = (Integer) sqlMap.queryForObject("countActiveAdministrators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return userCount;
    }

    /**
     * Returns the total number of suspended administrators.
     *
     * @return              the total number of suspended administrators
     * @throws java.sql.SQLException
     */
    @Override
    public int countSuspendedAdministrators() throws SQLException {
        int userCount = 0;

        try {
            sqlMap.startTransaction();
            userCount = (Integer) sqlMap.queryForObject("countSuspendedAdministrators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return userCount;
    }

    /**
     * Returns the total number of encoders.
     *
     * @return              the total number of encoders
     * @throws java.sql.SQLException
     */
    @Override
    public int countEncoders() throws SQLException {
        int encoderCount = 0;

        try {
            sqlMap.startTransaction();
            encoderCount = (Integer) sqlMap.queryForObject("countEncoders");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return encoderCount;
    }

    /**
     * Returns the total number of active encoders.
     *
     * @return              the total number of active encoders
     * @throws java.sql.SQLException
     */
    @Override
    public int countActiveEncoders() throws SQLException {
        int encoderCount = 0;

        try {
            sqlMap.startTransaction();
            encoderCount = (Integer) sqlMap.queryForObject("countActiveEncoders");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return encoderCount;
    }

    /**
     * Returns the total number of suspended encoders.
     *
     * @return              the total number of suspended encoders
     * @throws java.sql.SQLException
     */
    @Override
    public int countSuspendedEncoders() throws SQLException {
        int encoderCount = 0;

        try {
            sqlMap.startTransaction();
            encoderCount = (Integer) sqlMap.queryForObject("countSuspendedEncoders");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return encoderCount;
    }

    /**
     * Returns the total number of investigators.
     *
     * @return              the total number of investigators
     * @throws java.sql.SQLException
     */
    @Override
    public int countInvestigators() throws SQLException {
        int investigatorCount = 0;

        try {
            sqlMap.startTransaction();
            investigatorCount = (Integer) sqlMap.queryForObject("countInvestigators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorCount;
    }

    /**
     * Returns the total number of active investigators.
     *
     * @return              the total number of active investigators
     * @throws java.sql.SQLException
     */
    @Override
    public int countActiveInvestigators() throws SQLException {
        int investigatorCount = 0;

        try {
            sqlMap.startTransaction();
            investigatorCount = (Integer) sqlMap.queryForObject("countActiveInvestigators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorCount;
    }

    /**
     * Returns the total number of suspended investigators.
     *
     * @return              the total number of suspended investigators
     * @throws java.sql.SQLException
     */
    @Override
    public int countSuspendedInvestigators() throws SQLException {
        int investigatorCount = 0;

        try {
            sqlMap.startTransaction();
            investigatorCount = (Integer) sqlMap.queryForObject("countSuspendedInvestigators");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return investigatorCount;
    }

    /**
     * Returns the total number of active users.
     *
     * @return              the total number of active users
     * @throws java.sql.SQLException
     */
    @Override
    public int countActiveUsers() throws SQLException {
        int activeCount = 0;

        try {
            sqlMap.startTransaction();
            activeCount = (Integer) sqlMap.queryForObject("countActiveUsers");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return activeCount;
    }

    /**
     * Returns the total number of suspended users.
     *
     * @return              the total number of suspended users
     * @throws java.sql.SQLException
     */
    @Override
    public int countSuspendedUsers() throws SQLException {
        int suspendedCount = 0;

        try {
            sqlMap.startTransaction();
            suspendedCount = (Integer) sqlMap.queryForObject("countSuspendedUsers");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return suspendedCount;
    }

    /**
     * Returns the total number of users encoded by a given user.
     *
     * @param creatorId     the user ID
     * @return              the total number of users encoded by a given user
     * @throws java.sql.SQLException
     */
    @Override
    public int countEncodedUsers(Integer creatorId) throws SQLException {
        int encodedCount = 0;

        try {
            sqlMap.startTransaction();
            encodedCount = (Integer) sqlMap.queryForObject("countEncodedUsers", creatorId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return encodedCount;
    }
}