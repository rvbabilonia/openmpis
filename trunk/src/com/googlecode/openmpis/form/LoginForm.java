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
public class LoginForm extends ActionForm {

    /**
     * The username
     */
    private String j_username;
    /**
     * The password
     */
    private String j_password;

    /**
     * Gets the username.
     * 
     * @return           the username
     */
    public String getJ_username() {
        return j_username;
    }

    /**
     * Sets the username.
     * 
     * @param    j_username  the username
     */
    public void setJ_username(String j_username) {
        this.j_username = j_username;
    }

    /**
     * Gets the password.
     * 
     * @return          the password
     */
    public String getJ_password() {
        return j_password;
    }

    /**
     * Sets the password.
     * 
     * @param   j_password   the password
     */
    public void setJ_password(String j_password) {
        this.j_password = j_password;
    }

    /**
     * Sole constructor.
     */
    public LoginForm() {
        super();
    }

    /**
     * Reset all properties to their default values.
     *
     * @param mapping the mapping used to select this instance
     * @param request the servlet request we are processing
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        j_username = null;
        j_password = null;
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
        Validator validator = new Validator();

        // Retain j_username in the login form after an incorrect attempt
        request.setAttribute("j_username", j_username);

        // Check if j_username is null
        if (j_username == null) {
            errors.add("j_username", new ActionMessage(""));
        } else {
            // Check if j_username is empty
            if (j_username.length() < 1) {
                errors.add("j_username", new ActionMessage("error.username.required"));
            } else {
                // Check if j_username consists of 5 or 6 alphanumeric characters
                if ((!validator.isValidUsername(j_username))) {
                    errors.add("j_username", new ActionMessage("error.username.invalid"));
                }
            }
        }

        // Check if j_username is null
        if (j_username == null) {
            errors.add("j_username", new ActionMessage(""));
        } else {
            // Check if the MD5-encrypted j_password is empty
            if (j_password.equals("d41d8cd98f00b204e9800998ecf8427e")) {
                errors.add("j_password", new ActionMessage("error.password.required"));
            }
        }
        
        return errors;
    }
}