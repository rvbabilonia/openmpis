<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="true" %>

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
        <bean:message key="admin.user.title"/>
        <html:base/>
    </head>
    <body>
        <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <bean:message key="admin.user.menu"/>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="admin.user.heading"/>
                    <table id="usertable" class="tableclass">
                        <tr>
                            <th>User ID</th><th>Group ID</th><th>Username</th>
                        </tr>
                        <c:forEach items="${userlist}" var="user">
                        <tr>
                            <td>${user.id}</td><td>${user.groupID}</td><td>${user.username}</td>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>