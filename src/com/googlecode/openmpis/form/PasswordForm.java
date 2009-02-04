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
 * The PasswordForm class provides methods to validate the paswword retrieval form inputs.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class PasswordForm extends ActionForm {

    /**
     * The username
     */
    private String username;
    /**
     * The password
     */
    private String password;
    /**
     * The email address
     */
    private String email;
    /**
     * The question
     */
    private int question;
    /**
     * The answer
     */
    private String answer;

    /**
     * Gets the username.
     * 
     * @return           the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param    username  the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the question.
     * 
     * @return          the question
     */
    public int getQuestion() {
        return question;
    }

    /**
     * Sets the question.
     * 
     * @param   question   the question
     */
    public void setQuestion(int question) {
        this.question = question;
    }

    /**
     * Gets the answer.
     * 
     * @return          the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the answer.
     * 
     * @param   answer the answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Gets the email address.
     * 
     * @return          the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * 
     * @param   email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password.
     * 
     * @return          the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param   password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sole constructor.
     */
    public PasswordForm() {
        super();
    }
}