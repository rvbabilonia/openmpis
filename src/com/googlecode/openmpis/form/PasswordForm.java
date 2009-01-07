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

import com.googlecode.openmpis.model.Validator;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The FeedbackForm class provides methods to validate the feedback form inputs.
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
     * Gets the administrator's email address.
     * 
     * @return          the administrator's email address
     */
    public String getAdminEmail() {
        Properties properties = new Properties();

        // Retrieve administrator's email address
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("mail.properties");
            properties.load(is);
            is.close();
        } catch (FileNotFoundException fnfe){
            //throw new FileNotFoundException("Cannot find mail.properties file.");
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        return (String) properties.get("mail.administrator");
    }

    /**
     * Sole constructor.
     */
    public PasswordForm() {
       super();
    }

    /**
     * Validates the inputs from the feedback form.
     * 
     * @param   mapping the action mapping
     * @param   request the HTTP request
     * @return          the action errors
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        request.setAttribute("username", username);
        request.setAttribute("question", question);
        request.setAttribute("answer", answer);
       
        Validator v = new Validator();

        if (username == null)
            errors.add("username", new ActionMessage(""));
        else {
            if (username.length() < 1)
                errors.add("username", new ActionMessage("error.username.required"));
            else {
                if ((!v.isValidUsername(username)))
                    errors.add("username", new ActionMessage("error.username.invalid"));
            }
        }

        if (answer == null)
            errors.add("answer", new ActionMessage(""));
        else {
            if (answer.length() < 1)
                errors.add("answer", new ActionMessage("error.answer.required"));
            else {
                if (!v.isValidKeyword(answer))
                    errors.add("answer", new ActionMessage("error.answer.invalid"));
            }
        }

        return errors;
    }
}