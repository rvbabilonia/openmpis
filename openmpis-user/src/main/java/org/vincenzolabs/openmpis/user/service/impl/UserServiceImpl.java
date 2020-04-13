/*
 * This file is part of OpenMPIS.
 *
 * Copyright (c) 2019 VincenzoLabs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.vincenzolabs.openmpis.user.service.impl;

import java.time.ZoneId;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.vincenzolabs.openmpis.domain.User;
import org.vincenzolabs.openmpis.user.dao.UserDAO;
import org.vincenzolabs.openmpis.user.service.UserService;

/**
 * The implementation of {@link UserService}.
 *
 * @author Rey Vincent Babilonia
 */
@Service
public class UserServiceImpl
    implements UserService {

    private final UserDAO userDAO;

    /**
     * Default constructor.
     *
     * @param userDAO the {@link UserDAO}
     */
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User createUser(User user, ZoneId zoneId) {
        if (StringUtils.isBlank(user.getEmailAddress())) {
            throw new IllegalArgumentException("Email address must not be blank");
        }
        if (StringUtils.isBlank(user.getFirstName())) {
            throw new IllegalArgumentException("First name must not be blank");
        }
        if (StringUtils.isBlank(user.getLastName())) {
            throw new IllegalArgumentException("Last name must not be blank");
        }
        if (StringUtils.isBlank(user.getDesignation())) {
            throw new IllegalArgumentException("Designation must not be blank");
        }
        if (StringUtils.isBlank(user.getAgencyUuid())) {
            throw new IllegalArgumentException("Agency must not be blank");
        }
        if (user.getGroup() == null) {
            throw new IllegalArgumentException("Group must not be null");
        }
        if (StringUtils.isBlank(user.getCreatorUuid())) {
            throw new IllegalArgumentException("Creator must not be blank");
        }

        String temporaryPassword = null;
        if (StringUtils.isBlank(user.getPassword())) {
            temporaryPassword = RandomStringUtils.randomAlphanumeric(8);

            user = User.builder(user)
                .withPassword(temporaryPassword)
                .build();
        }

        User newUser = userDAO.createUser(user, zoneId);

        if (StringUtils.isNotBlank(temporaryPassword)) {
            // FIXME use SES
        }

        return newUser;
    }

    @Override
    public Set<User> retrieveUsers() {
        return userDAO.retrieveUsers();
    }

    @Override
    public User retrieveUser(final String userUuid) {
        return userDAO.retrieveUser(userUuid);
    }

    @Override
    public User login(final String emailAddress, final String password, ZoneId zoneId, final String ipAddress) {
        if (StringUtils.isBlank(emailAddress)) {
            throw new IllegalArgumentException("Email address must not be blank");
        }
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Password must not be blank");
        }
        return userDAO.login(emailAddress, password, zoneId, ipAddress);
    }

    @Override
    public User updateUser(User user) {
        if (StringUtils.isBlank(user.getFirstName())) {
            throw new IllegalArgumentException("First name must not be blank");
        }
        if (StringUtils.isBlank(user.getLastName())) {
            throw new IllegalArgumentException("Last name must not be blank");
        }
        if (StringUtils.isBlank(user.getDesignation())) {
            throw new IllegalArgumentException("Designation must not be blank");
        }
        if (StringUtils.isBlank(user.getAgencyUuid())) {
            throw new IllegalArgumentException("Agency must not be blank");
        }
        if (user.getGroup() == null) {
            throw new IllegalArgumentException("Group must not be null");
        }
        if (StringUtils.isBlank(user.getCreatorUuid())) {
            throw new IllegalArgumentException("Creator must not be blank");
        }
        return userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUser(final String userUuid) {
        return userDAO.deleteUser(userUuid);
    }

    @Override
    public User archiveUser(final String userUuid) {
        User user = retrieveUser(userUuid);

        return updateUser(User.builder(user)
            .withActive(false)
            .build());
    }
}
