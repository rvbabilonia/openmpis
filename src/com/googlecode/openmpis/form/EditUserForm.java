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

import java.sql.Date;

/**
 * The UserForm class provides methods to validate the user form inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class EditUserForm extends ActionForm {
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
    private Date lastLogin;
    /**
     * The date this user account was created
     */
    private Date date;
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
    public Date getLastLogin() {
       return lastLogin;
    }

    /**
     * Sets the date of the user's last login
     * 
     * @param   lastLogin   the user's last login date
     */
    public void setLastLogin(Date lastLogin) {
       this.lastLogin = lastLogin;
    }
    
    /**
     * Gets the date this user account was created.
     * 
     * @return              the date this user account was created
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * Sets the date this user account was created.
     * 
     * @param   date        the date this user account was created
     */
    public void setDate(Date date) {
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
     * Sole constructor.
     */
    public EditUserForm() {
       super();
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
        user.setId(id);
        user.setGroupId(groupId);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        user.setEmail(email);
        user.setDesignation(designation);
        user.setAgency(agency);
        user.setNumber(number);
        user.setQuestion(question);
        user.setAnswer(answer);
        user.setStatus(status);
        user.setCreatorId(creatorId);
        user.setLastLogin(lastLogin);
        user.setIpAddress(ipAddress);
        user.setDate(date);
        request.setAttribute("user", user);
        if (answer != null) {
            request.setAttribute("answer", answer);
        }
       
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

        if (password == null) {
            errors.add("password", new ActionMessage(""));
        } else {
            if (password.length() < 1) {
                errors.add("password", new ActionMessage("error.password.required"));
            } else {
                if (!v.isValidPassword(password)) {
                    errors.add("password", new ActionMessage("error.password.invalid"));
                } else if (password.equals(retype)) {
                    errors.add("password", new ActionMessage("error.password.mismatch"));
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

        if (answer == null) {
            errors.add("answer", new ActionMessage(""));
        } else {
            if (answer.length() < 1) {
                errors.add("answer", new ActionMessage("error.answer.required"));
            } else {
                if (!v.isValidKeyword(answer)) {
                    errors.add("answer", new ActionMessage("error.answer.invalid"));
                }
            }
        }

        return errors;
    }
}