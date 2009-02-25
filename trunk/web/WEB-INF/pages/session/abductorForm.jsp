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
        <link rel="shortcut icon" href="/images/favicon.ico"/>
        <style type="text/css" media="all">@import "<bean:message key="global.style"/>";</style>
        <script type="text/javascript" src="scripts/openmpis.js"></script>
        <bean:message key="case.title"/>
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
                            <c:choose>
                                <c:when test="${action eq 'newPerson'}">
                                    <bean:message key="case.add"/>
                                </c:when>
                                <c:when test="${action eq 'editPerson'}">
                                    <bean:message key="case.edit"/>
                                </c:when>
                                <c:when test="${action eq 'erasePerson'}">
                                    <bean:message key="case.delete"/>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <bean:message key="case.view"/>
                        </c:otherwise>
                    </c:choose>
                    <noscript>
                        <bean:message key="error.javascript.disabled"/>
                    </noscript>
                    <html:form method="post" action="viewPerson" styleClass="adduserclass" enctype="multipart/form-data">
                        <p class="contentclass">
                            <html:hidden property="action" value="addPerson"/>
                        </p>
                        <c:if test="${personForm.id != 0}">
                            <p class="contentclass">
                                <label id="idlabel" class="labelclass" for="idfield">
                                    <bean:message key="label.id"/>
                                </label>
                                <html:text styleId="idfield" styleClass="inputclass" property="id" readonly="true"/>
                            </p>
                        </c:if>
                        <p class="contentclass">
                            <html:img src="${personForm.photo}" alt="The person's photo" title="Photo"/>
                            <html:img src="${personForm.agedPhoto}" alt="The person's aged-progressed photo" title="Age-progressed photo"/>
                        </p>
                        <p class="contentclass">
                            <label id="photolabel" class="labelclass" for="photofile">
                                <bean:message key="label.photo"/>
                            </label>
                            <html:file styleId="photofile" property="photoFile" accept="image/png,image/jpg,image/gif" styleClass="fileclass" size="35"/>
                        </p>
                        <p class="contentclass">
                            <label id="agedphotolabel" class="labelclass" for="agedphotofile">
                                <bean:message key="label.agedphoto"/>
                            </label>
                            <html:file styleId="agedphotofile" property="agedPhotoFile" accept="image/png,image/jpg,image/gif" styleClass="fileclass" size="35"/>
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
                            <html:select styleId="typefield" styleClass="selectclass" property="type"
                                onchange="javascript: toggleMissingOrFound(typefield.value);"
                                onkeyup="javascript: toggleMissingOrFound(typefield.value);">
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
                                <bean:message key="label.date.birth"/>
                            </label>
                                <html:select styleId="monthfield" styleClass="monthselectclass" property="birthMonth"
                                    onchange="javascript: setAge();"
                                    onkeyup="javascript: setAge();">
                                    <c:forEach begin="1" end="12" step="1" var="i">
                                        <html:option value="${i}" styleId="monthfield${i}" styleClass="monthoptionclass"><bean:message key="month.${i}"/></html:option>
                                    </c:forEach>
                                </html:select>
                                <html:select styleId="dayfield" styleClass="dayselectclass" property="birthDay"
                                    onchange="javascript: setAge();"
                                    onkeyup="javascript: setAge();">
                                    <c:forEach begin="1" end="31" step="1" var="i">
                                        <html:option value="${i}" styleId="dayfield${i}" styleClass="dayoptionclass">${i}</html:option>
                                    </c:forEach>
                                </html:select>
                                <jsp:useBean id="currentDate" class="java.util.Date"/>
                                <fmt:formatDate var="currentYear" value="${currentDate}" pattern="yyyy"/>
                                <html:select styleId="yearfield" styleClass="yearselectclass" property="birthYear"
                                    onchange="javascript: setAge();"
                                    onkeyup="javascript: setAge();">
                                    <c:forEach begin="${currentYear - 80}" end="${currentYear}" step="1" var="i">
                                        <html:option value="${i}" styleId="yearfield${i}" styleClass="yearoptionclass">${i}</html:option>
                                    </c:forEach>
                                </html:select>
                                <bean:message key="label.age"/>
                                <html:text styleId="agefield" styleClass="ageinputclass" property="age" disabled="true"/>
                                <html:errors property="birthdate"/>
                        </p>
                        <p class="contentclass">
                            <label id="streetlabel" class="labelclass" for="streetfield">
                                <bean:message key="label.address.street"/>
                            </label>
                            <html:text styleId="streetfield" styleClass="inputclass" property="street" maxlength="60"/>
                            <html:errors property="street"/>
                        </p>
                        <p class="contentclass">
                            <label id="citylabel" class="labelclass" for="cityfield">
                                <bean:message key="label.address.city"/>
                            </label>
                            <html:select styleId="cityfield" styleClass="selectclass" property="city" >
                            </html:select>
                            <html:text styleId="citytextfield" styleClass="hiddeninputclass" property="city" maxlength="30" disabled="true"/>
                        </p>
                        <p class="contentclass">
                            <label id="provincelabel" class="labelclass" for="provincefield">
                                <bean:message key="label.address.province"/>
                            </label>
                            <html:select styleId="provincefield" styleClass="selectclass" property="province"
                                onchange="javascript: setCities(this, cityfield);"
                                onkeyup="javascript: setCities(this, cityfield);">
                            </html:select>
                            <html:text styleId="provincetextfield" styleClass="hiddeninputclass" property="province" maxlength="30" disabled="true"/>
                        </p>
                        <p class="contentclass">
                            <label id="countrylabel" class="labelclass" for="countryfield">
                                <bean:message key="label.address.country"/>
                            </label>
                            <html:select styleId="countryfield" styleClass="selectclass" property="country"
                                onchange="javascript: toggle(this, provincefield, provincetextfield, cityfield, citytextfield);"
                                onkeyup="javascript: toggle(this, provincefield, provincetextfield, cityfield, citytextfield);">
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="sexlabel" class="labelclass" for="sexfield">
                                <bean:message key="label.sex"/>
                            </label>
                            <html:select styleId="sexfield" styleClass="selectclass" property="sex">
                                <html:option value="0" styleId="sexfield0" styleClass="optionclass"><bean:message key="sex.0"/></html:option>
                                <html:option value="1" styleId="sexfield1" styleClass="optionclass"><bean:message key="sex.1"/></html:option>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="heightlabel" class="labelclass" for="feetfield">
                                <bean:message key="label.height"/>
                            </label>
                            <html:select styleId="feetfield" styleClass="feetselectclass" property="feet"
                                onchange="javascript: setCm();"
                                onkeyup="javascript: setCm();">
                                <c:forEach begin="1" end="7" step="1" var="i">
                                    <html:option value="${i}" styleId="feetfield${i}" styleClass="feetoptionclass">${i}</html:option>
                                </c:forEach>
                            </html:select>&nbsp;<bean:message key="label.height.ft"/>
                            <html:select styleId="inchesfield" styleClass="inchesselectclass" property="inches"
                                onchange="javascript: setCm();"
                                onkeyup="javascript: setCm();">
                                <c:forEach begin="0" end="11" step="1" var="i">
                                    <html:option value="${i}" styleId="inchesfield${i}" styleClass="inchesoptionclass">${i}</html:option>
                                </c:forEach>
                            </html:select>&nbsp;<bean:message key="label.height.in"/>
                            <html:text styleId="cmfield" styleClass="heightinputclass" property="cm" disabled="true"/>&nbsp;<bean:message key="label.height.cm"/>
                        </p>
                        <p class="contentclass">
                            <label id="weightlabel" class="labelclass" for="weightfield">
                                <bean:message key="label.weight"/>
                            </label>
                            <html:text styleId="weightfield" styleClass="weightinputclass" property="weight" maxlength="3" onkeyup="javascript: mass.value = weight.value / 2.2;"/>&nbsp;<bean:message key="label.weight.lbs"/>
                            <html:text styleId="massfield" styleClass="weightinputclass" property="mass" maxlength="3" onkeyup="javascript: weight.value = mass.value * 2.2;"/>&nbsp;<bean:message key="label.weight.kg"/>
                            <html:errors property="weight"/>
                        </p>
                        <p class="contentclass">
                            <label id="religionlabel" class="labelclass" for="religionfield">
                                <bean:message key="label.religion"/>
                            </label>
                            <html:select styleId="religionfield" styleClass="selectclass" property="religion">
                                <c:forEach begin="0" end="7" step="1" var="i">
                                    <html:option value="${i}" styleId="religionfield${i}" styleClass="optionclass"><bean:message key="religion.${i}"/></html:option>
                                </c:forEach>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="racelabel" class="labelclass" for="racefield">
                                <bean:message key="label.race"/>
                            </label>
                            <html:select styleId="racefield" styleClass="selectclass" property="race">
                                <c:forEach begin="0" end="13" step="1" var="i">
                                    <html:option value="${i}" styleId="racefield${i}" styleClass="optionclass"><bean:message key="race.${i}"/></html:option>
                                </c:forEach>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="eyecolorlabel" class="labelclass" for="eyecolorfield">
                                <bean:message key="label.color.eye"/>
                            </label>
                            <html:select styleId="eyecolorfield" styleClass="selectclass" property="eyeColor">
                                <c:forEach begin="0" end="7" step="1" var="i">
                                    <html:option value="${i}" styleId="eyecolorfield${i}" styleClass="optionclass"><bean:message key="color.eye.${i}"/></html:option>
                                </c:forEach>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="haircolorlabel" class="labelclass" for="haircolorfield">
                                <bean:message key="label.color.hair"/>
                            </label>
                            <html:select styleId="haircolorfield" styleClass="selectclass" property="hairColor">
                                <c:forEach begin="0" end="7" step="1" var="i">
                                    <html:option value="${i}" styleId="haircolorfield${i}" styleClass="optionclass"><bean:message key="color.hair.${i}"/></html:option>
                                </c:forEach>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="medicalconditionlabel" class="labelclass" for="medicalconditionfield">
                                <bean:message key="label.condition"/>
                            </label>
                            <html:textarea styleId="medicalconditionfield" styleClass="textareaclass" property="medicalCondition"/>
                            <html:errors property="medicalcondition"/>
                        </p>
                        <p class="contentclass">
                            <label id="markslabel" class="labelclass" for="marksfield">
                                <bean:message key="label.marks"/>
                            </label>
                            <html:textarea styleId="marksfield" styleClass="textareaclass" property="marks"/>
                            <html:errors property="marks"/>
                        </p>
                        <p class="contentclass">
                            <label id="personaleffectslabel" class="labelclass" for="personaleffectsfield">
                                <bean:message key="label.personaleffects"/>
                            </label>
                            <html:textarea styleId="personaleffectsfield" styleClass="textareaclass" property="personalEffects"/>
                            <html:errors property="personaleffects"/>
                        </p>
                        <p class="contentclass">
                            <label id="remarkslabel" class="labelclass" for="remarksfield">
                                <bean:message key="label.remarks"/>
                            </label>
                            <html:textarea styleId="remarksfield" styleClass="textareaclass" property="remarks"/>
                            <html:errors property="remarks"/>
                        </p>
                        <c:choose>
                            <c:when test="${personForm.type > 4}">
                                <c:set var="missing" value="display: none;"/>
                                <c:set var="found" value="display: block;"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="missing" value="display: block;"/>
                                <c:set var="found" value="display: none;"/>
                            </c:otherwise>
                        </c:choose>
                        <div id="ifmissing" style="${missing}">
                            <p class="contentclass">
                                <label id="datemissinglabel" class="labelclass" for="missingmonthfield">
                                    <bean:message key="label.date.missing"/>
                                </label>
                                <html:select styleId="missingmonthfield" styleClass="monthselectclass" property="monthMissingOrFound"
                                    onchange="javascript: setTimeElapsed(missingyearfield.value, missingmonthfield.value, missingdayfield.value, daysfield);"
                                    onkeyup="javascript: setTimeElapsed(missingyearfield.value, missingmonthfield.value, missingdayfield.value, daysfield);">
                                    <c:forEach begin="1" end="12" step="1" var="i">
                                        <html:option value="${i}" styleId="missingmonthfield${i}" styleClass="monthoptionclass"><bean:message key="month.${i}"/></html:option>
                                    </c:forEach>
                                </html:select>
                                <html:select styleId="missingdayfield" styleClass="dayselectclass" property="dayMissingOrFound"
                                    onchange="javascript: setTimeElapsed(missingyearfield.value, missingmonthfield.value, missingdayfield.value, daysfield);"
                                    onkeyup="javascript: setTimeElapsed(missingyearfield.value, missingmonthfield.value, missingdayfield.value, daysfield);">
                                    <c:forEach begin="1" end="31" step="1" var="i">
                                        <html:option value="${i}" styleId="missingdayfield${i}" styleClass="dayoptionclass">${i}</html:option>
                                    </c:forEach>
                                </html:select>
                                <html:select styleId="missingyearfield" styleClass="yearselectclass" property="yearMissingOrFound"
                                    onchange="javascript: setTimeElapsed(missingyearfield.value, missingmonthfield.value, missingdayfield.value, daysfield);"
                                    onkeyup="javascript: setTimeElapsed(missingyearfield.value, missingmonthfield.value, missingdayfield.value, daysfield);">
                                    <c:forEach begin="${currentYear - 80}" end="${currentYear}" step="1" var="i">
                                        <html:option value="${i}" styleId="missingyearfield${i}" styleClass="yearoptionclass">${i}</html:option>
                                    </c:forEach>
                                </html:select>
                                <html:text styleId="daysfield" styleClass="ageinputclass" property="daysMissing" disabled="true"/>&nbsp;<bean:message key="label.missing.day"/>
                                <html:errors property="datemissingorfound"/>
                            </p>
                            <p class="contentclass">
                                 <bean:message key="label.address.missingfrom"/>
                            </p>
                            <p class="contentclass">
                                <label id="missingfromcitylabel" class="labelclass" for="missingfromcityfield">
                                    <bean:message key="label.address.city"/>
                                </label>
                                <html:select styleId="missingfromcityfield" styleClass="selectclass" property="missingFromCity" >
                                </html:select>
                                <html:text styleId="missingfromcitytextfield" styleClass="hiddeninputclass" property="missingFromCity" maxlength="30" disabled="true"/>
                                <html:errors property="missingfromcity"/>
                            </p>
                            <p class="contentclass">
                                <label id="missingfromprovincelabel" class="labelclass" for="missingfromprovincefield">
                                    <bean:message key="label.address.province"/>
                                </label>
                                <html:select styleId="missingfromprovincefield" styleClass="selectclass" property="missingFromProvince"
                                    onchange="javascript: setCities(this, missingfromcityfield);"
                                    onkeyup="javascript: setCities(this, missingfromcityfield);">
                                </html:select>
                                <html:text styleId="missingfromprovincetextfield" styleClass="hiddeninputclass" property="missingFromProvince" maxlength="30" disabled="true"/>
                                <html:errors property="missingfromprovince"/>
                            </p>
                            <p class="contentclass">
                                <label id="missingfromcountrylabel" class="labelclass" for="missingfromcountryfield">
                                    <bean:message key="label.address.country"/>
                                </label>
                                <html:select styleId="missingfromcountryfield" styleClass="selectclass" property="missingFromCountry"
                                    onchange="javascript: toggle(this, missingfromprovincefield, missingfromprovincetextfield, missingfromcityfield, missingfromcitytextfield);"
                                    onkeyup="javascript: toggle(this, missingfromprovincefield, missingfromprovincetextfield, missingfromcityfield, missingfromcitytextfield);">
                                </html:select>
                            </p>
                            <p class="contentclass">
                                 <bean:message key="label.address.possiblelocation"/>
                            </p>
                            <p class="contentclass">
                                <label id="possiblecitylabel" class="labelclass" for="possiblecityfield">
                                    <bean:message key="label.address.city"/>
                                </label>
                                <html:select styleId="possiblecityfield" styleClass="selectclass" property="possibleCity" >
                                </html:select>
                                <html:text styleId="possiblecitytextfield" styleClass="hiddeninputclass" property="possibleCity" maxlength="30" disabled="true"/>
                                <html:errors property="possiblecity"/>
                            </p>
                            <p class="contentclass">
                                <label id="possibleprovincelabel" class="labelclass" for="possibleprovincefield">
                                    <bean:message key="label.address.province"/>
                                </label>
                                <html:select styleId="possibleprovincefield" styleClass="selectclass" property="possibleProvince"
                                    onchange="javascript: setCities(this, possiblecityfield);"
                                    onkeyup="javascript: setCities(this, possiblecityfield);">
                                </html:select>
                                <html:text styleId="possibleprovincetextfield" styleClass="hiddeninputclass" property="possibleProvince" maxlength="30" disabled="true"/>
                                <html:errors property="possibleprovince"/>
                            </p>
                            <p class="contentclass">
                                <label id="possiblecountrylabel" class="labelclass" for="possiblecountryfield">
                                    <bean:message key="label.address.country"/>
                                </label>
                                <html:select styleId="possiblecountryfield" styleClass="selectclass" property="possibleCountry"
                                onchange="javascript: toggle(this, possibleprovincefield, possibleprovincetextfield, possiblecityfield, possiblecitytextfield);"
                                onkeyup="javascript: toggle(this, possibleprovincefield, possibleprovincetextfield, possiblecityfield, possiblecitytextfield);">
                                </html:select>
                            </p>
                            <p class="contentclass">
                                <label id="circumstancelabel" class="labelclass" for="circumstancefield">
                                    <bean:message key="label.circumstance"/>
                                </label>
                                <html:textarea styleId="circumstancefield" styleClass="textareaclass" property="circumstance"/>
                                <html:errors property="circumstance"/>
                            </p>
                            <p class="contentclass">
                                <label id="rewardlabel" class="labelclass" for="rewardfield">
                                    <bean:message key="label.reward"/>
                                </label>
                                <html:text styleId="rewardfield" styleClass="inputclass" property="reward" maxlength="30"/>
                                <html:errors property="reward"/>
                            </p>
                        </div>
                        <div id="iffound" style="${found}">
                            <p class="contentclass">
                                <label id="datefoundlabel" class="labelclass" for="foundmonthfield">
                                    <bean:message key="label.date.found"/>
                                </label>
                                <html:select styleId="foundmonthfield" styleClass="monthselectclass" property="monthMissingOrFound">
                                    <c:forEach begin="1" end="12" step="1" var="i">
                                        <html:option value="${i}" styleId="foundmonthfield${i}" styleClass="monthoptionclass"><bean:message key="month.${i}"/></html:option>
                                    </c:forEach>
                                </html:select>
                                <html:select styleId="founddayfield" styleClass="dayselectclass" property="dayMissingOrFound">
                                    <c:forEach begin="1" end="31" step="1" var="i">
                                        <html:option value="${i}" styleId="founddayfield${i}" styleClass="dayoptionclass">${i}</html:option>
                                    </c:forEach>
                                </html:select>
                                <html:select styleId="foundyearfield" styleClass="yearselectclass" property="yearMissingOrFound">
                                    <c:forEach begin="${currentYear - 80}" end="${currentYear}" step="1" var="i">
                                        <html:option value="${i}" styleId="foundyearfield${i}" styleClass="yearoptionclass">${i}</html:option>
                                    </c:forEach>
                                </html:select>
                                <html:errors property="datemissingorfound"/>
                            </p>
                            <p class="contentclass">
                                 <bean:message key="label.address.currentlocation"/>
                            </p>
                            <p class="contentclass">
                                <label id="institutionlabel" class="labelclass" for="institutionfield">
                                    <bean:message key="label.institution"/>
                                </label>
                                <html:text styleId="institutionfield" styleClass="inputclass" property="institution" maxlength="60"/>
                                <html:errors property="institution"/>
                            </p>
                            <p class="contentclass">
                                <label id="institutionstreetlabel" class="labelclass" for="institutionstreetfield">
                                    <bean:message key="label.address.street"/>
                                </label>
                                <html:text styleId="institutionstreetfield" styleClass="inputclass" property="institutionStreet" maxlength="60"/>
                                <html:errors property="institutionstreet"/>
                            </p>
                            <p class="contentclass">
                                <label id="institutioncitylabel" class="labelclass" for="institutioncityfield">
                                    <bean:message key="label.address.city"/>
                                </label>
                                <html:select styleId="institutioncityfield" styleClass="selectclass" property="institutionCity" >
                                </html:select>
                                <html:text styleId="institutioncitytextfield" styleClass="hiddeninputclass" property="institutionCity" maxlength="30" disabled="true"/>
                                <html:errors property="institutioncity"/>
                            </p>
                            <p class="contentclass">
                                <label id="institutionprovincelabel" class="labelclass" for="institutionprovincefield">
                                    <bean:message key="label.address.province"/>
                                </label>
                                <html:select styleId="institutionprovincefield" styleClass="selectclass" property="institutionProvince"
                                    onchange="javascript: setCities(this, institutioncityfield);"
                                    onkeyup="javascript: setCities(this, institutioncityfield);">
                                </html:select>
                                <html:text styleId="institutionprovincetextfield" styleClass="hiddeninputclass" property="institutionProvince" maxlength="30" disabled="true"/>
                                <html:errors property="institutionprovince"/>
                            </p>
                            <p class="contentclass">
                                <label id="institutioncountrylabel" class="labelclass" for="institutioncountryfield">
                                    <bean:message key="label.address.country"/>
                                </label>
                                <html:select styleId="institutioncountryfield" styleClass="selectclass" property="institutionCountry"
                                    onchange="javascript: toggle(this, institutionprovincefield, institutionprovincetextfield, institutioncityfield, institutioncitytextfield);"
                                    onkeyup="javascript: toggle(this, institutionprovincefield, institutionprovincetextfield, institutioncityfield, institutioncitytextfield);">
                                </html:select>
                            </p>
                            <p class="contentclass">
                                <label id="institutionemaillabel" class="labelclass" for="institutionemailfield">
                                    <bean:message key="label.email"/>
                                </label>
                                <html:text styleId="institutionemailfield" styleClass="inputclass" property="institutionEmail" maxlength="60"/>
                                <html:errors property="institutionemail"/>
                            </p>
                            <p class="contentclass">
                                <label id="institutionnumberlabel" class="labelclass" for="institutionnumberfield">
                                    <bean:message key="label.number"/>
                                </label>
                                <html:text styleId="institutionnumberfield" styleClass="inputclass" property="institutionNumber" maxlength="30"/>
                                <html:errors property="institutionnumber"/>
                            </p>
                        </div>
                        <p class="contentclass">
                            <label id="codisidlabel" class="labelclass" for="codisidfield">
                                <bean:message key="label.codis.id"/>
                            </label>
                            <html:text styleId="codisidfield" styleClass="inputclass" property="codisId" maxlength="30"/>
                            <html:errors property="codisid"/>
                        </p>
                        <p class="contentclass">
                            <label id="afisidlabel" class="labelclass" for="afisidfield">
                                <bean:message key="label.afis.id"/>
                            </label>
                            <html:text styleId="afisidfield" styleClass="inputclass" property="afisId" maxlength="30"/>
                            <html:errors property="afisid"/>
                        </p>
                        <p class="contentclass">
                            <label id="dentalidlabel" class="labelclass" for="dentalidfield">
                                <bean:message key="label.dental.id"/>
                            </label>
                            <html:text styleId="dentalidfield" styleClass="inputclass" property="dentalId" maxlength="30"/>
                            <html:errors property="dentalid"/>
                        </p>
                        <p class="contentclass">
                            <c:if test="${currentuser.groupId == 1}">
                                <c:choose>
                                    <c:when test="${action eq 'newPerson'}">
                                        <bean:message key="case.add.buttons"/>
                                    </c:when>
                                    <c:when test="${action eq 'editPerson'}">
                                        <bean:message key="case.edit.buttons"/>
                                    </c:when>
                                    <c:when test="${action eq 'erasePerson'}">
                                        <bean:message key="case.delete.buttons"/>
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </p>
                    </html:form>

                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>