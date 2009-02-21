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
 * The Validator class provides methods to sanitize inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class Validator {

    /**
     * Creates a new validator.
     */
    public Validator() {
    }

    /**
     * Validates the ID.
     * 
     * @param id            the ID
     * @return              <code>true</code> if ID is valid; <code>false</code> otherwise
     */
    public boolean isValidID(String id) {
        return id.matches("[0-9]+");
    }

    /**
     * Validates the first name.
     * 
     * @param firstName     the first name
     * @return              <code>true</code> if the first name is valid; <code>false</code> otherwise
     */
    public boolean isValidFirstName(String firstName) {
        return firstName.matches("[A-Z][a-z]+([\\s'-][a-zA-Z]+)*");
    }

    /**
     * Validates the last name.
     * 
     * @param lastName      the last name
     * @return              <code>true</code> if the last name is valid; <code>false</code> otherwise
     */
    public boolean isValidLastName(String lastName) {
        return lastName.matches("[A-Z][a-z]+|[A-Z][a-z]+([\\s][A-Z][a-z]+)+|([a-z]+[\\s])+[A-Z][a-z]+|[a-z][\'][A-Z][a-z]+|[A-Z][a-z]+[\\-][A-Za-z][a-z]+");
    }

    /**
     * Validates the email address of the sender.
     * 
     * @param address       the email address of the sender
     * @return              <code>true</code> if the email address is valid; <code>false</code> otherwise
     */
    public boolean isValidEmailAddress(String address) {
        return address.matches(".+@.+\\.[a-z]+");
    }

    /**
     * Validates the keyword to be searched.
     * 
     * @param keyword       the keyword to be searched
     * @return              <code>true</code> if the keyword is valid; <code>false</code> otherwise
     */
    public boolean isValidKeyword(String keyword) {
        return keyword.matches("[a-zA-Z0-9\\s\\.\\-]*");
    }

    /**
     * Validates the username.
     * 
     * @param username      the username
     * @return              <code>true</code> if the username is valid; <code>false</code> otherwise
     */
    public boolean isValidUsername(String username) {
        return username.matches("[A-Z]{2}[0-9]{4}");
    }

    /**
     * Validates the telephone number.
     * 
     * @param number        the telephone number
     * @return              <code>true</code> if the telephone number is valid; <code>false</code> otherwise
     */
    public boolean isValidNumber(String number) {
        return number.matches("[0-9\\s\\Q+-.\\E()]+(loc\\.\\s|LOC\\.\\s|EXT\\.\\s|ext\\.\\s)?[0-9]+");
    }
}