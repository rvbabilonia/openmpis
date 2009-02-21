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
package com.googlecode.openmpis.util;

/**
 * The Constants class provides constant values.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public abstract class Constants {

    /**
     * The OpenMPIS version
     */
    public static final double VERSION = 1.0;
    /**
     * The logical name of the forwarding instance for general failure
     */
    public static final String FAILURE = "failure";
    /**
     * The logical name of the forwarding instance for expired session
     */
    public static final String EXPIRED = "expired";
    /**
     * The logical name of the forwarding instance for unauthorized access
     */
    public static final String UNAUTHORIZED = "unauthorized";
    /**
     * The logical name of the forwarding instance for authenticated user
     */
    public static final String AUTHENTICATED = "authenticated";
    /**
     * The logical name of the forwarding instance for successful user listing
     */
    public static final String LIST_USER = "list-user";
    /**
     * The logical name of the forwarding instance for adding new user
     */
    public static final String ADD_USER = "add-user";
    /**
     * The logical name of the forwarding instance for unsuccessful adding of new user
     */
    public static final String ADD_USER_REDO = "add-user-redo";
    /**
     * The logical name of the forwarding instance for successful adding of new user
     */
    public static final String ADD_USER_SUCCESS = "add-user-success";
    /**
     * The logical name of the forwarding instance for viewing and editing user profile
     */
    public static final String EDIT_USER = "edit-user";
    /**
     * The logical name of the forwarding instance for unsuccessful editing of user profile
     */
    public static final String EDIT_USER_REDO = "edit-user-redo";
    /**
     * The logical name of the forwarding instance for successful editing of user profile
     */
    public static final String EDIT_USER_SUCCESS = "edit-user-success";
    /**
     * The logical name of the forwarding instance for deleting a user
     */
    public static final String DELETE_USER = "delete-user";
    /**
     * The logical name of the forwarding instance for deletion confirmation code mismatch
     */
    public static final String DELETE_USER_REDO = "delete-user-redo";
    /**
     * The logical name of the forwarding instance for successful deletion
     */
    public static final String DELETE_USER_SUCCESS = "delete-user-success";
    /**
     * The logical name of the forwarding instance for unsuccessful sending of feedback
     */
    public static final String FEEDBACK_REDO = "feedback-redo";
    /**
     * The logical name of the forwarding instance for successful sending of feedback
     */
    public static final String FEEDBACK_SUCCESS = "feedback-success";
    /**
     * The logical name of the forwarding instance for unsuccessful log in
     */
    public static final String LOGIN_REDO = "login-redo";
    /**
     * The logical name of the forwarding instance for successful log in
     */
    public static final String LOGIN_SUCCESS = "login-success";
    /**
     * The logical name of the forwarding instance for log out
     */
    public static final String LOGOUT_SUCCESS = "logout-success";
    /**
     * The logical name of the forwarding instance for unsuccessful password reset
     */
    public static final String PASSWORD_REDO = "password-redo";
    /**
     * The logical name of the forwarding instance for successful password reset
     */
    public static final String PASSWORD_SUCCESS = "password-success";
    /**
     * The logical name of the forwarding instance for successful user count
     */
    public static final String USER_STATISTICS = "user-statistics";
    /**
     * The logical name of the forwarding instance for successful case count
     */
    public static final String CASE_STATISTICS = "case-statistics";
    /**
     * The logical name of the forwarding instance for successful person listing
     */
    public static final String LIST_PERSON = "list-person";
    /**
     * The logical name of the forwarding instance for viewing a case
     */
    public static final String VIEW_PERSON = "view-person";
    /**
     * The logical name of the forwarding instance for adding a new person
     */
    public static final String ADD_PERSON = "add-person";
    /**
     * The logical name of the forwarding instance for unsuccessful adding of a new person
     */
    public static final String ADD_PERSON_REDO = "add-person-redo";
    /**
     * The logical name of the forwarding instance for successful adding of a new person
     */
    public static final String ADD_PERSON_SUCCESS = "add-person-success";
    /**
     * The logical name of the forwarding instance for viewing a person's poster
     */
    public static final String VIEW_PERSON_POSTER = "view-person-poster";
    /**
     * The logical name of the forwarding instance for successful listing of all missing persons
     */
    public static final String LIST_PERSON_ALL_MISSING = "list-person-all-missing";
    /**
     * The logical name of the forwarding instance for successful listing of all missing persons
     */
    public static final String LIST_PERSON_ALL_FOUND = "list-person-all-found";
    /**
     * The logical name of the forwarding instance for successful listing of all missing persons
     */
    public static final String LIST_PERSON_UNIDENTIFIED = "list-person-unidentified";
}