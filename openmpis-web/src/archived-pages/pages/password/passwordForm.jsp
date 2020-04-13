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
        <title><bean:message key="password.title"/></title>
    </head>
    <body>
        <div id="container">
            <tag:header/>
        
            <!-- Menu -->
            <div id="menu">
                <bean:message key="password.menu"/>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="password.heading"/>
                    
                    <html:form method="post" action="resetPassword" styleClass="login">
                        <html:errors property="question"/>
                        <html:errors property="user"/>
                        <p class="contentclass">
                            <label id="usernamelabel" class="labelclass" for="usernamefield">
                                <bean:message key="label.username"/>
                            </label>
                            <html:text styleId="usernamefield" styleClass="inputclass" property="username" size="37" maxlength="6"/>
                            <html:errors property="username"/>
                        </p>
                        <p class="contentclass">
                            <label id="questionlabel" class="labelclass" for="questionfield">
                                <bean:message key="label.question"/>
                            </label>
                            <html:select property="question" styleId="questionfield" styleClass="selectclass">
                                <html:option value="0" styleId="optionfield0" styleClass="optionclass"><bean:message key="question.0"/></html:option>
                                <html:option value="1" styleId="optionfield1" styleClass="optionclass"><bean:message key="question.1"/></html:option>
                                <html:option value="2" styleId="optionfield2" styleClass="optionclass"><bean:message key="question.2"/></html:option>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="answerlabel" class="labelclass" for="answerfield">
                                <bean:message key="label.answer"/>
                            </label>
                            <html:text styleId="answerfield" styleClass="inputclass" property="answer" size="37" maxlength="30"/>
                            <html:errors property="answer"/>
                        </p>
                        <p class="contentclass">
                            <bean:message key="password.buttons"/>
                        </p>
                    </html:form>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>