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
package com.googlecode.openmpis.persistence.ibatis.service;

import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The UserService interface provides the ability to add, edit, delete, update and
 * retrieve administrators, encoders and investigators.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public interface UserService {

    /**
     * Retrieves all users.
     * 
     * @param pagination    the pagination context
     * @return              the list of users
     * @throws java.sql.SQLException
     */
    public List<User> getAllUsers(Pagination pagination) throws SQLException;

    /**
     * Retrieves a user given his ID.
     * 
     * @param id            the user ID
     * @return              the user
     * @throws java.sql.SQLException
     */
    public User getUserById(Integer id) throws SQLException;

    /**
     * Retrieves a user given his username.
     * 
     * @param username      the username
     * @return              the user
     * @throws java.sql.SQLException
     */
    public User getUserByUsername(String username) throws SQLException;

    /**
     * Inserts a new user.
     * 
     * @param user          the new user
     * @return              <code>true</code> if the user was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean insertUser(User user) throws SQLException;

    /**
     * Updates an existing user.
     * 
     * @param user          the existing user
     * @return              <code>true</code> if the user was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean updateUser(User user) throws SQLException;

    /**
     * Updates a user's last log in date and IP address.
     * 
     * @param user          the user who logged in
     * @return              <code>true</code> if the log in date and IP address were successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean updateLogin(User user) throws SQLException;

    /**
     * Resets a user's password.
     * 
     * @param user          the user who supplied the correct security question and answer
     * @return              <code>true</code> if the password was successfully reset; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean updatePassword(User user) throws SQLException;

    /**
     * Deletes a user with the specified ID.
     * 
     * @param id            the ID of the user to be deleted
     * @return              <code>true</code> if the user was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean deleteUser(Integer id) throws SQLException;

    /**
     * Checks if a username is unique.
     * 
     * @param user          the existing user
     * @return              <code>true</code> if the username is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean isUniqueUsername(User user) throws SQLException;

    /**
     * Checks if an email address is unique.
     * 
     * @param user          the existing user
     * @return              <code>true</code> if the email address is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean isUniqueEmail(User user) throws SQLException;

    /**
     * Returns the total number of users.
     *
     * @return              the total number of users
     * @throws java.sql.SQLException
     */
    public int countAllUsers() throws SQLException;

    /**
     * Returns the total number of administrators.
     *
     * @return              the total number of administrators
     * @throws java.sql.SQLException
     */
    public int countAdministrators() throws SQLException;

    /**
     * Returns the total number of encoders.
     *
     * @return              the total number of encoders
     * @throws java.sql.SQLException
     */
    public int countEncoders() throws SQLException;

    /**
     * Returns the total number of investigators.
     *
     * @return              the total number of investigators
     * @throws java.sql.SQLException
     */
    public int countInvestigators() throws SQLException;

    /**
     * Returns the total number of active users.
     *
     * @return              the total number of active users
     * @throws java.sql.SQLException
     */
    public int countActiveUsers() throws SQLException;

    /**
     * Returns the total number of suspended users.
     *
     * @return              the total number of suspended users
     * @throws java.sql.SQLException
     */
    public int countSuspendedUsers() throws SQLException;
}