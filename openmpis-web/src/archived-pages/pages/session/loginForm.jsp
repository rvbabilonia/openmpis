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
        <script type="text/javascript" src="scripts/md5.js"></script>
        <script type="text/javascript" src="scripts/openmpis.js"></script>
        <title><bean:message key="login.title"/></title>
    </head>
    <body>
        <div id="container">
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
                    <html:form styleClass="loginclass" method="post" action="login"
                        onsubmit="javascript: j_password.value = hex_md5(password.value); password.value = convert(password.value);">
                        <html:errors property="login"/>
                        <p class="contentclass">
                            <label id="usernamelabel" class="labelclass" for="usernamefield">
                                <bean:message key="label.username"/>
                            </label>
                            <html:text styleId="usernamefield" styleClass="inputclass" property="j_username" size="20" maxlength="6"/>
                            <html:errors property="j_username"/>
                        </p>
                        <p class="contentclass">
                            <label id="passwordlabel" class="labelclass" for="passwordfield">
                                <bean:message key="label.password"/>
                            </label>
                            <html:password styleId="passwordfield" name="password" styleClass="inputclass" property="password" size="20" maxlength="10" redisplay="false"/>
                            <html:errors property="j_password"/>
                            <html:hidden property="j_password"/>
                        </p>
                        <p class="contentclass">
                            <bean:message key="login.buttons"/>
                        </p>
                    </html:form>
                    <p class="logincontentclass">
                        <bean:message key="login.content"/>
                    </p>

                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>