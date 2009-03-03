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
 * The FeedbackForm class provides methods to validate the feedback form inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class FeedbackForm extends ActionForm {

    /**
     * The first name of the sender
     */
    private String firstName;
    /**
     * The last name of the sender
     */
    private String lastName;
    /**
     * The email address of the sender
     */
    private String email;
    /**
     * The subject of the email
     */
    private String subject;
    /**
     * The message body of the email
     */
    private String message;

    /**
     * Gets the first name of the sender.
     * 
     * @return              the sender's first name
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
     * @return              the sender's last name
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
     * Gets the message body of the email.
     * 
     * @return              the message body of the email
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message body of the email.
     * 
     * @param message       the message body of the email
     */
    public void setMessage(String message) {
        this.message = message.trim();
    }

    /**
     * Gets the subject of the email.
     * 
     * @return              the subject of the email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email.
     * 
     * @param subject       the subject of the email
     */
    public void setSubject(String subject) {
        this.subject = subject.trim();
    }

    /**
     * Returns a String representation of this data transfer object.
     *
     * @return              the String representation of this data transfer object
     */
    @Override
    public String toString() {
        String content = "";
        content += "\nEmail Address: " + email;
        content += "\nFirst Name: " + firstName;
        content += "\nLast Name: " + lastName;
        content += "\nSubject: " + subject;
        content += "\nMessage: " + message;
        return content;
    }
}