<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

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
                    <bean:message key="user.list" arg0="${currentpage}" arg1="${totalpages}"/>
                    <c:choose>
                        <c:when test="${usercount == 0}">
                            <p class="contentclass">
                                <bean:message key="user.content.empty"/>
                            </p>
                            <p class="contentclass">
                                <c:if test="${currentuser.groupId == 1}">
                                    <bean:message key="user.new.button"/>
                                </c:if>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <div class="table">
                                <div class="row">
                                    <div class="columnheader"><bean:message key="label.count"/></div>
                                    <div class="columnheader"><bean:message key="label.username"/></div>
                                    <div class="columnheader"><bean:message key="label.group"/></div>
                                    <div class="columnheader"><bean:message key="label.name"/></div>
                                    <div class="columnheader"><bean:message key="label.agency"/></div>
                                    <div class="columnheader"><bean:message key="label.date.lastlogin"/></div>
                                    <div class="columnheader"><bean:message key="label.status"/></div>
                                </div>
                                <c:forEach items="${userlist}" var="user" varStatus="userCount">
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
                                        <div class="cell"><html:link href="mailto:${user.email}">${user.lastName}, ${user.firstName}</html:link></div>
                                        <div class="cell">${user.agency}</div>
                                        <div class="cell">${user.lastLogin}</div>
                                        <div class="cell"><bean:message key="status.user.${user.status}"/></div>
                                    </div>
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
                                    <c:when test="${morepages == 'true'}">
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
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>