<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>

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
        <bean:message key="search.title"/>
        <html:base/>
    </head>
    <body>
        <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <bean:message key="search.menu"/>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <html:form method="get" action="searchBean.do" styleClass="search">
                        <html:text styleClass="input" property="keyword" size="40" maxlength="2000" value="${keyword}"/>
                        <bean:message key="search.searchbutton"/>
                    </html:form>
                    <br/>
                    <c:choose>
                        <c:when test="${pagecount > 1}">
                            <bean:message key="search.start"/>
                            <c:forEach items="${sequencelist}" var="sequence">
                                <bean:message key="search.iteration" arg0="${sequence[0]}" arg1="${sequence[1]}"/>
                            </c:forEach>
                            <bean:message key="search.end"/>
                            <br/>
                            <div id="pagination">
                                <c:choose>
                                    <c:when test="${currentpage > 1}">
                                        <a href="searchBean.do?keyword=${keyword}&pn=${currentpage - 1}"><bean:message key="search.previous"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <bean:message key="search.previous"/>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${currentpage != pagecount}">
                                        <a href="searchBean.do?keyword=${keyword}&pn=${currentpage + 1}"><bean:message key="search.next"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <bean:message key="search.next"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <br/>
                            <br/>
                            <html:form method="get" action="searchBean.do" styleClass="search">
                                <html:text styleClass="input" property="keyword" size="40" maxlength="2000" value="${keyword}"/>
                                <bean:message key="search.searchbutton"/>
                            </html:form>
                        </c:when>
                        <c:when test="${pagecount == 1}">
                            <bean:message key="search.start"/>
                            <c:forEach items="${sequencelist}" var="sequence">
                                <bean:message key="search.iteration" arg0="${sequence[0]}" arg1="${sequence[1]}"/>
                            </c:forEach>
                            <bean:message key="search.end"/>
                            <br/>
                            <html:form method="get" action="searchBean.do" styleClass="search">
                                <html:text styleClass="input" property="keyword" size="40" maxlength="2000" value="${keyword}"/>
                                <bean:message key="search.searchbutton"/>
                            </html:form>
                        </c:when>
                        <c:otherwise>
                            <bean:message key="search.empty"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>