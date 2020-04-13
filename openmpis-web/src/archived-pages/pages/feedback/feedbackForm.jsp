<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

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
        <title><bean:message key="feedback.title"/></title>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <bean:message key="feedback.menu"/>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="feedback.heading"/>
                    <p class="contentclass">
                        <label id="addresslabel" class="labelclass">
                            <bean:message key="label.address"/>
                        </label>
                        <bean:message key="feedback.address"/>
                    </p>
                    <p class="contentclass">
                        <label id="numberlabel" class="labelclass">
                            <bean:message key="label.number"/>
                        </label>
                        <bean:message key="feedback.number"/>
                    </p>

                    <html:form method="post" action="feedback" styleClass="feedbackclass">
                        <p class="contentclass">
                            <label id="firstnamelabel" class="labelclass" for="firstnamefield">
                                <bean:message key="label.firstname"/>
                            </label>
                            <html:text styleId="firstnamefield" styleClass="inputclass" property="firstName" size="66" maxlength="30"/>
                            <html:errors property="firstname"/>
                        </p>
                        <p class="contentclass">
                            <label id="lastnamelabel" class="labelclass" for="lastnamefield">
                                <bean:message key="label.lastname"/>
                            </label>
                            <html:text styleId="lastnamefield" styleClass="inputclass" property="lastName" size="66" maxlength="30"/>
                            <html:errors property="lastname"/>
                        </p>
                        <p class="contentclass">
                            <label id="emaillabel" class="labelclass" for="emailfield">
                                <bean:message key="label.email"/>
                            </label>
                            <html:text styleId="emailfield" styleClass="inputclass" property="email" size="66" maxlength="30"/>
                            <html:errors property="email"/>
                        </p>
                        <p class="contentclass">
                            <label id="subjectlabel" class="labelclass" for="subjectfield">
                                <bean:message key="label.subject"/>
                            </label>
                            <html:text styleId="subjectfield" styleClass="inputclass" property="subject" size="66" maxlength="60"/>
                            <html:errors property="subject"/>
                        </p>
                        <p class="contentclass">
                            <label id="messagelabel" class="labelclass" for="messagefield">
                                <bean:message key="label.message"/>
                            </label>
                            <html:textarea styleId="messagefield" styleClass="textareaclass" property="message" cols="50" rows="10"/>
                            <html:errors property="message"/>
                        </p>
                        <p class="contentclass">
                            <bean:message key="feedback.buttons"/>
                        </p>
                    </html:form>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>