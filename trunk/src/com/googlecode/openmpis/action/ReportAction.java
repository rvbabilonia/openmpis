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

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.dto.Report;
import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.form.ReportForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.ReportDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.PersonDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.UserDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.ReportService;
import com.googlecode.openmpis.persistence.ibatis.service.PersonService;
import com.googlecode.openmpis.persistence.ibatis.service.UserService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.ReportServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.PersonServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.UserServiceImpl;
import com.googlecode.openmpis.util.Configuration;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Mail;
import com.googlecode.openmpis.util.Pagination;
import com.googlecode.openmpis.util.Validator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

/**
 * The ReportAction class provides the method to list, add, edit, view and delete
 * progress reports.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class ReportAction extends DispatchAction {

    /**
     * The report service
     */
    private ReportService reportService = new ReportServiceImpl(new ReportDAOImpl());
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
     * Lists all reports for a given investigator.
     * This is the list action called from the Struts framework.
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

            // Retrieve list of reports for a given person
            List<Report> reportList = reportService.getAllReportsByInvestigator(pagination, currentUser.getId());

            // Return list of reports
            request.setAttribute("reportlist", reportList);

            // Return number of reports
            request.setAttribute("reportcount", reportList.size());

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

            return mapping.findForward(Constants.LIST_REPORT);
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
    public ActionForward newReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is an investigator
        if (currentUser.getGroupId() == 2) {
            int personId = 0;
            try {
                if (request.getParameter("personid") != null) {
                    personId = Integer.parseInt(request.getParameter("personid"));
                } else {
                    //return mapping.findForward(Constants.);
                }
            } catch (NumberFormatException nfe) {
                //return mapping.findForward(Constants.LIST_ABDUCTOR);
            }
            Person person = (Person) personService.getPersonById(personId);

            ReportForm reportForm = (ReportForm) form;
            reportForm.setPersonId(person.getId());
            request.setAttribute("personid", person.getId());
            request.setAttribute("firstname", person.getFirstName());
            request.setAttribute("nickname", person.getNickname());
            request.setAttribute("lastname", person.getLastName());
            request.setAttribute("action", request.getParameter("action"));

            return mapping.findForward(Constants.ADD_REPORT);
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
    public ActionForward addReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is an investigator
        if (currentUser.getGroupId() == 2) {
            ReportForm reportForm = (ReportForm) form;
            System.out.println("hidden person id:" + reportForm.getPersonId());
            reportForm.setPersonId(reportForm.getPersonId());
            request.setAttribute("action", request.getParameter("action"));
            Person person = (Person) personService.getPersonById(reportForm.getPersonId());
            request.setAttribute("firstname", person.getFirstName());
            request.setAttribute("nickname", person.getNickname());
            request.setAttribute("lastname", person.getLastName());

            // Check if form is valid
            if (isValidReport(request, form)) {
                // Create report
                Report report = new Report();
                report.setPersonId(reportForm.getPersonId());
                report.setReport(reportForm.getReport());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(System.currentTimeMillis());
                report.setDate(date);
                report.setInvestigatorId(currentUser.getId());
                boolean isInserted = reportService.insertReport(report);

                // Log report receipt
                Log reportLog = new Log();
                reportLog.setLog(currentUser.getUsername() + " submitted a report for person ID " + person.getId() + ".");
                reportLog.setDate(date);

                // Insert log
                logService.insertLog(reportLog);
                logger.info(reportLog.toString());

                if (isInserted) {
                    request.setAttribute("action", request.getParameter("action"));

                    return mapping.findForward(Constants.ADD_REPORT_SUCCESS);
                } else {
                    request.setAttribute("personid", request.getParameter("personid"));
                    request.setAttribute("action", request.getParameter("action"));

                    return mapping.findForward(Constants.ADD_REPORT_REDO);
                }
            } else {
                request.setAttribute("personid", request.getParameter("personid"));
                System.out.println("invalid person id: " + request.getParameter("personid"));

                logger.info("Invalid report by " + currentUser.getUsername() + ".");

                return mapping.findForward(Constants.ADD_REPORT_REDO);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Views the report.
     * This is the view report action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward viewReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;
        ReportForm reportForm = (ReportForm) form;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if (currentUser.getGroupId() == 2) {
            // Retrieve report ID
            int id = 0;
            try {
                if (request.getParameter("id") != null) {
                    id = Integer.parseInt(request.getParameter("id"));
                }
            } catch (NumberFormatException nfe) {
                //id = currentUser.getId();
            }
            Report report = (Report) reportService.getReportById(id);

            // Return report
            reportForm.setId(report.getId());
            reportForm.setPersonId(report.getPersonId());
            reportForm.setReport(report.getReport());
            reportForm.setDate(report.getDate());

            Person person = (Person) personService.getPersonById(report.getPersonId());
            request.setAttribute("firstname", person.getFirstName());
            request.setAttribute("nickname", person.getNickname());
            request.setAttribute("lastname", person.getLastName());
            request.setAttribute("action", request.getParameter("action"));

            return mapping.findForward(Constants.EDIT_REPORT);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Validates the inputs from the report form.
     * 
     * @param request       the HTTP Request we are processing
     * @param form          the ActionForm bean for this request
     * @return              <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    private boolean isValidReport(HttpServletRequest request, ActionForm form) {
        ActionMessages errors = new ActionMessages();
        boolean isValid = true;

        ReportForm reportForm = (ReportForm) form;
        String report = reportForm.getReport();

        if (report.length() < 1) {
            errors.add("report", new ActionMessage("error.report.required"));
        } else {
            if (report.length() < 10) {
                errors.add("report", new ActionMessage("error.report.invalid"));
            }
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            isValid = false;
        }

        return isValid;
    }
}