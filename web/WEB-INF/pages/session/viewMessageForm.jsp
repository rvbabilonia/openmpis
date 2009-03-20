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
        <link rel="shortcut icon" href="images/favicon.ico"/>
        <style type="text/css" media="all">@import "<bean:message key="global.style"/>";</style>
        <title><bean:message key="sighting.title"/></title>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 0}">
                        <bean:message key="admin.feedback.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.sighting.menu"/>
                    </c:when>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${currentuser.groupId == 0}">
                            <bean:message key="feedback.heading"/>
                        </c:when>
                        <c:when test="${currentuser.groupId == 2}">
                            <bean:message key="sighting.heading"/>
                        </c:when>
                    </c:choose>

                    <p class="contentclass">
                        <label id="namelabel" class="labelclass">
                            <bean:message key="label.from"/>
                        </label>
                        <html:link href="mailto:${messageForm.email}">${messageForm.firstName} ${messageForm.lastName}</html:link>
                    </p>
                    <p class="contentclass">
                        <label id="subjectlabel" class="labelclass">
                            <bean:message key="label.subject"/>
                        </label>
                        ${messageForm.subject}
                    </p>
                    <p class="contentclass">
                        <label id="messagelabel" class="labelclass">
                            <bean:message key="label.message"/>
                        </label>
                        ${messageForm.message}
                    </p>
                    <p class="contentclass">
                        <label id="ipaddresslabel" class="labelclass">
                            <bean:message key="label.ipaddress"/>
                        </label>
                        ${messageForm.ipAddress}
                    </p>
                    <p class="contentclass">
                        <label id="datelabel" class="labelclass">
                            <bean:message key="label.date.sent"/>
                        </label>
                        ${messageForm.date}
                    </p>
                    <p class="contentclass">
                        <c:choose>
                            <c:when test="${currentuser.groupId == 0}">
                                <bean:message key="feedback.list.button"/>
                            </c:when>
                            <c:when test="${currentuser.groupId == 2}">
                                <bean:message key="sighting.list.button"/>
                            </c:when>
                        </c:choose>
                    </p>
                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>