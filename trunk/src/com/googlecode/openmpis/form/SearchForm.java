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
package com.googlecode.openmpis.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.googlecode.openmpis.util.Validator;

/**
 * The SearchForm class provides methods to validate the search form input.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class SearchForm extends ActionForm {

    /**
     * The keyword to be searched.
     */
    private String keyword;

    /**
     * Gets the keyword to be searched.
     * 
     * @return          the keyword to be searched
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Sets the keyword to be searched.
     * 
     * @param   keyword  the keyword to be searched
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Validates the keyword from the search form.
     * 
     * @param   mapping the action mapping
     * @param   request the HTTP request
     * @return          the action errors
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        request.setAttribute("keyword", keyword);

        Validator v = new Validator();

        if ((keyword == null) || (keyword.trim().length() < 1)) {
            errors.add("keyword", new ActionMessage(""));
        } else {
            if (!v.isValidKeyword(keyword)) {
                errors.add("keyword", new ActionMessage("error.keyword.required"));
            }
        }

        return errors;
    }
}