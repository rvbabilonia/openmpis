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

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import com.googlecode.openmpis.form.LoginForm;
import com.googlecode.openmpis.model.User;
import com.googlecode.openmpis.model.SqlMapConfig;
import com.googlecode.openmpis.model.Log;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.Date;
import java.sql.SQLException;

/**
 * The SearchAction class provides the method to pass queries and results.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class LoginAction extends Action {
    /**
     * The forwarding instances
     */
    private final static String SUCCESS = "success";
    private final static String REDO = "redo";
    private final static String FAILURE = "failure";
    
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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        LoginForm loginForm = (LoginForm) form;
        SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();

        // Retrieve user object with the specified j_username
        try {
            sqlMap.startTransaction();
            User user = (User) sqlMap.queryForObject("getUser", loginForm.getJ_username());
            sqlMap.commitTransaction();
            sqlMap.endTransaction();

            // Check if j_username exists in the database
            if (user == null) {
                request.setAttribute("usererror", "1");
                return mapping.findForward(REDO);
            } else {
                // Check if j_password matches
                if (user.getPassword().equals(loginForm.getJ_password())) {                     
                    // Start the session
                    HttpSession session = request.getSession();
                    session.setAttribute("currentuser", user);
                    
                    // Update user log
                    Date date = new Date(System.currentTimeMillis());
                    user.setLastLogin(date);
                    String ipAddress = request.getRemoteAddr();
                    user.setIpAddress(ipAddress);
        
                    Log log = new Log();
                    log.setLog("User " + user.getUsername() + " logged in from " + ipAddress + ".");
                    log.setDate(date);
                    
                    sqlMap.startTransaction();
                    int row = sqlMap.update("updateLogin", user);
                    sqlMap.insert("insertLog", log);
                    sqlMap.commitTransaction();
                    sqlMap.endTransaction();

                    if (row == 1)
                        return mapping.findForward(SUCCESS);
                    else
                        return mapping.findForward(FAILURE);
                } else {
                    request.setAttribute("usererror", "1");
                    return mapping.findForward(REDO);
                }
            }
        } catch (SQLException sqle) {
            sqlMap.endTransaction();
            throw new SQLException(sqle.getCause());
        }
    }
}