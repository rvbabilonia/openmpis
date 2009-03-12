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

import com.googlecode.openmpis.dto.Log;
import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.form.InvestigatorForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.AbductorDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.PersonDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.RelativeDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.UserDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.AbductorService;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.PersonService;
import com.googlecode.openmpis.persistence.ibatis.service.RelativeService;
import com.googlecode.openmpis.persistence.ibatis.service.UserService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.AbductorServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.PersonServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.RelativeServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.UserServiceImpl;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Pagination;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 * The CaseAction class provides the methods to list persons.
 *
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class CaseAction extends DispatchAction {

    /**
     * The person service
     */
    private PersonService personService = new PersonServiceImpl(new PersonDAOImpl());
    /**
     * The user service
     */
    private UserService userService = new UserServiceImpl(new UserDAOImpl());
    /**
     * The relative service
     */
    private RelativeService relativeService = new RelativeServiceImpl(new RelativeDAOImpl());
    /**
     * The abductor service
     */
    private AbductorService abductorService = new AbductorServiceImpl(new AbductorDAOImpl());
    /**
     * The pagination context
     */
    private Pagination pagination = new Pagination();
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
     * Lists all cases. Administrators and the general public can view all cases.
     * Encoders can view only those cases that they themselves encoded.
     * Investigators can view only those cases that were assigned to them.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        User currentUser = (User) request.getSession().getAttribute("currentuser");
        List<Person> personList = null;

        // Retrieve list of persons
        if (currentUser != null) {
            if (currentUser.getGroupId() == 1) {
                personList = personService.getPersonsByEncoderId(pagination, currentUser.getId());
            } else if (currentUser.getGroupId() == 2) {
                personList = personService.getPersonsByInvestigatorId(pagination, currentUser.getId());
            } else {
                personList = personService.getAllPersons(pagination);
            }
        } else {
            personList = personService.getAllPersons(pagination);
        }

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON);
    }

    /**
     * Lists all missing persons.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listAllMissing(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getAllMissing(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_ALL_MISSING);
    }

    /**
     * Lists missing persons.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listMissing(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getMissing(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_ALL_MISSING);
    }

    /**
     * Lists family abductions.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listFamilyAbduction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getFamilyAbduction(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_ALL_MISSING);
    }

    /**
     * Lists non-family abductions.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listNonFamilyAbduction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getNonFamilyAbduction(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_ALL_MISSING);
    }

    /**
     * Lists runaway persons.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listRunaway(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getRunaway(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_ALL_MISSING);
    }

    /**
     * Lists cases with unknown classification.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listUnknown(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getUnknown(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_ALL_MISSING);
    }

    /**
     * Lists all found persons.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listAllFound(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getAllFound(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_ALL_FOUND);
    }

    /**
     * Lists abandoned persons.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listAbandoned(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getAbandoned(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_ALL_FOUND);
    }

    /**
     * Lists throwaway persons.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listThrowaway(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getThrowaway(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_ALL_FOUND);
    }

    /**
     * Lists all unidentified persons.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listUnidentified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getUnidentified(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_UNIDENTIFIED);
    }

    /**
     * Lists all solved cases.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listSolved(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getSolved(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_SOLVED);
    }

    /**
     * Lists all unsolved cases.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listUnsolved(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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

        // Retrieve list of persons
        List<Person> personList = personService.getUnsolved(pagination);

        // Return number of persons
        request.setAttribute("personcount", personList.size());

        // Return list of persons
        request.setAttribute("personlist", personList);

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

        return mapping.findForward(Constants.LIST_PERSON_UNSOLVED);
    }

    /**
     * Counts the number of cases in the system.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward countCases(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("casecount", personService.countAllPersons());
        request.setAttribute("ongoingcount", personService.countOngoing());
        request.setAttribute("solvedcount", personService.countSolved());
        request.setAttribute("unsolvedcount", personService.countUnsolved());
        request.setAttribute("missingcount", personService.countMissing());
        request.setAttribute("familyabductioncount", personService.countFamilyAbduction());
        request.setAttribute("nonfamilyabductioncount", personService.countNonFamilyAbduction());
        request.setAttribute("runawaycount", personService.countRunaway());
        request.setAttribute("unknowncount", personService.countUnknown());
        request.setAttribute("abandonedcount", personService.countAbandoned());
        request.setAttribute("throwawaycount", personService.countThrowaway());
        request.setAttribute("unidentifiedcount", personService.countUnidentified());
        request.setAttribute("relativecount", relativeService.countAllRelatives());
        request.setAttribute("abductorcount", abductorService.countAllAbductors());

        return mapping.findForward(Constants.CASE_STATISTICS);
    }

    /**
     * Lists investigators for selection.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward selectInvestigator(ActionMapping mapping, ActionForm form,
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
            List<User> investigatorList = userService.listActiveInvestigators();
            request.setAttribute("investigatorlist", investigatorList);

            InvestigatorForm investigatorForm = (InvestigatorForm) form;
            Person person = personService.getPersonById(Integer.parseInt(request.getAttribute("personid").toString()));

            if (person.getInvestigatorId() != null) {
                investigatorForm.setId(person.getInvestigatorId());
            }

            return mapping.findForward(Constants.ASSIGN_INVESTIGATOR);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Assigns an investigator and saves everything in the database.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward assignInvestigator(ActionMapping mapping, ActionForm form,
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
            InvestigatorForm investigatorForm = (InvestigatorForm) form;
            Person person = personService.getPersonById(Integer.parseInt(request.getParameter("personid")));
            User investigator = userService.getUserById(investigatorForm.getId());
            person.setInvestigatorId(investigator.getId());
            personService.updatePersonInvestigator(person);

            // Log person modification event
            Log assignLog = new Log();
            assignLog.setLog("Person " + person.getNickname() + " was assigned to " + investigator.getUsername() + " by " + currentUser.getUsername() + ".");

            assignLog.setDate(simpleDateFormat.format(System.currentTimeMillis()));
            logService.insertLog(assignLog);
            logger.info(assignLog.toString());

            request.setAttribute("person", person);
            request.setAttribute("investigator", investigator);

            return mapping.findForward(Constants.EDIT_PERSON_SUCCESS);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Writes the cases to a PDF file.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward printCases(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") != null) {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Set the paper size and margins
        Document document = new Document(PageSize.LETTER.rotate(), 50, 50, 50, 50);

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

        // Set the header
        String date = simpleDateFormat.format(System.currentTimeMillis());
        document.setHeader(new HeaderFooter(new Phrase("Statistics for cases as of " + date), false));

        // Set the footer
        HeaderFooter footer = new HeaderFooter(new Phrase("Page : "), true);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.setFooter(footer);

        // Open the document for writing
        document.open();
        Table table = new Table(2);
        table.setBorderWidth(1);
        table.setBorderColor(new Color(0, 0, 0));
        table.setPadding(2);
        table.setSpacing(0);
        Paragraph paragraph = new Paragraph("Cases", FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD, new Color(0, 0, 0)));
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        Cell cell = new Cell(paragraph);
        cell.setHeader(true);
        cell.setColspan(2);
        table.addCell(cell);
        table.endHeaders();
        table.addCell("Total On-going Cases");
        table.addCell("" + personService.countOngoing());
        table.addCell("\t\t\t\t\tMissing Persons");
        table.addCell("\t\t\t\t\t" + personService.countMissing());
        table.addCell("\t\t\t\t\tFamily Abductions");
        table.addCell("\t\t\t\t\t" + personService.countFamilyAbduction());
        table.addCell("\t\t\t\t\tNon-Family Abductions");
        table.addCell("\t\t\t\t\t" + personService.countNonFamilyAbduction());
        table.addCell("\t\t\t\t\tRunaway Persons");
        table.addCell("\t\t\t\t\t" + personService.countRunaway());
        table.addCell("\t\t\t\t\tUnknown");
        table.addCell("\t\t\t\t\t" + personService.countUnknown());
        table.addCell("\t\t\t\t\tAbandoned Persons");
        table.addCell("\t\t\t\t\t" + personService.countAbandoned());
        table.addCell("\t\t\t\t\tThrowaway Persons");
        table.addCell("\t\t\t\t\t" + personService.countThrowaway());
        table.addCell("\t\t\t\t\tUnidentified");
        table.addCell("\t\t\t\t\t" + personService.countUnidentified());
        table.addCell("Total Solved Cases");
        table.addCell("" + personService.countSolved());
        table.addCell("\t\t\t\t\tMissing Persons");
        table.addCell("\t\t\t\t\t" + personService.countMissing());
        table.addCell("\t\t\t\t\tFamily Abductions");
        table.addCell("\t\t\t\t\t" + personService.countFamilyAbduction());
        table.addCell("\t\t\t\t\tNon-Family Abductions");
        table.addCell("\t\t\t\t\t" + personService.countNonFamilyAbduction());
        table.addCell("\t\t\t\t\tRunaway Persons");
        table.addCell("\t\t\t\t\t" + personService.countRunaway());
        table.addCell("\t\t\t\t\tUnknown");
        table.addCell("\t\t\t\t\t" + personService.countUnknown());
        table.addCell("\t\t\t\t\tAbandoned Persons");
        table.addCell("\t\t\t\t\t" + personService.countAbandoned());
        table.addCell("\t\t\t\t\tThrowaway Persons");
        table.addCell("\t\t\t\t\t" + personService.countThrowaway());
        table.addCell("\t\t\t\t\tUnidentified");
        table.addCell("\t\t\t\t\t" + personService.countUnidentified());
        table.addCell("Total Unsolved Cases");
        table.addCell("" + personService.countUnsolved());
        table.addCell("\t\t\t\t\tMissing Persons");
        table.addCell("\t\t\t\t\t" + personService.countMissing());
        table.addCell("\t\t\t\t\tFamily Abductions");
        table.addCell("\t\t\t\t\t" + personService.countFamilyAbduction());
        table.addCell("\t\t\t\t\tNon-Family Abductions");
        table.addCell("\t\t\t\t\t" + personService.countNonFamilyAbduction());
        table.addCell("\t\t\t\t\tRunaway Persons");
        table.addCell("\t\t\t\t\t" + personService.countRunaway());
        table.addCell("\t\t\t\t\tUnknown");
        table.addCell("\t\t\t\t\t" + personService.countUnknown());
        table.addCell("\t\t\t\t\tAbandoned Persons");
        table.addCell("\t\t\t\t\t" + personService.countAbandoned());
        table.addCell("\t\t\t\t\tThrowaway Persons");
        table.addCell("\t\t\t\t\t" + personService.countThrowaway());
        table.addCell("\t\t\t\t\tUnidentified");
        table.addCell("\t\t\t\t\t" + personService.countUnidentified());
        table.addCell("Total Cases");
        table.addCell("" + personService.countAllPersons());
        table.addCell("\t\t\t\t\tTotal Missing Persons");
        table.addCell("\t\t\t\t\t" + personService.countAllMissing());
        table.addCell("\t\t\t\t\tTotal Found Persons");
        table.addCell("\t\t\t\t\t" + personService.countAllFound());
        table.addCell("\t\t\t\t\tTotal Unidentified Persons");
        table.addCell("\t\t\t\t\t" + personService.countUnidentified());
        table.addCell("Total Relatives");
        table.addCell("" + relativeService.countAllRelatives());
        table.addCell("Total Abductors");
        table.addCell("" + abductorService.countAllAbductors());
        document.add(table);
        if (currentUser != null) {
            document.setHeader(new HeaderFooter(new Phrase("List of missing persons as of " + date), false));
            document.newPage();
            float[] widths = {0.03f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.2f, 0.05f};
            PdfPTable pdfptable = new PdfPTable(widths);
            pdfptable.setWidthPercentage(100);
            pdfptable.addCell(new Phrase("ID", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            pdfptable.addCell(new Phrase("Last Name", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            pdfptable.addCell(new Phrase("First Name", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            pdfptable.addCell(new Phrase("Nickname", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            pdfptable.addCell(new Phrase("Middle Name", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            pdfptable.addCell(new Phrase("Case Type", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            pdfptable.addCell(new Phrase("Status", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            pdfptable.addCell(new Phrase("Photo", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            pdfptable.addCell(new Phrase("Relative", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            pdfptable.addCell(new Phrase("Abductor", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            pdfptable.addCell(new Phrase("Photo", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            List<Person> personList = null;
            if (personList != null) {
                for (Person person : personList) {
                    // Process the photo
                    String tokens[] = person.getPhoto().split("\\/");
                    String defaultPhotoBasename = "";
                    for (int i = 0; i < tokens.length - 1; i++) {
                        defaultPhotoBasename += tokens[i] + File.separator;
                    }
                    defaultPhotoBasename += tokens[tokens.length - 1];
                    String absoluteDefaultPhotoFilename = getServlet().getServletContext().getRealPath("/") + defaultPhotoBasename;
                    Image image = Image.getInstance(absoluteDefaultPhotoFilename);
                    image.scaleAbsolute(200, 300);
                    image.setAlignment(Image.ALIGN_CENTER);

                    pdfptable.addCell(new Phrase("" + person.getId(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                    pdfptable.addCell(new Phrase(person.getLastName(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                    pdfptable.addCell(new Phrase(person.getFirstName(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                    pdfptable.addCell(new Phrase(person.getNickname(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                    pdfptable.addCell(new Phrase(person.getMiddleName(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("type." + person.getType()), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("status.case." + person.getStatus()), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                    pdfptable.addCell(image);
                }
            }
            document.add(pdfptable);
        }
        document.close();

        // Set the response to return the poster (PDF file)
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        response.setHeader("Content-disposition", "attachment; filename=Case_Statistics.pdf");

        // Close the output stream
        baos.writeTo(response.getOutputStream());
        response.getOutputStream().flush();

        return null;
    }
}