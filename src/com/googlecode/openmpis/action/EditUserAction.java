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

import com.googlecode.openmpis.form.EditUserForm;
import com.googlecode.openmpis.model.User;
import com.googlecode.openmpis.model.SqlMapConfig;
import com.googlecode.openmpis.model.Log;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

/**
 * The EditUserAction class provides the method to edit administrators, encoders and investigators.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class EditUserAction extends Action {
    /**
     * The forwarding instance
     */
    private final static String SUCCESS = "success";
    private final static String EXPIRED = "expired";
    private final static String REDO = "redo";
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
            throws SQLException, Exception {
        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(EXPIRED);
        } else {
            User currentUser = (User) request.getSession().getAttribute("currentuser");
            
            // Check the group
            if((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
                SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
        
                try {
                    EditUserForm userForm = (EditUserForm) form;
                    
                    User checker = new User();
                    checker.setId(userForm.getId());
                    checker.setUsername(userForm.getUsername());
                    checker.setEmail(userForm.getEmail());

                    System.out.println("Username: " + userForm.getUsername());
                    System.out.println("Email: " + userForm.getEmail());
                    
                    // Check for duplicates
                    sqlMap.startTransaction();
                    List usernames = sqlMap.queryForList("checkUsername", checker);
                    List addresses = sqlMap.queryForList("checkEmail", checker);
                    sqlMap.commitTransaction();
                    sqlMap.endTransaction();

                    ////System.out.println("Usernames: " + usernames.size());
                    ////System.out.println("Addresses: " + addresses.size());
                    
                    // Check if updated username and email address already exists
                    if ((usernames.size() > 0) || (addresses.size() > 0)) {
                        System.out.println("duplicateerror");///////////////////
                        request.setAttribute("editduplicateerror", 1);
                        return mapping.findForward(REDO);
                    } else {
                        System.out.println("update");///////////////////////////
                        // Update user
                        User user = new User();
                        user.setGroupId(userForm.getGroupId());
                        user.setUsername(userForm.getUsername());
                        user.setPassword(userForm.getPassword());
                        user.setFirstName(userForm.getFirstName());
                        user.setLastName(userForm.getLastName());
                        user.setMiddleName(userForm.getMiddleName());
                        user.setEmail(userForm.getEmail());
                        user.setDesignation(userForm.getDesignation());
                        user.setAgency(userForm.getAgency());
                        user.setNumber(userForm.getNumber());
                        user.setQuestion(userForm.getQuestion());
                        user.setAnswer(userForm.getAnswer());
                        user.setStatus(userForm.getStatus());

                        // Log user modification event
                        Log log = new Log();
                        log.setLog("User " + user.getUsername() + " was updated by " + currentUser.getUsername() + ".");
                        log.setDate(new Date(System.currentTimeMillis()));

                        sqlMap.startTransaction();
                        sqlMap.insert("insertUser", user);
                        //sqlMap.update("updateUser", user);
                        sqlMap.insert("insertLog", log);
                        sqlMap.commitTransaction();
                        sqlMap.endTransaction();
                        
                        //if (row == 1) {
                            request.setAttribute("username", userForm.getUsername());
                            request.setAttribute("operation", "edit");

                            return mapping.findForward(SUCCESS);
                        //} else
                        //    return mapping.findForward(REDO);
                    }
                } catch (SQLException sqle){
                    sqlMap.endTransaction();
                    throw new SQLException(sqle.getCause());
                } catch (Exception e) {
                    throw new Exception(e.getCause());
                    //return mapping.findForward(FAILURE);
                }
            } else {
                return mapping.findForward(UNAUTHORIZED);
            }
        }
    }
}