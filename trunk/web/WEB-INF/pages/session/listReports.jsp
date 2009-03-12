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
        <bean:message key="report.title"/>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.report.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.report.menu"/>
                    </c:when>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="report.list" arg0="${currentpage}" arg1="${totalpages}"/>
                    <c:choose>
                        <c:when test="${reportcount == 0}">
                            <p class="contentclass">
                                <bean:message key="report.content.empty"/>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <div class="table">
                                <div class="row">
                                    <div class="columnheader"><bean:message key="label.count"/></div>
                                    <div class="columnheader"><bean:message key="label.for"/></div>
                                    <div class="columnheader"><bean:message key="label.date.reported"/></div>
                                </div>
                                <c:forEach items="${reportlist}" var="report" varStatus="reportCount">
                                    <div class="row">
                                        <div class="cell">${reportCount.count + ((currentpage - 1) * maxresults)}</div>
                                        <c:url var="url" scope="page" value="/viewReport.do">
                                            <c:param name="action" value="viewReport"/>
                                            <c:param name="id" value="${report.id}"/>
                                        </c:url>
                                        <div class="cell">
                                            <html:link href="${fn:escapeXml(url)}">
                                                ${report.firstName}
                                                <c:if test="${report.nickname != null}">
                                                    "${report.nickname}"
                                                </c:if>
                                                ${report.lastName}
                                            </html:link>
                                        </div>
                                        <div class="cell">${report.date}</div>
                                    </div>
                                </c:forEach>
                            </div>
                            <p class="contentclass">
                                <c:choose>
                                    <c:when test="${currentpage > 1}">
                                        <c:url var="url" scope="page" value="/viewreport.do">
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
                                        <c:url var="url" scope="page" value="/viewreport.do">
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
                                        <c:url var="url" scope="page" value="/viewreport.do">
                                            <c:param name="action" value="list"/>
                                            <c:param name="page" value="next"/>
                                        </c:url>
                                        <bean:message key="pagination.nextpage.enabled" arg0="${fn:escapeXml(url)}"/>
                                        <c:url var="url" scope="page" value="/viewreport.do">
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