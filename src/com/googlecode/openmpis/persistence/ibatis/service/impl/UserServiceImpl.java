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

import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.persistence.ibatis.dao.UserDAO;
import com.googlecode.openmpis.persistence.ibatis.service.UserService;

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
     * The UserDAO
     */
    private UserDAO userDAO = null;
    
    /**
     * Sole constructor.
     * 
     * @param userDAO   the user DAO
     */
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Deletes a user with the specified ID.
     * 
     * @param id    the ID of the user to be deleted
     * @return      <code>true</code> if the user was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deleteUser(Integer id) throws SQLException {
        return userDAO.deleteUser(id);
    }

    /**
     * Retrieves all users.
     * 
     * @return      the list of users
     * @throws java.sql.SQLException
     */
    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    /**
     * Retrieves a user given his ID.
     * 
     * @param id    the user ID
     * @return      the user
     * @throws java.sql.SQLException
     */
    @Override
    public User getUserById(Integer id) throws SQLException {
        return userDAO.getUserById(id);
    }

    /**
     * Retrieves a user given his username.
     * 
     * @param username  the username
     * @return          the user
     * @throws java.sql.SQLException
     */
    @Override
    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }

    /**
     * Inserts a new user.
     * 
     * @param user  the new user
     * @return      <code>true</code> if the user was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean insertUser(User user) throws SQLException {
        return userDAO.insertUser(user);
    }

    /**
     * Checks if an email address is unique.
     * 
     * @param user  the existing user
     * @return      <code>true</code> if the email address is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isUniqueEmail(User user) throws SQLException {
        return userDAO.isUniqueEmail(user);
    }

    /**
     * Checks if a username is unique.
     * 
     * @param user  the existing user
     * @return      <code>true</code> if the username is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isUniqueUsername(User user) throws SQLException {
        return userDAO.isUniqueUsername(user);
    }

    /**
     * Updates a user's last log in date and IP address.
     * 
     * @param user  the user who logged in
     * @return      <code>true</code> if the log in date and IP address were successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updateLogin(User user) throws SQLException {
        return userDAO.updateLogin(user);
    }

    /**
     * Resets a user's password.
     * 
     * @param user  the user who supplied the correct security question and answer
     * @return      <code>true</code> if the password was successfully reset; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updatePassword(User user) throws SQLException {
        return userDAO.updatePassword(user);
    }

    /**
     * Updates an existing user.
     * 
     * @param user  the existing user
     * @return      <code>true</code> if the user was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updateUser(User user) throws SQLException {
        return userDAO.updateUser(user);
    }
}