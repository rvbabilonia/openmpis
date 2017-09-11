/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008-2017  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nz.org.vincenzo.openmpis.user.service.impl;

import nz.org.vincenzo.openmpis.user.dao.UserDAO;
import nz.org.vincenzo.openmpis.user.model.User;
import nz.org.vincenzo.openmpis.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The implementation of {@link UserService}.
 *
 * @author Rey Vincent Babilonia
 * @since 1.0.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    /**
     * Default constructor.
     *
     * @param userDAO the {@link UserDAO}
     */
    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean createUser(String emailAddress, String creator) {
        return userDAO.retrieveUser(creator) != null && userDAO.createUser(emailAddress, creator);
    }

    @Override
    public List<User> retrieveUsers() {
        return userDAO.retrieveUsers();
    }

    @Override
    public List<User> retrieveInvestigators() {
        return userDAO.retrieveInvestigators();
    }

    @Override
    public List<User> retrieveActiveInvestigators() {
        return userDAO.retrieveActiveInvestigators();
    }

    @Override
    public List<User> retrieveInactiveInvestigators() {
        return userDAO.retrieveInactiveInvestigators();
    }

    @Override
    public List<User> retrieveAdministrators() {
        return userDAO.retrieveAdministrators();
    }

    @Override
    public List<User> retrieveActiveAdministrators() {
        return userDAO.retrieveActiveAdministrators();
    }

    @Override
    public List<User> retrieveInactiveAdministrators() {
        return userDAO.retrieveInactiveAdministrators();
    }

    @Override
    public List<User> retrieveEncoders() {
        return userDAO.retrieveEncoders();
    }

    @Override
    public List<User> retrieveActiveEncoders() {
        return userDAO.retrieveActiveEncoders();
    }

    @Override
    public List<User> retrieveInactiveEncoders() {
        return userDAO.retrieveInactiveEncoders();
    }

    @Override
    public List<User> retrieveUsersByAgency(String agency) {
        return userDAO.retrieveUsersByAgency(agency);
    }

    @Override
    public List<User> retrieveUsersByCreator(String creator) {
        return userDAO.retrieveUsersByCreator(creator);
    }

    @Override
    public User retrieveUser(String emailAddress) {
        return userDAO.retrieveUser(emailAddress);
    }

    @Override
    public boolean isUniqueEmail(String emailAddress) {
        return userDAO.isUniqueEmail(emailAddress);
    }

    @Override
    public long countUsers() {
        return userDAO.countUsers();
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public boolean updateLastLogin(User user) {
        return userDAO.updateLastLogin(user);
    }

    @Override
    public boolean deactivateUser(String emailAddress) {
        return userDAO.deactivateUser(emailAddress);
    }
}
