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
}