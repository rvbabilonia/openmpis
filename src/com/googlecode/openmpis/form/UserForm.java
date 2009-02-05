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
package com.googlecode.openmpis.form;

import org.apache.struts.action.ActionForm;

/**
 * The UserForm class provides methods to validate the user form inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class UserForm extends ActionForm {

    /**
     * The user ID
     */
    private int id;
    /**
     * The group ID of the user
     */
    private int groupId;
    /**
     * The username
     */
    private String username;
    /**
     * The password of the user
     */
    private String password;
    /**
     * The retyped password of the user
     */
    private String retype;
    /**
     * The first name of the user
     */
    private String firstName;
    /**
     * The first name of the user
     */
    private String lastName;
    /**
     * The first name of the user
     */
    private String middleName;
    /**
     * The email address of the user
     */
    private String email;
    /**
     * The position or designation of the user
     */
    private String designation;
    /**
     * The agency to which the user belongs
     */
    private String agency;
    /**
     * The telephone number of the user
     */
    private String number;
    /**
     * The last IP address used
     */
    private String ipAddress;
    /**
     * The last login date
     */
    private String lastLogin;
    /**
     * The date this user account was created
     */
    private String date;
    /**
     * The encoder of this user account
     */
    private int creatorId;
    /**
     * The status of the user
     */
    private int status;
    /**
     * The first name of the user
     */
    private int question;
    /**
     * The first name of the user
     */
    private String answer;
    /**
     * The generated confirmation code
     */
    private int code;
    /**
     * The user-inputted confirmation code 
     */
    private int userCode;

    /**
     * Gets the ID of the user.
     * 
     * @return               the ID of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * 
     * @param    id          the ID of the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the group ID of the user.
     * 
     * @return               the group Id of the user
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * Sets the group ID of the user.
     * 
     * @param    groupId     the group Id of the user
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets the username.
     * 
     * @return               the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param    username    the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * 
     * @return               the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param    password    the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the retyped password of the user.
     * 
     * @return               the retyped password of the user
     */
    public String getRetype() {
        return retype;
    }

    /**
     * Sets the retyped password of the user.
     * 
     * @param    retype      the retyped password of the user
     */
    public void setRetype(String retype) {
        this.retype = retype;
    }

    /**
     * Gets the first name of the user.
     * 
     * @return               the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * 
     * @param    firstName   the first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     * 
     * @return               the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * 
     * @param    lastName    the last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the middle name of the user.
     * 
     * @return               the middle name of the user
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name of the user.
     * 
     * @param    middleName  the middle name of the user
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the email address of the user.
     * 
     * @return              the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * 
     * @param   email       the email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the position or designation of the user.
     * 
     * @return              the position or designation of the user
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the position or designation of the user.
     * 
     * @param   designation the position or designation of the user
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Gets the agency to which the user belongs.
     * 
     * @return              the agency to which the user belongs
     */
    public String getAgency() {
        return agency;
    }

    /**
     * Sets the agency to which the user belongs.
     * 
     * @param   agency      the agency to which the user belongs
     */
    public void setAgency(String agency) {
        this.agency = agency;
    }

    /**
     * Gets the telephone number of the user.
     * 
     * @return              the telephone number of the user
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the telephone number of the user.
     * 
     * @param   number      the telephone number of the user
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets the last IP address of the user.
     * 
     * @return              the last IP address of the user
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the last IP address of the user.
     * 
     * @param   ipAddress   the last IP address of the user
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Gets the date of the user's last login
     * 
     * @return              the user's last login date
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     * Sets the date of the user's last login
     * 
     * @param   lastLogin   the user's last login date
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Gets the date this user account was created.
     * 
     * @return              the date this user account was created
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date this user account was created.
     * 
     * @param   date        the date this user account was created
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the encoder of this user account.
     * 
     * @return              the encoder of this user account
     */
    public int getCreatorId() {
        return creatorId;
    }

    /**
     * Sets the encoder of this user account.
     * 
     * @param   creatorId   the encoder of this user account
     */
    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * Gets the status of the user.
     * 
     * @return              the status of the user
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the user.
     * 
     * @param   status      the status of the user
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the security question of the user.
     * 
     * @return              the security question of the user
     */
    public int getQuestion() {
        return question;
    }

    /**
     * Sets the security question of the user.
     * 
     * @param   question    the security question of the user
     */
    public void setQuestion(int question) {
        this.question = question;
    }

    /**
     * Gets the security answer of the user.
     * 
     * @return              the security answer of the user
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the security answer of the user.
     * 
     * @param   answer      the security answer of the user
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
     * @param code          the user-inputted confirmation code
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
        String user = "ID: " + id;
        user += "\nGroup ID: " + groupId;
        user += "\nUsername: " + username;
        user += "\nPassword: " + password;
        user += "\nFirst Name: " + firstName;
        user += "\nMiddle Name: " + middleName;
        user += "\nLast Name: " + lastName;
        user += "\nAgency: " + agency;
        user += "\nDesignation: " + designation;
        user += "\nEmail: " + email;
        user += "\nNumber: " + number;
        user += "\nLast Login: " + lastLogin;
        user += "\nIP Address: " + ipAddress;
        user += "\nDate: " + date;
        user += "\nStatus: " + status;
        user += "\nQuestion: " + question;
        user += "\nAnswer: " + answer;
        user += "\nCreator ID: " + creatorId;
        return user;
    }

    /**
     * Sole constructor.
     */
    public UserForm() {
        super();
    }
}