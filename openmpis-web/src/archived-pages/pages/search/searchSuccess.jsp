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
        <title><bean:message key="case.title"/></title>
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
                        <bean:message key="search.menu"/>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${type == 'allmissing'}">
                            <bean:message key="case.list.allmissing" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type == 'missing'}">
                            <bean:message key="case.list.missing" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type == 'familyabduction'}">
                            <bean:message key="case.list.familyabduction" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type == 'nonfamilyabduction'}">
                            <bean:message key="case.list.nonfamily" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type == 'runaway'}">
                            <bean:message key="case.list.runaway" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type == 'unknown'}">
                            <bean:message key="case.list.unknown" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type == 'abandoned'}">
                            <bean:message key="case.list.abandoned" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type == 'throwaway'}">
                            <bean:message key="case.list.throwaway" arg0="${currentpage}" arg1="${totalpages}"/>
                        </c:when>
                        <c:when test="${type == 'unidentified'}">
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
                                <bean:message key="search.empty"/>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <div class="table">
                                <c:forEach begin="0" end="${(personcount - (personcount % 4)) / 4}" step="1" var="i">
                                    <div class="row">
                                        <c:forEach begin="0" end="3" step="1" var="j">
                                            <c:choose>
                                                <c:when test="${personlist[(4 * i) + j].id != null}">
                                                    <div class="cell" style="width: 25%;">
                                                        <c:url var="url" scope="page" value="/viewPerson.do">
                                                            <c:param name="action" value="viewPerson"/>
                                                            <c:param name="id" value="${personlist[(4 * i) + j].id}"/>
                                                        </c:url>
                                                        <p style="text-align: center;">
                                                            <html:link href="${fn:escapeXml(url)}">
                                                                <html:img styleClass="photoclass" src="${personlist[(4 * i) + j].photo}" alt="The person's photo" title="Photo"/>
                                                            </html:link>
                                                        </p>
                                                        <p style="text-align: center;">
                                                            <c:choose>
                                                                <c:when test="${personlist[(4 * i) + j].nickname == ''}">
                                                                    <html:link href="${fn:escapeXml(url)}">${personlist[(4 * i) + j].firstName} ${personlist[(4 * i) + j].lastName}</html:link>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <html:link href="${fn:escapeXml(url)}">${personlist[(4 * i) + j].firstName} "${personlist[(4 * i) + j].nickname}" ${personlist[(4 * i) + j].lastName}</html:link>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </p>
                                                        <p style="text-align: center;">
                                                            <c:choose>
                                                                <c:when test="${personlist[(4 * i) + j].type < 5}">
                                                                    <bean:message key="label.date.missing"/>: <bean:message key="month.${personlist[(4 * i) + j].monthMissingOrFound}"/> ${personlist[(4 * i) + j].dayMissingOrFound}, ${personlist[(4 * i) + j].yearMissingOrFound}
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <bean:message key="label.date.found"/>: ${personlist[(4 * i) + j].monthMissingOrFound} ${personlist[(4 * i) + j].dayMissingOrFound}, ${personlist[(4 * i) + j].yearMissingOrFound}
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </p>
                                                        <p style="text-align: center;">
                                                            ${personlist[(4 * i) + j].missingFromCity}, ${personlist[(4 * i) + j].missingFromProvince}
                                                        </p>
                                                        <p style="text-align: center;">
                                                            <bean:message key="type.${personlist[(4 * i) + j].type}"/>
                                                        </p>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="hiddencell" style="width: 25%;"></div>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
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
                                    <c:when test="${morepages == 'true'}">
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
                            </p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>