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
        <link rel="shortcut icon" href="/images/favicon.ico"/>
        <style type="text/css" media="all">@import "<bean:message key="global.style"/>";</style>
        <bean:message key="case.title"/>
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
                        <bean:message key="encoder.case.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.case.menu"/>
                    </c:when>
                    <c:otherwise>
                        <bean:message key="global.menu"/>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <div id="casemenu">
                        <bean:message key="case.menu"/>
                    </div>
                    <c:choose>
                        <c:when test="${type eq 'allmissing'}">
                            <bean:message key="case.list.allmissing" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type eq 'missing'}">
                            <bean:message key="case.list.missing" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type eq 'familyabduction'}">
                            <bean:message key="case.list.familyabduction" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type eq 'nonfamilyabduction'}">
                            <bean:message key="case.list.nonfamily" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type eq 'runaway'}">
                            <bean:message key="case.list.runaway" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type eq 'unknown'}">
                            <bean:message key="case.list.unknown" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type eq 'abandoned'}">
                            <bean:message key="case.list.abandoned" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type eq 'throwaway'}">
                            <bean:message key="case.list.throwaway" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type eq 'unidentified'}">
                            <bean:message key="case.list.unidentified" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${currentuser.groupId == 1}">
                            <bean:message key="encoder.case.list" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${currentuser.groupId == 2}">
                            <bean:message key="investigator.case.list" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:otherwise>
                            <bean:message key="case.list" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${personcount == 0}">
                            <p class="contentclass">
                                <bean:message key="case.content.empty"/>
                            </p>
                            <p class="contentclass">
                                <c:if test="${currentuser.groupId == 1}">
                                    <bean:message key="case.new.button"/>
                                </c:if>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <div class="table">
                                <div class="row">
                                    <div class="columnheader"><bean:message key="label.count"/></div>
                                    <div class="columnheader"><bean:message key="label.case.id"/></div>
                                    <div class="columnheader"><bean:message key="label.case.type"/></div>
                                    <div class="columnheader"><bean:message key="label.status"/></div>
                                    <div class="columnheader"><bean:message key="label.firstname"/></div>
                                    <div class="columnheader"><bean:message key="label.lastname"/></div>
                                    <div class="columnheader"></div>
                                    <div class="columnheader"></div>
                                    <div class="columnheader"></div>
                                    <div class="columnheader"></div>
                                </div>
                                <c:forEach items="${personlist}" var="person" varStatus="personCount">
                                    <div class="row">
                                        <div class="cell">${personCount.count + ((currentpage - 1) * maxresults)}</div>
                                        <c:url var="url" scope="page" value="/viewCase.do">
                                            <c:param name="action" value="viewPerson"/>
                                            <c:param name="id" value="${user.id}"/>
                                        </c:url>
                                        <div class="cell"><html:link href="${fn:escapeXml(url)}">${person.id}</html:link></div>
                                        <div class="cell"><bean:message key="group.${person.type}"/></div>
                                        <div class="cell"><bean:message key="status.case.${person.status}"/></div>
                                        <div class="cell">${person.firstName}</div>
                                        <div class="cell">${person.lastName}</div>
                                        <div class="cell"></div>
                                        <div class="cell"></div>
                                        <div class="cell"></div>
                                        <div class="cell"></div>
                                    </div>
                                </c:forEach>
                            </div>
                            <p class="contentclass">
                                <c:choose>
                                    <c:when test="${currentpage > 1}">
                                        <c:url var="url" scope="page" value="/viewCase.do">
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
                                        <c:url var="url" scope="page" value="/viewCase.do">
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
                                        <c:url var="url" scope="page" value="/viewCase.do">
                                            <c:param name="action" value="list"/>
                                            <c:param name="page" value="next"/>
                                        </c:url>
                                        <bean:message key="pagination.nextpage.enabled" arg0="${fn:escapeXml(url)}"/>
                                        <c:url var="url" scope="page" value="/viewCase.do">
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
                                <c:if test="${currentuser.groupId == 1}">
                                    <bean:message key="case.new.button"/>
                                </c:if>
                            </p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>