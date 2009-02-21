package WEB-INF.pages.session;

package WEB-INF.pages.session;

<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tab" uri="http://ditchnet.org/jsp-tabs-taglib" %>

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
        <script type="text/javascript" src="<html:rewrite page=''/>/scripts/md5.js"></script>
        <script type="text/javascript" src="<html:rewrite page=''/>/scripts/openmpis.js"></script>
        <bean:message key="case.title"/>
        <html:base/>
        <tab:tabConfig/>
    </head>
    <body onload="javascript: setDefaultCities(); setProvinces(); setCountries();">
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.case.menu"/>
                    </c:when>
                </c:choose>
            </div>
        
            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${currentuser.groupId == 1}">
                            <bean:message key="case.add"/>
                        </c:when>
                    </c:choose>
                    <c:if test="${editduplicateerror == 1}">
                        <bean:message key="error.duplicate"/>
                    </c:if>
                    <noscript>
                        <bean:message key="error.javascript.disabled"/>
                    </noscript>

                    <html:form method="post" action="viewPerson" styleClass="adduserclass">
                        <p class="contentclass">
                            <html:hidden property="action" value="addPerson"/>
                        </p>
                        <p class="contentclass">
                            <label id="statuslabel" class="labelclass" for="statusfield">
                                <bean:message key="label.status"/>
                            </label>
                            <html:select styleId="statusfield" styleClass="selectclass" property="status">
                                <html:option value="0" styleId="statusfield0" styleClass="optionclass"><bean:message key="status.case.0"/></html:option>
                                <html:option value="1" styleId="statusfield1" styleClass="optionclass"><bean:message key="status.case.1"/></html:option>
                                <html:option value="2" styleId="statusfield2" styleClass="optionclass"><bean:message key="status.case.2"/></html:option>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="typelabel" class="labelclass" for="typefield">
                                <bean:message key="label.case.type"/>
                            </label>
                            <html:select styleId="typefield" styleClass="selectclass" property="type">
                                <c:forEach begin="0" end="7" step="1" var="i">
                                    <html:option value="${i}" styleId="typefield${i}" styleClass="optionclass"><bean:message key="type.${i}"/></html:option>
                                </c:forEach>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="firstnamelabel" class="labelclass" for="firstnamefield">
                                <bean:message key="label.firstname"/>
                            </label>
                            <html:text styleId="firstnamefield" styleClass="inputclass" property="firstName" maxlength="30"/>
                            <html:errors property="firstname"/>
                        </p>
                        <p class="contentclass">
                            <label id="nicknamelabel" class="labelclass" for="nicknamefield">
                                <bean:message key="label.nickname"/>
                            </label>
                            <html:text styleId="nicknamefield" styleClass="inputclass" property="nickname" maxlength="30"/>
                            <html:errors property="nickname"/>
                        </p>
                        <p class="contentclass">
                            <label id="middlenamelabel" class="labelclass" for="middlenamefield">
                                <bean:message key="label.middlename"/>
                            </label>
                            <html:text styleId="middlenamefield" styleClass="inputclass" property="middleName" maxlength="30"/>
                            <html:errors property="middlename"/>
                        </p>
                        <p class="contentclass">
                            <label id="lastnamelabel" class="labelclass" for="lastnamefield">
                                <bean:message key="label.lastname"/>
                            </label>
                            <html:text styleId="lastnamefield" styleClass="inputclass" property="lastName" maxlength="30"/>
                            <html:errors property="lastname"/>
                        </p>
                        <p class="contentclass">
                            <label id="birthdatelabel" class="labelclass" for="monthfield">
                                <bean:message key="label.birthdate"/>
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
                            <html:select styleId="yearfield" styleClass="yearselectclass" property="birthYear">
                                <c:forEach begin="1929" end="2009" step="1" var="i">
                                    <html:option value="${i}" styleId="yearfield${i}" styleClass="yearoptionclass">${i}</html:option>
                                </c:forEach>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="streetlabel" class="labelclass" for="streetfield">
                                <bean:message key="label.address.street"/>
                            </label>
                            <html:text styleId="streetfield" styleClass="inputclass" property="street" maxlength="30"/>
                            <html:errors property="street"/>
                        </p>
                        <p class="contentclass">
                            <label id="citylabel" class="labelclass" for="cityfield">
                                <bean:message key="label.address.city"/>
                            </label>
                            <html:select styleId="cityfield" styleClass="selectclass" property="city" >
                            </html:select>
                            <html:text styleId="citytextfield" styleClass="inputclass" property="city" maxlength="30" disabled="true"/>
                        </p>
                        <p class="contentclass">
                            <label id="provincelabel" class="labelclass" for="provincefield">
                                <bean:message key="label.address.province"/>
                            </label>
                            <html:select styleId="provincefield" styleClass="selectclass" property="province"
                            onchange="javascript: setCities(this);" onkeyup="javascript: setCities(this);">
                            </html:select>
                            <html:text styleId="provincetextfield" styleClass="inputclass" property="province" maxlength="30" disabled="true"/>
                        </p>
                        <p class="contentclass">
                            <label id="countrylabel" class="labelclass" for="countryfield">
                                <bean:message key="label.address.country"/>
                            </label>
                            <html:select styleId="countryfield" styleClass="selectclass" property="country" value="Philippines"
                                onchange="javascript: if (countryfield.value != 'Philippines') {provincefield.disabled = true; provincetextfield.disabled = false; cityfield.disabled = true; citytextfield.disabled = false;}
                                else {provincefield.disabled = false; provincetextfield.disabled = true; cityfield.disabled = false; citytextfield.disabled = true;}">
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="medicalconditionlabel" class="labelclass" for="medicalconditionfield">
                                <bean:message key="label.condition"/>
                            </label>
                            <html:text styleId="medicalconditionfield" styleClass="inputclass" property="medicalCondition" maxlength="30"/>
                            <html:errors property="medicalcondition"/>
                        </p>
                        <p class="contentclass">
                            <label id="markslabel" class="labelclass" for="marksfield">
                                <bean:message key="label.marks"/>
                            </label>
                            <html:text styleId="marksfield" styleClass="inputclass" property="marks" maxlength="30"/>
                            <html:errors property="marks"/>
                        </p>
                        <p class="contentclass">
                            <label id="personaleffectslabel" class="labelclass" for="personaleffectsfield">
                                <bean:message key="label.personaleffects"/>
                            </label>
                            <html:text styleId="personaleffectsfield" styleClass="inputclass" property="personalEffects" maxlength="30"/>
                            <html:errors property="personaleffects"/>
                        </p>
                        <p class="contentclass">
                            <label id="remarkslabel" class="labelclass" for="remarksfield">
                                <bean:message key="label.remarks"/>
                            </label>
                            <html:text styleId="remarksfield" styleClass="inputclass" property="remarks" maxlength="30"/>
                            <html:errors property="remarks"/>
                        </p>
                        <p class="contentclass">
                            <c:choose>
                                <c:when test="${(currentuser.id == userForm.creatorId)}">
                                    <bean:message key="case.delete.buttons" arg0="${userForm.id}"/>
                                </c:when>
                                <c:when test="${(currentuser.groupId == 0) && (currentuser.creatorId != userForm.id) && (currentuser.id != userForm.id)}">
                                    <bean:message key="case.delete.buttons" arg0="${userForm.id}"/>
                                </c:when>
                                <c:otherwise>
                                    <bean:message key="case.edit.buttons"/>
                                </c:otherwise>
                            </c:choose>
                        </p>
                    </html:form>
                    
                </div>
            </div>
        
            <tag:footer/>
        </div>
    </body>
</html:html>