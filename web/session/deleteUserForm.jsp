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
        <bean:message key="global.icon"/>
        <bean:message key="global.style"/>
        <bean:message key="global.script"/>
        <c:choose>
            <c:when test="${currentuser.code == 0}">
                <bean:message key="admin.user.title"/>
            </c:when>
            <c:when test="${currentuser.code == 1}">
                <bean:message key="encoder.user.title"/>
            </c:when>
            <c:when test="${currentuser.code == 2}">
                <bean:message key="investigator.user.title"/>
            </c:when>
        </c:choose>
        <html:base/>
    </head>
    <body>
        <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.code == 0}">
                        <bean:message key="admin.user.menu"/>
                    </c:when>
                    <c:when test="${currentuser.code == 1}">
                        <bean:message key="encoder.user.menu"/>
                    </c:when>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${currentuser.code == 0}">
                            <bean:message key="admin.user.delete"/>
                        </c:when>
                        <c:when test="${currentuser.code == 1}">
                            <bean:message key="encoder.user.delete"/>
                        </c:when>
                    </c:choose>
                    
                    <html:form method="post" action="viewUser" styleClass="adduserclass">
                        <html:hidden property="action" value="delete"/>
                        <html:hidden property="id" value="${userForm.id}"/>
                        <p class="contentclass">
                            <bean:message key="user.content.confirm" arg0="${userForm.username}"/>
                        </p>
                        <p class="contentclass">
                            <label id="codelabel" class="labelclass" for="codefield"><bean:message key="user.label.code"/></label>
                            <html:text styleId="codefield" styleClass="inputclass" property="code" maxlength="4" readonly="true"/>
                        </p>
                        <p class="contentclass">
                            <label id="usercodelabel" class="labelclass" for="usercodefield"><bean:message key="user.label.code.verify"/></label>
                            <html:text styleId="usercodefield" styleClass="inputclass" property="userCode" maxlength="4"/>
                            <html:errors property="usercode"/>
                        </p>
                        <p class="contentclass">
                            <bean:message key="user.delete.confirm.buttons" arg0="${userForm.id}"/>
                        </p>
                    </html:form>
                    
                </div>
            </div>
        
        <tag:footer/>
    </body>
</html:html>