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
package nz.org.vincenzo.openmpis.user.controller;

import nz.org.vincenzo.openmpis.user.dto.UserDTO;
import nz.org.vincenzo.openmpis.user.model.User;
import nz.org.vincenzo.openmpis.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * The {@link RestController} for user microservice.
 *
 * @author Rey Vincent Babilonia
 * @since 2.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * Default constructor.
     *
     * @param userService the {@link UserService}
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new {@link User}.
     *
     * @param emailAddress the email address of the new {@link User}
     * @param creator      the email address of the creator
     * @return {@code true} if the {@link User} has been inserted; {@code false} otherwise
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/v1/create/{emailAddress}/{creator}")
    public HttpEntity<UserDTO> createUser(@PathVariable final String emailAddress, @PathVariable final String creator) {
        try {
            userService.createUser(emailAddress, creator);

            UserDTO userDTO = new UserDTO();
            userDTO.setEmailAddress(emailAddress);
            userDTO.setCreator(creator);
            userDTO.add(linkTo(methodOn(UserController.class).createUser(emailAddress, creator)).withSelfRel());

            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    /**
//     * Retrieves all users.
//     *
//     * @return the {@link List} of users
//     */
//    List<User> retrieveUsers() {}
//
//    /**
//     * Retrieves all investigators according to agency then last name.
//     *
//     * @return the {@link List} of investigators
//     */
//    List<User> retrieveInvestigators() {}
//
//    /**
//     * Retrieves all active investigators according to agency then last name.
//     *
//     * @return the {@link List} of active investigators
//     */
//    List<User> retrieveActiveInvestigators() {}
//
//    /**
//     * Retrieves all inactive investigators according to agency then last name.
//     *
//     * @return the {@link List} of inactive investigators
//     */
//    List<User> retrieveInactiveInvestigators() {}
//
//    /**
//     * Retrieves all administrators according to agency then last name.
//     *
//     * @return the {@link List} of administrators
//     */
//    List<User> retrieveAdministrators() {}
//
//    /**
//     * Retrieves all active administrators according to agency then last name.
//     *
//     * @return the {@link List} of active administrators
//     */
//    List<User> retrieveActiveAdministrators() {}
//
//    /**
//     * Retrieves all inactive administrators according to agency then last name.
//     *
//     * @return the {@link List} of inactive administrators
//     */
//    List<User> retrieveInactiveAdministrators() {}
//
//    /**
//     * Retrieves all encoders according to agency then last name.
//     *
//     * @return the {@link List} of encoders
//     */
//    List<User> retrieveEncoders() {}
//
//    /**
//     * Retrieves all active encoders according to agency then last name.
//     *
//     * @return the {@link List} of active encoders
//     */
//    List<User> retrieveActiveEncoders() {}
//
//    /**
//     * Retrieves all inactive encoders according to agency then last name.
//     *
//     * @return the {@link List} of inactive encoders
//     */
//    List<User> retrieveInactiveEncoders() {}
//
//    /**
//     * Returns the {@link List} of {@link User}s by agency.
//     *
//     * @param agency the agency
//     * @return the {@link List} of {@link User}s by agency
//     */
//    List<User> retrieveUsersByAgency(final String agency) {}
//
//    /**
//     * Returns the {@link List} of {@link User}s by creator.
//     *
//     * @param creator the email address of the creator
//     * @return the {@link List} of {@link User}s by creator
//     */
//    List<User> retrieveUsersByCreator(final String creator) {}
//
//    /**
//     * Retrieves a user given his email address.
//     *
//     * @param emailAddress the email address
//     * @return the {@link User}
//     */
//    User retrieveUser(final String emailAddress) {}
//
//    /**
//     * Checks if an email address is unique.
//     *
//     * @param emailAddress the email address
//     * @return {@code true} if the email address is unique; {@code false} otherwise
//     */
//    boolean isUniqueEmail(final String emailAddress) {}
//
//    /**
//     * Returns the total number of users.
//     *
//     * @return the total number of users
//     */
//    long countUsers() {}
//
//    /**
//     * Updates an existing user.
//     *
//     * @param user the existing user
//     * @return {@code true} if the user was successfully updated; {@code false} otherwise
//     */
//    boolean updateUser(User user) {}
//
//    /**
//     * Updates a user's last log in date and IP address.
//     *
//     * @param user the user who logged in
//     * @return {@code true} if the log in date and IP address were successfully updated; {@code false}
//     * otherwise
//     */
//    boolean updateLastLogin(User user) {}
//
//    /**
//     * Deactives a user with the specified ID.
//     *
//     * @param emailAddress the email address
//     * @return {@code true} if the user was successfully deleted; {@code false} otherwise
//     */
//    boolean deactivateUser(final String emailAddress) {}
}
