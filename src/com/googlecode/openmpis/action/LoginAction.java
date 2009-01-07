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

import com.ibatis.sqlmap.client.SqlMapClient;

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
    
    /**
     * This is the action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws  java.lang.Exception
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        LoginForm loginForm = (LoginForm) form;
        SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
        User user = null;

        // Retrieve user object with the specified j_username
        try {
            user = (User) sqlMap.queryForObject("getUser", loginForm.getJ_username());

            // Check if j_username exists in the database
            if (user == null) {
                request.setAttribute("error", "1");
                return mapping.findForward(REDO);
            } else {
                // Check if j_password matches
                if (user.getPassword().equals(loginForm.getJ_password())) {                        
                    // Start the session
                    HttpSession session = request.getSession();
                    session.setAttribute("firstname", user.getFirstName());
                    session.setAttribute("userid", user.getId());
                    session.setAttribute("groupid", user.getGroupID());

                    return mapping.findForward(SUCCESS);
                } else {
                    request.setAttribute("error", "1");
                    return mapping.findForward(REDO);
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Cannot connect to the database.");
        }
    }
}