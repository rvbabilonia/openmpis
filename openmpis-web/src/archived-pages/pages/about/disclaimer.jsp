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
        <title><bean:message key="disclaimer.title"/></title>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 0}">
                        <bean:message key="admin.global.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.global.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.global.menu" arg0="${currentuser.id}"/>
                    </c:when>
                    <c:otherwise>
                        <bean:message key="global.menu"/>
                    </c:otherwise>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <h1>Disclaimer</h1>
                    <p class="contentclass">
                        This Web site is for information only and should not be considered as a substitute for
                        seeking appropriate advice. So far as permissible by law, the Open Source Missing Persons
                        Information System does not accept liability to any person relating to the use of any
                        information on this Web site.
                    </p>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>