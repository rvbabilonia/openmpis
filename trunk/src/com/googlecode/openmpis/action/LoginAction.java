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

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.form.LoginForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.UserDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.UserService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.UserServiceImpl;
import com.googlecode.openmpis.util.Constants;

import com.googlecode.openmpis.util.Validator;
import java.sql.Date;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * The LoginAction class provides the method to authenticate a user.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class LoginAction extends Action {

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
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginForm loginForm = (LoginForm) form;
        UserService userService = new UserServiceImpl(new UserDAOImpl());
        LogService logService = new LogServiceImpl(new LogDAOImpl());

        // Check if form is valid
        if (isValidLogin(request, form)) {
            // Retrieve user with the specified j_username
            User user = (User) userService.getUserByUsername(loginForm.getJ_username());
            String forward = "";

            // Check if j_username exists in the database
            if ((user != null) && (user.getPassword().equals(loginForm.getJ_password()))) {
                // Store the user on the session
                request.getSession().setAttribute("currentuser", user);

                // Set user's new login date and IP address
                Date date = new Date(System.currentTimeMillis());
                user.setLastLogin(date);
                String ipAddress = request.getRemoteAddr();
                user.setIpAddress(ipAddress);

                // Set user login event
                Log log = new Log();
                log.setLog("User " + user.getUsername() + " logged in from " + ipAddress + ".");
                log.setDate(date);

                // Update user's last login date and IP address and insert log
                userService.updateLogin(user);
                logService.insertLog(log);

                forward = Constants.LOGIN_SUCCESS;
            } else {
                ActionMessages errors = new ActionMessages();
                errors.add("login", new ActionMessage("error.login.invalid"));
                saveErrors(request, errors);

                forward = Constants.LOGIN_REDO;
            }

            return mapping.findForward(forward);
        } else {
            return mapping.findForward(Constants.LOGIN_REDO);
        }
    }

    /**
     * Validates the inputs from the user form.
     * 
     * @param request   the HTTP Request we are processing
     * @param form      the ActionForm bean for this request
     * @return          <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    private boolean isValidLogin(HttpServletRequest request, ActionForm form) {
        ActionMessages errors = new ActionMessages();
        Validator validator = new Validator();
        boolean isValid = true;

        LoginForm loginForm = (LoginForm) form;
        String j_username = loginForm.getJ_username();
        String j_password = loginForm.getJ_password();

        // Check if j_username is null
        if (j_username == null) {
            errors.add("j_username", new ActionMessage(""));
        } else {
            // Check if j_username is empty
            if (j_username.length() < 1) {
                errors.add("j_username", new ActionMessage("error.username.required"));
            } else {
                // Check if j_username consists of 5 or 6 alphanumeric characters
                if ((!validator.isValidUsername(j_username))) {
                    errors.add("j_username", new ActionMessage("error.username.invalid"));
                }
            }
        }

        // Check if j_username is null
        if (j_username == null) {
            errors.add("j_username", new ActionMessage(""));
        } else {
            // Check if the MD5-encrypted j_password is empty
            if (j_password.equals("d41d8cd98f00b204e9800998ecf8427e")) {
                errors.add("j_password", new ActionMessage("error.password.required"));
            }
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            isValid = false;
        }

        return isValid;
    }
}