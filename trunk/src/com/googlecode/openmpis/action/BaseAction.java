/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.openmpis.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author rey
 */
public abstract class BaseAction extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control", "no-cache");
        //Directs caches not to store the page under any circumstance
        response.setHeader("Cache-Control", "no-store");
        //Causes the proxy cache to see the page as "stale"
        response.setDateHeader("Expires", 0);
        //HTTP 1.0 backward compatibility 
        response.setHeader("Pragma", "no-cache");

        if (!this.userIsLoggedIn(request)) {
            ActionMessages errors = new ActionMessages();
            errors.add("error", new ActionMessage("login.sessionended"));
            this.saveErrors(request, errors);
            return mapping.findForward("sessionended");
        }
        return executeAction(mapping, form, request, response);
    }

    protected abstract ActionForward executeAction(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;

    private boolean userIsLoggedIn(HttpServletRequest request) {
        if (request.getSession().getAttribute("User") == null)
            return false;
        return true;
    }
}