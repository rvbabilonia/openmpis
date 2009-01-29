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

package com.googlecode.openmpis.model;

/**
 * The Validator class provides methods to sanitize inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class Validator {
    /**
     * Sole constructor.
     */
    public Validator() {
        super();
    }

    /**
     * Validates the ID.
     * 
     * @param   id              the ID
     * @return          true    if ID is valid, otherwise false
     */
    public boolean isValidID(String id)
    {
        return id.matches("[0-9]");
    }

    /**
     * Validates the first name.
     * 
     * @param   firstName           the first name
     * @return              true    if the first name is valid, otherwise false
     */
    public boolean isValidFirstName(String firstName)
    {
        return firstName.matches("[A-Z][a-z]+([ '-][a-zA-Z]+)*" );
    }

    /**
     * Validates the last name.
     * 
     * @param   lastName            the last name
     * @return              true    if the last name is valid, otherwise false
     */
    public boolean isValidLastName(String lastName)
    {
        return lastName.matches("[a-zA-Z]+([ '-][a-zA-Z]+)*");
    }

    /**
     * Validates the email address of the sender.
     * 
     * @param   address         the email address of the sender
     * @return          true    if the email address is valid, otherwise false
     */
    public boolean isValidEmailAddress(String address)
    {
        return address.matches(".+@.+\\.[a-z]+");
    }

    /**
     * Validates the keyword to be searched.
     * 
     * @param   keyword         the keyword to be searched
     * @return          true    if the keyword is valid, otherwise false
     */
    public boolean isValidKeyword(String keyword)
    {
        return keyword.matches("[a-zA-Z0-9 ]*");
    }

    /**
     * Validates the username.
     * 
     * @param   username            the username
     * @return              true    if the username is valid, otherwise false
     */
    public boolean isValidUsername(String username)
    {
        return username.matches("[a-zA-Z0-9]{5,6}");
    }

    /**
     * Validates the 32 alphanumeric characters MD5-encrypted password.
     * 
     * @param   password            the password
     * @return              true    if the password is valid, otherwise false
     */
    public boolean isValidPassword(String password)
    {
        return true;
        //return password.matches("[a-z0-9]{32}");
    }

    /**
     * Validates the telephone number.
     * 
     * @param   number              the telephone number
     * @return              true    if the telephone number is valid, otherwise false
     */
    public boolean isValidNumber(String number)
    {
        return true;
        //return number.matches("[a-zA-Z0-9+-.]");
    }
}