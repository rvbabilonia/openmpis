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

import com.googlecode.openmpis.form.FeedbackForm;
import com.googlecode.openmpis.model.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * The FeedbackAction class provides the method to email a feedback to the administrator.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class FeedbackAction extends Action {
    /**
     * The forwarding instance
     */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    
    /**
     * This is the action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response) {
        FeedbackForm feedbackForm = (FeedbackForm) form;
        
        Mail mail = new Mail();
        try {
            mail.send(feedbackForm.getFirstname(), feedbackForm.getLastname(), feedbackForm.getEmail(),
                    feedbackForm.getAdminEmail(), feedbackForm.getSubject(), feedbackForm.getMessage(),
                    request.getRemoteAddr());

            return mapping.findForward(SUCCESS);
        } catch (AddressException ae) {
            return mapping.findForward(FAILURE);
        } catch (MessagingException me) {
            return mapping.findForward(FAILURE);
        } catch (Exception e){
            return mapping.findForward(FAILURE);
        }
    }
}