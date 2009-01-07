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
        <bean:message key="password.title"/>
        <html:base/>
    </head>
    <body>
        <tag:header/>
        
        <!-- Menu -->
        <div id="menu">
            <bean:message key="password.menu"/>
        </div>
        
        <!-- Content -->
        <div id="content">
            <div id="contentitem">
                <bean:message key="password.heading"/>
                <noscript>
                    <bean:message key="error.javascript.disabled"/>
                </noscript>
                <html:form styleId="passwordform" method="post" action="passwordBean" styleClass="login">
                    <c:if test="${error == 1}">
                        <bean:message key="error.question.invalid"/>
                    </c:if>
                    <p>
                        <label id="usernamelabel" class="labelclass" for="usernamefield"><bean:message key="password.username"/></label>
                        <html:text styleId="usernamefield" styleClass="inputclass" property="username" size="37" maxlength="6" value="${username}"/>
                        <html:errors property="username"/>
                    </p>
                    <p>
                        <label id="questionlabel" class="labelclass" for="questionfield"><bean:message key="password.question"/></label>
                        <html:select property="question" styleId="questionfield" styleClass="selectclass">
                            <html:option value="0" styleId="optionfield0" styleClass="optionclass"><bean:message key="question.0"/></html:option>
                            <html:option value="1" styleId="optionfield1" styleClass="optionclass"><bean:message key="question.1"/></html:option>
                            <html:option value="2" styleId="optionfield2" styleClass="optionclass"><bean:message key="question.2"/></html:option>
                        </html:select>
                    </p>
                    <p>
                        <label id="answerlabel" class="labelclass" for="answerfield"><bean:message key="password.answer"/></label>
                        <html:text styleId="answerfield" styleClass="inputclass" property="answer" size="37" maxlength="10" value="${answer}"/>
                        <html:errors property="answer"/>
                    </p>
                    <p>
                        <bean:message key="password.buttons"/>
                    </p>
                </html:form>
            </div>
        </div>
        
        <tag:footer/>
    </body>
</html:html>