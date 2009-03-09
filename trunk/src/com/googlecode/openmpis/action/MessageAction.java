/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.googlecode.openmpis.action;

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.dto.Message;
import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.form.MessageForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.MessageDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.PersonDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.UserDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.MessageService;
import com.googlecode.openmpis.persistence.ibatis.service.PersonService;
import com.googlecode.openmpis.persistence.ibatis.service.UserService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.MessageServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.PersonServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.UserServiceImpl;
import com.googlecode.openmpis.util.Configuration;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Mail;
import com.googlecode.openmpis.util.Pagination;
import com.googlecode.openmpis.util.Validator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts.actions.DispatchAction;

/**
 * The MessageAction class provides the method to list and read feedbacks and sightings
 * and report sightings.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class MessageAction extends DispatchAction {

    /**
     * The message service
     */
    private MessageService messageService = new MessageServiceImpl(new MessageDAOImpl());
    /**
     * The user service
     */
    private UserService userService = new UserServiceImpl(new UserDAOImpl());
    /**
     * The person service
     */
    private PersonService personService = new PersonServiceImpl(new PersonDAOImpl());
    /**
     * The log service
     */
    private LogService logService = new LogServiceImpl(new LogDAOImpl());
    /**
     * The file logger
     */
    private Logger logger = Logger.getLogger(this.getClass());
    /**
     * The pagination context
     */
    private Pagination pagination = new Pagination();

    /**
     * Lists all sightings.
     * This is the list action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listSighting(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if (currentUser.getGroupId() == 2) {
            String page = request.getParameter("page");

            // Set pagination direction
            if (page != null) {
                if (page.equals("next")) {
                    pagination.nextPage();
                } else if (page.equals("previous")) {
                    pagination.previousPage();
                } else if (page.equals("start")) {
                    pagination.firstPage();
                } else if (page.equals("end")) {
                    pagination.lastPage();
                }
            }

            // Retrieve list of sightings for a given person
            List<Message> messageList = messageService.getAllFeedbacks(pagination, currentUser.getId());

            // Return list of sightings
            request.setAttribute("messagelist", messageList);

            // Return number of sightings
            request.setAttribute("messagecount", messageList.size());

            // Return current page
            request.setAttribute("currentpage", pagination.getCurrentPage());

            // Return total number of pages
            request.setAttribute("totalpages", pagination.getTotalPages());

            // Return total results
            request.setAttribute("totalresults", pagination.getTotalResults());

            // Return max results
            request.setAttribute("maxresults", pagination.getMaxResults());

            // Return condition if there are more pages
            request.setAttribute("morepages", pagination.hasMorePages());

            return mapping.findForward(Constants.LIST_SIGHTING);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Lists all feedbacks.
     * This is the list action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listFeedback(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if (currentUser.getGroupId() == 0) {
            String page = (String) request.getParameter("page");

            // Set pagination direction
            if (page != null) {
                if (page.equals("next")) {
                    pagination.nextPage();
                } else if (page.equals("previous")) {
                    pagination.previousPage();
                } else if (page.equals("start")) {
                    pagination.firstPage();
                } else if (page.equals("end")) {
                    pagination.lastPage();
                }
            }

            // Retrieve list of feedbacks for current user
            List<Message> messageList = messageService.getAllFeedbacks(pagination, currentUser.getId());

            // Return list of feedbacks
            request.setAttribute("messagelist", messageList);

            // Return number of feedbacks
            request.setAttribute("messagecount", messageList.size());

            // Return current page
            request.setAttribute("currentpage", pagination.getCurrentPage());

            // Return total number of pages
            request.setAttribute("totalpages", pagination.getTotalPages());

            // Return total results
            request.setAttribute("totalresults", pagination.getTotalResults());

            // Return max results
            request.setAttribute("maxresults", pagination.getMaxResults());

            // Return condition if there are more pages
            request.setAttribute("morepages", pagination.hasMorePages());

            return mapping.findForward(Constants.LIST_FEEDBACK);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

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
    public ActionForward newSighting(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        MessageForm messageForm = (MessageForm) form;
        if (!request.getParameter("personid").isEmpty()) {
            Person person = personService.getPersonById(Integer.parseInt(request.getParameter("personid")));

            if (person.getNickname() != null) {
                messageForm.setSubject("Sighting for " + person.getFirstName() + " " + person.getLastName());
            } else {
                messageForm.setSubject("Sighting for " + person.getFirstName() + " \"" + person.getNickname() + "\" "+ person.getLastName());
            }
            request.setAttribute("personid", person.getId());

            return mapping.findForward(Constants.ADD_SIGHTING);
        } else {
            return mapping.findForward(Constants.LIST_PERSON);
        }
    }

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
    public ActionForward addSighting(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        MessageForm messageForm = (MessageForm) form;

        // Check if form is valid
        if (isValidMessage(request, form)) {
            // Retrieve mail properties
            Configuration config = new Configuration("mail.properties");

            Person person = personService.getPersonById(Integer.parseInt(request.getParameter("personid")));
            User investigator = userService.getUserById(person.getInvestigatorId());

            // Create message
            Message message = new Message();
            message.setFirstName(messageForm.getFirstName());
            message.setLastName(messageForm.getLastName());
            message.setEmail(messageForm.getEmail());
            message.setSubject(messageForm.getSubject());
            message.setMessage(messageForm.getMessage());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(System.currentTimeMillis());
            message.setDate(date);
            message.setPersonId(person.getId());
            message.setUserId(investigator.getId());
            message.setIpAddress(request.getRemoteAddr());

            // Log message receipt
            Log sightingLog = new Log();
            sightingLog.setLog("Sighting received from " + messageForm.getEmail() + ".");
            sightingLog.setDate(date);

            // Insert feedback and log
            boolean isInserted = messageService.insertSighting(message);
            logService.insertLog(sightingLog);
            logger.info(sightingLog.toString());

            if (isInserted) {
                // Check if email sending is enabled
                if (Boolean.parseBoolean(config.getProperty("mail.enable"))) {
                    Mail mail = new Mail();

                    // Send email
                    mail.send(messageForm.getFirstName(), messageForm.getLastName(), messageForm.getEmail(),
                            investigator.getEmail(), messageForm.getSubject(), messageForm.getMessage());
                }

                return mapping.findForward(Constants.ADD_SIGHTING_SUCCESS);
            } else {
                request.setAttribute("personid", request.getParameter("personid"));
                
                return mapping.findForward(Constants.ADD_SIGHTING_REDO);
            }
        } else {
            request.setAttribute("personid", request.getParameter("personid"));

            logger.info("Invalid sighting from " + request.getRemoteAddr() + ".");

            return mapping.findForward(Constants.ADD_SIGHTING_REDO);
        }
    }

    /**
     * Views the sighting.
     * This is the view sighting action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward viewSighting(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;
        MessageForm messageForm = (MessageForm) form;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if (currentUser.getGroupId() == 2) {
            // Retrieve message ID
            int id = 0;
            try {
                id = (request.getParameter("id") != null) ? Integer.parseInt(request.getParameter("id")) : currentUser.getId();
            } catch (NumberFormatException nfe) {
                id = currentUser.getId();
            }
            Message sighting = (Message) messageService.getMessageById(id);

            // Return message
            messageForm.setId(sighting.getId());
            messageForm.setSubject(sighting.getSubject());
            messageForm.setMessage(sighting.getMessage());
            messageForm.setFirstName(sighting.getFirstName());
            messageForm.setLastName(sighting.getLastName());
            messageForm.setEmail(sighting.getEmail());
            messageForm.setIpAddress(sighting.getIpAddress());
            messageForm.setDate(sighting.getDate());
            messageForm.setUserId(sighting.getUserId());
            messageForm.setStatus(sighting.getStatus());
            messageForm.setPersonId(sighting.getPersonId());

            //messageService.

            return mapping.findForward(Constants.VIEW_MESSAGE);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Validates the inputs from the message form.
     * 
     * @param request       the HTTP Request we are processing
     * @param form          the ActionForm bean for this request
     * @return              <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    private boolean isValidMessage(HttpServletRequest request, ActionForm form) {
        ActionMessages errors = new ActionMessages();
        Validator validator = new Validator();
        boolean isValid = true;

        MessageForm messageForm = (MessageForm) form;
        String firstName = messageForm.getFirstName();
        String lastName = messageForm.getLastName();
        String email = messageForm.getEmail();
        String subject = messageForm.getSubject();
        String message = messageForm.getMessage();

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