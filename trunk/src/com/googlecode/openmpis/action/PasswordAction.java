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

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.form.PasswordForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.UserDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.UserService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.UserServiceImpl;
import com.googlecode.openmpis.util.Configuration;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Mail;
import com.googlecode.openmpis.util.Validator;

/**
 * The PasswordAction class provides the method to reset a user's password.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class PasswordAction extends Action {

    /**
     * The file logger
     */
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * This is the action called from the Struts framework.
     * 
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserService userService = new UserServiceImpl(new UserDAOImpl());
        LogService logService = new LogServiceImpl(new LogDAOImpl());
        PasswordForm passwordForm = (PasswordForm) form;

        // Check if form is valid
        if (isValidAccount(request, form)) {
            // Retrieve user
            User user = (User) userService.getUserByUsername(passwordForm.getUsername());
            String forward = "";

            // Check if user exists
            if (user == null) {
                ActionMessages errors = new ActionMessages();
                errors.add("user", new ActionMessage("error.login.invalid"));
                saveErrors(request, errors);

                return mapping.findForward(Constants.PASSWORD_REDO);
            } else {
                // Check if security question and answer match
                if ((passwordForm.getQuestion() == user.getQuestion()) && (passwordForm.getAnswer().equals(user.getAnswer()))) {
                    // Return email address
                    request.setAttribute("email", user.getEmail());

                    // Reset password
                    String password = "op3nmp!s";
                    user.setPassword(password);

                    // Set password modification event
                    Log resetLog = new Log();
                    resetLog.setLog("User " + user.getUsername() + " reset his password.");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    resetLog.setDate(sdf.format(System.currentTimeMillis()));

                    // Update user password and insert log
                    userService.updatePassword(user);
                    logService.insertLog(resetLog);
                    logger.info(resetLog.toString());

                    // Retrieve mail properties
                    Configuration config = new Configuration("mail.properties");
                    // Check if email sending is enabled
                    if (Boolean.parseBoolean(config.getProperty("mail.enable"))) {
                        Mail mail = new Mail();

                        // Send email
                        mail.send("Administrator", "", config.getProperty("mail.administrator"),
                                user.getEmail(), "Password Retrieval",
                                "Dear " + user.getFirstName() + "," +
                                "\n\nYour new password is " + password + ". You received this email because " +
                                "you have forgotten your password or someone pretending to be you " +
                                "is trying to log into the system." +
                                "\n\nYours truly," +
                                "\nAdministrator");
                    }

                    forward = Constants.PASSWORD_SUCCESS;
                } else {
                    ActionMessages errors = new ActionMessages();
                    errors.add("question", new ActionMessage("error.question.invalid"));
                    saveErrors(request, errors);

                    logger.info("Invalid password or answer for user " + passwordForm.getUsername() +
                            " from " + request.getRemoteAddr() + ".");

                    forward = Constants.PASSWORD_REDO;
                }

                return mapping.findForward(forward);
            }
        } else {
            logger.info("Invalid password retrieval credentials from " + request.getRemoteAddr() + ".");
            return mapping.findForward(Constants.PASSWORD_REDO);
        }
    }

    /**
     * Validates the inputs from the user form.
     * 
     * @param request       the HTTP Request we are processing
     * @param form          the ActionForm bean for this request
     * @return              <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    private boolean isValidAccount(HttpServletRequest request, ActionForm form) {
        ActionMessages errors = new ActionMessages();
        Validator validator = new Validator();
        boolean isValid = true;

        PasswordForm passwordForm = (PasswordForm) form;
        String username = passwordForm.getUsername();
        String answer = passwordForm.getAnswer();

        if (username == null) {
            errors.add("username", new ActionMessage(""));
        } else {
            if (username.length() < 1) {
                errors.add("username", new ActionMessage("error.username.required"));
            } else {
                if (!validator.isValidUsername(username)) {
                    errors.add("username", new ActionMessage("error.username.invalid"));
                }
            }
        }

        if (answer == null) {
            errors.add("answer", new ActionMessage(""));
        } else {
            if (answer.length() < 1) {
                errors.add("answer", new ActionMessage("error.answer.required"));
            } else {
                if (!validator.isValidKeyword(answer)) {
                    errors.add("answer", new ActionMessage("error.answer.invalid"));
                }
            }
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            isValid = false;
        }

        return isValid;
    }
}