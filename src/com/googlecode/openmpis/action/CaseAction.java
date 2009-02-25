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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.AbductorDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.PersonDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.RelativeDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.AbductorService;
import com.googlecode.openmpis.persistence.ibatis.service.PersonService;
import com.googlecode.openmpis.persistence.ibatis.service.RelativeService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.AbductorServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.PersonServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.RelativeServiceImpl;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Pagination;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import java.text.SimpleDateFormat;

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
        if (currentUser.getGroupId() == 1) {
            personList = personService.getPersonsByEncoderId(pagination, currentUser.getId());
        } else if (currentUser.getGroupId() == 2) {
            personList = personService.getPersonsByInvestigatorId(pagination, currentUser.getId());
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
     * Prints the case statistics in PDF file.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward printCaseStatistics(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(System.currentTimeMillis());
        document.setHeader(new HeaderFooter(new Phrase("Statistics for cases as of " + date), false));

        // Set the footer
        HeaderFooter footer = new HeaderFooter(new Phrase("Page : "), true);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.setFooter(footer);

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
        response.setHeader("Content-disposition", "attachment; filename=Case_Statistics.pdf");

        // Close the output stream
        baos.writeTo(response.getOutputStream());
        response.getOutputStream().flush();

        return mapping.findForward(Constants.VIEW_PERSON_POSTER);
    }
}