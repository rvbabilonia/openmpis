<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="true" %>

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
        <bean:message key="global.script"/>
        <bean:message key="global.icon"/>
        <bean:message key="global.style"/>
        <bean:message key="login.title"/>
        <html:base/>
    </head>
    <body>
        <tag:header/>
        
        <!-- Menu -->
        <div id="menu">
            <bean:message key="login.menu"/>
        </div>
        
        <!-- Content -->
        <div id="content">
            <div id="contentitem">
                <bean:message key="login.heading"/>
                <noscript>
                    <bean:message key="error.javascript.disabled"/>
                </noscript>
                <html:form styleId="loginform" styleClass="loginclass" method="post" action="loginBean"
                    onsubmit="javascript:j_password.value = hex_md5(password.value); password.value = convert(password.value);">
                    <c:if test="${usererror == 1}">
                        <bean:message key="error.login.invalid"/>
                    </c:if>
                    <p>
                        <label id="usernamelabel" class="labelclass" for="usernamefield"><bean:message key="login.username"/></label>
                        <html:text styleId="usernamefield" styleClass="inputclass" property="j_username" size="20" maxlength="6" value="${j_username}"/>
                        <html:errors property="j_username"/>
                    </p>
                    <p>
                        <label id="passwordlabel" class="labelclass" for="passwordfield"><bean:message key="login.password"/></label>
                        <html:password styleId="passwordfield" name="password" styleClass="inputclass" property="password" size="20" maxlength="10" value="" redisplay="false"/>
                        <html:errors property="j_password"/>
                        <html:hidden property="j_password"/>
                    </p>
                    <p>
                        <bean:message key="login.buttons"/>
                    </p>
                </html:form>
                <p>Forgot password? Click <html:link page="/viewPassword.do">here</html:link>.</p>
            </div>
        </div>
        
        <tag:footer/>
    </body>
</html:html>