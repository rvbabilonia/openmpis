<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
        <script type="text/javascript" src="scripts/md5.js"></script>
        <title><bean:message key="user.title"/></title>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 0}">
                        <bean:message key="admin.user.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.user.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.user.menu"/>
                    </c:when>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <bean:message key="user.edit"/>
                    <noscript>
                        <bean:message key="error.javascript.disabled"/>
                    </noscript>
                    
                    <html:form method="post" action="viewUser" styleClass="adduserclass"
                        onsubmit="javascript: password.value = hex_md5(password.value); retype.value = hex_md5(retype.value); ">
                        <p class="contentclass">
                            <html:hidden property="action" value="editUser"/>
                        </p>
                        <p class="contentclass">
                            <label id="idlabel" class="labelclass" for="idfield">
                                <bean:message key="label.id"/>
                            </label>
                            <html:text styleId="idfield" styleClass="inputclass" property="id" readonly="true" maxlength="6"/>
                            <html:errors property="id"/>
                        </p>
                        <p class="contentclass">
                            <label id="groupidlabel" class="labelclass" for="groupidfield">
                                * <bean:message key="label.group"/>
                            </label>
                            <html:select property="groupId" styleId="groupidfield" styleClass="selectclass">
                                <c:choose>
                                    <c:when test="${currentuser.groupId == 0}">
                                        <html:option value="0" styleId="groupfield0" styleClass="optionclass"><bean:message key="group.0"/></html:option>
                                        <html:option value="1" styleId="groupfield1" styleClass="optionclass"><bean:message key="group.1"/></html:option>
                                    </c:when>
                                    <c:when test="${currentuser.id == userForm.id}">
                                        <html:option value="${userForm.groupId}" styleId="optionfield${userForm.groupId}" styleClass="optionclass"><bean:message key="group.${userForm.groupId}"/></html:option>
                                    </c:when>
                                    <c:otherwise>
                                        <html:option value="2" styleId="optionfield2" styleClass="optionclass"><bean:message key="group.2"/></html:option>
                                    </c:otherwise>
                                </c:choose>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="usernamelabel" class="labelclass" for="usernamefield">
                                <bean:message key="label.username"/>
                            </label>
                            <html:text styleId="usernamefield" styleClass="inputclass" property="username" maxlength="6" readonly="true"/>
                            <html:errors property="username"/>
                        </p>
                        <c:if test="${userForm.id == currentuser.id}">
                            <p class="contentclass">
                                <label id="passwordlabel" class="labelclass" for="passwordfield">
                                    <bean:message key="label.password"/>
                                </label>
                                <html:password styleId="passwordfield" styleClass="inputclass" property="password" maxlength="10" redisplay="false"/>
                                <html:errors property="password"/>
                            </p>
                            <p class="contentclass">
                                <label id="retypelabel" class="labelclass" for="retypefield">
                                    <bean:message key="label.password.verify"/>
                                </label>
                                <html:password styleId="retypefield" styleClass="inputclass" property="retype" maxlength="10" redisplay="false"/>
                            </p>
                        </c:if>
                        <p class="contentclass">
                            <label id="firstnamelabel" class="labelclass" for="firstnamefield">
                                * <bean:message key="label.firstname"/>
                            </label>
                            <html:text styleId="firstnamefield" styleClass="inputclass" property="firstName" maxlength="30"/>
                            <html:errors property="firstname"/>
                        </p>
                        <p class="contentclass">
                            <label id="middlenamelabel" class="labelclass" for="middlenamefield">
                                * <bean:message key="label.middlename"/>
                            </label>
                            <html:text styleId="middlenamefield" styleClass="inputclass" property="middleName" maxlength="30"/>
                            <html:errors property="middlename"/>
                        </p>
                        <p class="contentclass">
                            <label id="lastnamelabel" class="labelclass" for="lastnamefield">
                                * <bean:message key="label.lastname"/>
                            </label>
                            <html:text styleId="lastnamefield" styleClass="inputclass" property="lastName" maxlength="30"/>
                            <html:errors property="lastname"/>
                        </p>
                        <p class="contentclass">
                            <label id="birthdatelabel" class="labelclass" for="monthfield">
                                * <bean:message key="label.date.birth"/>
                            </label>
                            <html:select styleId="monthfield" styleClass="monthselectclass" property="birthMonth">
                                <c:forEach begin="1" end="12" step="1" var="i">
                                    <html:option value="${i}" styleId="monthfield${i}" styleClass="monthoptionclass"><bean:message key="month.${i}"/></html:option>
                                </c:forEach>
                            </html:select>
                            <html:select styleId="dayfield" styleClass="dayselectclass" property="birthDay">
                                <c:forEach begin="1" end="31" step="1" var="i">
                                    <html:option value="${i}" styleId="dayfield${i}" styleClass="dayoptionclass">${i}</html:option>
                                </c:forEach>
                            </html:select>
                            <jsp:useBean id="currentDate" class="java.util.Date"/>
                            <fmt:formatDate var="currentYear" value="${currentDate}" pattern="yyyy"/>
                            <html:select styleId="yearfield" styleClass="yearselectclass" property="birthYear">
                                <c:forEach begin="${currentYear - 60}" end="${currentYear - 21}" step="1" var="i">
                                    <html:option value="${i}" styleId="yearfield${i}" styleClass="yearoptionclass">${i}</html:option>
                                </c:forEach>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="designationlabel" class="labelclass" for="designationfield">
                                * <bean:message key="label.designation"/>
                            </label>
                            <html:text styleId="designationfield" styleClass="inputclass" property="designation" maxlength="30"/>
                            <html:errors property="designation"/>
                        </p>
                        <p class="contentclass">
                            <label id="agencylabel" class="labelclass" for="agencyfield">
                                * <bean:message key="label.agency"/>
                            </label>
                            <html:text styleId="agencyfield" styleClass="inputclass" property="agency" maxlength="60"/>
                            <html:errors property="agency"/>
                        </p>
                        <p class="contentclass">
                            <label id="emaillabel" class="labelclass" for="emailfield">
                                * <bean:message key="label.email"/>
                            </label>
                            <html:text styleId="emailfield" styleClass="inputclass" property="email" maxlength="60"/>
                            <html:errors property="email"/>
                        </p>
                        <p class="contentclass">
                            <label id="numberlabel" class="labelclass" for="numberfield">
                                * <bean:message key="label.number"/>
                            </label>
                            <html:text styleId="numberfield" styleClass="inputclass" property="number" maxlength="30"/>
                            <html:errors property="number"/>
                        </p>
                        <p class="contentclass">
                            <label id="ipaddresslabel" class="labelclass" for="ipaddressfield">
                                <bean:message key="label.ipaddress"/>
                            </label>
                            <html:text styleId="ipaddressfield" styleClass="inputclass" property="ipAddress" readonly="true" maxlength="30"/>
                            <html:errors property="ipaddress"/>
                        </p>
                        <p class="contentclass">
                            <label id="lastloginlabel" class="labelclass" for="lastloginfield">
                                <bean:message key="label.date.lastlogin"/>
                            </label>
                            <html:text styleId="lastloginfield" styleClass="inputclass" property="lastLogin" readonly="true" maxlength="30"/>
                            <html:errors property="lastlogin"/>
                        </p>
                        <p class="contentclass">
                            <label id="datelabel" class="labelclass" for="datefield">
                                <bean:message key="label.date.encoded"/>
                            </label>
                            <html:text styleId="datefield" styleClass="inputclass" property="date" readonly="true" maxlength="30"/>
                            <html:errors property="date"/>
                        </p>
                        <p class="contentclass">
                            <label id="creatoridlabel" class="labelclass" for="creatoridfield">
                                <bean:message key="label.creatorid"/>
                            </label>
                            <html:text styleId="creatoridfield" styleClass="inputclass" property="creatorId" readonly="true" maxlength="30"/>
                            <html:errors property="creatorid"/>
                        </p>
                        <c:if test="${userForm.id == currentuser.id}">
                            <p class="contentclass">
                                <label id="questionlabel" class="labelclass" for="questionfield">
                                    * <bean:message key="label.question"/>
                                </label>
                                <html:select property="question" styleId="questionfield" styleClass="selectclass">
                                    <html:option value="0" styleId="questionfield0" styleClass="optionclass"><bean:message key="question.0"/></html:option>
                                    <html:option value="1" styleId="questionfield1" styleClass="optionclass"><bean:message key="question.1"/></html:option>
                                    <html:option value="2" styleId="questionfield2" styleClass="optionclass"><bean:message key="question.2"/></html:option>
                                </html:select>
                            </p>
                            <p class="contentclass">
                                <label id="answerlabel" class="labelclass" for="answerfield">
                                    * <bean:message key="label.answer"/>
                                </label>
                                <html:text styleId="answerfield" styleClass="inputclass" property="answer" size="37" maxlength="30"/>
                                <html:errors property="answer"/>
                            </p>
                        </c:if>
                        <p class="contentclass">
                            <label id="statuslabel" class="labelclass" for="statusfield">
                                <bean:message key="label.status"/>
                            </label>
                            <html:select property="status" styleId="statusfield" styleClass="selectclass" >
                                <c:choose>
                                    <c:when test="${currentuser.id == userForm.creatorId}">
                                        <html:option value="0" styleId="statusfield0" styleClass="optionclass"><bean:message key="status.user.0"/></html:option>
                                        <html:option value="1" styleId="statusfield1" styleClass="optionclass"><bean:message key="status.user.1"/></html:option>
                                    </c:when>
                                    <c:when test="${(currentuser.groupId == 0) && (currentuser.creatorId != userForm.id) && (currentuser.id != userForm.id)}">
                                        <html:option value="0" styleId="statusfield0" styleClass="optionclass"><bean:message key="status.user.0"/></html:option>
                                        <html:option value="1" styleId="statusfield1" styleClass="optionclass"><bean:message key="status.user.1"/></html:option>
                                    </c:when>
                                    <c:otherwise>
                                        <html:option value="${userForm.status}" styleId="statusfield1" styleClass="optionclass"><bean:message key="status.user.${userForm.status}"/></html:option>
                                    </c:otherwise>
                                </c:choose>
                            </html:select>
                        </p>
                        <c:if test="${userForm.groupId == 2}">
                            <p class="contentclass">
                                <label id="caseshandledlabel" class="labelclass">
                                    <bean:message key="label.cases.handled"/>
                                </label>
                                ${caseshandled}
                            </p>
                        </c:if>
                        <c:if test="${userForm.groupId == 1}">
                            <p class="contentclass">
                                <label id="casesencodedlabel" class="labelclass">
                                    <bean:message key="label.cases.encoded"/>
                                </label>
                                ${casesencoded}
                            </p>
                        </c:if>
                        <c:if test="${(userForm.groupId == 1) || (userForm.groupId == 0)}">
                            <p class="contentclass">
                                <label id="usersencodedlabel" class="labelclass">
                                    <bean:message key="label.users.encoded"/>
                                </label>
                                ${usersencoded}
                            </p>
                        </c:if>
                        <p class="contentclass">
                            <bean:message key="user.edit.buttons"/>
                        </p>
                    </html:form>
                    
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>