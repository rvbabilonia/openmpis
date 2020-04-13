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
        <title><bean:message key="relative.title"/></title>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.case.menu"/>
                    </c:when>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="relative.delete"/>

                    <c:choose>
                        <c:when test="${personcount > 0}">
                            <bean:message key="relative.content.locked" arg0="${personcount}"/>
                        </c:when>
                        <c:otherwise>
                            <html:form method="post" action="viewRelative" styleClass="adduserclass">
                                <p class="contentclass">
                                    <html:hidden property="action" value="deleteRelative"/>
                                    <html:hidden property="id"/>
                                </p>
                                <p class="contentclass">
                                    <bean:message key="relative.content.confirm" arg0="${relativeForm.firstName}" arg1="${relativeForm.lastName}"/>
                                </p>
                                <p class="contentclass">
                                    <label id="codelabel" class="labelclass" for="codefield">
                                        <bean:message key="label.code"/>
                                    </label>
                                    <html:text styleId="codefield" styleClass="inputclass" property="code" maxlength="4" readonly="true"/>
                                </p>
                                <p class="contentclass">
                                    <label id="usercodelabel" class="labelclass" for="usercodefield">
                                        <bean:message key="label.code.verify"/>
                                    </label>
                                    <html:text styleId="usercodefield" styleClass="inputclass" property="userCode" maxlength="4"/>
                                    <html:errors property="usercode"/>
                                </p>
                                <p class="contentclass">
                                    <bean:message key="relative.delete.confirm.buttons" arg0="${relativeForm.id}"/>
                                </p>
                            </html:form>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>