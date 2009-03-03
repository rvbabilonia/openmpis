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
 * The MessageForm class is used to represent messages and feedbacks for users.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class MessageForm extends ActionForm {

    /**
     * The message ID
     */
    private int id;
    /**
     * The date this message was created
     */
    private String date;
    /**
     * The email address of the sender
     */
    private String email;
    /**
     * The first name of the sender
     */
    private String firstName;
    /**
     * The last name of the sender
     */
    private String lastName;
    /**
     * The subject of the message
     */
    private String subject;
    /**
     * The message body
     */
    private String message;
    /**
     * The status of the message
     */
    private int status;
    /**
     * The originating IP address of the message
     */
    private String ipAddress;
    /**
     * The ID of the person attributed with this sighting
     */
    private int personId;
    /**
     * The ID of this message's recipient
     */
    private int userId;
    /**
     * The generated confirmation code
     */
    private int code;
    /**
     * The user-inputted confirmation code
     */
    private int userCode;

    /**
     * Gets the message ID.
     * 
     * @return              the message ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the message ID.
     * 
     * @param id            the message ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the date this message was created.
     * 
     * @return              the date this message was created
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date this message was created.
     * 
     * @param date          the date this message was created
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the email address of the sender.
     * 
     * @return              the email address of the sender
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the sender.
     * 
     * @param email         the email address of the sender
     */
    public void setEmail(String email) {
        this.email = email.trim();
    }

    /**
     * Gets the first name of the sender.
     * 
     * @return              the first name of the sender
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the sender.
     * 
     * @param firstName     the first name of the sender
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    /**
     * Gets the last name of the sender.
     * 
     * @return              the last name of the sender
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the sender.
     * 
     * @param lastName      the last name of the sender
     */
    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    /**
     * Gets the subject of the message.
     * 
     * @return              the subject of the message
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the message.
     * 
     * @param subject       the subject of the message
     */
    public void setSubject(String subject) {
        this.subject = subject.trim();
    }

    /**
     * Gets the message body.
     * 
     * @return              the message body
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message body.
     * 
     * @param message       the message body
     */
    public void setMessage(String message) {
        this.message = message.trim();
    }

    /**
     * Gets the status of the message.
     *
     * @return              the status of the message
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the message.
     *
     * @param status        the status of the message
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the originating IP address of the message.
     * 
     * @return              the originating IP address of the message
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the originating IP address of the message.
     * 
     * @param ipAddress     the originating IP address of the message
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Gets the ID of the person attributed with this sighting.
     *
     * @return              the ID of the person attributed with this sighting
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * Sets the ID of the person attributed with this sighting.
     *
     * @param personId      the ID of the person attributed with this sighting
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * Gets the ID of this message's recipient.
     *
     * @return              the ID of this message's recipient
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of this message's recipient.
     *
     * @param userId        the ID of this message's recipient
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
        content += "\nDate: " + date;
        content += "\nEmail Address: " + email;
        content += "\nFirst Name: " + firstName;
        content += "\nLast Name: " + lastName;
        content += "\nSubject: " + subject;
        content += "\nMessage: " + message;
        content += "\nStatus: " + status;
        content += "\nIP Address: " + ipAddress;
        content += "\nPerson ID: " + personId;
        content += "\nUser ID: " + userId;
        return content;
    }
}