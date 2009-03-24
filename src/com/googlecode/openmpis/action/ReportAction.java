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

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

import com.googlecode.openmpis.dto.Abductor;
import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.dto.Message;
import com.googlecode.openmpis.dto.Report;
import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.dto.Relative;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.form.ReportForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.AbductorDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.MessageDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.ReportDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.PersonDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.RelativeDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.AbductorService;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.MessageService;
import com.googlecode.openmpis.persistence.ibatis.service.ReportService;
import com.googlecode.openmpis.persistence.ibatis.service.PersonService;
import com.googlecode.openmpis.persistence.ibatis.service.RelativeService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.AbductorServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.MessageServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.ReportServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.PersonServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.RelativeServiceImpl;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Pagination;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

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
     * The relative service
     */
    private RelativeService relativeService = new RelativeServiceImpl(new RelativeDAOImpl());
    /**
     * The abductor service
     */
    private AbductorService abductorService = new AbductorServiceImpl(new AbductorDAOImpl());
    /**
     * The log service
     */
    private LogService logService = new LogServiceImpl(new LogDAOImpl());
    /**
     * The message service
     */
    private MessageService messageService = new MessageServiceImpl(new MessageDAOImpl());
    /**
     * The file logger
     */
    private Logger logger = Logger.getLogger(this.getClass());
    /**
     * The pagination context
     */
    private Pagination pagination = new Pagination();
    /**
     * The date format
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
        if ((currentUser.getGroupId() == 1) || (currentUser.getGroupId() == 2)) {
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

            // Retrieve list of reports by a given investigator
            List<Report> reportList = reportService.getAllReportsByInvestigatorId(pagination, currentUser.getId());

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
     * Lists all reports for a given person.
     * This is the list action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward listReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if ((currentUser.getGroupId() == 1) || (currentUser.getGroupId() == 2)) {
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

            try {
                int personId = Integer.parseInt(request.getParameter("personid"));

                // Retrieve list of reports for a given person
                List<Report> reportList = reportService.getAllReportsForPerson(pagination, personId);

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
            } catch (NumberFormatException nfe) {
                return mapping.findForward(Constants.LIST_PERSON);
            } catch (NullPointerException npe) {
                return mapping.findForward(Constants.LIST_PERSON);
            }
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
            try {
                int personId = Integer.parseInt(request.getParameter("personid"));
                Person person = personService.getPersonById(personId);

                ReportForm reportForm = (ReportForm) form;
                reportForm.setPersonId(person.getId());
                reportForm.setFirstName(person.getFirstName());
                reportForm.setNickname(person.getNickname());
                reportForm.setLastName(person.getLastName());
                request.setAttribute("action", request.getParameter("action"));

                return mapping.findForward(Constants.ADD_REPORT);
            } catch (NumberFormatException nfe) {
                return mapping.findForward(Constants.LIST_PERSON);
            } catch (NullPointerException npe) {
                return mapping.findForward(Constants.LIST_PERSON);
            }
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
            reportForm.setPersonId(reportForm.getPersonId());
            Person person = personService.getPersonById(reportForm.getPersonId());
            reportForm.setFirstName(person.getFirstName());
            reportForm.setNickname(person.getNickname());
            reportForm.setLastName(person.getLastName());
            request.setAttribute("action", request.getParameter("action"));

            // Check if form is valid
            if (isValidReport(request, form)) {
                // Create report
                Report report = new Report();
                report.setPersonId(reportForm.getPersonId());
                report.setFirstName(person.getFirstName());
                report.setNickname(person.getNickname());
                report.setLastName(person.getLastName());
                report.setReport(reportForm.getReport());
                String date = simpleDateFormat.format(System.currentTimeMillis());
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
                    request.setAttribute("report", report);

                    return mapping.findForward(Constants.ADD_REPORT_SUCCESS);
                } else {
                    return mapping.findForward(Constants.FAILURE);
                }
            } else {
                reportForm.setPersonId(reportForm.getPersonId());

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

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if ((currentUser.getGroupId() == 1) || (currentUser.getGroupId() == 2)) {
            // Retrieve report ID
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Report report = reportService.getReportById(id);

                // Return report
                ReportForm reportForm = (ReportForm) form;
                reportForm.setId(report.getId());
                reportForm.setPersonId(report.getPersonId());
                reportForm.setReport(report.getReport());
                reportForm.setDate(report.getDate());

                // Return person
                Person person = personService.getPersonById(report.getPersonId());
                reportForm.setFirstName(person.getFirstName());
                reportForm.setNickname(person.getNickname());
                reportForm.setLastName(person.getLastName());

                request.setAttribute("action", request.getParameter("action"));

                return mapping.findForward(Constants.EDIT_REPORT);
            } catch (NumberFormatException nfe) {
                return mapping.findForward(Constants.LIST_PERSON);
            } catch (NullPointerException npe) {
                return mapping.findForward(Constants.LIST_PERSON);
            }
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
    public ActionForward editReport(ActionMapping mapping, ActionForm form,
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
            request.setAttribute("action", request.getParameter("action"));

            // Check if form is valid
            if (isValidReport(request, form)) {
                // Create report
                Report report = new Report();
                report.setId(reportForm.getId());
                report.setPersonId(reportForm.getPersonId());
                report.setReport(reportForm.getReport());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(System.currentTimeMillis());
                report.setDate(date);
                report.setInvestigatorId(currentUser.getId());
                boolean isUpdated = reportService.updateReport(report);

                // Log report receipt
                Log reportLog = new Log();
                reportLog.setLog(currentUser.getUsername() + " updated a report for person ID " + report.getPersonId() + ".");
                reportLog.setDate(date);

                // Insert log
                logService.insertLog(reportLog);
                logger.info(reportLog.toString());

                // Return report
                request.setAttribute("report", report);

                if (isUpdated) {
                    return mapping.findForward(Constants.ADD_REPORT_SUCCESS);
                } else {
                    reportForm.setPersonId(reportForm.getPersonId());

                    return mapping.findForward(Constants.ADD_REPORT_REDO);
                }
            } else {
                reportForm.setPersonId(reportForm.getPersonId());

                logger.info("Invalid report by " + currentUser.getUsername() + ".");

                return mapping.findForward(Constants.ADD_REPORT_REDO);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Prints the reports.
     * This is the print report action called from the Struts framework.
     *
     * @param mapping       the ActionMapping used to select this instance
     * @param form          the optional ActionForm bean for this request
     * @param request       the HTTP Request we are processing
     * @param response      the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws java.lang.Exception
     */
    public ActionForward printReport(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if ((currentUser.getGroupId() == 1) || (currentUser.getGroupId() == 2)) {
            // Retrieve person ID
            try {
                int personId = Integer.parseInt(request.getParameter("personid"));
                Person person = personService.getPersonById(personId);

                // Process the photo
                String tokens[] = person.getPhoto().split("\\/");
                String defaultPhotoBasename = "";
                for (int i = 0; i < tokens.length - 1; i++) {
                    defaultPhotoBasename += tokens[i] + File.separator;
                }
                defaultPhotoBasename += tokens[tokens.length - 1];
                String absoluteDefaultPhotoFilename = getServlet().getServletContext().getRealPath("/") + defaultPhotoBasename;

                // Set the paper size and margins
                Document document = new Document(PageSize.LETTER, 50, 50, 50, 50);

                // Create the PDF writer
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, baos);

                // Add some meta information to the document
                document.addTitle("Progress Report");
                document.addAuthor("OpenMPIS");
                document.addSubject("Progress Report");
                document.addKeywords("OpenMPIS, missing, found, unidentified");
                document.addProducer();
                document.addCreationDate();
                document.addCreator("OpenMPIS version " + Constants.VERSION);

                // Set the header
                String date = simpleDateFormat.format(System.currentTimeMillis());
                document.setHeader(new HeaderFooter(new Phrase("Report for " +
                        person.getFirstName() + " \"" + person.getNickname() +
                        "\" " + person.getLastName() + " as of " + date), false));

                // Set the footer
                HeaderFooter footer = new HeaderFooter(new Phrase("Page : "), true);
                footer.setAlignment(Element.ALIGN_CENTER);
                document.setFooter(footer);

                // Open the document for writing
                document.open();
                // Print the information on person
                // Add the banner
                if (person.getType() > 4) {
                    Paragraph foundParagraph = new Paragraph("F O U N D", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 36, Font.BOLD, new Color(255, 0, 0)));
                    foundParagraph.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(foundParagraph);
                } else {
                    Paragraph missingParagraph = new Paragraph("M I S S I N G", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 36, Font.BOLD, new Color(255, 0, 0)));
                    missingParagraph.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(missingParagraph);
                }
                // Add date missing or found
                Paragraph blackParagraph = new Paragraph(getResources(request).getMessage("month." + person.getMonthMissingOrFound()) + " " + person.getDayMissingOrFound() + ", " + person.getYearMissingOrFound(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));
                blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(blackParagraph);
                // Add missing from location
                if (person.getType() < 5) {
                    blackParagraph = new Paragraph(person.getMissingFromCity() + ", " + person.getMissingFromProvince(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0)));
                    blackParagraph.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(blackParagraph);
                }
                // Add name
                Paragraph redParagraph;
                if (!person.getNickname().isEmpty()) {
                    redParagraph = new Paragraph(person.getFirstName() + " \"" + person.getNickname() + "\" " + person.getLastName(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(255, 0, 0)));
                } else {
                    redParagraph = new Paragraph(person.getFirstName() + " " + person.getLastName(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(255, 0, 0)));
                }
                redParagraph.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(redParagraph);
                // Add the photo
                Image image = Image.getInstance(absoluteDefaultPhotoFilename);
                image.scaleAbsolute(200, 300);
                image.setAlignment(Image.ALIGN_CENTER);
                document.add(image);
                // Add description
                redParagraph = new Paragraph("Description", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(255, 0, 0)));
                redParagraph.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(redParagraph);
                float[] widths = {0.5f, 0.5f};
                PdfPTable pdfptable = new PdfPTable(widths);
                pdfptable.setWidthPercentage(100);
                if (person.getType() < 5) {
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.date.birth") + ": " + getResources(request).getMessage("month." + person.getBirthMonth()) + " " + person.getBirthDay() + ", " + person.getBirthYear(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.address.city") + ": " + person.getCity(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                }
                pdfptable.addCell(new Phrase(getResources(request).getMessage("label.sex") + ": " + getResources(request).getMessage("sex." + person.getSex()), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                pdfptable.addCell(new Phrase(getResources(request).getMessage("label.color.hair") + ": " + getResources(request).getMessage("color.hair." + person.getHairColor()), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                pdfptable.addCell(new Phrase(getResources(request).getMessage("label.height") + ": " + person.getFeet() + "' " + person.getInches() + "\"", FontFactory.getFont(FontFactory.HELVETICA, 12)));
                pdfptable.addCell(new Phrase(getResources(request).getMessage("label.color.eye") + ": " + getResources(request).getMessage("color.eye." + person.getEyeColor()), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                pdfptable.addCell(new Phrase(getResources(request).getMessage("label.weight") + ": " + person.getWeight() + " " + getResources(request).getMessage("label.weight.lbs"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                pdfptable.addCell(new Phrase(getResources(request).getMessage("label.race") + ": " + getResources(request).getMessage("race." + person.getRace()), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                document.add(pdfptable);

                // Print information on relative
                Relative relative = relativeService.getRelativeById(person.getRelativeId());

                document.newPage();
                document.add(new Paragraph(getResources(request).getMessage("label.relative.name") + ": " + relative.getFirstName() + " " + relative.getLastName()));
                document.add(new Paragraph(getResources(request).getMessage("label.relation") + ": " + getResources(request).getMessage("relation." + person.getRelationToRelative())));
                document.add(new Paragraph(getResources(request).getMessage("label.address") + ": " + relative.getStreet() + ", " + relative.getCity() + ", " + relative.getProvince()));
                document.add(new Paragraph(getResources(request).getMessage("label.number") + ": " + relative.getNumber()));
                document.add(new Paragraph(getResources(request).getMessage("label.email") + ": " + relative.getEmail()));

                // Print information on abductor
                if (person.getAbductorId() != null) {
                    Abductor abductor = abductorService.getAbductorById(person.getAbductorId());

                    document.newPage();
                    document.add(new Paragraph(getResources(request).getMessage("label.abductor.name") + ": " + abductor.getFirstName() + " " + abductor.getLastName()));
                }

                // Print sightings
                if ((currentUser.getGroupId() == 1) || (currentUser.getGroupId() == 2)) {
                    document.setHeader(new HeaderFooter(new Phrase("List of sightings as of " + date), false));
                    document.newPage();
                    float sightingsWidths[] = {0.1f, 0.1f, 0.1f, 0.3f, 0.1f, 0.1f, 0.1f, 0.1f};
                    pdfptable = new PdfPTable(sightingsWidths);
                    pdfptable.setWidthPercentage(100);
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.id"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.date.sent"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.subject"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.message"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.lastname"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.firstname"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.email"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.ipaddress"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    List<Message> sightingList = messageService.listAllSightingsForPerson(personId);
                    for (Message sighting : sightingList) {
                        pdfptable.addCell(new Phrase("" + sighting.getId(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                        pdfptable.addCell(new Phrase(sighting.getDate(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                        pdfptable.addCell(new Phrase(sighting.getSubject(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                        pdfptable.addCell(new Phrase(sighting.getMessage(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                        pdfptable.addCell(new Phrase(sighting.getLastName(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                        pdfptable.addCell(new Phrase(sighting.getFirstName(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                        pdfptable.addCell(new Phrase(sighting.getEmail(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                        pdfptable.addCell(new Phrase(sighting.getIpAddress(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                    }
                    document.add(pdfptable);
                }

                // Print progress reports
                if ((currentUser.getGroupId() == 1) || (currentUser.getGroupId() == 2)) {
                    document.setHeader(new HeaderFooter(new Phrase("List of progress reports as of " + date), false));
                    document.newPage();
                    float reportsWidths[] = {0.1f, 0.1f, 0.3f};
                    pdfptable = new PdfPTable(reportsWidths);
                    pdfptable.setWidthPercentage(100);
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.id"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.date.reported"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    pdfptable.addCell(new Phrase(getResources(request).getMessage("label.report"), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                    List<Report> reportList = reportService.listAllReportsForPerson(personId);
                    for (Report report : reportList) {
                        pdfptable.addCell(new Phrase("" + report.getId(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                        pdfptable.addCell(new Phrase(report.getDate(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                        pdfptable.addCell(new Phrase(report.getReport(), FontFactory.getFont(FontFactory.HELVETICA, 8)));
                    }
                    document.add(pdfptable);
                }
                document.close();

                // Set the response to return the poster (PDF file)
                response.setContentType("application/pdf");
                response.setContentLength(baos.size());
                response.setHeader("Content-disposition", "attachment; filename=Report.pdf");

                // Close the output stream
                baos.writeTo(response.getOutputStream());
                response.getOutputStream().flush();

                return null;
            } catch (NumberFormatException nfe) {
                return mapping.findForward(Constants.LIST_PERSON);
            } catch (NullPointerException npe) {
                return mapping.findForward(Constants.LIST_PERSON);
            }
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