<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html:html xhtml="true">
    <head>
        <meta http-equiv="content-type" content="text/html"/>
        <meta name="author" content="Rey Vincent Babilonia"/>
        <meta name="keywords" content="missing, filipino, person, openmpis"/>
        <meta name="description" content="This is the Web page for the OpenMPIS."/>
        <meta name="robots" content="all"/>
        <bean:message key="global.icon"/>
        <bean:message key="global.style"/>
        <c:choose>
            <c:when test="${currentuser.groupId eq '0'}">
                <bean:message key="admin.user.title"/>
            </c:when>
            <c:when test="${currentuser.groupId eq '1'}">
                <bean:message key="encoder.user.title"/>
            </c:when>
            <c:when test="${currentuser.groupId eq '2'}">
                <bean:message key="investigator.user.title"/>
            </c:when>
        </c:choose>
        <html:base/>
    </head>
    <body>
        <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId eq '0'}">
                        <bean:message key="admin.user.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId eq '1'}">
                        <bean:message key="encoder.user.menu"/>
                    </c:when>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${currentuser.groupId eq '0'}">
                            <bean:message key="admin.user.add"/>
                        </c:when>
                        <c:when test="${currentuser.groupId eq '1'}">
                            <bean:message key="encoder.user.add"/>
                        </c:when>
                    </c:choose>
                    <c:if test="${addduplicateerror == 1}">
                        <bean:message key="error.duplicate"/>
                    </c:if>
                    <html:form styleId="userform" method="post" action="addUserBean" styleClass="adduserclass">
                        <p class="contentclass">
                            <label id="groupidlabel" class="labelclass" for="groupidfield"><bean:message key="user.label.group"/></label>
                            <html:select property="groupId" value="${user.groupId}" styleId="groupidfield" styleClass="selectclass">
                                <c:if test="${currentuser.groupId eq '0'}">
                                    <html:option value="0" styleId="optionfield0" styleClass="optionclass"><bean:message key="group.0"/></html:option>
                                    <html:option value="1" styleId="optionfield1" styleClass="optionclass"><bean:message key="group.1"/></html:option>
                                </c:if>
                                <c:if test="${currentuser.groupId eq '1'}">
                                    <html:option value="1" styleId="optionfield1" styleClass="optionclass"><bean:message key="group.1"/></html:option>
                                </c:if>
                                <html:option value="2" styleId="optionfield2" styleClass="optionclass"><bean:message key="group.2"/></html:option>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="usernamelabel" class="labelclass" for="usernamefield"><bean:message key="user.label.username"/></label>
                            <html:text styleId="usernamefield" styleClass="inputclass" property="username" maxlength="6" value="${user.username}"/>
                            <html:errors property="username"/>
                        </p>
                        <p class="contentclass">
                            <label id="firstnamelabel" class="labelclass" for="firstnamefield"><bean:message key="user.label.firstname"/></label>
                            <html:text styleId="firstnamefield" styleClass="inputclass" property="firstName" maxlength="30" value="${user.firstName}"/>
                            <html:errors property="firstname"/>
                        </p>
                        <p class="contentclass">
                            <label id="middlenamelabel" class="labelclass" for="middlenamefield"><bean:message key="user.label.middlename"/></label>
                            <html:text styleId="middlenamefield" styleClass="inputclass" property="middleName" maxlength="30" value="${user.middleName}"/>
                            <html:errors property="middlename"/>
                        </p>
                        <p class="contentclass">
                            <label id="lastnamelabel" class="labelclass" for="lastnamefield"><bean:message key="user.label.lastname"/></label>
                            <html:text styleId="lastnamefield" styleClass="inputclass" property="lastName" maxlength="30" value="${user.lastName}"/>
                            <html:errors property="lastname"/>
                        </p>
                        <p class="contentclass">
                            <label id="designationlabel" class="labelclass" for="designationfield"><bean:message key="user.label.designation"/></label>
                            <html:text styleId="designationfield" styleClass="inputclass" property="designation" maxlength="30" value="${user.designation}"/>
                            <html:errors property="designation"/>
                        </p>
                        <p class="contentclass">
                            <label id="agencylabel" class="labelclass" for="agencyfield"><bean:message key="user.label.agency"/></label>
                            <html:text styleId="agencyfield" styleClass="inputclass" property="agency" maxlength="60" value="${user.agency}"/>
                            <html:errors property="agency"/>
                        </p>
                        <p class="contentclass">
                            <label id="emaillabel" class="labelclass" for="emailfield"><bean:message key="user.label.email"/></label>
                            <html:text styleId="emailfield" styleClass="inputclass" property="email" maxlength="60" value="${user.email}"/>
                            <html:errors property="email"/>
                        </p>
                        <p class="contentclass">
                            <label id="numberlabel" class="labelclass" for="numberfield"><bean:message key="user.label.number"/></label>
                            <html:text styleId="numberfield" styleClass="inputclass" property="number" maxlength="30" value="${user.number}"/>
                            <html:errors property="number"/>
                        </p>
                        <p class="contentclass">
                            <label id="statuslabel" class="labelclass" for="statusfield"><bean:message key="user.label.status"/></label>
                            <html:select property="status" value="${user.status}" styleId="statusfield" styleClass="selectclass" >
                                <html:option value="0" styleId="optionfield0" styleClass="optionclass"><bean:message key="status.0"/></html:option>
                                <html:option value="1" styleId="optionfield1" styleClass="optionclass"><bean:message key="status.1"/></html:option>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <bean:message key="user.add.buttons"/>
                        </p>
                    </html:form>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>