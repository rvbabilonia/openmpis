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
    public boolean isValidId(String id) {
        return id.matches("[A-Za-z0-9]+");
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

    /**
     * Validates the street name.
     *
     * @param street        the street name
     * @return              <code>true</code> if the street name is valid; <code>false</code> otherwise
     */
    public boolean isValidStreet(String street) {
        return street.matches("[A-Za-z0-9]+(['-,\\.A-Za-z0-9])*([\\s][A-Za-z0-9]+['-,\\.]*)*");
    }

    /**
     * Validates the city name.
     *
     * @param city          the city name
     * @return              <code>true</code> if the city name is valid; <code>false</code> otherwise
     */
    public boolean isValidCity(String city) {
        return city.matches("[A-Z][a-z]+([\\s'-][a-zA-Z]+)*");
    }

    /**
     * Validates the province name.
     *
     * @param province      the province name
     * @return              <code>true</code> if the province name is valid; <code>false</code> otherwise
     */
    public boolean isValidProvince(String province) {
        return province.matches("[A-Z][a-z]+([\\s'-][a-zA-Z]+)*");
    }

    /**
     * Validates the input.
     *
     * @param input         the input
     * @return              <code>true</code> if the input is valid; <code>false</code> otherwise
     */
    public boolean isValidInput(String input) {
        return input.matches(".+");
    }

    /**
     * Validates a institution.
     *
     * @param institution   the institution
     * @return              <code>true</code> if the institution is valid; <code>false</code> otherwise
     */
    public boolean isValidInstitution(String institution) {
        return institution.matches("[A-Z][a-z0-9\\.]+([\\s'-\\.][a-zA-Z0-9]+)*");
    }
}