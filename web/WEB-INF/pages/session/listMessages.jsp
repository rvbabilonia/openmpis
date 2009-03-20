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
        <title><bean:message key="sighting.title"/></title>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 0}">
                        <bean:message key="admin.feedback.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.sighting.menu"/>
                    </c:when>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${currentuser.groupId == 0}">
                            <bean:message key="feedback.list" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${currentuser.groupId == 2}">
                            <bean:message key="sighting.list" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${messagecount == 0}">
                            <c:choose>
                                <c:when test="${currentuser.groupId == 0}">
                                    <p class="contentclass">
                                        <bean:message key="feedback.content.empty"/>
                                    </p>
                                </c:when>
                                <c:when test="${currentuser.groupId == 2}">
                                    <p class="contentclass">
                                        <bean:message key="sighting.content.empty"/>
                                    </p>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <div class="table">
                                <div class="row">
                                    <div class="columnheader"><bean:message key="label.count"/></div>
                                    <div class="columnheader"><bean:message key="label.subject"/></div>
                                    <div class="columnheader"><bean:message key="label.from"/></div>
                                    <div class="columnheader"><bean:message key="label.date.sent"/></div>
                                    <div class="columnheader"><bean:message key="label.status"/></div>
                                </div>
                                <c:forEach items="${messagelist}" var="message" varStatus="messageCount">
                                    <div class="row">
                                        <div class="cell">${messageCount.count}</div>
                                        <c:choose>
                                            <c:when test="${currentuser.groupId == 2}">
                                                <div class="cell"><html:link action="viewMessage.do?action=viewSighting" paramName="message" paramId="id" paramProperty="id">${message.subject}</html:link></div>
                                            </c:when>
                                            <c:when test="${currentuser.groupId == 0}">
                                                <div class="cell"><html:link action="viewMessage.do?action=viewFeedback" paramName="message" paramId="id" paramProperty="id">${message.subject}</html:link></div>
                                            </c:when>
                                        </c:choose>
                                        <div class="cell"><html:link href="mailto:${message.email}">${message.firstName} ${message.lastName}</html:link></div>
                                        <div class="cell">${message.date}</div>
                                        <div class="cell"><bean:message key="status.message.${message.status}"/></div>
                                    </div>
                                </c:forEach>
                            </div>
                            <p class="contentclass">
                                <c:choose>
                                    <c:when test="${currentpage > 1}">
                                        <c:url var="url" scope="page" value="/viewmessage.do">
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
                                        <c:url var="url" scope="page" value="/viewmessage.do">
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
                                        <c:url var="url" scope="page" value="/viewmessage.do">
                                            <c:param name="action" value="list"/>
                                            <c:param name="page" value="next"/>
                                        </c:url>
                                        <bean:message key="pagination.nextpage.enabled" arg0="${fn:escapeXml(url)}"/>
                                        <c:url var="url" scope="page" value="/viewmessage.do">
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
                            </p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>