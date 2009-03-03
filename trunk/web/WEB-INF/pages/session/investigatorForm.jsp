<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html:html xhtml="true">
    <head>
        <meta http-equiv="content-type" content="text/html"/>
        <meta name="author" content="Rey Vincent Babilonia"/>
        <meta name="keywords" content="missing, filipino, person, openmpis"/>
        <meta name="description" content="This is the Web page for the OpenMPIS."/>
        <meta name="robots" content="all"/>
        <link rel="shortcut icon" href="images/favicon.ico"/>
        <style type="text/css" media="all">@import "<bean:message key="global.style"/>";</style>
        <script type="text/javascript" src="scripts/openmpis.js"></script>
        <bean:message key="investigator.title"/>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.case.menu"/>
                    </c:when>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="investigator.assign"/>
                    <noscript>
                        <bean:message key="error.javascript.disabled"/>
                    </noscript>

                    <html:form method="post" action="viewCase" styleClass="adduserclass">
                        <p class="contentclass">
                            <html:hidden property="action" value="assignInvestigator"/>
                            <html:hidden property="personid" value="${personid}"/>
                        </p>
                        <p class="contentclass">
                            <label id="investigatorlistlabel" class="labelclass" for="investigatorlistfield">
                                <bean:message key="label.investigator.existing"/>
                            </label>
                            <html:select styleId="investigatorlistfield" styleClass="selectclass" property="id"
                                onchange="javascript: if (investigatorlistfield.value > 0) { document.getElementById('assignbuttons').style.display = 'block'; } else { document.getElementById('assignbuttons').style.display = 'none'; }"
                                onkeyup="javascript: if (investigatorlistfield.value > 0) { document.getElementById('assignbuttons').style.display = 'block'; } else { document.getElementById('assignbuttons').style.display = 'none'; }">
                                <html:option value="0" styleId="investigatorfield0" styleClass="optionclass">----------</html:option>
                                <c:forEach items="${investigatorlist}" var="investigator">
                                    <html:option value="${investigator.id}" styleId="investigatorfield${i}" styleClass="optionclass">${investigator.lastName}, ${investigator.firstName}</html:option>
                                </c:forEach>
                            </html:select>
                        </p>
                        <c:choose>
                            <c:when test="${investigatorForm.id > 0}">
                                <c:set var="assign" value="display: block;"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="assign" value="display: none;"/>
                            </c:otherwise>
                        </c:choose>
                        <div id="assignbuttons" style="${assign}}">
                            <p class="contentclass">
                                <c:if test="${currentuser.groupId == 1}">
                                    <bean:message key="case.assign.buttons"/>
                                </c:if>
                            </p>
                        </div>
                    </html:form>

                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>