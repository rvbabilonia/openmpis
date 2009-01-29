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
 * The AddUserForm class provides methods to validate the user form inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class AddUserForm extends ActionForm {
    /**
     * The ID of the user
     */
   private int id;
    /**
     * The group Id of the user
     */
   private int groupId;
    /**
     * The username
     */
   private String username;
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
     * The status of the user
     */
    private int status;

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
    * Gets the group Id of the user.
    * 
    * @return               the group Id of the user
    */
    public int getGroupId() {
       return groupId;
    }

    /**
    * Sets the group Id of the user.
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
     * Sole constructor.
     */
    public AddUserForm() {
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
        groupId = 1;
        username = null;
        firstName = null;
        middleName = null;
        lastName = null;
        email = null;
        designation = null;
        agency = null;
        number = null;
        status = 0;
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
        
        com.googlecode.openmpis.model.User user = new com.googlecode.openmpis.model.User();
        user.setGroupId(groupId);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        user.setEmail(email);
        user.setDesignation(designation);
        user.setAgency(agency);
        user.setNumber(number);
        user.setStatus(status);
        request.setAttribute("user", user);
       
        Validator v = new Validator();

        if (username == null) {
            errors.add("username", new ActionMessage(""));
        } else {
            if (username.length() < 1) {
                errors.add("username", new ActionMessage("error.username.required"));
            } else {
                if (!v.isValidUsername(username)) {
                    errors.add("username", new ActionMessage("error.username.invalid"));
                }
            }
        }

        if (firstName == null) {
            errors.add("firstname", new ActionMessage(""));
        } else {
            if (firstName.length() < 1) {
                errors.add("firstname", new ActionMessage("error.firstname.required"));
            } else {
                if (!v.isValidFirstName(firstName)) {
                    errors.add("firstname", new ActionMessage("error.firstname.invalid"));
                }
            }
        }

        if (middleName == null) {
            errors.add("middlename", new ActionMessage(""));
        } else {
            if (middleName.length() < 1) {
                errors.add("middlename", new ActionMessage("error.middlename.required"));
            } else {
                if (!v.isValidLastName(middleName)) {
                    errors.add("middlename", new ActionMessage("error.middlename.invalid"));
                }
            }
        }

        if (lastName == null) {
            errors.add("lastname", new ActionMessage(""));
        } else {
            if (lastName.length() < 1) {
                errors.add("lastname", new ActionMessage("error.lastname.required"));
            } else {
                if (!v.isValidLastName(lastName)) {
                    errors.add("lastname", new ActionMessage("error.lastname.invalid"));
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

        if (designation == null) {
            errors.add("designation", new ActionMessage(""));
        } else {
            if (designation.length() < 1) {
                errors.add("designation", new ActionMessage("error.designation.required"));
            } else {
                if (!v.isValidKeyword(designation)) {
                    errors.add("designation", new ActionMessage("error.designation.invalid"));
                }
            }
        }

        if (agency == null) {
            errors.add("agency", new ActionMessage(""));
        } else {
            if (agency.length() < 1) {
                errors.add("agency", new ActionMessage("error.agency.required"));
            } else {
                if (!v.isValidKeyword(agency)) {
                    errors.add("agency", new ActionMessage("error.agency.invalid"));
                }
            }
        }

        if (number == null) {
            errors.add("number", new ActionMessage(""));
        } else {
            if (number.length() < 1) {
                errors.add("number", new ActionMessage("error.number.required"));
            } else {
                if (!v.isValidNumber(number)) {
                    errors.add("number", new ActionMessage("error.number.invalid"));
                }
            }
        }

        return errors;
    }
}