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

import com.googlecode.openmpis.form.PasswordForm;
import com.googlecode.openmpis.model.User;
import com.googlecode.openmpis.model.SqlMapConfig;
import com.googlecode.openmpis.model.Log;
import com.googlecode.openmpis.model.Mail;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.Date;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * The SearchAction class provides the method to pass queries and results.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class PasswordAction extends Action {
    /**
     * The forwarding instances
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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        PasswordForm passwordForm = (PasswordForm) form;
        User user = null;
        SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
        
        try {
            sqlMap.startTransaction();
            user = (User) sqlMap.queryForObject("getUser", passwordForm.getUsername());
            sqlMap.commitTransaction();
            sqlMap.endTransaction();
            
            if (user == null) {
                request.setAttribute("error", "1");
                return mapping.findForward(FAILURE);
            } else {
                if ((passwordForm.getQuestion() == user.getQuestion())
                        && (passwordForm.getAnswer().equals(user.getAnswer()))) {
                    request.setAttribute("email", user.getEmail());

                    // Reset password
                    String password = "op3nmp!s";
                    user.setPassword(password);

                    // Log password modification event
                    Log log = new Log();
                    log.setLog("User " + user.getUsername() + " reset his password.");
                    log.setDate(new Date(System.currentTimeMillis()));
                    
                    sqlMap.startTransaction();
                    sqlMap.update("updatePassword", user);
                    sqlMap.insert("insertLog", log);
                    sqlMap.commitTransaction();
                    sqlMap.endTransaction();

                    Mail mail = new Mail();
                    mail.send("Administrator", "", passwordForm.getAdminEmail(), user.getEmail(),
                            "Password Retrieval",
                            "Dear " + user.getFirstName() + "," +
                            "\n\nYour new password is " + password + ". You received this email because" +
                            "you have forgotten your password or someone pretending to be you" +
                            "is trying to log into the system." +
                            "\n\nYours truly," +
                            "\nAdministrator",
                            request.getRemoteAddr());

                    return mapping.findForward(SUCCESS);
                } else {
                    request.setAttribute("error", "1");
                    return mapping.findForward(FAILURE);
                }
            }
        } catch (SQLException sqle)  {
            sqlMap.endTransaction();
            throw new SQLException(sqle.getCause());
        } catch (AddressException ae) {
            return mapping.findForward(FAILURE);
        } catch (MessagingException me) {
            return mapping.findForward(FAILURE);
        } catch (Exception e){
            return mapping.findForward(FAILURE);
        }
    }
}