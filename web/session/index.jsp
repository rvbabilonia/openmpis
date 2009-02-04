<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="true" %>

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
            <c:when test="${currentuser.groupId == 0}">
                <bean:message key="admin.home.title"/>
            </c:when>
            <c:when test="${currentuser.groupId == 1}">
                <bean:message key="encoder.home.title"/>
            </c:when>
            <c:when test="${currentuser.groupId == 2}">
                <bean:message key="investigator.home.title"/>
            </c:when>
        </c:choose>
        <html:base/>
    </head>
    <body>
        <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 0}">
                        <bean:message key="admin.home.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.home.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.home.menu"/>
                    </c:when>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${currentuser.groupId == 0}">
                            <bean:message key="admin.home.heading"/>
                            <bean:message key="admin.home.content" arg0="${currentuser.firstName}"/>
                            You have 0 feedbacks.
                        </c:when>
                        <c:when test="${currentuser.groupId == 1}">
                            <bean:message key="encoder.home.heading"/>
                            <bean:message key="encoder.home.content" arg0="${currentuser.firstName}"/>
                        </c:when>
                        <c:when test="${currentuser.groupId == 2}">
                            <bean:message key="investigator.home.heading"/>
                            <bean:message key="investigator.home.content" arg0="${currentuser.firstName}"/>
                            Person has 1 sighting(s).
                        </c:when>
                    </c:choose>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>