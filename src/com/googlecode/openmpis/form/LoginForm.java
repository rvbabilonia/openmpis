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
 * The LoginForm class provides methods to validate the login form inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
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
     * @return              the username
     */
    public String getJ_username() {
        return j_username;
    }

    /**
     * Sets the username.
     * 
     * @param j_username    the username
     */
    public void setJ_username(String j_username) {
        this.j_username = j_username.trim();
    }

    /**
     * Gets the password.
     * 
     * @return              the password
     */
    public String getJ_password() {
        return j_password;
    }

    /**
     * Sets the password.
     * 
     * @param j_password    the password
     */
    public void setJ_password(String j_password) {
        this.j_password = j_password.trim();
    }
}