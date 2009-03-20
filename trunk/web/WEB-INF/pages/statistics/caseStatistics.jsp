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
        <title><bean:message key="statistics.title"/></title>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 0}">
                        <bean:message key="admin.statistics.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.statistics.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.statistics.menu"/>
                    </c:when>
                    <c:otherwise>
                        <bean:message key="statistics.menu"/>
                    </c:otherwise>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="statistics.heading"/>
                    <p class="contentclass">
                        <bean:message key="statistics.content.cases" arg0="${casecount}" arg1="${ongoingcount}" arg2="${solvedcount}" arg3="${unsolvedcount}"/>
                    </p>
                    <p class="contentclass">
                        <bean:message key="statistics.content.missing" arg0="${missingcount}" arg1="${familyabductioncount}" arg2="${nonfamilyabductioncount}" arg3="${runawaycount}" arg4="${unknowncount}"/>
                    </p>
                    <p class="contentclass">
                        <bean:message key="statistics.content.found" arg0="${foundcount}" arg1="${abandonedcount}" arg2="${throwawaycount}" arg3="${unidentifiedcount}"/>
                    </p>
                    <p class="contentclass">
                        <bean:message key="statistics.content.relatives" arg0="${relativecount}"/>
                    </p>
                    <p class="contentclass">
                        <bean:message key="statistics.content.abductors" arg0="${abductorcount}"/>
                    </p>
                    <p class="contentclass">
                        <bean:message key="statistics.print.cases"/>
                    </p>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>