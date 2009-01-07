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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import com.googlecode.openmpis.form.SearchForm;

import java.util.List;

/**
 * The SearchAction class provides the method to pass queries and results.
 * 
 * @author  <a href="mailto:rvincent@asti.dost.gov.ph">Rey Vincent Babilonia</a>
 */
public class SearchAction extends Action {
    /**
     * The forwarding instance
     */
    private final static String SUCCESS = "success";
    
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
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response) {
        SearchForm sf = (SearchForm) form;
        
        int pageNumber = request.getParameter("pn") != null ? Integer.parseInt(request.getParameter("pn")) : 1;
        
        //List<String[]> sequenceList = e.getSequences(sf.getKeyword(), pageNumber);
        
        //int pageCount;
        
        //request.setAttribute("sequencelist", sequenceList);
        request.setAttribute("currentpage", pageNumber);
        //request.setAttribute("pagecount", pageCount);
        request.setAttribute("keyword", sf.getKeyword());
        
        return mapping.findForward(SUCCESS);
    }
}