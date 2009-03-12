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
import com.googlecode.openmpis.dto.Abductor;
import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.form.AbductorForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.AbductorDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.PersonDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.ReportDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.AbductorService;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.PersonService;
import com.googlecode.openmpis.persistence.ibatis.service.ReportService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.AbductorServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.PersonServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.ReportServiceImpl;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Validator;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

/**
 * The AbductorAction class provides the methods to list, add, edit, delete and view
 * persons.
 *
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class AbductorAction extends DispatchAction {

    /**
     * The abductor service
     */
    private PersonService personService = new PersonServiceImpl(new PersonDAOImpl());
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
     * Prepares the form for abductor creation.
     * This is the new abductor action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     */
    public ActionForward newAbductor(ActionMapping mapping, ActionForm form,
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
            AbductorForm abductorForm = (AbductorForm) form;
            List<Abductor> abductorList = abductorService.listAllAbductors();
            request.setAttribute("abductorlist", abductorList);
            request.setAttribute("action", request.getParameter("action"));
            if (request.getAttribute("personid") != null) {
                request.setAttribute("personid", request.getAttribute("personid"));
            } else {
                request.setAttribute("personid", request.getParameter("personid"));
            }

            if (abductorList.size() == 0) {
                abductorForm.reset(mapping, request);
            }

            if (request.getAttribute("abductorid") != null) {
                Abductor abductor = abductorService.getAbductorById((Integer) request.getAttribute("abductorid"));
                
                // Return abductor
                abductorForm.setId(abductor.getId());
                abductorForm.setFirstName(abductor.getFirstName());
                abductorForm.setMiddleName(abductor.getMiddleName());
                abductorForm.setLastName(abductor.getLastName());
                abductorForm.setStreet(abductor.getStreet());
                abductorForm.setCity(abductor.getCity());
                abductorForm.setProvince(abductor.getProvince());
                abductorForm.setCountry(abductor.getCountry());
                abductorForm.setRelationToAbductor((Integer) request.getAttribute("relationtoabductor"));
            }

            return mapping.findForward(Constants.ADD_ABDUCTOR);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Inserts an abductor into the database.
     * This is the add abductor action called from the HTML form.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward addAbductor(ActionMapping mapping, ActionForm form,
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
            AbductorForm abductorForm = (AbductorForm) form;
            ActionMessages errors = new ActionMessages();
            request.setAttribute("action", request.getParameter("action"));

            // Check if form is valid
            if (isValidAbductor(request, form)) {
                // Check if updated abductor is unique
                // and if the rest of the form is valid
                Abductor checker = new Abductor();
                String firstName = abductorForm.getFirstName();
                String lastName = abductorForm.getLastName();
                checker.setId(abductorForm.getId());
                checker.setFirstName(firstName);
                checker.setLastName(lastName);

                // Check if abductor is unique
                if (abductorService.isUniqueAbductor(checker)) {

                    // Insert abductor
                    Abductor abductor = new Abductor();
                    abductor.setFirstName(firstName);
                    abductor.setNickname(abductorForm.getNickname());
                    abductor.setMiddleName(abductorForm.getMiddleName());
                    abductor.setLastName(lastName);
                        abductor.setBirthMonth(abductorForm.getBirthMonth());
                        abductor.setBirthDay(abductorForm.getBirthDay());
                        abductor.setBirthYear(abductorForm.getBirthYear());
                    abductor.setStreet(abductorForm.getStreet());
                    abductor.setCity(abductorForm.getCity());
                    abductor.setProvince(abductorForm.getProvince());
                    abductor.setCountry(abductorForm.getCountry());
                    abductor.setSex(abductorForm.getSex());
                    abductor.setFeet(abductorForm.getFeet());
                    abductor.setInches(abductorForm.getInches());
                    abductor.setWeight(abductorForm.getWeight());
                    abductor.setReligion(abductorForm.getReligion());
                    abductor.setRace(abductorForm.getRace());
                    abductor.setEyeColor(abductorForm.getEyeColor());
                    abductor.setHairColor(abductorForm.getHairColor());
                    abductor.setMarks(abductorForm.getMarks());
                    abductor.setPersonalEffects(abductorForm.getPersonalEffects());
                    abductor.setRemarks(abductorForm.getRemarks());
                    if (!abductorForm.getCodisId().isEmpty()) {
                        abductor.setCodisId(abductorForm.getCodisId());
                    }
                    if (!abductorForm.getAfisId().isEmpty()) {
                        abductor.setAfisId(abductorForm.getAfisId());
                    }
                    if (!abductorForm.getDentalId().isEmpty()) {
                        abductor.setDentalId(abductorForm.getDentalId());
                    }
                    int generatedId = abductorService.insertAbductor(abductor);

                    if (generatedId > 0) {

                        // Process uploaded photos
                        FormFile photoFile = abductorForm.getPhotoFile();
                        FormFile agedPhotoFile = abductorForm.getAgedPhotoFile();

                        // Set default context-relative photo filename
                        String contextUnknownPhotoFilename = "photo/unknown.png";
                        String contextDefaultPhotoFilename = contextUnknownPhotoFilename;
                        String contextAgedPhotoFilename = contextUnknownPhotoFilename;

                        // Split the filename to get the extension name
                        if ((photoFile.getFileName().length() > 0) || (agedPhotoFile.getFileName().length() > 0)) {
                            String tokens[] = photoFile.getFileName().toLowerCase().split("\\.");
                            String extensionName = tokens[1];
                            if (agedPhotoFile.getFileName().length() > 0) {
                                tokens = agedPhotoFile.getFileName().toLowerCase().split("\\.");
                                extensionName = tokens[1];
                            }

                            // Create directories for abductor
                            String directoryName = createDirectoryName(generatedId);

                            // Calculate age
                            // TODO what if birth date is unknown?
                            int age = getAge(abductorForm.getBirthMonth() - 1, abductorForm.getBirthDay(), abductorForm.getBirthYear());

                            // Create context-relative directories
                            String contextPhotoDirectory = "photo/" + directoryName;
                            String contextDefaultPhotoDirectory = contextPhotoDirectory + "/default";
                            String contextAgedPhotoDirectory = contextPhotoDirectory + "/aged";

                            // Create absolute directories
                            String absolutePhotoDirectory = getServlet().getServletContext().getRealPath("/") + "photo" + File.separator + directoryName;
                            String absoluteDefaultPhotoDirectory = absolutePhotoDirectory + File.separator + "default";
                            String absoluteAgedPhotoDirectory = absolutePhotoDirectory + File.separator + "aged";
                            File photoDirectory = new File(absolutePhotoDirectory);
                            File defaultPhotoDirectory = new File(absoluteDefaultPhotoDirectory);
                            File agedPhotoDirectory = new File(absoluteAgedPhotoDirectory);
                            if (!photoDirectory.exists()) {
                                photoDirectory.mkdir();
                                defaultPhotoDirectory.mkdir();
                                agedPhotoDirectory.mkdir();
                            } else {
                                if ((!defaultPhotoDirectory.exists()) || (!agedPhotoDirectory.exists())) {
                                    defaultPhotoDirectory.mkdir();
                                    agedPhotoDirectory.mkdir();
                                }
                            }

                            // Prepare filenames and upload photo
                            String absoluteDefaultPhotoFilename = absoluteDefaultPhotoDirectory + File.separator + directoryName + "-age-" + age + "." + extensionName;
                            contextDefaultPhotoFilename = contextDefaultPhotoDirectory + "/" + directoryName + "-age-" + age + "." + extensionName;
                            File file = new File(absoluteDefaultPhotoFilename);
                            FileOutputStream fos = new FileOutputStream(file);
                            fos.write(photoFile.getFileData());
                            fos.close();
                            fos.flush();
                            if (agedPhotoFile.getFileName().length() > 0) {
                                String absoluteAgedPhotoFilename = absoluteAgedPhotoDirectory + File.separator + agedPhotoFile.getFileName().toLowerCase();
                                contextAgedPhotoFilename = contextAgedPhotoDirectory + "/" + directoryName + "." + extensionName;
                                file = new File(absoluteAgedPhotoFilename);
                                fos = new FileOutputStream(file);
                                fos.write(photoFile.getFileData());
                                fos.close();
                                fos.flush();
                            }

                            abductor.setPhoto(contextDefaultPhotoFilename);
                            if (agedPhotoFile.getFileName().length() > 0) {
                                abductor.setAgedPhoto(contextAgedPhotoFilename);
                            } else {
                                abductor.setAgedPhoto(contextUnknownPhotoFilename);
                            }
                            
                            abductorService.updateAbductor(abductor);
                        }

                        // Update abductor ID in person
                        Person person = new Person();
                        person.setId(Integer.parseInt((String) request.getParameter("personid")));
                        person.setAbductorId(generatedId);
                        person.setRelationToAbductor(abductorForm.getRelationToAbductor());
                        personService.updatePersonAbductor(person);

                        // Log abductor creation event
                        Log addLog = new Log();
                        if ((!firstName.isEmpty()) && (!lastName.isEmpty())) {
                            addLog.setLog(firstName + " '" + abductorForm.getNickname() + "' " + lastName + " was encoded by " + currentUser.getUsername() + ".");
                        } else {
                            addLog.setLog("' " + abductorForm.getNickname() + " '" + " was encoded by " + currentUser.getUsername() + ".");
                        }
                        addLog.setDate(simpleDateFormat.format(System.currentTimeMillis()));
                        logService.insertLog(addLog);
                        logger.info(addLog.toString());

                        // Return abductor ID
                        request.setAttribute("personid", Integer.parseInt((String) request.getParameter("personid")));
                        request.setAttribute("investigatorid", generatedId);

                        return mapping.findForward(Constants.SELECT_INVESTIGATOR);
                    } else {
                        return mapping.findForward(Constants.FAILURE);
                    }
                } else {
                    request.setAttribute("personid", request.getParameter("personid"));

                    // Return duplicate abductor error
                    errors.add("abductor", new ActionMessage("error.abductor.duplicate"));
                    saveErrors(request, errors);

                    logger.error("Duplicate abductor.");

                    return mapping.findForward(Constants.ADD_ABDUCTOR_REDO);
                }
            } else {
                request.setAttribute("personid", request.getParameter("personid"));
                    
                // Return form validation errors
                return mapping.findForward(Constants.ADD_ABDUCTOR_REDO);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Retrieves a abductor from the database.
     * This is the view abductor action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward viewAbductor(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;
        AbductorForm abductorForm = (AbductorForm) form;

        // Retrieve abductor
        int id = 0;
        try {
            if (request.getParameter("id") != null) {
                id = Integer.parseInt(request.getParameter("id"));
            } else {
                //return mapping.findForward(Constants.LIST_ABDUCTOR);
            }
        } catch (NumberFormatException nfe) {
            //return mapping.findForward(Constants.LIST_ABDUCTOR);
        }
        Abductor abductor = abductorService.getAbductorById(id);

        // Return abductor
        if (abductor.getPhoto() != null) {
            abductorForm.setPhoto(abductor.getPhoto());
        }
        if (abductor.getAgedPhoto() != null) {
            abductorForm.setAgedPhoto(abductor.getAgedPhoto());
        }
        abductorForm.setId(abductor.getId());
        abductorForm.setFirstName(abductor.getFirstName());
        abductorForm.setNickname(abductor.getNickname());
        abductorForm.setMiddleName(abductor.getMiddleName());
        abductorForm.setLastName(abductor.getLastName());
        // TODO what if birth date is unknown?
            abductorForm.setBirthMonth(abductor.getBirthMonth());
            abductorForm.setBirthDay(abductor.getBirthDay());
            abductorForm.setBirthYear(abductor.getBirthYear());
            abductorForm.setAge(getAge(abductor.getBirthMonth() - 1, abductor.getBirthDay(), abductor.getBirthYear()));
        abductorForm.setStreet(abductor.getStreet());
        abductorForm.setCity(abductor.getCity());
        abductorForm.setProvince(abductor.getProvince());
        abductorForm.setCountry(abductor.getCountry());
        abductorForm.setSex(abductor.getSex());
        abductorForm.setFeet(abductor.getFeet());
        abductorForm.setInches(abductor.getInches());
        abductorForm.setWeight(abductor.getWeight());
        abductorForm.setReligion(abductor.getReligion());
        abductorForm.setRace(abductor.getRace());
        abductorForm.setEyeColor(abductor.getEyeColor());
        abductorForm.setHairColor(abductor.getHairColor());
        abductorForm.setMarks(abductor.getMarks());
        abductorForm.setPersonalEffects(abductor.getPersonalEffects());
        abductorForm.setRemarks(abductor.getRemarks());
        if (abductor.getCodisId() != null) {
            abductorForm.setCodisId(abductor.getCodisId());
        }
        if (abductor.getAfisId() != null) {
            abductorForm.setAfisId(abductor.getAfisId());
        }
        if (abductor.getDentalId() != null) {
            abductorForm.setDentalId(abductor.getDentalId());
        }

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") != null) {
            currentUser = (User) request.getSession().getAttribute("currentuser");
            request.setAttribute("action", request.getParameter("action"));

            // Edit what you created/encoded
            // Edit your profile
            // Administrator can edit all except administrators
            //if (currentUser.getId() == abductor.getEncoderId()) {
                return mapping.findForward(Constants.EDIT_ABDUCTOR);
            //} else {
            //    return mapping.findForward(Constants.VIEW_ABDUCTOR);
            //}
        } else {
            return mapping.findForward(Constants.VIEW_ABDUCTOR);
        }
    }

    /**
     * Updates a abductor.
     * This is the edit abductor action called from the HTML form.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward editAbductor(ActionMapping mapping, ActionForm form,
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
            AbductorForm abductorForm = (AbductorForm) form;
            ActionMessages errors = new ActionMessages();
            request.setAttribute("action", request.getParameter("action"));

            // Check if form is valid
            if (isValidAbductor(request, form)) {
                // Check if updated abductor is unique
                // and if the rest of the form is valid
                Abductor checker = new Abductor();
                String firstName = abductorForm.getFirstName();
                String lastName = abductorForm.getLastName();
                checker.setId(abductorForm.getId());
                checker.setFirstName(firstName);
                checker.setLastName(lastName);

                // Check if abductor is unique
                if (abductorService.isUniqueAbductor(checker)) {

                    // Process uploaded photos
                    FormFile photoFile = abductorForm.getPhotoFile();
                    FormFile agedPhotoFile = abductorForm.getAgedPhotoFile();

                    // Set default context-relative photo filename
                    String contextUnknownPhotoFilename = "photo/unknown.png";
                    String contextDefaultPhotoFilename = contextUnknownPhotoFilename;
                    String contextAgedPhotoFilename = contextUnknownPhotoFilename;

                    // Split the filename to get the extension name
                    if ((photoFile.getFileName().length() > 0) || (agedPhotoFile.getFileName().length() > 0)) {
                        String tokens[] = photoFile.getFileName().toLowerCase().split("\\.");
                        String extensionName = tokens[1];
                        tokens = agedPhotoFile.getFileName().toLowerCase().split("\\.");
                        extensionName = tokens[1];

                        // Create directories for abductor
                        String directoryName = createDirectoryName(abductorForm.getId());

                        // Calculate age
                        // TODO what if birth date is unknown?
                        int age = getAge(abductorForm.getBirthMonth() - 1, abductorForm.getBirthDay(), abductorForm.getBirthYear());

                        // Create context-relative directories
                        String contextPhotoDirectory = "photo/" + directoryName;
                        String contextDefaultPhotoDirectory = contextPhotoDirectory + "/default";
                        String contextAgedPhotoDirectory = contextPhotoDirectory + "/aged";

                        // Create absolute directories
                        String absolutePhotoDirectory = getServlet().getServletContext().getRealPath("/") + "photo" + File.separator + directoryName;
                        String absoluteDefaultPhotoDirectory = absolutePhotoDirectory + File.separator + "default";
                        String absoluteAgedPhotoDirectory = absolutePhotoDirectory + File.separator + "aged";
                        File photoDirectory = new File(absolutePhotoDirectory);
                        File defaultPhotoDirectory = new File(absoluteDefaultPhotoDirectory);
                        File agedPhotoDirectory = new File(absoluteAgedPhotoDirectory);
                        if (!photoDirectory.exists()) {
                            photoDirectory.mkdir();
                            defaultPhotoDirectory.mkdir();
                            agedPhotoDirectory.mkdir();
                        } else {
                            if ((!defaultPhotoDirectory.exists()) || (!agedPhotoDirectory.exists())) {
                                defaultPhotoDirectory.mkdir();
                                agedPhotoDirectory.mkdir();
                            }
                        }

                        // Prepare filenames and upload photo
                        String absoluteDefaultPhotoFilename = absoluteDefaultPhotoDirectory + File.separator + directoryName + "-age-" + age + "." + extensionName;
                        contextDefaultPhotoFilename = contextDefaultPhotoDirectory + "/" + directoryName + "-age-" + age + "." + extensionName;
                        File file = new File(absoluteDefaultPhotoFilename);
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(photoFile.getFileData());
                        fos.close();
                        fos.flush();
                        if (agedPhotoFile.getFileName().length() > 0) {
                            String absoluteAgedPhotoFilename = absoluteAgedPhotoDirectory + File.separator + agedPhotoFile.getFileName().toLowerCase();
                            contextAgedPhotoFilename = contextAgedPhotoDirectory + "/" + directoryName + "." + extensionName;
                            file = new File(absoluteAgedPhotoFilename);
                            fos = new FileOutputStream(file);
                            fos.write(photoFile.getFileData());
                            fos.close();
                            fos.flush();
                        }
                    }

                    // Insert abductor
                    Abductor abductor = new Abductor();
                    abductor.setId(abductorForm.getId());
                    if (agedPhotoFile.getFileName().length() > 0) {
                        abductor.setPhoto(contextDefaultPhotoFilename);
                    } else {
                        abductor.setPhoto(abductorForm.getPhoto());
                    }
                    if (agedPhotoFile.getFileName().length() > 0) {
                        abductor.setAgedPhoto(contextAgedPhotoFilename);
                    }
                    abductor.setFirstName(firstName);
                    abductor.setNickname(abductorForm.getNickname());
                    abductor.setMiddleName(abductorForm.getMiddleName());
                    abductor.setLastName(lastName);
                    abductor.setBirthMonth(abductorForm.getBirthMonth());
                    abductor.setBirthDay(abductorForm.getBirthDay());
                    abductor.setBirthYear(abductorForm.getBirthYear());
                    abductor.setStreet(abductorForm.getStreet());
                    abductor.setCity(abductorForm.getCity());
                    abductor.setProvince(abductorForm.getProvince());
                    abductor.setCountry(abductorForm.getCountry());
                    abductor.setSex(abductorForm.getSex());
                    abductor.setFeet(abductorForm.getFeet());
                    abductor.setInches(abductorForm.getInches());
                    abductor.setWeight(abductorForm.getWeight());
                    abductor.setReligion(abductorForm.getReligion());
                    abductor.setRace(abductorForm.getRace());
                    abductor.setEyeColor(abductorForm.getEyeColor());
                    abductor.setHairColor(abductorForm.getHairColor());
                    abductor.setMarks(abductorForm.getMarks());
                    abductor.setPersonalEffects(abductorForm.getPersonalEffects());
                    abductor.setRemarks(abductorForm.getRemarks());
                    if (!abductorForm.getCodisId().isEmpty()) {
                        abductor.setCodisId(abductorForm.getCodisId());
                    }
                    if (!abductorForm.getAfisId().isEmpty()) {
                        abductor.setAfisId(abductorForm.getAfisId());
                    }
                    if (!abductorForm.getDentalId().isEmpty()) {
                        abductor.setDentalId(abductorForm.getDentalId());
                    }
                    boolean isUpdated = abductorService.updateAbductor(abductor);

                    // Retrieve updated abductor
                    abductor = abductorService.getAbductorById(abductor.getId());

                    if (isUpdated) {
                        // Update abductor ID in person
                        Person person = new Person();
                        person.setId(Integer.parseInt(request.getParameter("personid")));
                        person.setAbductorId(abductorForm.getId());
                        person.setRelationToAbductor(abductorForm.getRelationToAbductor());
                        personService.updatePersonAbductor(person);

                        // Log abductor modification event
                        Log editLog = new Log();
                        if ((abductor.getFirstName().equals(abductorForm.getFirstName())) &&
                                (abductor.getNickname().equals(abductorForm.getNickname())) &&
                                (abductor.getMiddleName().equals(abductorForm.getMiddleName())) &&
                                (abductor.getLastName().equals(abductorForm.getLastName()))) {
                            editLog.setLog("Abductor " + abductor.getNickname() + " was updated by " + currentUser.getUsername() + ".");
                        } else {
                            editLog.setLog("Abductor " + abductor.getFirstName() + " '" + abductor.getNickname() + "' " + abductor.getLastName() +
                                    " was renamed to " + firstName + " '" + abductorForm.getNickname() + "' " + lastName + " by " + currentUser.getUsername() + ".");
                        }
                        editLog.setDate(simpleDateFormat.format(System.currentTimeMillis()));
                        logService.insertLog(editLog);
                        logger.info(editLog.toString());

                        person = personService.getPersonById(Integer.parseInt(request.getParameter("personid")));

                        // Check if abductor is abandoned, throwaway or unidentified
                        // Return abductor ID and investigator ID
                        request.setAttribute("personid", Integer.parseInt(request.getParameter("personid")));
                        request.setAttribute("investigatorid", person.getInvestigatorId());

                        return mapping.findForward(Constants.SELECT_INVESTIGATOR);
                    } else {
                        return mapping.findForward(Constants.FAILURE);
                    }
                } else {
                    // Return duplicate abductor error
                    errors.add("nickname", new ActionMessage("error.abductor.duplicate"));
                    saveErrors(request, errors);

                    logger.error("Duplicate abductor.");

                    return mapping.findForward(Constants.ADD_ABDUCTOR_REDO);
                }
            } else {
                // Return form validation errors
                return mapping.findForward(Constants.ADD_ABDUCTOR_REDO);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Prepares the form for deleting a abductor.
     * This is the erase abductor action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    /*
    public ActionForward eraseAbductor(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
    User currentUser = null;

    // Check if there exists a session
    if (request.getSession().getAttribute("currentuser") == null) {
    return mapping.findForward(Constants.EXPIRED);
    } else {
    currentUser = (User) request.getSession().getAttribute("currentuser");
    }

    // Check if current abductor is authorized
    if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
    AbductorForm abductorForm = (AbductorForm) form;
    // Retrieve abductor
    Abductor abductor = (Abductor) personService.getPersonById(new Integer(abductorForm.getId()));

    abductorForm.setPersonname(abductor.getPersonname());
    // Generate 4-digit random code
    abductorForm.setCode((int) (Math.random() * 9999) + 1000);

    // Delete what you created/encoded
    // Administrator can delete all except administrators
    if ((currentUser.getId() == abductor.getCreatorId()) ||
    ((currentUser.getGroupId() == 0) && (abductor.getGroupId() > 0))) {
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
     * Deletes a abductor from the database.
     * This is the delete abductor action called from the HTML form.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    /*
    public ActionForward deleteAbductor(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
    User currentUser = null;

    // Check if there exists a session
    if (request.getSession().getAttribute("currentuser") == null) {
    return mapping.findForward(Constants.EXPIRED);
    } else {
    currentUser = (User) request.getSession().getAttribute("currentuser");
    }

    // Check if current abductor is authorized
    if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
    AbductorForm abductorForm = (AbductorForm) form;

    Abductor abductor = (User) personService.getPersonById(new Integer(abductorForm.getId()));

    // Check if codes match
    if (abductorForm.getCode() == abductorForm.getPersonCode()) {
    // Administrator can delete a abductor except his creator/encoder
    // Abductor can delete a abductor that he encoded
    if (((currentUser.getGroupId() == 0) && (currentUser.getCreatorId() != abductor.getId())) ||
    ((currentUser.getGroupId() == 1) && (currentUser.getId() == abductor.getCreatorId()))) {
    // Delete abductor
    personService.deletePerson(new Integer(abductor.getId()));

    // Log abductor deletion event
    Log deleteLog = new Log();
    deleteLog.setLog("Abductor " + abductor.getPersonname() + " was deleted by " + currentUser.getPersonname() + ".");
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
    request.setAttribute("personname", abductor.getPersonname());
    request.setAttribute("operation", "delete");

    return mapping.findForward(Constants.DELETE_SUCCESS);
    } else {
    return mapping.findForward(Constants.UNAUTHORIZED);
    }
    } else {
    // Generate 4-digit random code
    abductorForm.setCode((int) (Math.random() * 9999) + 1000);
    abductorForm.setPersonname(abductor.getPersonname());

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
     * Prints the abductor's poster in PDF file.
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

        // Retrieve the abductor
        int id = 0;
        try {
            if (request.getParameter("id") != null) {
                id = Integer.parseInt(request.getParameter("id"));
            } else {
                //return mapping.findForward(Constants.LIST_ABDUCTOR);
            }
        } catch (NumberFormatException nfe) {
            //return mapping.findForward(Constants.LIST_ABDUCTOR);
        }
        Abductor abductor = abductorService.getAbductorById(id);

        // Process the photo
        String tokens[] = abductor.getPhoto().split("\\/");
        String defaultPhotoBasename = "";
        for (int i = 0; i < tokens.length - 1; i++) {
            defaultPhotoBasename += tokens[i] + File.separator;
        }
        defaultPhotoBasename += tokens[tokens.length - 1];
        String absoluteDefaultPhotoFilename = getServlet().getServletContext().getRealPath("/") + defaultPhotoBasename;

        // Add some meta information to the document
        document.addTitle("Poster");
        document.addAuthor("OpenMPIS");
        document.addSubject("Poster for " + abductor.getNickname());
        document.addKeywords("OpenMPIS, missing, found, unidentified");
        document.addProducer();
        document.addCreationDate();
        document.addCreator("OpenMPIS version " + Constants.VERSION);

        // Open the document for writing
        document.open();
        // Add the banner
        Paragraph wantedParagraph = new Paragraph("W A N T E D", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 36, Font.BOLD, new Color(255, 0, 0)));
        wantedParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(wantedParagraph);
        // Add name
        Paragraph redParagraph = new Paragraph(abductor.getFirstName() + " '" + abductor.getNickname() + "' " + abductor.getLastName(), FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new Color(255, 0, 0)));
        redParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(redParagraph);
        // Add the photo
        Image image = Image.getInstance(absoluteDefaultPhotoFilename);
        image.scaleAbsolute(200, 300);
        image.setAlignment(Image.ALIGN_CENTER);
        document.add(image);
        // Add birth date
        Paragraph blackParagraph = new Paragraph(getResources(request).getMessage("label.date.birth") + ": " + getResources(request).getMessage("month." + abductor.getBirthMonth()) + " " + abductor.getBirthDay() + ", " + abductor.getBirthYear(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 0)));
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        // Add birth place
        blackParagraph = new Paragraph(getResources(request).getMessage("label.address.city") + ": " + abductor.getCity(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 0)));
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        // Add sex
        blackParagraph = new Paragraph(getResources(request).getMessage("label.sex") + ": " + getResources(request).getMessage("sex." + abductor.getSex()), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 0)));
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        // Add height
        blackParagraph = new Paragraph(getResources(request).getMessage("label.height") + ": " + abductor.getFeet() + "' " + abductor.getInches() + "\"", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 0)));
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        // Add weight
        blackParagraph = new Paragraph(getResources(request).getMessage("label.weight") + ": " + abductor.getWeight() + " " + getResources(request).getMessage("label.weight.lbs"), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 0)));
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        // Add hair color
        blackParagraph = new Paragraph(getResources(request).getMessage("label.color.hair") + ": " + getResources(request).getMessage("color.hair." + abductor.getHairColor()), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 0)));
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        // Add eye color
        blackParagraph = new Paragraph(getResources(request).getMessage("label.color.eye") + ": " + getResources(request).getMessage("color.eye." + abductor.getEyeColor()), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 0)));
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        // Add race
        blackParagraph = new Paragraph(getResources(request).getMessage("label.race") + ": " + getResources(request).getMessage("race." + abductor.getRace()), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 0)));
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        // Add circumstance
        blackParagraph = new Paragraph(getResources(request).getMessage("label.remarks") + ": " + abductor.getRemarks(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0, 0, 0)));
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        // Add line
        blackParagraph = new Paragraph("---------------------------------------");
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        // Add contact
        blackParagraph = new Paragraph(getResources(request).getMessage("global.contact"), FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL, new Color(0, 0, 0)));
        blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(blackParagraph);
        document.close();

        // Set the response to return the poster (PDF file)
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        response.setHeader("Content-disposition", "attachment; filename=Poster.pdf");

        // Close the output stream
        baos.writeTo(response.getOutputStream());
        response.getOutputStream().flush();

        return null;
    }

    /**
     * Validates the inputs from the abductor form.
     *
     * @param request       the HTTP Request we are processing
     * @param form          the ActionForm bean for this request
     * @return              <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    private boolean isValidAbductor(HttpServletRequest request, ActionForm form) throws Exception {
        ActionMessages errors = new ActionMessages();
        Validator validator = new Validator();
        boolean isValid = true;
        Calendar calendar = Calendar.getInstance();

        AbductorForm abductorForm = (AbductorForm) form;
        FormFile photoFile = abductorForm.getPhotoFile();
        FormFile agedPhotoFile = abductorForm.getAgedPhotoFile();
        String firstName = abductorForm.getFirstName();
        String nickname = abductorForm.getNickname();
        String middleName = abductorForm.getMiddleName();
        String lastName = abductorForm.getLastName();
        int birthDay = abductorForm.getBirthDay();
        int birthMonth = abductorForm.getBirthMonth() - 1;
        int birthYear = abductorForm.getBirthYear();
        String street = abductorForm.getStreet();
        String city = abductorForm.getCity();
        String province = abductorForm.getProvince();
        String remarks = abductorForm.getRemarks();

        String codisId = abductorForm.getCodisId();
        String afisId = abductorForm.getAfisId();
        String dentalId = abductorForm.getDentalId();

        Abductor existingAbductor = new Abductor();
        if (abductorForm.getId() > 0) {
            existingAbductor = abductorService.getAbductorById(abductorForm.getId());
            if (existingAbductor.getPhoto() != null) {
                abductorForm.setPhoto(existingAbductor.getPhoto());
            }
            if (existingAbductor.getAgedPhoto() != null) {
                abductorForm.setAgedPhoto(existingAbductor.getAgedPhoto());
            }
        }

        if ((photoFile.getFileName().length() > 1) && (!((photoFile.getContentType().equals("image/png")) ||
                (photoFile.getContentType().equals("image/jpeg")) ||
                (photoFile.getContentType().equals("image/gif"))))) {
            errors.add("photofile", new ActionMessage("error.photo.invalid"));
        }

        if ((agedPhotoFile.getFileName().length() > 1) && ((!agedPhotoFile.getContentType().equals("image/png")) ||
                (!agedPhotoFile.getContentType().equals("image/jpeg")) ||
                (!agedPhotoFile.getContentType().equals("image/gif")))) {
            errors.add("agedphotofile", new ActionMessage("error.photo.invalid"));
        }

        if ((firstName.length() > 1) && (!validator.isValidFirstName(firstName))) {
            errors.add("firstname", new ActionMessage("error.firstname.invalid"));
        }

        if ((nickname.length() > 1) && (!validator.isValidFirstName(nickname))) {
            errors.add("nickname", new ActionMessage("error.nickname.invalid"));
        }

        if ((middleName.length() > 1) && (!validator.isValidLastName(middleName))) {
            errors.add("middlename", new ActionMessage("error.middlename.invalid"));
        }

        if ((lastName.length() > 1) && (!validator.isValidLastName(lastName))) {
            errors.add("lastname", new ActionMessage("error.lastname.invalid"));
        }

        if (birthMonth > calendar.get(Calendar.MONTH) && (birthYear == calendar.get(Calendar.YEAR))) {
            errors.add("birthdate", new ActionMessage("error.birthmonth.invalid"));
        }

        if ((birthMonth == calendar.get(Calendar.MONTH)) && (birthDay > calendar.get(Calendar.DATE)) && (birthYear == calendar.get(Calendar.YEAR))) {
            errors.add("birthdate", new ActionMessage("error.birthday.invalid"));
        }

        if ((street.length() > 1) && (!validator.isValidStreet(street))) {
            errors.add("street", new ActionMessage("error.street.invalid"));
        }

        if ((city.length() > 1) && (!validator.isValidCity(city))) {
            errors.add("city", new ActionMessage("error.city.invalid"));
        }

        if ((province.length() > 1) && (!validator.isValidProvince(province))) {
            errors.add("province", new ActionMessage("error.province.invalid"));
        }

        if (remarks.length() < 1) {
            errors.add("remarks", new ActionMessage("error.remarks.required"));
        } else {
            if (remarks.length() < 10) {
                errors.add("remarks", new ActionMessage("error.remarks.invalid"));
            }
        }

        if ((codisId.length() > 1) && (!validator.isValidId(codisId))) {
            errors.add("codisid", new ActionMessage("error.codisid.invalid"));
        }

        if ((afisId.length() > 1) && (!validator.isValidId(afisId))) {
            errors.add("afisid", new ActionMessage("error.afisid.invalid"));
        }

        if ((dentalId.length() > 1) && (!validator.isValidId(dentalId))) {
            errors.add("dentalid", new ActionMessage("error.dentalid.invalid"));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            isValid =
                    false;
        }

        Iterator i = errors.get();
        while (i.hasNext()) {
            System.out.println("errors: " + i.next());
        }

        return isValid;
    }

    /**
     * Counts the province of reports for a given abductor.
     *
     * @return              the province of reports associated with a given abductor
     * @throws java.lang.Exception
     */
    private int countReportsForPerson(Integer personId) throws Exception {
        return reportService.countAllReportsForPerson(personId);
    }

    /**
     * Creates a unique directory name for the abductor's uploaded photos.
     * Adapted from http://snipplr.com/view/4321/generate-md5-hash-from-string/.
     *
     * @param id            the id of the abductor on which the directory name is based
     * @return              the 32 alphanumeric-equivalent of the nickname
     * @throws java.security.NoSuchAlgorithmException
     */
    private String createDirectoryName(Integer id) throws NoSuchAlgorithmException {
        StringBuffer uniqueDirectoryName = new StringBuffer();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        md5.update(id.byteValue());
        byte digest[] = md5.digest();
        for (int i = 0; i < digest.length; i++) {
            uniqueDirectoryName.append(Integer.toHexString(0xFF & digest[i]));
        }

        return uniqueDirectoryName.toString();
    }

    /**
     * Calculates the age of a abductor.
     * Adapted from http://www.coderanch.com/t/391834/Java-General-beginner/java/there-better-way-calculate-age
     *
     * @param birthMonth    the abductor's birth month
     * @param birthDay      the abductor's birth day
     * @param birthYear     the abductor's birth year
     * @return              the abductor's age
     */
    private int getAge(int birthMonth, int birthDay, int birthYear) {
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(birthYear, birthMonth, birthDay);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }
}