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
        <link rel="shortcut icon" href="<html:rewrite page=""/>/images/favicon.ico"/>
        <style type="text/css" media="all">@import "<html:rewrite page=""/>/<bean:message key="global.style"/>";</style>
        <bean:message key="user.title"/>
        <html:base/>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 0}">
                        <bean:message key="admin.user.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.user.menu"/>
                    </c:when>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="user.add"/>
                    
                    <html:form method="post" action="viewUser" styleClass="adduserclass">
                        <p class="contentclass">
                            <html:hidden property="action" value="addUser"/>
                        </p>
                        <p class="contentclass">
                            <label id="groupidlabel" class="labelclass" for="groupidfield">
                                <bean:message key="label.group"/>
                            </label>
                            <html:select property="groupId" styleId="groupidfield" styleClass="selectclass">
                                <c:choose>
                                    <c:when test="${currentuser.groupId == 0}">
                                        <html:option value="0" styleId="groupfield0" styleClass="optionclass"><bean:message key="group.0"/></html:option>
                                        <html:option value="1" styleId="groupfield1" styleClass="optionclass"><bean:message key="group.1"/></html:option>
                                    </c:when>
                                    <c:otherwise>
                                        <html:option value="2" styleId="groupfield2" styleClass="optionclass"><bean:message key="group.2"/></html:option>
                                    </c:otherwise>
                                </c:choose>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="firstnamelabel" class="labelclass" for="firstnamefield">
                                <bean:message key="label.firstname"/>
                            </label>
                            <html:text styleId="firstnamefield" styleClass="inputclass" property="firstName" maxlength="30"/>
                            <html:errors property="firstname"/>
                        </p>
                        <p class="contentclass">
                            <label id="middlenamelabel" class="labelclass" for="middlenamefield">
                                <bean:message key="label.middlename"/>
                            </label>
                            <html:text styleId="middlenamefield" styleClass="inputclass" property="middleName" maxlength="30"/>
                            <html:errors property="middlename"/>
                        </p>
                        <p class="contentclass">
                            <label id="lastnamelabel" class="labelclass" for="lastnamefield">
                                <bean:message key="label.lastname"/>
                            </label>
                            <html:text styleId="lastnamefield" styleClass="inputclass" property="lastName" maxlength="30"/>
                            <html:errors property="lastname"/>
                        </p>
                        <p class="contentclass">
                            <label id="birthdatelabel" class="labelclass" for="monthfield">
                                <bean:message key="label.birthdate"/>
                            </label>
                            <html:select styleId="monthfield" styleClass="monthselectclass" property="birthMonth">
                                <c:forEach begin="1" end="12" step="1" var="i">
                                    <html:option value="${i}" styleId="monthfield${i}" styleClass="monthoptionclass"><bean:message key="month.${i}"/></html:option>
                                </c:forEach>
                            </html:select>
                            <html:select styleId="dayfield" styleClass="dayselectclass" property="birthDay">
                                <c:forEach begin="1" end="31" step="1" var="i">
                                    <html:option value="${i}" styleId="dayfield${i}" styleClass="dayoptionclass">${i}</html:option>
                                </c:forEach>
                            </html:select>
                            <html:select styleId="yearfield" styleClass="yearselectclass" property="birthYear">
                                <c:forEach begin="1949" end="1988" step="1" var="i">
                                    <html:option value="${i}" styleId="yearfield${i}" styleClass="yearoptionclass">${i}</html:option>
                                </c:forEach>
                            </html:select>
                            <html:errors property="birthdate"/>
                        </p>
                        <p class="contentclass">
                            <label id="designationlabel" class="labelclass" for="designationfield">
                                <bean:message key="label.designation"/>
                            </label>
                            <html:text styleId="designationfield" styleClass="inputclass" property="designation" maxlength="30"/>
                            <html:errors property="designation"/>
                        </p>
                        <p class="contentclass">
                            <label id="agencylabel" class="labelclass" for="agencyfield">
                                <bean:message key="label.agency"/>
                            </label>
                            <html:text styleId="agencyfield" styleClass="inputclass" property="agency" maxlength="60"/>
                            <html:errors property="agency"/>
                        </p>
                        <p class="contentclass">
                            <label id="emaillabel" class="labelclass" for="emailfield">
                                <bean:message key="label.email"/>
                            </label>
                            <html:text styleId="emailfield" styleClass="inputclass" property="email" maxlength="60"/>
                            <html:errors property="email"/>
                        </p>
                        <p class="contentclass">
                            <label id="numberlabel" class="labelclass" for="numberfield">
                                <bean:message key="label.number"/>
                            </label>
                            <html:text styleId="numberfield" styleClass="inputclass" property="number" maxlength="30"/>
                            <html:errors property="number"/>
                        </p>
                        <p class="contentclass">
                            <label id="statuslabel" class="labelclass" for="statusfield">
                                <bean:message key="label.status"/>
                            </label>
                            <html:select property="status" styleId="statusfield" styleClass="selectclass" >
                                <html:option value="0" styleId="statusfield0" styleClass="optionclass"><bean:message key="status.user.0"/></html:option>
                                <html:option value="1" styleId="statusfield1" styleClass="optionclass"><bean:message key="status.user.1"/></html:option>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <bean:message key="user.add.buttons"/>
                        </p>
                    </html:form>
                    
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>