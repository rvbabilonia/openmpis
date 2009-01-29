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
            <c:when test="${currentuser.groupId eq '0'}">
                <bean:message key="admin.home.title"/>
            </c:when>
            <c:when test="${currentuser.groupId eq '1'}">
                <bean:message key="encoder.home.title"/>
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
                    <p class="contentclass">
                        <c:choose>
                            <c:when test="${operation eq 'add'}">
                                <bean:message key="user.content.created" arg0="${username}"/>
                            </c:when>
                            <c:when test="${operation eq 'edit'}">
                                <bean:message key="user.content.updated" arg0="${username}"/>
                            </c:when>
                            <c:when test="${operation eq 'delete'}">
                                <bean:message key="user.content.deleted" arg0="${username}"/>
                            </c:when>
                        </c:choose>
                    </p>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>