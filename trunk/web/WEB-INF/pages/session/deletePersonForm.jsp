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
        <title><bean:message key="case.title"/></title>
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
                    <bean:message key="case.delete"/>

                    <html:form method="post" action="viewPerson" styleClass="adduserclass">
                        <p class="contentclass">
                            <html:hidden property="action" value="deletePerson"/>
                            <html:hidden property="id"/>
                        </p>
                        <p class="contentclass">
                            <bean:message key="case.content.confirm" arg0="${personForm.firstName}" arg1="${personForm.nickname}" arg2="${personForm.lastName}"/>
                            <c:if test="${personForm.relativeId != 0}">
                                <bean:message key="case.content.confirm.relative" arg0="${personForm.relativeFirstName}" arg1="${personForm.relativeLastName}"/>
                            </c:if>
                            <c:if test="${personForm.abductorId != 0}">
                                <bean:message key="case.content.confirm.abductor" arg0="${personForm.abductorFirstName}" arg1="${personForm.abductorNickname}" arg2="${personForm.abductorLastName}"/>
                            </c:if>
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
                            <bean:message key="case.delete.confirm.buttons"/>
                        </p>
                    </html:form>

                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>