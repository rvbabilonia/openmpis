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
package com.googlecode.openmpis.form;

import org.apache.struts.action.ActionForm;

/**
 * The RelativeForm class is used to represent a person's relative.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class RelativeForm extends ActionForm {

    /**
     * The relative ID
     */
    private int id;
    /**
     * The first name of the relative
     */
    private String firstName;
    /**
     * The last name of the relative
     */
    private String lastName;
    /**
     * The middle name of the relative
     */
    private String middleName;
    /**
     * The email address of the relative
     */
    private String email;
    /**
     * The telephone number of the relative
     */
    private String number;
    /**
     * The street address of the relative
     */
    private String street;
    /**
     * The home city of the relative
     */
    private String city;
    /**
     * The home province of the relative
     */
    private String province;
    /**
     * The home country of the relative
     */
    private String country;
    /**
     * The relationship of the relative to the person
     */
    private int relationToRelative;
    /**
     * The generated confirmation code
     */
    private int code;
    /**
     * The user-inputted confirmation code
     */
    private int userCode;

    /**
     * Gets the ID of the relative.
     *
     * @return               the ID of the relative
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the relative.
     *
     * @param id            the ID of the relative
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the relative.
     *
     * @return               the first name of the relative
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the relative.
     *
     * @param firstName     the first name of the relative
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    /**
     * Gets the last name of the relative.
     *
     * @return               the last name of the relative
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the relative.
     *
     * @param lastName      the last name of the relative
     */
    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    /**
     * Gets the middle name of the relative.
     *
     * @return               the middle name of the relative
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name of the relative.
     *
     * @param middleName    the middle name of the relative
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName.trim();
    }

    /**
     * Gets the email address of the relative.
     *
     * @return              the email address of the relative
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the relative.
     *
     * @param email         the email address of the relative
     */
    public void setEmail(String email) {
        this.email = email.trim();
    }

    /**
     * Gets the telephone number of the relative.
     *
     * @return              the telephone number of the relative
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the telephone number of the relative.
     *
     * @param number        the telephone number of the relative
     */
    public void setNumber(String number) {
        this.number = number.trim();
    }

    /**
     * Gets the street address of the relative.
     *
     * @return              the street address of the relative
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street address of the relative.
     *
     * @param street        the street address of the relative
     */
    public void setStreet(String street) {
        this.street = street.trim();
    }

    /**
     * Sets the home city of the relative.
     *
     * @return              the home city of the relative
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the home city of the relative.
     *
     * @param city          the home city of the relative
     */
    public void setCity(String city) {
        this.city = city.trim();
    }

    /**
     * Gets the home province of the relative.
     *
     * @return              the home province of the relative
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the home province of the relative.
     *
     * @param province      the home province of the relative
     */
    public void setProvince(String province) {
        this.province = province.trim();
    }

    /**
     * Gets the home country of the relative.
     *
     * @return              the home country of the relative
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the home country of the relative.
     * 
     * @param country       the home country of the relative
     */
    public void setCountry(String country) {
        this.country = country.trim();
    }

    /**
     * Gets the relationship of the relative to the person.
     *
     * @return              the relationship of the relative to the person
     */
    public int getRelationToRelative() {
        return relationToRelative;
    }

    /**
     * Sets the relationship of the relative to the person.
     *
     * @param relationToRelative the relationship of the relative to the person
     */
    public void setRelationToRelative(int relationToRelative) {
        this.relationToRelative = relationToRelative;
    }

    /**
     * Gets the 4-digit randomly-generated confirmation code.
     *
     * @return              the 4-digit randomly-generated confirmation code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the 4-digit randomly-generated confirmation code.
     *
     * @param code          the 4-digit randomly-generated confirmation code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets the user-inputted confirmation code.
     *
     * @return              the user-inputted confirmation code
     */
    public int getUserCode() {
        return userCode;
    }

    /**
     * Sets the user-inputted confirmation code.
     *
     * @param userCode      the user-inputted confirmation code
     */
    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    /**
     * Returns a String representation of this data transfer object.
     *
     * @return              the String representation of this data transfer object
     */
    @Override
    public String toString() {
        String content = "";
        content += "\nID: " + id;
        content += "\nFirst Name: " + firstName;
        content += "\nMiddle Name: " + middleName;
        content += "\nLast Name: " + lastName;
        content += "\nEmail: " + email;
        content += "\nNumber: " + number;
        content += "\nStreet: " + street;
        content += "\nCity: " + city;
        content += "\nProvince: " + province;
        content += "\nCountry: " + country;
        content += "\nRelation: " + relationToRelative;
        return content;
    }
}