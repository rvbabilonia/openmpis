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

/**
 * The FeedbackForm class provides methods to validate the feedback form inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class RelativeForm extends ActionForm {
    
    /**
     * The full name of the sender
     */
    private String name;
    
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
    * Gets the full name of the sender.
    * 
    * @return           the sender's full name
    */
    public String getName() {
       return name;
    }

    /**
    * Sets the full name of the sender.
    * 
    * @param    name  the full name of the sender
    */
    public void setName(String name) {
       this.name = name;
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
     * Sole constructor.
     */
    public RelativeForm() {
       super();
    }

    /**
     * Validates the inputs from the feedback form.
     * 
     * @param   mapping the action mapping
     * @param   request the HTTP request
     * @return          the action errors
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("subject", subject);
        request.setAttribute("message", message);
       
        Validator v = new Validator();

        if (name == null) {
            errors.add("name", new ActionMessage(""));
        } else {
            if (name.length() < 1) {
                errors.add("name", new ActionMessage("error.name.required"));
            } else {
                if ((!v.isValidFirstName(getName()))) {
                    errors.add("name", new ActionMessage("error.name.invalid"));
                }
            }
        }

        if (email == null) {
            errors.add("email", new ActionMessage(""));
        } else {
            if (email.length() < 1) {
                errors.add("email", new ActionMessage("error.email.required"));
            } else {
                if (!v.isValidEmailAddress(email)) {
                    errors.add("email", new ActionMessage("error.email.invalid"));
                }
            }
        }

        if (subject == null) {
            errors.add("subject", new ActionMessage(""));
        } else {
            if  (subject.length() < 1) {
                errors.add("subject", new ActionMessage("error.subject.required"));
            }
        }

        if (message == null) {
            errors.add("message", new ActionMessage(""));
        } else {
            if  (message.length() < 1) {
                errors.add("subject", new ActionMessage("error.message.required"));
            }
        }

        return errors;
    }
}