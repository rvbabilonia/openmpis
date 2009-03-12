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

import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.form.SearchForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.PersonDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.PersonService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.PersonServiceImpl;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Pagination;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The SearchAction class provides the method to pass queries and results.
 * 
 * @author  <a href="mailto:rvincent@asti.dost.gov.ph">Rey Vincent Babilonia</a>
 */
public class SearchAction extends Action {

    /**
     * The person service
     */
    private PersonService personService = new PersonServiceImpl(new PersonDAOImpl());
    /**
     * The pagination context
     */
    private Pagination pagination = new Pagination();

    /**
     * This is the action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws  java.lang.Exception
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        SearchForm searchForm = (SearchForm) form;
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

        List<Person> personList = null;

        // Retrieve list of persons
        personList = personService.simpleSearch(pagination, searchForm.getKeyword());

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

        return mapping.findForward(Constants.SEARCH_SUCCESS);
    }
}