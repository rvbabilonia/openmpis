<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/strict.dtd">

<html:html locale="true">
    <head>
        <meta http-equiv="content-type" content="text/html">
        <meta name="author" content="Rey Vincent Babilonia">
        <meta name="keywords" content="missing, filipino, person, openmpis">
        <meta name="description" content="This is the Web page for the OpenMPIS.">
        <meta name="robots" content="all">
        <bean:message key="global.icon"/>
        <bean:message key="global.style"/>
        <bean:message key="home.title"/>
        <html:base/>
    </head>
    <body>
        <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <bean:message key="home.menu"/>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentleft">
                    <bean:message key="home.left"/>
                </div>
                <div id="contentcenter">
                    <bean:message key="home.center"/>
                </div>
                <div id="contentright">
                    <bean:message key="home.right"/>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>