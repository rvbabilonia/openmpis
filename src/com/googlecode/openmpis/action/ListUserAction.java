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

import com.googlecode.openmpis.model.SqlMapConfig;
import com.googlecode.openmpis.model.User;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;

import java.util.List;

/**
 * The SearchAction class provides the method to pass queries and results.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class ListUserAction extends Action {

    /**
     * The forwarding instances
     */
    private final static String SUCCESS = "success";
    private final static String UNAUTHORIZED = "unauthorized";
    private final static String EXPIRED = "expired";
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
        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(EXPIRED);
        } else {
            User currentUser = (User) request.getSession().getAttribute("currentuser");
            
            // Check the group
            if((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
                SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();

                try {
                    // Retrieve users
                    sqlMap.startTransaction();
                    List userList = sqlMap.queryForList("getUsers");
                    sqlMap.commitTransaction();
                    sqlMap.endTransaction();
                    request.setAttribute("userlist", userList);

                    return mapping.findForward(SUCCESS);
                } catch (SQLException sqle) {
                    sqlMap.endTransaction();
                    throw new SQLException(sqle.getCause());
                }
            } else {
                return mapping.findForward(UNAUTHORIZED);
            }
        }
    }
}