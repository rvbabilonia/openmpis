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
        <title><bean:message key="home.title"/></title>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <bean:message key="home.menu"/>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contenthome">
                    <bean:message key="home.alert"/>

                    <div class="table">
                        <div class="row">
                            <c:forEach begin="0" end="3" step="1" var="j">
                                <c:choose>
                                    <c:when test="${personlist[j].id != null}">
                                        <div class="cell" style="width: 25%;">
                                            <c:url var="url" scope="page" value="/viewPerson.do">
                                                <c:param name="action" value="viewPerson"/>
                                                <c:param name="id" value="${personlist[j].id}"/>
                                            </c:url>
                                            <p style="text-align: center;">
                                                <html:link href="${fn:escapeXml(url)}">
                                                    <html:img styleClass="photoclass" src="${personlist[j].photo}" alt="The person's photo" title="Photo"/>
                                                </html:link>
                                            </p>
                                            <p style="text-align: center;">
                                                <c:choose>
                                                    <c:when test="${personlist[j].nickname == ''}">
                                                        <html:link href="${fn:escapeXml(url)}">${personlist[j].firstName} ${personlist[(4 * i) + j].lastName}</html:link>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <html:link href="${fn:escapeXml(url)}">${personlist[j].firstName} "${personlist[(4 * i) + j].nickname}" ${personlist[(4 * i) + j].lastName}</html:link>
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                            <p style="text-align: center;">
                                                <c:choose>
                                                    <c:when test="${personlist[j].type < 5}">
                                                        <bean:message key="label.date.missing"/>: <bean:message key="month.${personlist[(4 * i) + j].monthMissingOrFound}"/> ${personlist[(4 * i) + j].dayMissingOrFound}, ${personlist[(4 * i) + j].yearMissingOrFound}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <bean:message key="label.date.found"/>: ${personlist[j].monthMissingOrFound} ${personlist[(4 * i) + j].dayMissingOrFound}, ${personlist[(4 * i) + j].yearMissingOrFound}
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                            <p style="text-align: center;">
                                                ${personlist[j].missingFromCity}, ${personlist[j].missingFromProvince}
                                            </p>
                                            <p style="text-align: center;">
                                                <bean:message key="type.${personlist[j].type}"/>
                                            </p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="hiddencell" style="width: 25%;"></div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <p style="text-align: right; text-decoration: underline;">
                        <c:url var="url" scope="page" value="/viewCase.do">
                            <c:param name="action" value="listAllMissing"/>
                        </c:url>
                        <html:link href="${fn:escapeXml(url)}">more...</html:link>
                    </p>
                </div>
                <div id="contentitem">
                    <bean:message key="search.heading"/>
                    
                    <html:form method="get" action="search" styleClass="search">
                        <p class="contentclass">
                            <html:text styleId="searchfield" styleClass="inputclass" property="keyword" size="40" maxlength="2000" value="${keyword}"/>
                            <bean:message key="search.searchbutton"/>
                        </p>
                    </html:form>
                    <html:errors/>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>