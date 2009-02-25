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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.form.PersonForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.AbductorDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.PersonDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.ReportDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.RelativeDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.AbductorService;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.PersonService;
import com.googlecode.openmpis.persistence.ibatis.service.RelativeService;
import com.googlecode.openmpis.persistence.ibatis.service.ReportService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.AbductorServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.PersonServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.RelativeServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.ReportServiceImpl;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Validator;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * The AbductorAction class provides the methods to list, add, edit, delete and view
 * abductors.
 *
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class AbductorAction extends DispatchAction {

    /**
     * The person service
     */
    private PersonService personService = new PersonServiceImpl(new PersonDAOImpl());
    /**
     * The relative service
     */
    private RelativeService relativeService = new RelativeServiceImpl(new RelativeDAOImpl());
    /**
     * The report service
     */
    private ReportService reportService = new ReportServiceImpl(new ReportDAOImpl());
    /**
     * The abductor service
     */
    private AbductorService abductorService = new AbductorServiceImpl(new AbductorDAOImpl());
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
     * Prepares the form for person creation.
     * This is the new person action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     */
    public ActionForward newPerson(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is an encoder
        if (currentUser.getGroupId() == 1) {
            request.setAttribute("action", request.getParameter("action"));

            return mapping.findForward(Constants.ADD_PERSON);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Inserts a person into the database.
     * This is the add person action called from the HTML form.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward addPerson(ActionMapping mapping, ActionForm form,
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
            PersonForm personForm = (PersonForm) form;
            ActionMessages errors = new ActionMessages();

            // Check if form is valid
            if (isValidNewPerson(request, form)) {
                // Check if updated person is unique
                // and if the rest of the form is valid
                Person checker = new Person();
                String firstName = personForm.getFirstName();
                String nickname = personForm.getNickname();
                String lastName = personForm.getLastName();
                checker.setId(personForm.getId());
                checker.setFirstName(firstName);
                checker.setNickname(nickname);
                checker.setLastName(lastName);

                // Check if person is unique
                if (personService.isUniquePerson(checker)) {

                    // Insert person
                    Person person = new Person();
                    person.setFirstName(firstName);
                    person.setNickname(nickname);
                    person.setLastName(lastName);
                    person.setMiddleName(personForm.getMiddleName());
                    person.setBirthMonth(personForm.getBirthMonth());
                    person.setBirthDay(personForm.getBirthDay());
                    person.setBirthYear(personForm.getBirthYear());
                    person.setStreet(personForm.getStreet());
                    String date = simpleDateFormat.format(System.currentTimeMillis());
                    person.setDate(date);
                    person.setEncoderId(currentUser.getId());
                    person.setStatus(personForm.getStatus());
                    if (person.getType() <= 4)
                        person.setMissingFromCity(personForm.getMissingFromCity());
                    else
                        person.setInstitution(personForm.getInstitution());
                    int isInserted = personService.insertPerson(person);
                    //int generatedId = personService.insertPerson(person);

                    if (isInserted > 0) {
                        // Log person creation event
                        Log addLog = new Log();
                        if ((!firstName.isEmpty()) && (!lastName.isEmpty()))
                            addLog.setLog(firstName + " '" + nickname + "' " + lastName + " was encoded by " + currentUser.getUsername() + ".");
                        else
                            addLog.setLog("' " + nickname + " '" + " was encoded by " + currentUser.getUsername() + ".");
                        addLog.setDate(date);
                        logService.insertLog(addLog);
                        logger.info(addLog.toString());

                        // Return person and operation type
                        request.setAttribute("person", person);
                        request.setAttribute("operation", "add");

                        return mapping.findForward(Constants.ADD_PERSON_SUCCESS);
                    } else {
                        return mapping.findForward(Constants.FAILURE);
                    }
                } else {
                    // Return duplicate personname error
                    errors.add("person", new ActionMessage("error.person.duplicate"));
                    saveErrors(request, errors);

                    logger.error("Duplicate person.");

                    return mapping.findForward(Constants.ADD_PERSON_REDO);
                }
            } else {
                // Return form validation errors
                return mapping.findForward(Constants.ADD_PERSON_REDO);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * This is the view person action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    /*
    public ActionForward viewPerson(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
    User currentUser = null;
    PersonForm personForm = (PersonForm) form;

    // Check if there exists a session
    if (request.getSession().getAttribute("currentuser") == null) {
    return mapping.findForward(Constants.EXPIRED);
    } else {
    currentUser = (User) request.getSession().getAttribute("currentuser");
    }

    // Check if current person is authorized
    if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1) || (currentUser.getGroupId() == 2)) {
    // Retrieve person
    Person person = (User) personService.getPersonById(new Integer(request.getParameter("id")));

    // Return person
    personForm.setId(person.getId());
    personForm.setGroupId(person.getGroupId());
    personForm.setPersonname(person.getPersonname());
    personForm.setFirstName(person.getFirstName());
    personForm.setMiddleName(person.getMiddleName());
    personForm.setLastName(person.getLastName());
    personForm.setBirthMonth(person.getBirthMonth());
    personForm.setBirthDay(person.getBirthDay());
    personForm.setBirthYear(person.getBirthYear());
    personForm.setDesignation(person.getDesignation());
    personForm.setAgency(person.getAgency());
    personForm.setEmail(person.getEmail());
    personForm.setNumber(person.getNumber());
    personForm.setIpAddress(person.getIpAddress());
    personForm.setLastLogin(person.getLastLogin());
    personForm.setDate(person.getDate());
    personForm.setCreatorId(person.getCreatorId());
    personForm.setStatus(person.getStatus());
    personForm.setQuestion(person.getQuestion());
    personForm.setAnswer((person.getAnswer() != null) ? person.getAnswer() : "");
     * countReportsForPerson(person.getId)

    // Edit what you created/encoded
    // Edit your profile
    // Administrator can edit all except administrators
    if ((currentUser.getId() == person.getCreatorId()) ||
    (currentUser.getId() == person.getId()) ||
    ((currentUser.getGroupId() == 0) && (person.getGroupId() > 0))) {
    return mapping.findForward(Constants.EDIT_USER);
    } else {
    return mapping.findForward(Constants.UNAUTHORIZED);
    }
    } else {
    return mapping.findForward(Constants.UNAUTHORIZED);
    }
    }
     */
    /**
     * This is the edit person action called from the HTML form.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    /*
    public ActionForward editPerson(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
    User currentUser = null;

    // Check if there exists a session
    if (request.getSession().getAttribute("currentuser") == null) {
    return mapping.findForward(Constants.EXPIRED);
    } else {
    currentUser = (User) request.getSession().getAttribute("currentuser");
    }

    // Check if current person is authorized
    if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1) || (currentUser.getGroupId() == 2)) {
    PersonForm personForm = (PersonForm) form;
    ActionMessages errors = new ActionMessages();

    // Check if form is valid
    if (isValidExistingPerson(request, form)) {
    // Check if updated person has unique personname and nickname, if passwords match
    // and if the rest of the form is valid
    Person checker = new Person();
    checker.setId(personForm.getId());
    checker.setEmail(personForm.getEmail());
    String personname = createPersonname(personForm);
    checker.setPersonname(personname);

    // Check if personname is unique
    if (personService.isUniquePersonname(checker)) {
    // Check if nickname address is unique
    if (personService.isUniqueEmail(checker)) {
    // Update person
    Person person = new Person();
    person.setId(personForm.getId());
    person.setGroupId(personForm.getGroupId());
    person.setPersonname(personname);
    person.setFirstName(personForm.getFirstName());
    person.setMiddleName(personForm.getMiddleName());
    person.setLastName(personForm.getLastName());
    person.setBirthMonth(personForm.getBirthMonth());
    person.setBirthDay(personForm.getBirthDay());
    person.setBirthYear(personForm.getBirthYear());
    person.setDesignation(personForm.getDesignation());
    person.setAgency(personForm.getAgency());
    person.setEmail(personForm.getEmail());
    person.setNumber(personForm.getNumber());
    person.setStatus(personForm.getStatus());
    person.setQuestion(personForm.getQuestion());
    person.setAnswer(personForm.getAnswer());
    // Check if password is also being updated
    if ((personForm.getPassword() != null) &&
    (personForm.getRetype() != null) &&
    (personForm.getPassword().equals(personForm.getRetype()))) {
    person.setPassword(personForm.getPassword());
    personService.updatePassword(person);
    }
    boolean isUpdated = personService.updatePerson(person);

    if (isUpdated) {
    // Log person modification event
    Log editLog = new Log();
    if (personname.equals(personForm.getPersonname())) {
    editLog.setLog("Person " + personname + " was updated by " + currentUser.getPersonname() + ".");
    } else {
    editLog.setLog("Person " + personForm.getPersonname() + " was renamed to " + personname + " by " + currentUser.getPersonname() + ".");
    }
    editLog.setDate(simpleDateFormat.format(System.currentTimeMillis()));
    logService.insertLog(editLog);
    logger.info(editLog.toString());

    // Return personname and operation type
    request.setAttribute("personname", personname);
    request.setAttribute("operation", "edit");

    return mapping.findForward(Constants.EDIT_SUCCESS);
    } else {
    return mapping.findForward(Constants.FAILURE);
    }
    } else {
    // Return duplicate nickname error
    errors.add("nickname", new ActionMessage("error.nickname.duplicate"));
    saveErrors(request, errors);

    logger.error("Duplicate nickname.");

    return mapping.findForward(Constants.EDIT_REDO);
    }
    } else {
    // Return duplicate personname error
    errors.add("personname", new ActionMessage("error.personname.duplicate"));
    saveErrors(request, errors);

    logger.error("Duplicate personname.");

    return mapping.findForward(Constants.EDIT_REDO);
    }
    } else {
    // Return form validation errors
    return mapping.findForward(Constants.EDIT_REDO);
    }
    } else {
    return mapping.findForward(Constants.UNAUTHORIZED);
    }
    }
     */
    /**
     * This is the erase person action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    /*
    public ActionForward erasePerson(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
    User currentUser = null;

    // Check if there exists a session
    if (request.getSession().getAttribute("currentuser") == null) {
    return mapping.findForward(Constants.EXPIRED);
    } else {
    currentUser = (User) request.getSession().getAttribute("currentuser");
    }

    // Check if current person is authorized
    if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
    PersonForm personForm = (PersonForm) form;
    // Retrieve person
    Person person = (Person) personService.getPersonById(new Integer(personForm.getId()));

    personForm.setPersonname(person.getPersonname());
    // Generate 4-digit random code
    personForm.setCode((int) (Math.random() * 9999) + 1000);

    // Delete what you created/encoded
    // Administrator can delete all except administrators
    if ((currentUser.getId() == person.getCreatorId()) ||
    ((currentUser.getGroupId() == 0) && (person.getGroupId() > 0))) {
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
     * This is the delete person action called from the HTML form.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    /*
    public ActionForward deletePerson(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
    User currentUser = null;

    // Check if there exists a session
    if (request.getSession().getAttribute("currentuser") == null) {
    return mapping.findForward(Constants.EXPIRED);
    } else {
    currentUser = (User) request.getSession().getAttribute("currentuser");
    }

    // Check if current person is authorized
    if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
    PersonForm personForm = (PersonForm) form;

    Person person = (User) personService.getPersonById(new Integer(personForm.getId()));

    // Check if codes match
    if (personForm.getCode() == personForm.getPersonCode()) {
    // Administrator can delete a person except his creator/encoder
    // Person can delete a person that he encoded
    if (((currentUser.getGroupId() == 0) && (currentUser.getCreatorId() != person.getId())) ||
    ((currentUser.getGroupId() == 1) && (currentUser.getId() == person.getCreatorId()))) {
    // Delete person
    personService.deletePerson(new Integer(person.getId()));

    // Log person deletion event
    Log deleteLog = new Log();
    deleteLog.setLog("Person " + person.getPersonname() + " was deleted by " + currentUser.getPersonname() + ".");
    deleteLog.setDate(simpleDateFormat.format(System.currentTimeMillis()));
    logService.insertLog(deleteLog);
    logger.info(deleteLog.toString());

    // Retrieve mail properties
    Configuration config = new Configuration("mail.properties");
    // Check if nickname sending is enabled
    if (Boolean.parseBoolean(config.getProperty("mail.enable"))) {
    Mail mail = new Mail();

    // Send nickname
    mail.send(currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), "nickname",
    "Account Deletion",
    "Dear " + "firstname" + "," +
    "\n\nThis is to inform you that after a long deliberation, your account has to be deleted." +
    "\n\nIf you have any questions, please feel free to nickname me." +
    "\n\nYours truly," +
    "\n" + currentUser.getFirstName());
    }

    // Return personname and operation type
    request.setAttribute("personname", person.getPersonname());
    request.setAttribute("operation", "delete");

    return mapping.findForward(Constants.DELETE_SUCCESS);
    } else {
    return mapping.findForward(Constants.UNAUTHORIZED);
    }
    } else {
    // Generate 4-digit random code
    personForm.setCode((int) (Math.random() * 9999) + 1000);
    personForm.setPersonname(person.getPersonname());

    // Return duplicate personname error
    ActionMessages errors = new ActionMessages();
    errors.add("personcode", new ActionMessage("error.code.mismatch"));
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
     * Validates the inputs from the person form.
     *
     * @param request       the HTTP Request we are processing
     * @param form          the ActionForm bean for this request
     * @return              <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    private boolean isValidNewPerson(HttpServletRequest request, ActionForm form) throws Exception {
        ActionMessages errors = new ActionMessages();
        Validator validator = new Validator();
        boolean isValid = true;

        PersonForm personForm = (PersonForm) form;
        String firstName = personForm.getFirstName();
        String nickname = personForm.getNickname();
        String middleName = personForm.getMiddleName();
        String lastName = personForm.getLastName();
        Calendar calendar = Calendar.getInstance();
        calendar.set(personForm.getBirthYear(), (personForm.getBirthMonth() - 1), personForm.getBirthDay());
        String street = personForm.getStreet();
        String city = personForm.getCity();
        String province = personForm.getProvince();
        FormFile photoFile = personForm.getPhotoFile();
        /*
        String hashcode = "test";
        File directory = new File("D:" + File.separator + hashcode);
        directory.mkdir();
        directory = new File("D:" + File.separator + hashcode + File.separator + "default");
        directory.mkdir();
        directory = new File("D:" + File.separator + hashcode + File.separator + "aged");
        directory.mkdir();
        File file = new File("D:" + File.separator + hashcode + File.separator + "default" + File.separator + photoFile.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(photoFile.getFileData());
        fos.close();
        fos.flush();
        System.out.println(photoFile.getContentType());
         */

        // TODO error if birth date and date missingorfound are greater than current date

        if (firstName == null) {
            errors.add("firstname", new ActionMessage(""));
        } else {
            if (firstName.length() < 1) {
                errors.add("firstname", new ActionMessage("error.firstname.required"));
            } else {
                if (!validator.isValidFirstName(firstName)) {
                    errors.add("firstname", new ActionMessage("error.firstname.invalid"));
                }
            }
        }

        if (middleName == null) {
            errors.add("middlename", new ActionMessage(""));
        } else {
            if (middleName.length() < 1) {
                errors.add("middlename", new ActionMessage("error.middlename.required"));
            } else {
                if (!validator.isValidLastName(middleName)) {
                    errors.add("middlename", new ActionMessage("error.middlename.invalid"));
                }
            }
        }

        if (lastName == null) {
            errors.add("lastname", new ActionMessage(""));
        } else {
            if (lastName.length() < 1) {
                errors.add("lastname", new ActionMessage("error.lastname.required"));
            } else {
                if (!validator.isValidLastName(lastName)) {
                    errors.add("lastname", new ActionMessage("error.lastname.invalid"));
                }
            }
        }

        if (personForm.getBirthMonth() == calendar.get(Calendar.MONTH)) {
            errors.add("birthdate", new ActionMessage("error.birthdate.invalid"));
        }

        if (nickname == null) {
            errors.add("nickname", new ActionMessage(""));
        } else {
            if (nickname.length() < 1) {
                errors.add("nickname", new ActionMessage("error.nickname.required"));
            } else {
                if (!validator.isValidEmailAddress(nickname)) {
                    errors.add("nickname", new ActionMessage("error.nickname.invalid"));
                }
            }
        }

        if (city == null) {
            errors.add("city", new ActionMessage(""));
        } else {
            if (city.length() < 1) {
                errors.add("city", new ActionMessage("error.city.required"));
            } else {
                if (!validator.isValidKeyword(city)) {
                    errors.add("city", new ActionMessage("error.city.invalid"));
                }
            }
        }

        if (street == null) {
            errors.add("street", new ActionMessage(""));
        } else {
            if (street.length() < 1) {
                errors.add("street", new ActionMessage("error.street.required"));
            } else {
                if (!validator.isValidKeyword(street)) {
                    errors.add("street", new ActionMessage("error.street.invalid"));
                }
            }
        }

        if (province == null) {
            errors.add("province", new ActionMessage(""));
        } else {
            if (province.length() < 1) {
                errors.add("province", new ActionMessage("error.province.required"));
            } else {
                if (!validator.isValidNumber(province)) {
                    errors.add("province", new ActionMessage("error.province.invalid"));
                }
            }
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            isValid = false;
        }

        return isValid;
    }

    /**
     * Validates the inputs from the person form.
     *
     * @param request       the HTTP Request we are processing
     * @param form          the ActionForm bean for this request
     * @return              <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    /*
    private boolean isValidExistingPerson(HttpServletRequest request, ActionForm form) {
    ActionMessages errors = new ActionMessages();
    Validator validator = new Validator();
    boolean isValid = true;
    User currentUser = (User) request.getSession().getAttribute("currentuser");

    PersonForm personForm = (PersonForm) form;
    String firstName = personForm.getFirstName();
    String middleName = personForm.getMiddleName();
    String lastName = personForm.getLastName();
    Calendar calendar = Calendar.getInstance();
    calendar.set(personForm.getBirthYear(), (personForm.getBirthMonth() - 1), personForm.getBirthDay());
    String street = personForm.getAgency();
    String city = personForm.getDesignation();
    String nickname = personForm.getEmail();
    String province = personForm.getNumber();
    String password = personForm.getPassword();
    String retype = personForm.getRetype();
    String answer = personForm.getAnswer();

    // Check if current person is editing his profile
    // Password and security answer are required
    if (currentUser.getId() == personForm.getId()) {
    // Check if retyped password exists
    if ((retype != null) && (retype.length() > 0)) {
    if (password == null) {
    errors.add("password", new ActionMessage(""));
    } else {
    if (password.length() < 1) {
    errors.add("password", new ActionMessage("error.password.required"));
    } else {
    if (!validator.isValidPassword(password)) {
    errors.add("password", new ActionMessage("error.password.invalid"));
    } else if (!password.equals(retype)) {
    errors.add("password", new ActionMessage("error.password.mismatch"));
    }
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
    }

    if (firstName == null) {
    errors.add("firstname", new ActionMessage(""));
    } else {
    if (firstName.length() < 1) {
    errors.add("firstname", new ActionMessage("error.firstname.required"));
    } else {
    if (!validator.isValidFirstName(firstName)) {
    errors.add("firstname", new ActionMessage("error.firstname.invalid"));
    }
    }
    }

    if (middleName == null) {
    errors.add("middlename", new ActionMessage(""));
    } else {
    if (middleName.length() < 1) {
    errors.add("middlename", new ActionMessage("error.middlename.required"));
    } else {
    if (!validator.isValidLastName(middleName)) {
    errors.add("middlename", new ActionMessage("error.middlename.invalid"));
    }
    }
    }

    if (lastName == null) {
    errors.add("lastname", new ActionMessage(""));
    } else {
    if (lastName.length() < 1) {
    errors.add("lastname", new ActionMessage("error.lastname.required"));
    } else {
    if (!validator.isValidLastName(lastName)) {
    errors.add("lastname", new ActionMessage("error.lastname.invalid"));
    }
    }
    }

    if (personForm.getBirthMonth() == calendar.get(Calendar.MONTH)) {
    errors.add("birthdate", new ActionMessage("error.birthdate.invalid"));
    }

    if (nickname == null) {
    errors.add("nickname", new ActionMessage(""));
    } else {
    if (nickname.length() < 1) {
    errors.add("nickname", new ActionMessage("error.nickname.required"));
    } else {
    if (!validator.isValidEmailAddress(nickname)) {
    errors.add("nickname", new ActionMessage("error.nickname.invalid"));
    }
    }
    }

    if (city == null) {
    errors.add("city", new ActionMessage(""));
    } else {
    if (city.length() < 1) {
    errors.add("city", new ActionMessage("error.city.required"));
    } else {
    if (!validator.isValidKeyword(city)) {
    errors.add("city", new ActionMessage("error.city.invalid"));
    }
    }
    }

    if (street == null) {
    errors.add("street", new ActionMessage(""));
    } else {
    if (street.length() < 1) {
    errors.add("street", new ActionMessage("error.street.required"));
    } else {
    if (!validator.isValidKeyword(street)) {
    errors.add("street", new ActionMessage("error.street.invalid"));
    }
    }
    }

    if (province == null) {
    errors.add("province", new ActionMessage(""));
    } else {
    if (province.length() < 1) {
    errors.add("province", new ActionMessage("error.province.required"));
    } else {
    if (!validator.isValidNumber(province)) {
    errors.add("province", new ActionMessage("error.province.invalid"));
    }
    }
    }

    if (!errors.isEmpty()) {
    saveErrors(request, errors);
    isValid = false;
    }

    return isValid;
    }
     */
    /**
     * Counts the province of reports for a given person.
     *
     * @return              the province of reports associated with a given person
     * @throws java.lang.Exception
     */
    private int countReportsForPerson(Integer personId) throws Exception {
        return reportService.countAllReportsForPerson(personId);
    }

    /**
     * Prints the person's poster in PDF file.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward printPoster(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Set the paper size and margins
        Document document = new Document(PageSize.LETTER, 50, 50, 50, 50);

        // Create the PDF writer
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        // Add some meta information to the document
        document.addTitle("Case Statistics");
        document.addAuthor("OpenMPIS");
        document.addSubject("Statistics for All Cases");
        document.addKeywords("OpenMPIS, missing, found, unidentified");
        document.addProducer();
        document.addCreationDate();
        document.addCreator("OpenMPIS version " + Constants.VERSION);

        // Open the document for writing
        document.open();
        document.add(new Paragraph("There is a total of " + personService.countAllPersons() + " reported case(s)."));
        document.add(new Paragraph("There are " + personService.countOngoing() + " ongoing case(s)."));
        document.add(new Paragraph("There are " + personService.countSolved() + " solved case(s)."));
        document.add(new Paragraph("There are " + personService.countUnsolved() + " unsolved case(s)."));
        document.add(new Paragraph("There are " + personService.countMissing() + " missing person(s)."));
        document.add(new Paragraph("There are " + personService.countFamilyAbduction() + " family abduction(s)."));
        document.add(new Paragraph("There are " + personService.countNonFamilyAbduction() + " non-family abduction(s)."));
        document.add(new Paragraph("There are " + personService.countRunaway() + " runaway person(s)."));
        document.add(new Paragraph("There are " + personService.countUnknown() + " unknown case(s)."));
        document.add(new Paragraph("There are " + personService.countAbandoned() + " abandoned person(s)."));
        document.add(new Paragraph("There are " + personService.countThrowaway() + " throwaway person(s)."));
        document.add(new Paragraph("There are " + personService.countUnidentified() + " unidentified person(s)."));
        document.add(new Paragraph("There are " + relativeService.countAllRelatives() + " relative(s)."));
        document.add(new Paragraph("There are " + abductorService.countAllAbductors() + " abductor(s)."));
        document.close();

        // Set the response to return the poster (PDF file)
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        response.setHeader("Content-disposition", "attachment; filename=Poster.pdf");

        // Close the output stream
        baos.writeTo(response.getOutputStream());
        response.getOutputStream().flush();

        return mapping.findForward(Constants.VIEW_PERSON_POSTER);
    }
}