<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

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
        <link rel="shortcut icon" href="images/favicon.ico"/>
        <style type="text/css" media="all">@import "<bean:message key="global.style"/>";</style>
        <bean:message key="report.title"/>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <bean:message key="investigator.report.menu"/>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${(action eq 'newReport') || (action eq 'addReport')}">
                            <bean:message key="report.add"/>
                        </c:when>
                        <c:when test="${(action eq 'editReport') || (action eq 'viewReport')}">
                            <bean:message key="report.edit"/>
                        </c:when>
                    </c:choose>
                    
                    <html:form method="post" action="viewReport" styleClass="reportclass">
                        <p class="contentclass">
                            <c:choose>
                                <c:when test="${(action eq 'newReport') || (action eq 'addReport')}">
                                    <html:hidden property="action" value="addReport"/>
                                    <html:hidden property="personid" value="${personid}"/>
                                    <html:hidden property="personId"/>
                                </c:when>
                                <c:when test="${(action eq 'editReport') || (action eq 'viewReport')}">
                                    <html:hidden property="action" value="editReport"/>
                                    <html:hidden property="personid" value="${personid}"/>
                                </c:when>
                            </c:choose>
                        </p>
                        <c:if test="${reportForm.id > 0}">
                            <p class="contentclass">
                                <label id="idlabel" class="labelclass" for="idfield">
                                    <bean:message key="label.id"/>
                                </label>
                                <html:text styleId="idfield" styleClass="inputclass" property="id" readonly="true"/>
                            </p>
                        </c:if>
                        <p class="contentclass">
                            <label id="personidlabel" class="labelclass" for="personidfield">
                                <bean:message key="label.for"/>
                            </label>
                            ${firstname}
                            <c:if test="${nickname != null}">
                                ${nickname}
                            </c:if>
                            ${lastname}
                            <!--html:text styleId="personidfield" styleClass="inputclass" property="personId" readonly="true"/-->
                        </p>
                        <p class="contentclass">
                            <label id="reportlabel" class="labelclass" for="reportfield">
                                <bean:message key="label.report"/>
                            </label>
                            <html:textarea styleId="reportfield" styleClass="textareaclass" property="report" cols="50" rows="10"/>
                            <html:errors property="report"/>
                        </p>
                        <p class="contentclass">
                            <c:if test="${currentuser.groupId == 2}">
                                <c:choose>
                                    <c:when test="${(action eq 'newReport') || (action eq 'addReport')}">
                                        <bean:message key="report.add.buttons"/>
                                    </c:when>
                                    <c:when test="${(action eq 'editReport') || (action eq 'viewReport')}">
                                        <bean:message key="report.delete.buttons"/>
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </p>
                    </html:form>
                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>