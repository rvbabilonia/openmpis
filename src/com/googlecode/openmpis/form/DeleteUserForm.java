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

import java.util.ArrayList;

/**
 * The AddUserForm class provides methods to validate the user form inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class DeleteUserForm extends ActionForm {
    /**
     * The user IDs to be deleted
     */
    private ArrayList users = new ArrayList();

   /**
    * Gets the user IDs to be deleted.
    * 
    * @return               the user IDs to be deleted
    */
    public ArrayList getUsers() {
       return users;
    }

    /**
    * Sets the user IDs to be deleted.
    * 
    * @param    users     the user IDs to be deleted
    */
    public void setUsers(ArrayList users) {
       this.users = users;
    }
    
    /**
     * Sole constructor.
     */
    public DeleteUserForm() {
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
    }

    /**
     * Validates the inputs from the user form.
     * 
     * @param   mapping the action mapping
     * @param   request the HTTP request
     * @return          the action errors
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

            //errors.add("username", new ActionMessage(""));

        return errors;
    }
}