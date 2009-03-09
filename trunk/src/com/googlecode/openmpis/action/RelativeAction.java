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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.dto.Relative;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.form.RelativeForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.PersonDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.RelativeDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.PersonService;
import com.googlecode.openmpis.persistence.ibatis.service.RelativeService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.PersonServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.RelativeServiceImpl;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Validator;

/**
 * The RelativeAction class provides the methods to list, add, edit, delete and view
 * relatives.
 *
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class RelativeAction extends DispatchAction {

    /**
     * The relative service
     */
    private PersonService personService = new PersonServiceImpl(new PersonDAOImpl());
    /**
     * The relative service
     */
    private RelativeService relativeService = new RelativeServiceImpl(new RelativeDAOImpl());
    /**
     * The log service
     */
    private LogService logService = new LogServiceImpl(new LogDAOImpl());
    /**
     * The file logger
     */
    private Logger logger = Logger.getLogger(this.getClass());
    /**
     * The format for date (e.g. 2009-02-28)
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Prepares the form for relative creation.
     * This is the new relative action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     */
    public ActionForward newRelative(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is an encoder
        if (currentUser.getGroupId() == 1) {
            List<Relative> relativeList = relativeService.listAllRelatives();
            request.setAttribute("relativelist", relativeList);
            request.setAttribute("action", request.getParameter("action"));
            if (request.getAttribute("personid") != null) {
                request.setAttribute("personid", request.getAttribute("personid"));
            } else {
                request.setAttribute("personid", request.getParameter("personid"));
            }

            if (request.getAttribute("relativeid") != null) {
                RelativeForm relativeForm = (RelativeForm) form;
                Relative relative = (Relative) relativeService.getRelativeById((Integer) request.getAttribute("relativeid"));

                // Return relative
                relativeForm.setId(relative.getId());
                relativeForm.setFirstName(relative.getFirstName());
                relativeForm.setMiddleName(relative.getMiddleName());
                relativeForm.setLastName(relative.getLastName());
                relativeForm.setEmail(relative.getEmail());
                relativeForm.setNumber(relative.getNumber());
                relativeForm.setStreet(relative.getStreet());
                relativeForm.setCity(relative.getCity());
                relativeForm.setProvince(relative.getProvince());
                relativeForm.setCountry(relative.getCountry());
                relativeForm.setRelationToRelative((Integer) request.getAttribute("relationtorelative"));
            }

            return mapping.findForward(Constants.ADD_RELATIVE);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Inserts a relative into the database.
     * This is the add relative action called from the HTML form.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward addRelative(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is an encoder
        if (currentUser.getGroupId() == 1) {
            RelativeForm relativeForm = (RelativeForm) form;
            ActionMessages errors = new ActionMessages();
            List<Relative> relativeList = relativeService.listAllRelatives();
            request.setAttribute("relativelist", relativeList);

            // Check if relative is selected from list
            if (relativeForm.getId() > 0) {
                // Update relative ID in person
                Person person = new Person();
                person.setId(Integer.parseInt(request.getParameter("personid")));
                person.setRelativeId(relativeForm.getId());
                person.setRelationToRelative(relativeForm.getRelationToRelative());
                personService.updatePersonRelative(person);

                // Log relative assignment event
                Log addLog = new Log();
                addLog.setLog("Relative " + relativeForm.getId() + " was attributed to person " + person.getId() + ".");
                addLog.setDate(simpleDateFormat.format(System.currentTimeMillis()));
                logService.insertLog(addLog);
                logger.info(addLog.toString());

                person = personService.getPersonById(Integer.parseInt(request.getParameter("personid")));
                //return mapping.findForward(Constants.ADD_RELATIVE_SUCCESS);
                if ((person.getType() == 1) || (person.getType() == 2)) {
                    // Check if abducted by a family member or kidnappers
                    // Return person ID
                    request.setAttribute("personid", person.getId());
                    request.setAttribute("abductorid", person.getAbductorId());

                    return mapping.findForward(Constants.ADD_ABDUCTOR);
                } else {
                    request.setAttribute("personid", person.getId());

                    //return mapping.findForward(Constants.ADD_RELATIVE_SUCCESS);
                    return mapping.findForward(Constants.SELECT_INVESTIGATOR);
                }
            } else {
                request.setAttribute("action", request.getParameter("action"));
                // Check if form is valid
                if (isValidRelative(request, form)) {
                    Relative checker = new Relative();
                    String firstName = relativeForm.getFirstName();
                    String middleName = relativeForm.getMiddleName();
                    String lastName = relativeForm.getLastName();
                    checker.setId(relativeForm.getId());
                    checker.setFirstName(firstName);
                    checker.setMiddleName(middleName);
                    checker.setLastName(lastName);

                    // Check if relative is unique
                    if (relativeService.isUniqueRelative(checker)) {
                        // Insert relative
                        Relative relative = new Relative();
                        relative.setFirstName(firstName);
                        relative.setMiddleName(relativeForm.getMiddleName());
                        relative.setLastName(lastName);
                        relative.setStreet(relativeForm.getStreet());
                        relative.setCity(relativeForm.getCity());
                        relative.setProvince(relativeForm.getProvince());
                        relative.setCountry(relativeForm.getCountry());
                        relative.setEmail(relativeForm.getEmail());
                        relative.setNumber(relativeForm.getNumber());
                        int generatedId = relativeService.insertRelative(relative);

                        if (generatedId > 0) {
                            // Update relative ID in person
                            Person person = new Person();
                            person.setId(Integer.parseInt((String) request.getParameter("personid")));
                            person.setRelativeId(generatedId);
                            person.setRelationToRelative(relativeForm.getRelationToRelative());
                            personService.updatePersonRelative(person);

                            // Log relative creation event
                            Log addLog = new Log();
                            addLog.setLog(firstName + " " + lastName + " was encoded by " + currentUser.getUsername() + ".");
                            addLog.setLog("Relative " + generatedId + " was attributed to person " + person.getId() + ".");
                            addLog.setDate(simpleDateFormat.format(System.currentTimeMillis()));
                            logService.insertLog(addLog);
                            logger.info(addLog.toString());

                            // Return relative and operation type
                            //relative.setId(generatedId);
                            //request.setAttribute("relative", relative);
                            //request.setAttribute("operation", "add");
                            person = personService.getPersonById(generatedId);
                            //return mapping.findForward(Constants.ADD_RELATIVE_SUCCESS);
                            if ((person.getType() == 1) || (person.getType() == 2)) {
                                // Check if abducted by a family member or kidnappers
                                // Return person ID
                                request.setAttribute("personid", person.getId());
                                request.setAttribute("abductorid", person.getAbductorId());

                                return mapping.findForward(Constants.ADD_ABDUCTOR);
                            } else {
                                request.setAttribute("personid", person.getId());

                                return mapping.findForward(Constants.SELECT_INVESTIGATOR);
                            }
                        } else {
                            return mapping.findForward(Constants.FAILURE);
                        }
                    } else {
                        request.setAttribute("personid", request.getParameter("personid"));

                        // Return duplicate relative error
                        errors.add("firstname", new ActionMessage("error.relative.duplicate", firstName + " " + middleName + " " + lastName));
                        saveErrors(request, errors);

                        logger.error("Duplicate relative.");

                        return mapping.findForward(Constants.ADD_RELATIVE_REDO);
                    }
                } else {
                    request.setAttribute("personid", request.getParameter("personid"));

                    // Return form validation errors
                    return mapping.findForward(Constants.ADD_RELATIVE_REDO);
                }
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * This is the view relative action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward viewRelative(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;
        RelativeForm relativeForm = (RelativeForm) form;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is an encoder
        if (currentUser.getGroupId() == 1) {
            request.setAttribute("action", request.getParameter("action"));
            List<Relative> relativeList = relativeService.listAllRelatives();
            request.setAttribute("relativelist", relativeList);

            int personId = 0;
            try {
                if (request.getParameter("personid") != null) {
                    personId = Integer.parseInt(request.getParameter("personid"));
                    request.setAttribute("personid", personId);
                    Person person = personService.getPersonById(personId);
                    request.setAttribute("relativeid", person.getRelativeId());
                    request.setAttribute("relationtorelative", person.getRelationToRelative());
                } else {
                    //return mapping.findForward(Constants.LIST_PERSON);
                }
            } catch (NumberFormatException nfe) {
                //return mapping.findForward(Constants.LIST_PERSON);
            }

            // Retrieve relative
            int id = 0;
            try {
                if (request.getParameter("id") != null) {
                    id = Integer.parseInt(request.getParameter("id"));
                } else {
                    return mapping.findForward(Constants.LIST_PERSON);
                }
            } catch (NumberFormatException nfe) {
                return mapping.findForward(Constants.LIST_PERSON);
            }
            Relative relative = (Relative) relativeService.getRelativeById(id);

            // Return relative
            relativeForm.setId(relative.getId());
            relativeForm.setFirstName(relative.getFirstName());
            relativeForm.setMiddleName(relative.getMiddleName());
            relativeForm.setLastName(relative.getLastName());
            relativeForm.setEmail(relative.getEmail());
            relativeForm.setNumber(relative.getNumber());
            relativeForm.setStreet(relative.getStreet());
            relativeForm.setCity(relative.getCity());
            relativeForm.setProvince(relative.getProvince());
            relativeForm.setCountry(relative.getCountry());

            return mapping.findForward(Constants.EDIT_RELATIVE);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * This is the edit relative action called from the HTML form.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward editRelative(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is an encoder
        if (currentUser.getGroupId() == 1) {
            RelativeForm relativeForm = (RelativeForm) form;
            ActionMessages errors = new ActionMessages();
            request.setAttribute("action", request.getParameter("action"));
            List<Relative> relativeList = relativeService.listAllRelatives();
            request.setAttribute("relativelist", relativeList);
            request.setAttribute("personid", request.getParameter("personid"));

            // Check if form is valid
            if (isValidRelative(request, form)) {
                    Relative checker = new Relative();
                    String firstName = relativeForm.getFirstName();
                    String middleName = relativeForm.getMiddleName();
                    String lastName = relativeForm.getLastName();
                    checker.setId(relativeForm.getId());
                    checker.setFirstName(firstName);
                    checker.setMiddleName(middleName);
                    checker.setLastName(lastName);
                    checker.setEmail(relativeForm.getEmail());

                // Check if relative is unique
                if (relativeService.isUniqueRelative(checker)) {
                        // Update relative
                        Relative relative = new Relative();
                        relative.setId(relativeForm.getId());
                        relative.setFirstName(firstName);
                        relative.setMiddleName(relativeForm.getMiddleName());
                        relative.setLastName(lastName);
                        relative.setStreet(relativeForm.getStreet());
                        relative.setCity(relativeForm.getCity());
                        relative.setProvince(relativeForm.getProvince());
                        relative.setCountry(relativeForm.getCountry());
                        relative.setEmail(relativeForm.getEmail());
                        relative.setNumber(relativeForm.getNumber());
                        boolean isUpdated = relativeService.updateRelative(relative);

                        if (isUpdated) {
                            // Update relative ID in person
                            Person person = new Person();
                            person.setId(Integer.parseInt(request.getParameter("personid")));
                            person.setRelativeId(relativeForm.getId());
                            person.setRelationToRelative(relativeForm.getRelationToRelative());
                            personService.updatePersonRelative(person);

                            // Log relative modification event
                            Log editLog = new Log();
                            editLog.setLog(firstName + " " + lastName + " with ID " + relativeForm.getId() + " was updated by " + currentUser.getUsername() + ".");
                            editLog.setLog("Relative " + relativeForm.getId() + " was attributed to person " + person.getId() + ".");
                            editLog.setDate(simpleDateFormat.format(System.currentTimeMillis()));
                            logService.insertLog(editLog);
                            logger.info(editLog.toString());

                            person = personService.getPersonById(Integer.parseInt(request.getParameter("personid")));
                            // Return relative and operation type
                            if ((person.getType() == 1) || (person.getType() == 2)) {
                                // Check if abducted by a family member or kidnappers
                                // Return person ID
                                request.setAttribute("personid", person.getId());
                                request.setAttribute("abductorid", person.getAbductorId());

                                return mapping.findForward(Constants.ADD_ABDUCTOR);
                            } else {
                                request.setAttribute("personid", person.getId());
                                request.setAttribute("investigatorid", request.getAttribute("investigatorid"));

                                return mapping.findForward(Constants.SELECT_INVESTIGATOR);
                            }
                            //request.setAttribute("relative", relative);
                            //request.setAttribute("operation", "edit");
                            //request.setAttribute("investigatorid", request.getAttribute("investigatorid"));
                            //System.out.println("editrelative investigator id attribute: " + request.getAttribute("investigatorid"));
                            //System.out.println("editrelative investigator id parameter: " + request.getParameter("investigatorid"));

                            //return mapping.findForward(Constants.EDIT_RELATIVE_SUCCESS);
                        } else {
                            return mapping.findForward(Constants.FAILURE);
                        }
                } else {
                    // Return duplicate relative error
                    errors.add("firstname", new ActionMessage("error.relative.duplicate"));
                    saveErrors(request, errors);

                    logger.error("Duplicate relative.");

                    return mapping.findForward(Constants.EDIT_RELATIVE_REDO);
                }
            } else {
                // Return form validation errors
                return mapping.findForward(Constants.EDIT_RELATIVE_REDO);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * This is the erase relative action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    /*
    public ActionForward eraseRelative(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
    User currentUser = null;

    // Check if there exists a session
    if (request.getSession().getAttribute("currentuser") == null) {
    return mapping.findForward(Constants.EXPIRED);
    } else {
    currentUser = (User) request.getSession().getAttribute("currentuser");
    }

    // Check if current relative is authorized
    if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
    RelativeForm relativeForm = (RelativeForm) form;
    // Retrieve relative
    Relative relative = (Person) relativeService.getPersonById(new Integer(relativeForm.getId()));

    relativeForm.setPersonname(relative.getPersonname());
    // Generate 4-digit random code
    relativeForm.setCode((int) (Math.random() * 9999) + 1000);

    // Delete what you created/encoded
    // Administrator can delete all except administrators
    if ((currentUser.getId() == relative.getCreatorId()) ||
    ((currentUser.getGroupId() == 0) && (relative.getGroupId() > 0))) {
    return mapping.findForward(Constants.DELETE_USER);
    } else {
    return mapping.findForward(Constants.UNAUTHORIZED);
    }
    } else {
    return mapping.findForward(Constants.UNAUTHORIZED);
    }
    }
     */
    /**
     * This is the delete relative action called from the HTML form.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    /*
    public ActionForward deleteRelative(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
    User currentUser = null;

    // Check if there exists a session
    if (request.getSession().getAttribute("currentuser") == null) {
    return mapping.findForward(Constants.EXPIRED);
    } else {
    currentUser = (User) request.getSession().getAttribute("currentuser");
    }

    // Check if current relative is authorized
    if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
    RelativeForm relativeForm = (RelativeForm) form;

    Relative relative = (Relative) relativeService.getPersonById(new Integer(relativeForm.getId()));

    // Check if codes match
    if (relativeForm.getCode() == relativeForm.getPersonCode()) {
    // Administrator can delete a relative except his creator/encoder
    // Person can delete a relative that he encoded
    if (((currentUser.getGroupId() == 0) && (currentUser.getCreatorId() != relative.getId())) ||
    ((currentUser.getGroupId() == 1) && (currentUser.getId() == relative.getCreatorId()))) {
    // Delete relative
    relativeService.deleteRelative(new Integer(relative.getId()));

    // Log relative deletion event
    Log deleteLog = new Log();
    deleteLog.setLog("Person " + relative.getPersonname() + " was deleted by " + currentUser.getPersonname() + ".");
    deleteLog.setDate(simpleDateFormat.format(System.currentTimeMillis()));
    logService.insertLog(deleteLog);
    logger.info(deleteLog.toString());

    // Retrieve mail properties
    Configuration config = new Configuration("mail.properties");
    // Check if email sending is enabled
    if (Boolean.parseBoolean(config.getProperty("mail.enable"))) {
    Mail mail = new Mail();

    // Send email
    mail.send(currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), "email",
    "Account Deletion",
    "Dear " + "firstname" + "," +
    "\n\nThis is to inform you that after a long deliberation, your account has to be deleted." +
    "\n\nIf you have any questions, please feel free to email me." +
    "\n\nYours truly," +
    "\n" + currentUser.getFirstName());
    }

    // Return relativename and operation type
    request.setAttribute("relativename", relative.getPersonname());
    request.setAttribute("operation", "delete");

    return mapping.findForward(Constants.DELETE_SUCCESS);
    } else {
    return mapping.findForward(Constants.UNAUTHORIZED);
    }
    } else {
    // Generate 4-digit random code
    relativeForm.setCode((int) (Math.random() * 9999) + 1000);
    relativeForm.setPersonname(relative.getPersonname());

    // Return duplicate relativename error
    ActionMessages errors = new ActionMessages();
    errors.add("relativecode", new ActionMessage("error.code.mismatch"));
    saveErrors(request, errors);

    logger.error("Codes did not match.");

    return mapping.findForward(Constants.DELETE_REDO);
    }
    } else {
    return mapping.findForward(Constants.UNAUTHORIZED);
    }
    }
     */
    /**
     * Validates the inputs from the relative form.
     *
     * @param request       the HTTP Request we are processing
     * @param form          the ActionForm bean for this request
     * @return              <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    private boolean isValidRelative(HttpServletRequest request, ActionForm form) throws Exception {
        ActionMessages errors = new ActionMessages();
        Validator validator = new Validator();
        boolean isValid = true;

        RelativeForm relativeForm = (RelativeForm) form;
        String firstName = relativeForm.getFirstName();
        String middleName = relativeForm.getMiddleName();
        String lastName = relativeForm.getLastName();
        String street = relativeForm.getStreet();
        String city = relativeForm.getCity();
        String province = relativeForm.getProvince();
        String email = relativeForm.getEmail();
        String number = relativeForm.getNumber();

        if (firstName.length() < 1) {
            errors.add("firstname", new ActionMessage("error.firstname.required"));
        } else {
            if (!validator.isValidFirstName(firstName)) {
                errors.add("firstname", new ActionMessage("error.firstname.invalid"));
            }
        }

        if (middleName.length() < 1) {
            errors.add("middlename", new ActionMessage("error.middlename.required"));
        } else {
            if (!validator.isValidLastName(middleName)) {
                errors.add("middlename", new ActionMessage("error.middlename.invalid"));
            }
        }

        if (lastName.length() < 1) {
            errors.add("lastname", new ActionMessage("error.lastname.required"));
        } else {
            if (!validator.isValidLastName(lastName)) {
                errors.add("lastname", new ActionMessage("error.lastname.invalid"));
            }
        }

        if ((email.length() > 0) && (!validator.isValidEmailAddress(email))) {
            errors.add("email", new ActionMessage("error.email.invalid"));
        }

        if (number.length() < 1) {
            errors.add("number", new ActionMessage("error.number.required"));
        } else {
            if (!validator.isValidNumber(number)) {
                errors.add("number", new ActionMessage("error.number.invalid"));
            }
        }

        if (street.length() < 1) {
            errors.add("street", new ActionMessage("error.street.required"));
        } else {
            if (!validator.isValidStreet(street)) {
                errors.add("street", new ActionMessage("error.street.invalid"));
            }
        }

        if (city.length() < 1) {
            errors.add("city", new ActionMessage("error.city.required"));
        } else {
            if (!validator.isValidProvince(city)) {
                errors.add("city", new ActionMessage("error.city.invalid"));
            }
        }

        if (province.length() < 1) {
            errors.add("province", new ActionMessage("error.province.required"));
        } else {
            if (!validator.isValidProvince(province)) {
                errors.add("province", new ActionMessage("error.province.invalid"));
            }
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            isValid = false;
        }

        return isValid;
    }
}