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
        <bean:message key="accessibility.title"/>
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
                    <h1>Accessibility</h1>
                    <p class="contentclass">
                        The Open Source Missing Persons Information System is committed to providing information
                        that is accessible by all users. This Web site has been built to comply with guidelines
                        laid out by the Web Accessibility Initiative (WAI), conforming to AAA Standards.
                    </p>
                    <p class="contentclass">
                        If you have any suggestions which will help to improve your experience of this site,
                        I would be very grateful to hear from you. Please contact me at
                        <a href="mailto:rvbabilonia@gmail.com?subject=Suggestions">rvbabilonia@gmail.com</a>.
                    </p>
                    <h1>Access Keys</h1>
                    <p class="contentclass">
                        Access keys allow navigation to places on this site using the following keyboard shortcuts:
                    </p>
                    <ul>
                        <li>accesskey "1" = Home</li>
                        <li>accesskey "2" = About</li>
                        <li>accesskey "3" = Search</li>
                        <li>accesskey "4" = Feedback</li>
                        <li>accesskey "5" = Statistics</li>
                        <li>accesskey "6" = Safety Tips</li>
                        <li>accesskey "7" = Missing Persons</li>
                        <li>accesskey "8" = Found Persons</li>
                        <li>accesskey "9" = Unidentified Persons</li>
                        <li>accesskey="a" = Privacy Policy</li>
                        <li>accesskey="b" = Terms and Conditions</li>
                        <li>accesskey="c" = Disclaimer</li>
                        <li>accesskey="d" = Accessibility</li>
                        <li>accesskey="e" = Sitemap</li>
                    </ul>
                    <h1>To use access keys:</h1>
                    <p class="contentclass">
                        On Internet Explorer, simply hold down the the Alt key and press the number you want.
                    </p>
                    <p class="contentclass">
                        On Safari, simply hold down the the Ctrl key and press the number you want.
                    </p>
                    <p class="contentclass">
                        On Firefox, simply hold down the the Shift+Alt key and press the number you want.
                    </p>
                    <p class="contentclass">
                        Access keys are not supported on some older versions of Web browsers.
                    </p>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>