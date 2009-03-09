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
package com.googlecode.openmpis.persistence.ibatis.service.impl;

import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.persistence.ibatis.dao.UserDAO;
import com.googlecode.openmpis.persistence.ibatis.service.UserService;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The UserServiceImpl class implements the UserService interface.
 * This provides the ability to add, edit, delete, update and
 * retrieve administrators, encoders and investigators.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    /**
     * The user DAO
     */
    private UserDAO userDAO = null;

    /**
     * Creates a new user service implementation.
     * 
     * @param userDAO       the user DAO
     */
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Deletes a user with the specified ID.
     * 
     * @param id            the ID of the user to be deleted
     * @return              <code>true</code> if the user was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deleteUser(Integer id) throws SQLException {
        return userDAO.deleteUser(id);
    }

    /**
     * Retrieves all users.
     *
     * @param pagination    the pagination context
     * @return              the list of users
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> getAllUsers(Pagination pagination) throws SQLException {
        return userDAO.getAllUsers(pagination);
    }

    /**
     * Retrieves all investigators according to agency then last name.
     *
     * @return              the list of investigators
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> listInvestigators() throws SQLException {
        return userDAO.listInvestigators();
    }

    /**
     * Retrieves all active investigators according to agency then last name.
     *
     * @return              the list of active investigators
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> listActiveInvestigators() throws SQLException {
        return userDAO.listActiveInvestigators();
    }

    /**
     * Retrieves all suspended investigators according to agency then last name.
     *
     * @return              the list of suspended investigators
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> listSuspendedInvestigators() throws SQLException {
        return userDAO.listSuspendedInvestigators();
    }

    /**
     * Retrieves all administrators according to agency then last name.
     *
     * @return              the list of administrators
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> listAdministrators() throws SQLException {
        return userDAO.listAdministrators();
    }

    /**
     * Retrieves all active administrators according to agency then last name.
     *
     * @return              the list of active administrators
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> listActiveAdministrators() throws SQLException {
        return userDAO.listActiveAdministrators();
    }

    /**
     * Retrieves all suspended administrators according to agency then last name.
     *
     * @return              the list of suspended administrators
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> listSuspendedAdministrators() throws SQLException {
        return userDAO.listSuspendedAdministrators();
    }

    /**
     * Retrieves all encoders according to agency then last name.
     *
     * @return              the list of encoders
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> listEncoders() throws SQLException {
        return userDAO.listEncoders();
    }

    /**
     * Retrieves all active encoders according to agency then last name.
     *
     * @return              the list of active encoders
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> listActiveEncoders() throws SQLException {
        return userDAO.listActiveEncoders();
    }

    /**
     * Retrieves all suspended encoders according to agency then last name.
     *
     * @return              the list of suspended encoders
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> listSuspendedEncoders() throws SQLException {
        return userDAO.listSuspendedEncoders();
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
        return userDAO.getUserById(id);
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
        return userDAO.getUserByUsername(username);
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
        return userDAO.insertUser(user);
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
        return userDAO.isUniqueEmail(user);
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
        return userDAO.isUniqueUsername(user);
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
        return userDAO.updateLogin(user);
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
        return userDAO.updatePassword(user);
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
        return userDAO.updateUser(user);
    }

    /**
     * Returns the total number of users.
     *
     * @return              the total number of users
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllUsers() throws SQLException {
        return userDAO.countAllUsers();
    }

    /**
     * Returns the total number of administrators.
     *
     * @return              the total number of administrators
     * @throws java.sql.SQLException
     */
    @Override
    public int countAdministrators() throws SQLException {
        return userDAO.countAdministrators();
    }

    /**
     * Returns the total number of active administrators.
     *
     * @return              the total number of active administrators
     * @throws java.sql.SQLException
     */
    @Override
    public int countActiveAdministrators() throws SQLException {
        return userDAO.countActiveAdministrators();
    }

    /**
     * Returns the total number of suspended administrators.
     *
     * @return              the total number of suspended administrators
     * @throws java.sql.SQLException
     */
    @Override
    public int countSuspendedAdministrators() throws SQLException {
        return userDAO.countSuspendedAdministrators();
    }

    /**
     * Returns the total number of encoders.
     *
     * @return              the total number of encoders
     * @throws java.sql.SQLException
     */
    @Override
    public int countEncoders() throws SQLException {
        return userDAO.countEncoders();
    }

    /**
     * Returns the total number of active encoders.
     *
     * @return              the total number of active encoders
     * @throws java.sql.SQLException
     */
    @Override
    public int countActiveEncoders() throws SQLException {
        return userDAO.countActiveEncoders();
    }

    /**
     * Returns the total number of suspended encoders.
     *
     * @return              the total number of suspended encoders
     * @throws java.sql.SQLException
     */
    @Override
    public int countSuspendedEncoders() throws SQLException {
        return userDAO.countSuspendedEncoders();
    }

    /**
     * Returns the total number of investigators.
     *
     * @return              the total number of investigators
     * @throws java.sql.SQLException
     */
    @Override
    public int countInvestigators() throws SQLException {
        return userDAO.countInvestigators();
    }

    /**
     * Returns the total number of active investigators.
     *
     * @return              the total number of active investigators
     * @throws java.sql.SQLException
     */
    @Override
    public int countActiveInvestigators() throws SQLException {
        return userDAO.countActiveInvestigators();
    }

    /**
     * Returns the total number of suspended investigators.
     *
     * @return              the total number of suspended investigators
     * @throws java.sql.SQLException
     */
    @Override
    public int countSuspendedInvestigators() throws SQLException {
        return userDAO.countSuspendedInvestigators();
    }

    /**
     * Returns the total number of active users.
     *
     * @return              the total number of active users
     * @throws java.sql.SQLException
     */
    @Override
    public int countActiveUsers() throws SQLException {
        return userDAO.countActiveUsers();
    }

    /**
     * Returns the total number of suspended users.
     *
     * @return              the total number of suspended users
     * @throws java.sql.SQLException
     */
    @Override
    public int countSuspendedUsers() throws SQLException {
        return userDAO.countSuspendedUsers();
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
        return userDAO.countEncodedUsers(creatorId);
    }
}