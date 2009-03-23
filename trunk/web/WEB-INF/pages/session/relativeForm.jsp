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
        <script type="text/javascript" src="scripts/openmpis.js"></script>
        <title><bean:message key="relative.title"/></title>
    </head>
    <body onload="javascript: setCities('${relativeForm.country}', '${relativeForm.province}', '${relativeForm.city}');
        setProvinces('${relativeForm.country}', '${relativeForm.province}');
        setCountry('${relativeForm.country}');">
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.case.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.case.menu"/>
                    </c:when>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${currentuser.groupId == 1}">
                            <c:choose>
                                <c:when test="${((action == 'newRelative') && (relativeForm.id == 0)) || (action == 'addRelative')}">
                                    <bean:message key="relative.add"/>
                                </c:when>
                                <c:when test="${((action == 'viewRelative') && (relativeForm.personId != null)) || (action == 'editRelative') || ((action == 'newRelative') && (relativeForm.id != 0))}">
                                    <bean:message key="relative.edit"/>
                                </c:when>
                                <c:when test="${(action == 'viewRelative') && (relativeForm.personId == null)}">
                                    <bean:message key="relative.view"/>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <bean:message key="relative.view"/>
                        </c:otherwise>
                    </c:choose>
                    <noscript>
                        <bean:message key="error.javascript.disabled"/>
                    </noscript>
                    
                    <html:form method="post" action="viewRelative" styleClass="adduserclass">
                        <p class="contentclass">
                            <c:choose>
                                <c:when test="${((action == 'newRelative') && (relativeForm.id == 0)) || (action == 'addRelative')}">
                                    <html:hidden property="action" value="addRelative"/>
                                    <html:hidden property="personId"/>
                                </c:when>
                                <c:when test="${((action == 'viewRelative') && (relativeForm.personId != null)) || (action == 'editRelative') || ((action == 'newRelative') && (relativeForm.id != 0))}">
                                    <html:hidden property="action" value="editRelative"/>
                                    <html:hidden property="personId"/>
                                </c:when>
                            </c:choose>
                        </p>
                        <c:if test="${currentuser.groupId == 1}">
                            <p class="contentclass">
                                <label id="relativelistlabel" class="labelclass" for="relativelistfield">
                                    <bean:message key="label.relative.existing"/>
                                </label>
                                <html:select styleId="relativelistfield" styleClass="selectclass" property="id"
                                    onchange="javascript: if (relativelistfield.value > 0) {window.location = 'viewRelative.do?action=viewRelative&id=' + this.value + '&personid=' + ${relativeForm.personId};} else {window.location = 'viewRelative.do?action=newRelative' + '&personid=' + ${relativeForm.personId};}"
                                    onkeyup="javascript: if (relativelistfield.value > 0) {window.location = 'viewRelative.do?action=viewRelative&id=' + this.value + '&personid=' + ${relativeForm.personId};} else {window.location = 'viewRelative.do?action=newRelative' + '&personid=' + ${relativeForm.personId};}">
                                    <html:option value="0" styleId="relativefield0" styleClass="optionclass">----------</html:option>
                                    <c:forEach items="${relativelist}" var="relative">
                                        <html:option value="${relative.id}" styleId="relativefield${i}" styleClass="optionclass">${relative.lastName}, ${relative.firstName}</html:option>
                                    </c:forEach>
                                </html:select>
                            </p>
                        </c:if>
                        <c:if test="${relativeForm.id != 0}">
                            <p class="contentclass">
                                <label id="idlabel" class="labelclass" for="idfield">
                                    <bean:message key="label.id"/>
                                </label>
                                <html:text styleId="idfield" styleClass="inputclass" property="id" readonly="true"/>
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
                            <label id="streetlabel" class="labelclass" for="streetfield">
                                * <bean:message key="label.address.street"/>
                            </label>
                            <html:text styleId="streetfield" styleClass="inputclass" property="street" maxlength="60"/>
                            <html:errors property="street"/>
                        </p>
                        <p class="contentclass">
                            <label id="citylabel" class="labelclass" for="cityfield">
                                * <bean:message key="label.address.city"/>
                            </label>
                            <html:select styleId="cityfield" styleClass="selectclass" property="city">
                            </html:select>
                            <html:text styleId="citytextfield" styleClass="hiddeninputclass" property="city" maxlength="30" disabled="true"/>
                            <html:errors property="city"/>
                        </p>
                        <p class="contentclass">
                            <label id="provincelabel" class="labelclass" for="provincefield">
                                * <bean:message key="label.address.province"/>
                            </label>
                            <html:select styleId="provincefield" styleClass="selectclass" property="province"
                                onchange="javascript: setCitiesOnProvinceChange(this, cityfield);"
                                onkeyup="javascript: setCitiesOnProvinceChange(this, cityfield);">
                            </html:select>
                            <html:text styleId="provincetextfield" styleClass="hiddeninputclass" property="province" maxlength="30" disabled="true"/>
                            <html:errors property="province"/>
                        </p>
                        <p class="contentclass">
                            <label id="countrylabel" class="labelclass" for="countryfield">
                                * <bean:message key="label.address.country"/>
                            </label>
                            <html:select styleId="countryfield" styleClass="selectclass" property="country"
                                onchange="javascript: toggleSelectOrText(this, provincefield, provincetextfield, cityfield, citytextfield);"
                                onkeyup="javascript: toggleSelectOrText(this, provincefield, provincetextfield, cityfield, citytextfield);">
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="emaillabel" class="labelclass" for="emailfield">
                                <bean:message key="label.email"/>
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
                            <label id="relationlabel" class="labelclass" for="relationfield">
                                * <bean:message key="label.relation"/>
                            </label>
                            <html:select styleId="relationfield" styleClass="selectclass" property="relationToRelative">
                                <c:forEach begin="0" end="30" step="1" var="i">
                                    <html:option value="${i}" styleId="relationfield${i}" styleClass="optionclass"><bean:message key="relation.${i}"/></html:option>
                                </c:forEach>
                            </html:select>
                        </p>
                        <c:if test="${relativeForm.personId != 0}">
                            <p class="contentclass">
                                <c:if test="${currentuser.groupId == 1}">
                                    <c:choose>
                                        <c:when test="${((action == 'newRelative') && (relativeForm.id == 0)) || (action == 'addRelative')}">
                                            <bean:message key="relative.add.buttons"/>
                                        </c:when>
                                        <c:when test="${((action == 'viewRelative') && (relativeForm.personId != null)) || (action == 'editRelative') || ((action == 'newRelative') && (relativeForm.id != 0))}">
                                            <bean:message key="relative.delete.buttons" arg0="${relativeForm.id}"/>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </p>
                        </c:if>
                    </html:form>

                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>