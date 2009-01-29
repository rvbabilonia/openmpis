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
        <bean:message key="global.script"/>
        <c:choose>
            <c:when test="${currentuser.groupId eq '0'}">
                <bean:message key="admin.home.title"/>
            </c:when>
            <c:when test="${currentuser.groupId eq '1'}">
                <bean:message key="encoder.home.title"/>
            </c:when>
            <c:when test="${currentuser.groupId eq '2'}">
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
                            <bean:message key="admin.user.list"/>
                        </c:when>
                        <c:when test="${currentuser.groupId eq '1'}">
                            <bean:message key="encoder.user.list"/>
                        </c:when>
                    </c:choose>
                    <noscript>
                        <bean:message key="error.javascript.disabled"/>
                    </noscript>
                    <table id="usertable" class="tableclass">
                        <tr>
                            <th><bean:message key="user.label.count"/></th>
                            <th><bean:message key="user.label.username"/></th>
                            <th><bean:message key="user.label.group"/></th>
                            <th><bean:message key="user.label.firstname"/></th>
                            <th><bean:message key="user.label.lastname"/></th>
                            <th><bean:message key="user.label.agency"/></th>
                            <th><bean:message key="user.label.designation"/></th>
                            <th><bean:message key="user.label.lastlogin"/></th>
                            <th><bean:message key="user.label.email"/></th>
                            <th><bean:message key="user.label.status"/></th>
                        </tr>
                        <c:forEach items="${userlist}" var="user" varStatus="userCount">
                        <tr>
                            <td>${userCount.count}</td>
                            <td><html:link href="/openmpis/viewUser.do?id=${user.id}">${user.username}</html:link></td>
                            <td><bean:message key="group.${user.groupId}"/></td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.agency}</td>
                            <td>${user.designation}</td>
                            <td>${user.lastLogin}</td>
                            <td><html:link href="mailto:${user.email}">${user.email}</html:link></td>
                            <td><bean:message key="status.${user.status}"/></td>
                        </tr>
                        </c:forEach>
                    </table>
                    <p class="contentclass">
                        <!-- pagination -->
                        <bean:message key="user.new.button"/>
                    </p>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>