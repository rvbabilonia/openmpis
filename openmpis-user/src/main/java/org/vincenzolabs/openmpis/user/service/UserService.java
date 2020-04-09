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
package org.vincenzolabs.openmpis.user.service;

import java.time.ZoneId;
import java.util.Set;

import org.vincenzolabs.openmpis.domain.User;

/**
 * The service for user.
 *
 * @author Rey Vincent Babilonia
 */
public interface UserService {

    /**
     * Creates an {@link User}.
     *
     * @param user   the {@link User}
     * @param zoneId the {@link ZoneId}
     * @return the {@link User}
     */
    User createUser(User user, ZoneId zoneId);

    /**
     * Returns the {@link Set} of {@link User}s.
     *
     * @return the {@link Set} of {@link User}s
     */
    Set<User> retrieveUsers();

    /**
     * Returns the {@link User} matching the given UUID.
     *
     * @param userUuid the {@link User} UUID
     * @return the {@link User}
     */
    User retrieveUser(String userUuid);

    /**
     * Authenticates the user.
     *
     * @param emailAddress the email address
     * @param password     the plain-text password
     * @param zoneId       the {@link ZoneId}
     * @param ipAddress    the client IP address
     * @return the {@link User}
     */
    User login(String emailAddress, String password, ZoneId zoneId, String ipAddress);

    /**
     * @param user the {@link User}
     * @return the {@link User}
     */
    User updateUser(User user);

    /**
     * Deletes a {@link User}.
     *
     * @param userUuid the {@link User} UUID
     * @return {@code true} if the {@link User} has been deleted; {@code false} otherwise
     */
    boolean deleteUser(String userUuid);

    /**
     * Archives a {@link User}.
     *
     * @param userUuid the {@link User} UUID
     * @return the {@link User}
     */
    User archiveUser(String userUuid);
}
