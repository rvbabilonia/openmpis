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

import com.googlecode.openmpis.dto.Log;
import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.form.UserForm;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.LogDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.UserDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.LogService;
import com.googlecode.openmpis.persistence.ibatis.service.UserService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.LogServiceImpl;
import com.googlecode.openmpis.persistence.ibatis.service.impl.UserServiceImpl;
import com.googlecode.openmpis.util.Configuration;
import com.googlecode.openmpis.util.Constants;
import com.googlecode.openmpis.util.Mail;

import com.googlecode.openmpis.util.Validator;
import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

/**
 * The UserAction class provides the methods to add, edit, delete and view administrators, encoders and investigators.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class UserAction extends DispatchAction {

    /**
     * The user service
     */
    private static UserService userService = new UserServiceImpl(new UserDAOImpl());
    /**
     * The log service
     */
    private static LogService logService = new LogServiceImpl(new LogDAOImpl());
    /**
     * The file logger
     */
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * This is the list action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     */
    public ActionForward all(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
            // Retrieve list of users
            List<User> userList = userService.getAllUsers();

            // Return list of users
            request.setAttribute("userlist", userList);

            return mapping.findForward(Constants.LIST_SUCCESS);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * This is the new user action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     */
    public ActionForward newUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
            return mapping.findForward(Constants.ADD_USER);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * This is the add action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
            UserForm userForm = (UserForm) form;
            ActionMessages errors = new ActionMessages();

            // Check if updated user has unique username and email, if passwords match
            // and if the rest of the form is valid
            User checker = new User();
            checker.setId(userForm.getId());
            checker.setEmail(userForm.getEmail());
            checker.setUsername(userForm.getUsername());
            
            // Check if form is valid
            if (isValidNewUser(request, form)) {
                // Check if username is unique
                if (userService.isUniqueUsername(checker)) {
                    // Check if email address is unique
                    if (userService.isUniqueEmail(checker)) {

                        // Insert user
                        User user = new User();
                        user.setGroupId(userForm.getGroupId());
                        user.setUsername(userForm.getUsername());
                        String password = "p@$$w0rd";
                        user.setPassword(password);
                        user.setFirstName(userForm.getFirstName());
                        user.setLastName(userForm.getLastName());
                        user.setMiddleName(userForm.getMiddleName());
                        user.setEmail(userForm.getEmail());
                        user.setDesignation(userForm.getDesignation());
                        user.setAgency(userForm.getAgency());
                        user.setNumber(userForm.getNumber());
                        Date date = new Date(System.currentTimeMillis());
                        user.setDate(date);
                        user.setCreatorId(currentUser.getId());
                        user.setQuestion(0);
                        user.setStatus(userForm.getStatus());
                        userService.insertUser(user);

                        // Log user creation event
                        Log addLog = new Log();
                        addLog.setLog("User " + user.getUsername() + " was created by " + currentUser.getUsername() + ".");
                        addLog.setDate(date);
                        logService.insertLog(addLog);
                        logger.info(addLog);

                        // Retrieve mail properties
                        Configuration config = new Configuration("mail.properties");
                        // Check if email sending is enabled
                        if (Boolean.parseBoolean(config.getProperty("mail.enable"))) {
                            Mail mail = new Mail();

                            // Send email
                            mail.send(currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), user.getEmail(),
                                    "Account Creation",
                                    "Dear " + user.getFirstName() + "," +
                                    "\n\nYou received this email because your account has been created. " +
                                    "Your password is " + password + ". Please log in to the system immediately and modify your profile. " +
                                    "\n\nIf you have any questions, please feel free to email me." +
                                    "\n\nYours truly," +
                                    "\n" + currentUser.getFirstName());
                        }

                        // Return username and operation type
                        request.setAttribute("username", userForm.getUsername());
                        request.setAttribute("operation", "add");

                        return mapping.findForward(Constants.ADD_SUCCESS);
                    } else {
                        // Return duplicate email error
                        errors.add("email", new ActionMessage("error.email.duplicate"));
                        saveErrors(request, errors);
                        
                        logger.error("Duplicate email.");
                        
                        return mapping.findForward(Constants.ADD_REDO);
                    }
                } else {
                    // Return duplicate username error
                    errors.add("username", new ActionMessage("error.username.duplicate"));
                    saveErrors(request, errors);
                        
                    logger.error("Duplicate username.");
                
                    return mapping.findForward(Constants.ADD_REDO);
                }
            } else {
                // Return form validation errors
                logger.error("Form errors.");
                    
                return mapping.findForward(Constants.ADD_REDO);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

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
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;
        UserForm userForm = (UserForm) form;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
            // Retrieve user
            User user = (User) userService.getUserById(new Integer(request.getParameter("id")));

            // Return user
            userForm.setId(user.getId());
            userForm.setGroupId(user.getGroupId());
            userForm.setUsername(user.getUsername());
            userForm.setFirstName(user.getFirstName());
            userForm.setMiddleName(user.getMiddleName());
            userForm.setLastName(user.getLastName());
            userForm.setDesignation(user.getDesignation());
            userForm.setAgency(user.getAgency());
            userForm.setEmail(user.getEmail());
            userForm.setNumber(user.getNumber());
            userForm.setIpAddress(user.getIpAddress());
            userForm.setLastLogin(user.getLastLogin());
            userForm.setDate(user.getDate());
            userForm.setCreatorId(user.getCreatorId());
            userForm.setStatus(user.getStatus());

            // Edit what you created/encoded
            // Edit your profile
            // Administrator can edit all except administrators
            if ( (currentUser.getId() == user.getCreatorId()) ||
                    (currentUser.getId() == user.getId()) ||
                    ((currentUser.getGroupId() == 0) && (user.getGroupId() > 0)) ) {
                return mapping.findForward(Constants.EDIT_USER);
            } else {
                return mapping.findForward(Constants.UNAUTHORIZED);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * This is the edit action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws  java.lang.Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
            UserForm userForm = (UserForm) form;
            ActionMessages errors = new ActionMessages();

            // Check if updated user has unique username and email, if passwords match
            // and if the rest of the form is valid
            User checker = new User();
            checker.setId(userForm.getId());
            checker.setEmail(userForm.getEmail());
            checker.setUsername(userForm.getUsername());
            
            // Check if form is valid
            if (isValidExistingUser(request, form)) {
                // Check if username is unique
                if (userService.isUniqueUsername(checker)) {
                    // Check if email address is unique
                    if (userService.isUniqueEmail(checker)) {
                        // Retrieve user
                        User tmpUser = (User) userService.getUserById(new Integer(userForm.getId()));

                        // Update user
                        User user = new User();
                        user.setId(userForm.getId());
                        user.setGroupId(userForm.getGroupId());
                        user.setUsername(userForm.getUsername());
                        if ( (userForm.getPassword() != null) &&
                                (userForm.getRetype() != null) &&
                                (userForm.getPassword().equals(userForm.getRetype())) ) {
                            user.setPassword(userForm.getPassword());
                        } else {
                            user.setPassword(tmpUser.getPassword());
                        }
                        user.setFirstName(userForm.getFirstName());
                        user.setMiddleName(userForm.getMiddleName());
                        user.setLastName(userForm.getLastName());
                        user.setDesignation(userForm.getDesignation());
                        user.setAgency(userForm.getAgency());
                        user.setEmail(userForm.getEmail());
                        user.setNumber(userForm.getNumber());
                        user.setStatus(userForm.getStatus());
                        user.setQuestion(userForm.getQuestion());
                        user.setAnswer(userForm.getAnswer());
                        logger.info("Inserting...");
                        if (userService.updateUser(user))
                            logger.info("Inserted.");
                        //logger.info(user.toString());
                        else
                            logger.info("Failed insertion.");

                        // Log user modification event
                        Log editLog = new Log();
                        editLog.setLog("User " + user.getUsername() + " was updated by " + currentUser.getUsername() + ".");
                        editLog.setDate(new Date(System.currentTimeMillis()));
                        logService.insertLog(editLog);
                        logger.info(editLog);

                        // Return username and operation type
                        request.setAttribute("username", userForm.getUsername());
                        request.setAttribute("operation", "edit");

                        return mapping.findForward(Constants.EDIT_SUCCESS);
                    } else {
                        // Return duplicate email error
                        errors.add("email", new ActionMessage("error.email.duplicate"));
                        saveErrors(request, errors);
                        
                        logger.error("Duplicate email.");
                        
                        return mapping.findForward(Constants.EDIT_REDO);
                    }
                } else {
                    // Return duplicate username error
                    errors.add("username", new ActionMessage("error.username.duplicate"));
                    saveErrors(request, errors);
                    
                    logger.error("Duplicate username.");
                
                    return mapping.findForward(Constants.EDIT_REDO);
                }
            } else {
                // Return form validation errors
                return mapping.findForward(Constants.EDIT_REDO);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * This is the delete user action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     */
    public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
            UserForm userForm = (UserForm) form;
            // Retrieve user
            User user = (User) userService.getUserById(new Integer(userForm.getId()));
            
            userForm.setUsername(user.getUsername());
            // Generate 4-digit random code
            userForm.setCode((int) (Math.random() * 9999) + 1000);
            
            // Delete what you created/encoded
            // Administrator can delete all except administrators
            if ( (currentUser.getId() == user.getCreatorId()) ||
                    ((currentUser.getGroupId() == 0) && (user.getGroupId() > 0)) ) {
                return mapping.findForward(Constants.DELETE_USER);
            } else {
                return mapping.findForward(Constants.UNAUTHORIZED);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * This is the delete action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     * @throws  java.lang.Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        User currentUser = null;

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            currentUser = (User) request.getSession().getAttribute("currentuser");
        }

        // Check if current user is authorized
        if ((currentUser.getGroupId() == 0) || (currentUser.getGroupId() == 1)) {
            UserForm userForm = (UserForm) form;

            // TODO Confirm user deletion
            User user = (User) userService.getUserById(new Integer(userForm.getId()));

            // Check if codes match
            if (userForm.getCode() == userForm.getUserCode()) {
                // Administrator can delete a user except his creator/encoder
                // User can delete a user that he encoded
                if ( ((currentUser.getGroupId() == 0) && (currentUser.getCreatorId() != user.getId())) ||
                        ((currentUser.getGroupId() == 1) && (currentUser.getId() == user.getCreatorId())) ) {
                    // Delete user
                    userService.deleteUser(new Integer(user.getId()));

                    // Log user deletion event
                    Log deleteLog = new Log();
                    deleteLog.setLog("User " + user.getUsername() + " was deleted by " + currentUser.getUsername() + ".");
                    deleteLog.setDate(new Date(System.currentTimeMillis()));
                    logService.insertLog(deleteLog);
                    logger.info(deleteLog);

                    // Retrieve mail properties
                    Configuration config = new Configuration("mail.properties");
                    // Check if email sending is enabled
                    if (Boolean.parseBoolean(config.getProperty("mail.enable"))) {
                        Mail mail = new Mail();

                        // Send email
                        mail.send(currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), "email",
                                "Account Deletion",
                                "Dear " + "firstname" + "," +
                                "\n\nThis is to inform you that after a long deliberation, your account has to be deleted." +
                                "\n\nIf you have any questions, please feel free to email me." +
                                "\n\nYours truly," +
                                "\n" + currentUser.getFirstName());
                    }

                    // Return username and operation type
                    request.setAttribute("username", user.getUsername());
                    request.setAttribute("operation", "delete");

                    return mapping.findForward(Constants.DELETE_SUCCESS);
                } else {
                    return mapping.findForward(Constants.UNAUTHORIZED);
                }
            } else {
                // Generate 4-digit random code
                userForm.setCode((int) (Math.random() * 9999) + 1000);
                userForm.setUsername(user.getUsername());
                
                // Return duplicate username error
                ActionMessages errors = new ActionMessages();
                errors.add("usercode", new ActionMessage("error.code.mismatch"));
                saveErrors(request, errors);

                logger.error("Codes did not match.");
                
                return mapping.findForward(Constants.DELETE_REDO);
            }
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * This is the unspecified action called from the Struts framework.
     * 
     * @param   mapping     the ActionMapping used to select this instance
     * @param   form        the optional ActionForm bean for this request
     * @param   request     the HTTP Request we are processing
     * @param   response    the HTTP Response we are processing
     * @return              the forwarding instance
     */
    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        // Check if there exists a session
        if (request.getSession().getAttribute("currentuser") == null) {
            return mapping.findForward(Constants.EXPIRED);
        } else {
            return mapping.findForward(Constants.UNAUTHORIZED);
        }
    }

    /**
     * Validates the inputs from the user form.
     * 
     * @param request   the HTTP Request we are processing
     * @param form      the ActionForm bean for this request
     * @return          <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    private boolean isValidNewUser(HttpServletRequest request, ActionForm form) {
        ActionMessages errors = new ActionMessages();
        Validator validator = new Validator();
        boolean isValid = true;

        UserForm userForm = (UserForm) form;
        String username = userForm.getUsername();
        String firstName = userForm.getFirstName();
        String middleName = userForm.getMiddleName();
        String lastName = userForm.getLastName();
        String agency = userForm.getAgency();
        String designation = userForm.getDesignation();
        String email = userForm.getEmail();
        String number = userForm.getNumber();

        if (username == null) {
            errors.add("username", new ActionMessage(""));
        } else {
            if (username.length() < 1) {
                errors.add("username", new ActionMessage("error.username.required"));
            } else {
                if (!validator.isValidUsername(username)) {
                    errors.add("username", new ActionMessage("error.username.invalid"));
                }
            }
        }

        if (firstName == null) {
            errors.add("firstname", new ActionMessage(""));
        } else {
            if (firstName.length() < 1) {
                errors.add("firstname", new ActionMessage("error.firstname.required"));
            } else {
                if (!validator.isValidFirstName(firstName)) {
                    errors.add("firstname", new ActionMessage("error.firstname.invalid"));
                }
            }
        }

        if (middleName == null) {
            errors.add("middlename", new ActionMessage(""));
        } else {
            if (middleName.length() < 1) {
                errors.add("middlename", new ActionMessage("error.middlename.required"));
            } else {
                if (!validator.isValidLastName(middleName)) {
                    errors.add("middlename", new ActionMessage("error.middlename.invalid"));
                }
            }
        }

        if (lastName == null) {
            errors.add("lastname", new ActionMessage(""));
        } else {
            if (lastName.length() < 1) {
                errors.add("lastname", new ActionMessage("error.lastname.required"));
            } else {
                if (!validator.isValidLastName(lastName)) {
                    errors.add("lastname", new ActionMessage("error.lastname.invalid"));
                }
            }
        }

        if (email == null) {
            errors.add("email", new ActionMessage(""));
        } else {
            if (email.length() < 1) {
                errors.add("email", new ActionMessage("error.email.required"));
            } else {
                if (!validator.isValidEmailAddress(email)) {
                    errors.add("email", new ActionMessage("error.email.invalid"));
                }
            }
        }

        if (designation == null) {
            errors.add("designation", new ActionMessage(""));
        } else {
            if (designation.length() < 1) {
                errors.add("designation", new ActionMessage("error.designation.required"));
            } else {
                if (!validator.isValidKeyword(designation)) {
                    errors.add("designation", new ActionMessage("error.designation.invalid"));
                }
            }
        }

        if (agency == null) {
            errors.add("agency", new ActionMessage(""));
        } else {
            if (agency.length() < 1) {
                errors.add("agency", new ActionMessage("error.agency.required"));
            } else {
                if (!validator.isValidKeyword(agency)) {
                    errors.add("agency", new ActionMessage("error.agency.invalid"));
                }
            }
        }

        if (number == null) {
            errors.add("number", new ActionMessage(""));
        } else {
            if (number.length() < 1) {
                errors.add("number", new ActionMessage("error.number.required"));
            } else {
                if (!validator.isValidNumber(number)) {
                    errors.add("number", new ActionMessage("error.number.invalid"));
                }
            }
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            isValid = false;
        }

        return isValid;
    }

    /**
     * Validates the inputs from the user form.
     * 
     * @param request   the HTTP Request we are processing
     * @param form      the ActionForm bean for this request
     * @return          <code>true</code> if there are no errors in the form; <code>false</code> otherwise
     */
    private boolean isValidExistingUser(HttpServletRequest request, ActionForm form) {
        ActionMessages errors = new ActionMessages();
        Validator validator = new Validator();
        boolean isValid = true;

        UserForm userForm = (UserForm) form;
        String username = userForm.getUsername();
        String firstName = userForm.getFirstName();
        String middleName = userForm.getMiddleName();
        String lastName = userForm.getLastName();
        String agency = userForm.getAgency();
        String designation = userForm.getDesignation();
        String email = userForm.getEmail();
        String number = userForm.getNumber();
        String password = userForm.getPassword();
        String retype = userForm.getRetype();
        String answer = userForm.getAnswer();

        if (username == null) {
            errors.add("username", new ActionMessage(""));
        } else {
            if (username.length() < 1) {
                errors.add("username", new ActionMessage("error.username.required"));
            } else {
                if (!validator.isValidUsername(username)) {
                    errors.add("username", new ActionMessage("error.username.invalid"));
                }
            }
        }

        if (password == null) {
            errors.add("password", new ActionMessage(""));
        } else {
            if (password.length() < 1) {
                errors.add("password", new ActionMessage("error.password.required"));
            } else {
                if (!validator.isValidPassword(password)) {
                    errors.add("password", new ActionMessage("error.password.invalid"));
                } else if (password.equals(retype)) {
                    errors.add("password", new ActionMessage("error.password.mismatch"));
                }
            }
        }

        if (firstName == null) {
            errors.add("firstname", new ActionMessage(""));
        } else {
            if (firstName.length() < 1) {
                errors.add("firstname", new ActionMessage("error.firstname.required"));
            } else {
                if (!validator.isValidFirstName(firstName)) {
                    errors.add("firstname", new ActionMessage("error.firstname.invalid"));
                }
            }
        }

        if (middleName == null) {
            errors.add("middlename", new ActionMessage(""));
        } else {
            if (middleName.length() < 1) {
                errors.add("middlename", new ActionMessage("error.middlename.required"));
            } else {
                if (!validator.isValidLastName(middleName)) {
                    errors.add("middlename", new ActionMessage("error.middlename.invalid"));
                }
            }
        }

        if (lastName == null) {
            errors.add("lastname", new ActionMessage(""));
        } else {
            if (lastName.length() < 1) {
                errors.add("lastname", new ActionMessage("error.lastname.required"));
            } else {
                if (!validator.isValidLastName(lastName)) {
                    errors.add("lastname", new ActionMessage("error.lastname.invalid"));
                }
            }
        }

        if (email == null) {
            errors.add("email", new ActionMessage(""));
        } else {
            if (email.length() < 1) {
                errors.add("email", new ActionMessage("error.email.required"));
            } else {
                if (!validator.isValidEmailAddress(email)) {
                    errors.add("email", new ActionMessage("error.email.invalid"));
                }
            }
        }

        if (designation == null) {
            errors.add("designation", new ActionMessage(""));
        } else {
            if (designation.length() < 1) {
                errors.add("designation", new ActionMessage("error.designation.required"));
            } else {
                if (!validator.isValidKeyword(designation)) {
                    errors.add("designation", new ActionMessage("error.designation.invalid"));
                }
            }
        }

        if (agency == null) {
            errors.add("agency", new ActionMessage(""));
        } else {
            if (agency.length() < 1) {
                errors.add("agency", new ActionMessage("error.agency.required"));
            } else {
                if (!validator.isValidKeyword(agency)) {
                    errors.add("agency", new ActionMessage("error.agency.invalid"));
                }
            }
        }

        if (number == null) {
            errors.add("number", new ActionMessage(""));
        } else {
            if (number.length() < 1) {
                errors.add("number", new ActionMessage("error.number.required"));
            } else {
                if (!validator.isValidNumber(number)) {
                    errors.add("number", new ActionMessage("error.number.invalid"));
                }
            }
        }

        if (answer == null) {
            errors.add("answer", new ActionMessage(""));
        } else {
            if (answer.length() < 1) {
                errors.add("answer", new ActionMessage("error.answer.required"));
            } else {
                if (!validator.isValidKeyword(answer)) {
                    errors.add("answer", new ActionMessage("error.answer.invalid"));
                }
            }
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            isValid = false;
        }

        return isValid;
    }
}