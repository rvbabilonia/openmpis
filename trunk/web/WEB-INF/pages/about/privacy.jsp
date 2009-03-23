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
        <title><bean:message key="privacy.title"/></title>
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
                    <h1>Privacy Policy</h1>
                    <p class="contentclass">
                        The Open Source Missing Persons Information System is committed to protecting your
                        privacy online. We are required to notify users about the data processing practices
                        used on the Open Source Missing Persons Information System.
                    </p>
                    <h1>Collection of Information</h1>
                    <p class="contentclass">
                        At times, in connection with certain service offered through this site, you may be
                        asked to supply personal information such as name, email address, postal address and
                        telephone number.
                    </p>
                    <p class="contentclass">
                        For reporting purposes and to prevent misuse, our server automatically records
                        information that your browser sends whenever you visit a Web site. These server logs
                        may include information such as your Web request, Internet Protocol address, browser
                        type, browser language, the date and time of your request and one or more cookies
                        that may uniquely identify your browser.
                    </p>
                    <p class="contentclass">
                        When you send email or other communications to the Open Source Missing Persons
                        Information System, we may retain those communications in order to process your
                        inquiries, respond to your requests and improve our services.
                    </p>
                    <p class="contentclass">
                        To track user transactions or sessions, we send cookies – a small file containing a
                        string of characters – to your computer that uniquely identifies your browser.
                        Cookies are specific to the server that created them and cannot be used to track
                        your movements around the web. They do not personally identify users and passwords
                        are not stored in them. If you do not want cookies to be stored on your PC, you can
                        change your browser to prevent it.
                    </p>
                    <h1>Use of Information</h1>
                    <p class="contentclass">
                        This information will be used to identify you enabling us to send out information or
                        supply a service and to increase our understanding of how this site is used.
                    </p>
                    <h1>Sharing of Information</h1>
                    <p class="contentclass">
                        We do not share any personal information about visitors to this Web site except what
                        they voluntarily choose to give us via sightings. We do not pass any personal data to
                        outside organisations except with express consent.
                    </p>
                    <h1>Security of Information</h1>
                    <p class="contentclass">
                        We take appropriate security measures to protect against unauthorized access to or
                        unauthorized alteration, disclosure or destruction of data. These include internal
                        reviews of our data collection, storage and processing practices and security
                        measures, as well as physical security measures to guard against unauthorized access
                        to systems where we store data.
                    </p>
                    <p class="contentclass">
                        We restrict access to personal information to our employees and agents who need to
                        know that information in order to operate, develop or improve our services. These
                        individuals are bound by confidentiality obligations and may be subject to
                        discipline, including termination and criminal prosecution, if they fail to meet
                        these obligations.
                    </p>
                    <h1>Integrity of Data</h1>
                    <p class="contentclass">
                        The Open Source Missing Persons Information System processes personal information
                        only for the purposes for which it was collected and in accordance with this Privacy
                        Policy or any applicable service-specific privacy notice. We review our data
                        collection, storage and processing practices to ensure that we only collect, store
                        and process the personal information needed to provide or improve our services. We
                        take reasonable steps to ensure that the personal information we process is accurate,
                        complete, and current, but we depend on our users to update or correct their personal
                        information whenever necessary.
                    </p>
                    <h1>Enforcement</h1>
                    <p class="contentclass">
                        The Open Source Missing Persons Information System regularly reviews its compliance
                        with this Privacy Policy. Please feel free to direct any questions or concerns
                        regarding this Privacy Policy or the Open Source Missing Persons Information System
                        treatment of personal information by contacting us through this Web site.
                    </p>
                    <h1>Changes to Privacy Policy</h1>
                    <p class="contentclass">
                        Please note that this Privacy Policy may change from time to time. We will not
                        reduce your rights under this Privacy Policy without your explicit consent, and
                        we expect most such changes will be minor. Regardless, we will post any Privacy
                        Policy changes on this page and, if the changes are significant, we will provide
                        a more prominent notice (including, for certain services, email notification of
                        Privacy Policy changes). Each version of this Privacy Policy will be identified
                        at the bottom of the page by its effective date, and we will also keep prior versions
                        of this Privacy Policy in an archive for your review.
                    </p>
                    <p class="contentclass">
                        Last update: 25 January 2009
                    </p>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>