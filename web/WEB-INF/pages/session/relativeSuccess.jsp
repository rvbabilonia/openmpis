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
        <title><bean:message key="user.title"/></title>
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
                    <p class="contentclass">
                        <c:choose>
                            <c:when test="${operation == 'add'}">
                                <bean:message key="relative.content.created" arg0="${relative.firstName}" arg1="${relative.lastName}" arg2="${relative.id}"/>
                            </c:when>
                            <c:when test="${operation == 'edit'}">
                                <bean:message key="relative.content.updated" arg0="${relative.firstName}" arg1="${relative.lastName}" arg2="${relative.id}"/>
                            </c:when>
                            <c:when test="${operation == 'delete'}">
                                <bean:message key="relative.content.deleted" arg0="${relative.firstName}" arg1="${relative.lastName}" arg2="${relative.id}"/>
                            </c:when>
                        </c:choose>
                    </p>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>