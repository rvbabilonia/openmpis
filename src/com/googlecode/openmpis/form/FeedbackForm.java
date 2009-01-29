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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.googlecode.openmpis.model.Validator;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

import java.util.Properties;

/**
 * The FeedbackForm class provides methods to validate the feedback form inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
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
    * @return           the sender's first name
    */
    public String getFirstname() {
       return firstName;
    }

    /**
    * Sets the first name of the sender.
    * 
    * @param    firstName  the first name of the sender
    */
    public void setFirstname(String firstName) {
       this.firstName = firstName;
    }

   /**
    * Gets the last name of the sender.
    * 
    * @return           the sender's last name
    */
    public String getLastname() {
       return lastName;
    }

    /**
    * Sets the last name of the sender.
    * 
    * @param    lastName  the last name of the sender
    */
    public void setLastname(String lastName) {
       this.lastName = lastName;
    }

    /**
     * Gets the email address of the sender.
     * 
     * @return          the email address of the sender
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the sender.
     * 
     * @param   email   the email address of the sender
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the message body of the email.
     * 
     * @return          the message body of the email
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message body of the email.
     * 
     * @param   message the message body of the email
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the subject of the email.
     * 
     * @return          the subject of the email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email.
     * 
     * @param   subject the subject of the email
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /**
     * Gets the administrator's email address.
     * 
     * @return          the administrator's email address
     */
    public String getAdminEmail() {
        Properties properties = new Properties();

        // Retrieve administrator's email address
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("mail.properties");
            properties.load(is);
            is.close();
        } catch (FileNotFoundException fnfe){
            //throw new FileNotFoundException("Cannot find mail.properties file.");
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        return (String) properties.get("mail.administrator");
    }

    /**
     * Sole constructor.
     */
    public FeedbackForm() {
       super();
    }

    /**
     * Validates the inputs from the feedback form.
     * 
     * @param   mapping the action mapping
     * @param   request the HTTP request
     * @return          the action errors
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        Validator v = new Validator();
        
        request.setAttribute("firstname", firstName);
        request.setAttribute("lastname", lastName);
        request.setAttribute("email", email);
        request.setAttribute("subject", subject);
        request.setAttribute("message", message);

        if (firstName == null)
            errors.add("firstname", new ActionMessage(""));
        else {
            if (firstName.length() < 1)
                errors.add("firstname", new ActionMessage("error.firstname.required"));
            else {
                if ((!v.isValidFirstName(firstName)))
                    errors.add("firstname", new ActionMessage("error.firstname.invalid"));
            }
        }

        if (lastName == null)
            errors.add("lastname", new ActionMessage(""));
        else {
            if (lastName.length() < 1)
                errors.add("lastname", new ActionMessage("error.lastname.required"));
            else {
                if ((!v.isValidLastName(lastName)))
                    errors.add("lastname", new ActionMessage("error.lastname.invalid"));
            }
        }

        if (email == null)
            errors.add("email", new ActionMessage(""));
        else {
            if (email.length() < 1)
                errors.add("email", new ActionMessage("error.email.required"));
            else {
                if (!v.isValidEmailAddress(email))
                    errors.add("email", new ActionMessage("error.email.invalid"));
            }
        }

        if (subject == null)
            errors.add("subject", new ActionMessage(""));
        else {
            if  (subject.length() < 2)
                errors.add("subject", new ActionMessage("error.subject.invalid"));
        }

        if (message == null)
            errors.add("message", new ActionMessage(""));
        else {
            if  (message.length() < 10)
                errors.add("message", new ActionMessage("error.message.invalid"));
        }

        return errors;
    }
}