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

package com.googlecode.openmpis.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import com.googlecode.openmpis.form.AddUserForm;
import com.googlecode.openmpis.model.User;
import com.googlecode.openmpis.model.SqlMapConfig;
import com.googlecode.openmpis.model.Log;
import com.googlecode.openmpis.model.Mail;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * The AddUserAction class provides the method to add administrators, encoders and investigators.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class AddUserAction extends Action {
    /**
     * The forwarding instance
     */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private final static String REDO = "redo";
    private final static String EXPIRED = "expired";
    private final static String UNAUTHORIZED = "unauthorized";
    
    /**
     * This is the action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(EXPIRED);
        } else {
            User currentUser = (User) request.getSession().getAttribute("currentuser");
            
            // Check the group
            if((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
                SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
                
                try {
                    AddUserForm userForm = (AddUserForm) form;
                    
                    User checker = new User();
                    checker.setId(userForm.getId());
                    checker.setUsername(userForm.getUsername());
                    checker.setEmail(userForm.getEmail());
                    
                    // Check for duplicates
                    sqlMap.startTransaction();
                    List usernames = sqlMap.queryForList("checkUsername", checker);
                    List addresses = sqlMap.queryForList("checkEmail", checker);
                    sqlMap.commitTransaction();
                    sqlMap.endTransaction();
                    
                    // Check if updated username and email address already exists
                    if ((usernames.size() > 0) || (addresses.size() > 0)) {
                        request.setAttribute("addduplicateerror", 1);
                        return mapping.findForward(REDO);
                    } else {
                        // Create new user
                        User user = new User();
                        user.setGroupId(userForm.getGroupId());
                        user.setUsername(userForm.getUsername());
                        String password = "p@$$w0rd";
                        user.setPassword(password);
                        user.setFirstName(userForm.getFirstName());
                        user.setLastName(userForm.getLastName());
                        user.setMiddleName(userForm.getMiddleName());
                        user.setEmail(userForm.getEmail());
                        user.setDesignation(userForm.getDesignation());
                        user.setAgency(userForm.getAgency());
                        user.setNumber(userForm.getNumber());
                        Date date = new Date(System.currentTimeMillis());
                        user.setDate(date);
                        user.setCreatorId(currentUser.getCreatorId());
                        user.setQuestion(0);
                        user.setStatus(userForm.getStatus());
                        
                        // Email username and password to user
                        Mail mail = new Mail();
                        mail.send(currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), user.getEmail(),
                                "Account Creation",
                                "Dear " + user.getFirstName() + "," +
                                "\n\nYou received this email because your account has been created." +
                                "Your password is " + password + "." +
                                "You may log in to the system now." +
                                "\n\nIf you have any questions, please feel free to email me." +
                                "\n\nYours truly," +
                                "\n" + currentUser.getFirstName(),
                                request.getRemoteAddr());

                        // Log user creation event
                        Log log = new Log();
                        log.setLog("User " + user.getUsername() + " was created by " + currentUser.getUsername() + ".");
                        log.setDate(date);

                        sqlMap.startTransaction();
                        sqlMap.insert("insertUser", user);
                        sqlMap.insert("insertLog", log);
                        sqlMap.commitTransaction();
                        sqlMap.endTransaction();
                        
                        request.setAttribute("username", userForm.getUsername());
                        request.setAttribute("operation", "add");

                        return mapping.findForward(SUCCESS);
                    }
                } catch (SQLException sqle){
                    sqlMap.endTransaction();
                    throw new SQLException(sqle.getCause());
                } catch (FileNotFoundException fnfe) {
                    return mapping.findForward(FAILURE);
                } catch (AddressException ae) {
                    return mapping.findForward(FAILURE);
                } catch (MessagingException me) {
                    return mapping.findForward(FAILURE);
                } catch (Exception e) {
                    return mapping.findForward(FAILURE);
                }
            } else {
                return mapping.findForward(UNAUTHORIZED);
            }
        }
    }
}