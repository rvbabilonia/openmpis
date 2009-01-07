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
        <bean:message key="terms.title"/>
        <html:base/>
    </head>
    <body>
        <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <bean:message key="global.menu"/>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <h1>Terms and Conditions</h1>
                    <p class="contentclass">
                        Use of this site is provided by the Open Source Missing Persons Information System
                        and is subject to the following terms and conditions. The Open Source Missing
                        Persons Information System reserves the right to change the Terms and Conditions at
                        any time by posting changes online.
                    </p>
                    <h2>1. Use of site</h2>
                    <p class="contentclass">
                        This site contains information for personal use only, and must not be reproduced
                        on any other Web site or for commercial gain.
                    </p>
                    <h2>2. Links</h2>
                    <p class="contentclass">
                        The Open Source Missing Persons Information System must approve links to the site
                        prior to their creation. Please use the email address below.
                    </p>
                    <h2>3. Copyright and Trade Marks</h2>
                    <p class="contentclass">
                        The Open Source Missing Persons Information System or its content suppliers own the intellectual
                        property rights of any material used on the site including text, video, photographs and pictures.
                        Unauthorised use including reproduction, storage, modification, distribution or publication
                        without prior consent is prohibited.
                    </p>
                    <p class="contentclass">
                        The names, logos and all related design marks, slogans, product and service names are the trade
                        names, trade marks or service marks of the Open Source Missing Persons Information System and
                        must not be used without my consent.
                    </p>
                    <p class="contentclass">
                        If you have any queries or complaints about these Terms and Conditions, please email
                        <a href="mailto:rvbabilonia@gmail.com?subject=Queries">rvbabilonia@gmail.com</a>.
                    </p>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>