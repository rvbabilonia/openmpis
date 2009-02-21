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
        <link rel="shortcut icon" href="<html:rewrite page=""/>/images/favicon.ico"/>
        <style type="text/css" media="all">@import "<html:rewrite page=""/>/<bean:message key="global.style"/>";</style>
        <bean:message key="sitemap.title"/>
        <html:base/>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 0}">
                        <bean:message key="admin.global.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.global.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.global.menu" arg0="${currentuser.id}"/>
                    </c:when>
                    <c:otherwise>
                        <bean:message key="global.menu"/>
                    </c:otherwise>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <h1>Sitemap</h1>
                    <h2>Home Page</h2>
                    <ul>
                        <li>Home</li>
                        <li>About</li>
                        <li>Search</li>
                        <li>Feedback</li>
                        <li>Statistics</li>
                        <li>Download</li>
                        <li>Safety Tips</li>
                        <li>Log In</li>
                        <li>Missing Persons</li>
                        <li>Found Persons</li>
                        <li>Unidentified Persons</li>
                        <li>Privacy Policy</li>
                        <li>Terms and Conditions</li>
                        <li>Disclaimer</li>
                        <li>Accessibility</li>
                        <li>Sitemap</li>
                    </ul>
                    <h2>Log In Page</h2>
                    <ul>
                        <li>Home</li>
                        <li>About</li>
                        <li>Search</li>
                        <li>Feedback</li>
                        <li>Statistics</li>
                        <li>Download</li>
                        <li>Safety Tips</li>
                        <li>Log In</li>
                        <li>Password Retrieval</li>
                        <li>Privacy Policy</li>
                        <li>Terms and Conditions</li>
                        <li>Disclaimer</li>
                        <li>Accessibility</li>
                        <li>Sitemap</li>
                    </ul>
                    <h2>Administrator Page</h2>
                    <ul>
                        <li>Home</li>
                        <li>Users</li>
                        <li>Reports</li>
                        <li>Feedbacks</li>
                        <li>Log Out</li>
                        <li>Privacy Policy</li>
                        <li>Terms and Conditions</li>
                        <li>Disclaimer</li>
                        <li>Accessibility</li>
                        <li>Sitemap</li>
                    </ul>
                    <h2>Encoder Page</h2>
                    <ul>
                        <li>Home</li>
                        <li>Users</li>
                        <li>Cases</li>
                        <li>Reports</li>
                        <li>Log Out</li>
                        <li>Privacy Policy</li>
                        <li>Terms and Conditions</li>
                        <li>Disclaimer</li>
                        <li>Accessibility</li>
                        <li>Sitemap</li>
                    </ul>
                    <h2>Investigator Page</h2>
                    <ul>
                        <li>Home</li>
                        <li>Profile</li>
                        <li>Cases</li>
                        <li>Sightings</li>
                        <li>Reports</li>
                        <li>Log Out</li>
                        <li>Privacy Policy</li>
                        <li>Terms and Conditions</li>
                        <li>Disclaimer</li>
                        <li>Accessibility</li>
                        <li>Sitemap</li>
                    </ul>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>