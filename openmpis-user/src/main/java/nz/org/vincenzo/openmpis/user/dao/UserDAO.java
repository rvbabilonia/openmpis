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
package nz.org.vincenzo.openmpis.user.dao;

import com.sleepycat.je.Transaction;
import nz.org.vincenzo.openmpis.user.model.User;

import java.util.List;

/**
 * The data access object for user.
 *
 * @author Rey Vincent Babilonia
 * @since 1.0.0
 */
public interface UserDAO {

    /**
     * Creates a new {@link User}.
     *
     * @param transaction  the {@link Transaction}
     * @param emailAddress the email address of the new {@link User}
     * @param creator      the email address of the creator
     * @return {@code true} if the {@link User} has been inserted; {@code false} otherwise
     */
    boolean createUser(final String emailAddress, final String creator);

    /**
     * Retrieves all users.
     *
     * @return the {@link List} of users
     */
    List<User> retrieveUsers();

    /**
     * Retrieves all investigators according to agency then last name.
     *
     * @return the {@link List} of investigators
     */
    List<User> retrieveInvestigators();

    /**
     * Retrieves all active investigators according to agency then last name.
     *
     * @return the {@link List} of active investigators
     */
    List<User> retrieveActiveInvestigators();

    /**
     * Retrieves all inactive investigators according to agency then last name.
     *
     * @return the {@link List} of inactive investigators
     */
    List<User> retrieveInactiveInvestigators();

    /**
     * Retrieves all administrators according to agency then last name.
     *
     * @return the {@link List} of administrators
     */
    List<User> retrieveAdministrators();

    /**
     * Retrieves all active administrators according to agency then last name.
     *
     * @return the {@link List} of active administrators
     */
    List<User> retrieveActiveAdministrators();

    /**
     * Retrieves all inactive administrators according to agency then last name.
     *
     * @return the {@link List} of inactive administrators
     */
    List<User> retrieveInactiveAdministrators();

    /**
     * Retrieves all encoders according to agency then last name.
     *
     * @return the {@link List} of encoders
     */
    List<User> retrieveEncoders();

    /**
     * Retrieves all active encoders according to agency then last name.
     *
     * @return the {@link List} of active encoders
     */
    List<User> retrieveActiveEncoders();

    /**
     * Retrieves all inactive encoders according to agency then last name.
     *
     * @return the {@link List} of inactive encoders
     */
    List<User> retrieveInactiveEncoders();

    /**
     * Returns the {@link List} of {@link User}s by agency.
     *
     * @param agency the agency
     * @return the {@link List} of {@link User}s by agency
     */
    List<User> retrieveUsersByAgency(final String agency);

    /**
     * Returns the {@link List} of {@link User}s by creator.
     *
     * @param creator the email address of the creator
     * @return the {@link List} of {@link User}s by creator
     */
    List<User> retrieveUsersByCreator(final String creator);

    /**
     * Retrieves a user given his email address.
     *
     * @param emailAddress the email address
     * @return the {@link User}
     */
    User retrieveUser(final String emailAddress);

    /**
     * Checks if an email address is unique.
     *
     * @param emailAddress the email address
     * @return {@code true} if the email address is unique; {@code false} otherwise
     */
    boolean isUniqueEmail(final String emailAddress);

    /**
     * Returns the total number of users.
     *
     * @return the total number of users
     */
    long countUsers();

    /**
     * Updates an existing user.
     *
     * @param user the existing user
     * @return {@code true} if the user was successfully updated; {@code false} otherwise
     */
    boolean updateUser(User user);

    /**
     * Updates a user's last log in date and IP address.
     *
     * @param user the user who logged in
     * @return {@code true} if the log in date and IP address were successfully updated; {@code false}
     * otherwise
     */
    boolean updateLastLogin(User user);

    /**
     * Deactives a user with the specified ID.
     *
     * @param emailAddress the email address
     * @return {@code true} if the user was successfully deleted; {@code false} otherwise
     */
    boolean deactivateUser(final String emailAddress);
}