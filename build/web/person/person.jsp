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
        <bean:message key="view.title"/>
        <html:base/>
    </head>
    <body>
        <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <bean:message key="view.menu"/>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="top-left">
                    <div id="top-right"></div>
                </div>
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${format eq 'genbank'}">
                            <bean:message key="view.backbutton"/>
                            <bean:message key="view.downloadgenbank" arg0="${id}"/>
                        </c:when>
                        <c:when test="${format eq 'fasta'}">
                            <bean:message key="view.backbutton"/>
                            <bean:message key="view.downloadfasta" arg0="${id}"/>
                        </c:when>
                    </c:choose>
                    <pre>${sequence}</pre>
                    <c:choose>
                        <c:when test="${format eq 'genbank'}">
                            <bean:message key="view.backbutton"/>
                            <bean:message key="view.downloadgenbank" arg0="${id}"/>
                        </c:when>
                        <c:when test="${format eq 'fasta'}">
                            <bean:message key="view.backbutton"/>
                            <bean:message key="view.downloadfasta" arg0="${id}"/>
                        </c:when>
                    </c:choose>
                </div>
                <div id="bottom-left">
                    <div id="bottom-right"></div>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>