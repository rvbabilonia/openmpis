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
        <link rel="shortcut icon" href="/images/favicon.ico"/>
        <style type="text/css" media="all">@import "<bean:message key="global.style"/>";</style>
        <bean:message key="about.title"/>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <bean:message key="about.menu"/>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="about.heading"/>
                    <p class="contentclass">
                        OpenMPIS is the Open Source Missing Persons Information System. It addresses
                        1.) the lack of a centralized service for disseminating advisories, 2.) the
                        lack of a joint effort for finding missing Filipinos and 3.) the lack of a
                        free and open source Missing Persons Information System.
                    </p>
                    <p class="contentclass">
                        The purposes of this research are 1.) to implement an information system
                        that will integrate and consolidate all government efforts in a.) finding
                        missing, abducted, runaway, throwaway and abandoned persons and b.)
                        reporting found and unidentified persons; 2.) to inform and encourage
                        citizens to help look for these missing persons or abductors; and 3.) to
                        promote awareness for the public in keeping their relatives safe.
                    </p>
                    <p class="contentclass">
                        The target beneficiaries of this system are 1.) the proposed Missing Persons
                        Council or Commission on Missing Persons, 2.) the Philippine National Police,
                        3.) the National Bureau of Investigation, 4.) the Department of Social
                        Welfare and Development, 5.) the missing and found persons, 6.) their
                        relatives and 7.) the general public.
                    </p>
                    <p class="contentclass">
                        OpenMPIS was developed by Rey Vincent Babilonia in partial fulfillment of
                        the requirements for the degree Master of Information Systems from the
                        University of the Philippines - Open University, Los Baños, Laguna.
                    </p>
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>