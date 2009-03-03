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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.dto.Message;
import com.googlecode.openmpis.form.FeedbackForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.MessageDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.MessageService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.MessageServiceImpl;
import com.googlecode.openmpis.util.Configuration;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Mail;
import com.googlecode.openmpis.util.Validator;
import org.apache.log4j.Logger;

/**
 * The FeedbackAction class provides the method to email a feedback to the administrator.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class FeedbackAction extends Action {

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
        FeedbackForm feedbackForm = (FeedbackForm) form;
        MessageService messageService = new MessageServiceImpl(new MessageDAOImpl());
        LogService logService = new LogServiceImpl(new LogDAOImpl());

        // Check if form is valid
        if (isValidFeedback(request, form)) {
            // Retrieve mail properties
            Configuration config = new Configuration("mail.properties");

            // Create message
            Message message = new Message();
            message.setFirstName(feedbackForm.getFirstName());
            message.setLastName(feedbackForm.getLastName());
            message.setEmail(feedbackForm.getEmail());
            message.setSubject(feedbackForm.getSubject());
            message.setMessage(feedbackForm.getMessage());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(System.currentTimeMillis());
            message.setDate(date);
            message.setUserId(Integer.parseInt(config.getProperty("id.administrator")));
            message.setIpAddress(request.getRemoteAddr());

            // Log message receipt
            Log feedbackLog = new Log();
            feedbackLog.setLog("Email received from " + feedbackForm.getEmail() + ".");
            feedbackLog.setDate(date);

            // Insert feedback and log
            boolean isInserted = messageService.insertFeedback(message);
            logService.insertLog(feedbackLog);
            logger.info(feedbackLog.toString());

            if (isInserted) {
                // Check if email sending is enabled
                if (Boolean.parseBoolean(config.getProperty("mail.enable"))) {
                    Mail mail = new Mail();

                    // Send email
                    mail.send(feedbackForm.getFirstName(), feedbackForm.getLastName(), feedbackForm.getEmail(),
                            config.getProperty("mail.administrator"), feedbackForm.getSubject(), feedbackForm.getMessage());
                }

                return mapping.findForward(Constants.FEEDBACK_SUCCESS);
            } else {
                return mapping.findForward(Constants.FEEDBACK_REDO);
            }
        } else {
            logger.info("Invalid feedback from " + request.getRemoteAddr() + ".");

            return mapping.findForward(Constants.FEEDBACK_REDO);
        }
    }

    /**
     * Validates the inputs from the user form.
     * 
     * @param request       the HTTP Request we are processing
     * @param form          the ActionForm bean for this request
     * @return              <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    private boolean isValidFeedback(HttpServletRequest request, ActionForm form) {
        ActionMessages errors = new ActionMessages();
        Validator validator = new Validator();
        boolean isValid = true;

        FeedbackForm feedbackForm = (FeedbackForm) form;
        String firstName = feedbackForm.getFirstName();
        String lastName = feedbackForm.getLastName();
        String email = feedbackForm.getEmail();
        String subject = feedbackForm.getSubject();
        String message = feedbackForm.getMessage();

        if (firstName.length() < 1) {
            errors.add("firstname", new ActionMessage("error.firstname.required"));
        } else {
            if ((!validator.isValidFirstName(firstName))) {
                errors.add("firstname", new ActionMessage("error.firstname.invalid"));
            }
        }

        if (lastName.length() < 1) {
            errors.add("lastname", new ActionMessage("error.lastname.required"));
        } else {
            if ((!validator.isValidLastName(lastName))) {
                errors.add("lastname", new ActionMessage("error.lastname.invalid"));
            }
        }

        if (email.length() < 1) {
            errors.add("email", new ActionMessage("error.email.required"));
        } else {
            if (!validator.isValidEmailAddress(email)) {
                errors.add("email", new ActionMessage("error.email.invalid"));
            }
        }

        if (subject.length() < 2) {
            errors.add("subject", new ActionMessage("error.subject.invalid"));
        }

        if (message.length() < 10) {
            errors.add("message", new ActionMessage("error.message.invalid"));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            isValid = false;
        }

        return isValid;
    }
}