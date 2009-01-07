<%-- 
    Document   : footer
    Author     : Rey Vincent Babilonia
--%>

<%@ tag description="This is the footer and it contains the W3C Validators and Google Analytics." pageEncoding="UTF-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>

            <!-- W3C Validators -->
            <div id="footer">
                <div id="copyright">
                    <bean:message key="global.footer.copyright"/>
                </div>
                <div id="link">
                    <bean:message key="global.footer.link"/>
                </div>
            </div>
        </div>
    
        <!-- Google Analytics -->
        <!--
        <script type="text/javascript">
            var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
            document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
        </script>
        <script type="text/javascript">
            var pageTracker = _gat._getTracker("UA-2141098-2");
            pageTracker._initData();
            pageTracker._trackPageview();
        </script>
        -->