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
            <c:when test="${currentuser.groupId == 0}">
                <bean:message key="admin.user.title"/>
            </c:when>
            <c:when test="${currentuser.groupId == 1}">
                <bean:message key="encoder.user.title"/>
            </c:when>
            <c:when test="${currentuser.groupId == 2}">
                <bean:message key="investigator.user.title"/>
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
                    <c:choose>
                        <c:when test="${currentuser.groupId == 0}">
                            <bean:message key="admin.user.list"/>
                        </c:when>
                        <c:when test="${currentuser.groupId == 1}">
                            <bean:message key="encoder.user.list"/>
                        </c:when>
                    </c:choose>
                    <noscript>
                        <bean:message key="error.javascript.disabled"/>
                    </noscript>
                    <div id="table">
                        <div id="row">
                            <div id="columnheader"><bean:message key="user.label.count"/></div>
                            <div id="columnheader"><bean:message key="user.label.username"/></div>
                            <div id="columnheader"><bean:message key="user.label.group"/></div>
                            <div id="columnheader"><bean:message key="user.label.firstname"/></div>
                            <div id="columnheader"><bean:message key="user.label.lastname"/></div>
                            <div id="columnheader"><bean:message key="user.label.agency"/></div>
                            <div id="columnheader"><bean:message key="user.label.designation"/></div>
                            <div id="columnheader"><bean:message key="user.label.lastlogin"/></div>
                            <div id="columnheader"><bean:message key="user.label.email"/></div>
                            <div id="columnheader"><bean:message key="user.label.status"/></div>
                        </div>
                        <c:forEach items="${userlist}" var="user" varStatus="userCount">
                        <div id="row">
                            <div id="cell">${userCount.count}</div>
                            <c:choose>
                                <c:when test="${(currentuser.id == user.creatorId) || (currentuser.id == user.id) || ((currentuser.groupId == 0) && (user.groupId > 0))}">
                                    <c:url var="url" scope="page" value="/viewUser.do">
                                        <c:param name="action" value="view"/>
                                        <c:param name="id" value="${user.id}"/>
                                    </c:url>
                                    <div id="cell"><html:link href="${url}">${user.username}</html:link></div>
                                </c:when>
                                <c:otherwise>
                                    <div id="cell">${user.username}</div>
                                </c:otherwise>
                            </c:choose>
                            <div id="cell"><bean:message key="group.${user.groupId}"/></div>
                            <div id="cell">${user.firstName}</div>
                            <div id="cell">${user.lastName}</div>
                            <div id="cell">${user.agency}</div>
                            <div id="cell">${user.designation}</div>
                            <div id="cell">${user.lastLogin}</div>
                            <div id="cell"><html:link href="mailto:${user.email}">${user.email}</html:link></div>
                            <div id="cell"><bean:message key="status.${user.status}"/></div>
                        </div>
                        </c:forEach>
                    </div>
                    <p class="contentclass">
                        <!-- pagination -->
                        <bean:message key="user.new.button"/>
                    </p>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>