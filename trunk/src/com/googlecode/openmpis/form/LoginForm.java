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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * The LoginForm class provides methods to validate the login form inputs.
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
}