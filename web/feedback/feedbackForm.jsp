<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="false" %>

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
        <bean:message key="global.icon"/>
        <bean:message key="global.style"/>
        <bean:message key="feedback.title"/>
        <html:base/>
    </head>
    <body>
        <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <bean:message key="feedback.menu"/>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="feedback.heading"/>
                    <p>
                        <label id="addresslabel" class="labelclass"><bean:message key="feedback.label.address"/></label>
                        <bean:message key="feedback.address"/>
                    </p>
                    <p>
                        <label id="numberlabel" class="labelclass"><bean:message key="feedback.label.number"/></label>
                        <bean:message key="feedback.number"/>
                    </p>
                    <html:form styleId="feedbackform" method="post" action="feedbackBean" styleClass="feedbackclass">
                        <p>
                            <label id="firstnamelabel" class="labelclass" for="firstnamefield"><bean:message key="feedback.label.firstname"/></label>
                            <html:text styleId="firstnamefield" styleClass="inputclass" property="firstname" size="66" maxlength="30" value="${firstname}"/>
                            <html:errors property="firstname"/>
                        </p>
                        <p>
                            <label id="lastnamelabel" class="labelclass" for="lastnamefield"><bean:message key="feedback.label.lastname"/></label>
                            <html:text styleId="lastnamefield" styleClass="inputclass" property="lastname" size="66" maxlength="30" value="${lastname}"/>
                            <html:errors property="lastname"/>
                        </p>
                        <p>
                            <label id="emaillabel" class="labelclass" for="emailfield"><bean:message key="feedback.label.email"/></label>
                            <html:text styleId="emailfield" styleClass="inputclass" property="email" size="66" maxlength="30" value="${email}"/>
                            <html:errors property="email"/>
                        </p>
                        <p>
                            <label id="subjectlabel" class="labelclass" for="subjectfield"><bean:message key="feedback.label.subject"/></label>
                            <html:text styleId="subjectfield" styleClass="inputclass" property="subject" size="66" maxlength="60" value="${subject}"/>
                            <html:errors property="subject"/>
                        </p>
                        <p>
                            <label id="messagelabel" class="labelclass" for="messagefield"><bean:message key="feedback.label.message"/></label>
                            <html:textarea styleId="messagefield" styleClass="textareaclass" property="message" cols="50" rows="10" value="${message}"/>
                            <html:errors property="message"/>
                        </p>
                        <p>
                            <bean:message key="feedback.buttons"/>
                        </p>
                    </html:form>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>