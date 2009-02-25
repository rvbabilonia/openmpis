<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="true" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
        <bean:message key="search.title"/>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 0}">
                        <bean:message key="admin.search.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.search.menu"/>
                    </c:when>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${currentuser.groupId == 0}">
                            <bean:message key="admin.user.list" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${currentuser.groupId == 1}">
                            <bean:message key="encoder.user.list" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                    </c:choose>
                    <div class="table">
                        <div class="row">
                            <div class="columnheader"><bean:message key="label.count"/></div>
                            <div class="columnheader"><bean:message key="label.username"/></div>
                            <div class="columnheader"><bean:message key="label.group"/></div>
                            <div class="columnheader"><bean:message key="label.firstname"/></div>
                            <div class="columnheader"><bean:message key="label.lastname"/></div>
                            <div class="columnheader"><bean:message key="label.agency"/></div>
                            <div class="columnheader"><bean:message key="label.designation"/></div>
                            <div class="columnheader"><bean:message key="label.date.lastlogin"/></div>
                            <div class="columnheader"><bean:message key="label.email"/></div>
                            <div class="columnheader"><bean:message key="label.status"/></div>
                        </div>
                        <c:forEach items="${userlist}" var="user" varStatus="userCount">
                            <c:choose>
                                <c:when test="${userCount.count == 0}">
                                    <p class="contentclass">
                                        <bean:message key="user.empty"/>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <div class="row">
                                        <div class="cell">${userCount.count + ((currentpage - 1) * maxresults)}</div>
                                        <c:choose>
                                            <c:when test="${(currentuser.id == user.creatorId) || (currentuser.id == user.id) || ((currentuser.groupId == 0) && (user.groupId > 0))}">
                                                <c:url var="url" scope="page" value="/viewUser.do">
                                                    <c:param name="action" value="viewUser"/>
                                                    <c:param name="id" value="${user.id}"/>
                                                </c:url>
                                                <div class="cell"><html:link href="${fn:escapeXml(url)}">${user.username}</html:link></div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="cell">${user.username}</div>
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="cell"><bean:message key="group.${user.groupId}"/></div>
                                        <div class="cell">${user.firstName}</div>
                                        <div class="cell">${user.lastName}</div>
                                        <div class="cell">${user.agency}</div>
                                        <div class="cell">${user.designation}</div>
                                        <div class="cell">${user.lastLogin}</div>
                                        <div class="cell"><html:link href="mailto:${user.email}">${user.email}</html:link></div>
                                        <div class="cell"><bean:message key="status.user.${user.status}"/></div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                    <p class="contentclass">
                        <c:choose>
                            <c:when test="${currentpage > 1}">
                                <c:url var="url" scope="page" value="/viewUser.do">
                                    <c:param name="action" value="list"/>
                                    <c:param name="page" value="start"/>
                                </c:url>
                                <bean:message key="pagination.firstpage.enabled" arg0="${fn:escapeXml(url)}"/>
                            </c:when>
                            <c:otherwise>
                                <bean:message key="pagination.firstpage.disabled"/>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${(currentpage - 1) > 0}">
                                <c:url var="url" scope="page" value="/viewUser.do">
                                    <c:param name="action" value="list"/>
                                    <c:param name="page" value="previous"/>
                                </c:url>
                                <bean:message key="pagination.previouspage.enabled" arg0="${fn:escapeXml(url)}"/>
                            </c:when>
                            <c:otherwise>
                                <bean:message key="pagination.previouspage.disabled"/>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${morepages eq 'true'}">
                                <c:url var="url" scope="page" value="/viewUser.do">
                                    <c:param name="action" value="list"/>
                                    <c:param name="page" value="next"/>
                                </c:url>
                                <bean:message key="pagination.nextpage.enabled" arg0="${fn:escapeXml(url)}"/>
                                <c:url var="url" scope="page" value="/viewUser.do">
                                    <c:param name="action" value="list"/>
                                    <c:param name="page" value="end"/>
                                </c:url>
                                <bean:message key="pagination.lastpage.enabled" arg0="${fn:escapeXml(url)}"/>
                            </c:when>
                            <c:otherwise>
                                <bean:message key="pagination.nextpage.disabled"/>
                                <bean:message key="pagination.lastpage.disabled"/>
                            </c:otherwise>
                        </c:choose>
                        <bean:message key="user.new.button"/>
                    </p>
                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>