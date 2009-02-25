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
        <link rel="shortcut icon" href="/images/favicon.ico"/>
        <style type="text/css" media="all">@import "<bean:message key="global.style"/>";</style>
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
    </head>
    <body>
        <div id="container">
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
                        <bean:message key="investigator.home.menu" arg0="${currentuser.id}"/>
                    </c:when>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${currentuser.groupId == 0}">
                            <bean:message key="admin.home.heading"/>
                            <p class="contentclass">
                                <bean:message key="admin.home.content" arg0="${currentuser.firstName}"/>
                            </p>
                            <p class="contentclass">
                                <bean:message key="admin.home.content.feedbacks" arg0="${newmessages}"/>
                            </p>
                        </c:when>
                        <c:when test="${currentuser.groupId == 1}">
                            <bean:message key="encoder.home.heading"/>
                            <p class="contentclass">
                                <bean:message key="encoder.home.content" arg0="${currentuser.firstName}"/>
                            </p>
                        </c:when>
                        <c:when test="${currentuser.groupId == 2}">
                            <bean:message key="investigator.home.heading"/>
                            <p class="contentclass">
                                <bean:message key="investigator.home.content" arg0="${currentuser.firstName}"/>
                            </p>
                            <p class="contentclass">
                                <bean:message key="investigator.home.content.sightings" arg0="${newmessages}"/>
                            </p>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>