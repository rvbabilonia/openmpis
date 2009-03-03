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
        <bean:message key="case.title"/>
    </head>
    <body>
        <div id="container">
            <tag:header/>

            <!-- Menu -->
            <div id="menu">
                <c:choose>
                    <c:when test="${currentuser.groupId == 0}">
                        <bean:message key="admin.case.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 1}">
                        <bean:message key="encoder.case.menu"/>
                    </c:when>
                    <c:when test="${currentuser.groupId == 2}">
                        <bean:message key="investigator.case.menu" arg0="${currentuser.id}"/>
                    </c:when>
                    <c:otherwise>
                        <bean:message key="global.menu"/>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <div id="casemenu">
                        <bean:message key="case.menu"/>
                    </div>
                    <bean:message key="case.view"/>

                    <p class="contentclass">
                        <html:img styleClass="photoclass" src="${personForm.photo eq null ? '/photo/unknown.png' : personForm.photo}" alt="The person's photo" title="Photo"/>
                        <html:img styleClass="photoclass" src="${personForm.agedPhoto eq null ? '/photo/unknown.png' : personForm.agedPhoto}" alt="The person's aged-progressed photo" title="Age-progressed photo"/>
                    </p>
                    <p class="contentclass">
                        <label id="statuslabel" class="labelclass">
                            <bean:message key="label.status"/>
                        </label>
                        <bean:message key="status.case.${personForm.status}"/>
                    </p>
                    <p class="contentclass">
                        <label id="typelabel" class="labelclass">
                            <bean:message key="label.case.type"/>
                        </label>
                        <bean:message key="type.${personForm.type}"/>
                    </p>
                    <p class="contentclass">
                        <label id="firstnamelabel" class="labelclass">
                            <bean:message key="label.firstname"/>
                        </label>
                        ${personForm.firstName}
                    </p>
                    <p class="contentclass">
                        <label id="nicknamelabel" class="labelclass">
                            <bean:message key="label.nickname"/>
                        </label>
                        ${personForm.nickname}
                    </p>
                    <p class="contentclass">
                        <label id="middlenamelabel" class="labelclass">
                            <bean:message key="label.middlename"/>
                        </label>
                        ${personForm.middleName}
                    </p>
                    <p class="contentclass">
                        <label id="lastnamelabel" class="labelclass">
                            <bean:message key="label.lastname"/>
                        </label>
                        ${personForm.lastName}
                    </p>
                    <p class="contentclass">
                        <label id="birthdatelabel" class="labelclass">
                            <bean:message key="label.date.birth"/>
                        </label>
                        <bean:message key="month.${personForm.birthMonth}"/>&nbsp;${personForm.birthDay},&nbsp;${personForm.birthYear}
                        <bean:message key="label.age"/>${personForm.age}
                    </p>
                    <p class="contentclass">
                        <label id="streetlabel" class="labelclass">
                            <bean:message key="label.address.street"/>
                        </label>
                        ${personForm.street}
                    </p>
                    <p class="contentclass">
                        <label id="citylabel" class="labelclass">
                            <bean:message key="label.address.city"/>
                        </label>
                        ${personForm.city}
                    </p>
                    <p class="contentclass">
                        <label id="provincelabel" class="labelclass">
                            <bean:message key="label.address.province"/>
                        </label>
                        ${personForm.province}
                    </p>
                    <p class="contentclass">
                        <label id="countrylabel" class="labelclass">
                            <bean:message key="label.address.country"/>
                        </label>
                        ${personForm.country}
                    </p>
                    <p class="contentclass">
                        <label id="sexlabel" class="labelclass">
                            <bean:message key="label.sex"/>
                        </label>
                        <bean:message key="sex.${personForm.sex}"/>
                    </p>
                    <p class="contentclass">
                        <label id="heightlabel" class="labelclass">
                            <bean:message key="label.height"/>
                        </label>
                        ${personForm.feet}&nbsp;<bean:message key="label.height.ft"/>
                        ${personForm.inches}&nbsp;<bean:message key="label.height.in"/>
                    </p>
                    <p class="contentclass">
                        <label id="weightlabel" class="labelclass">
                            <bean:message key="label.weight"/>
                        </label>
                        ${personForm.weight}&nbsp;<bean:message key="label.weight.lbs"/>
                    </p>
                    <p class="contentclass">
                        <label id="religionlabel" class="labelclass">
                            <bean:message key="label.religion"/>
                        </label>
                        <bean:message key="religion.${personForm.religion}"/>
                    </p>
                    <p class="contentclass">
                        <label id="racelabel" class="labelclass">
                            <bean:message key="label.race"/>
                        </label>
                        <bean:message key="race.${personForm.race}"/>
                    </p>
                    <p class="contentclass">
                        <label id="eyecolorlabel" class="labelclass">
                            <bean:message key="label.color.eye"/>
                        </label>
                        <bean:message key="color.eye.${personForm.eyeColor}"/>
                    </p>
                    <p class="contentclass">
                        <label id="haircolorlabel" class="labelclass">
                            <bean:message key="label.color.hair"/>
                        </label>
                        <bean:message key="color.hair.${personForm.hairColor}"/>
                    </p>
                    <p class="contentclass">
                        <label id="medicalconditionlabel" class="labelclass">
                            <bean:message key="label.condition"/>
                        </label>
                        ${personForm.medicalCondition}
                    </p>
                    <p class="contentclass">
                        <label id="markslabel" class="labelclass">
                            <bean:message key="label.marks"/>
                        </label>
                        ${personForm.marks}
                    </p>
                    <p class="contentclass">
                        <label id="personaleffectslabel" class="labelclass">
                            <bean:message key="label.personaleffects"/>
                        </label>
                        ${personForm.personalEffects}
                    </p>
                    <p class="contentclass">
                        <label id="remarkslabel" class="labelclass">
                            <bean:message key="label.remarks"/>
                        </label>
                        ${personForm.remarks}
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
                            <label id="datemissinglabel" class="labelclass">
                                <bean:message key="label.date.missing"/>
                            </label>
                            <bean:message key="month.${personForm.monthMissingOrFound}"/>&nbsp;${personForm.dayMissingOrFound},&nbsp;${personForm.yearMissingOrFound}
                            ${personForm.daysMissing}&nbsp;<bean:message key="label.missing.day"/>
                        </p>
                        <p class="contentclass">
                             <bean:message key="label.address.missingfrom"/>
                        </p>
                        <p class="contentclass">
                            <label id="missingfromcitylabel" class="labelclass">
                                <bean:message key="label.address.city"/>
                            </label>
                            ${personForm.missingFromCity}
                        </p>
                        <p class="contentclass">
                            <label id="missingfromprovincelabel" class="labelclass">
                                <bean:message key="label.address.province"/>
                            </label>
                            ${personForm.missingFromProvince}
                        </p>
                        <p class="contentclass">
                            <label id="missingfromcountrylabel" class="labelclass">
                                <bean:message key="label.address.country"/>
                            </label>
                            ${personForm.missingFromCountry}
                        </p>
                        <p class="contentclass">
                             <bean:message key="label.address.possiblelocation"/>
                        </p>
                        <p class="contentclass">
                            <label id="possiblecitylabel" class="labelclass">
                                <bean:message key="label.address.city"/>
                            </label>
                            ${personForm.possibleCity}
                        </p>
                        <p class="contentclass">
                            <label id="possibleprovincelabel" class="labelclass">
                                <bean:message key="label.address.province"/>
                            </label>
                            ${personForm.possibleProvince}
                        </p>
                        <p class="contentclass">
                            <label id="possiblecountrylabel" class="labelclass">
                                <bean:message key="label.address.country"/>
                            </label>
                            ${personForm.possibleCountry}
                        </p>
                        <p class="contentclass">
                            <label id="circumstancelabel" class="labelclass">
                                <bean:message key="label.circumstance"/>
                            </label>
                            ${personForm.circumstance}
                        </p>
                        <p class="contentclass">
                            <label id="rewardlabel" class="labelclass">
                                <bean:message key="label.reward"/>
                            </label>
                            ${personForm.reward}
                        </p>
                    </div>
                    <div id="iffound" style="${found}">
                        <p class="contentclass">
                            <label id="datefoundlabel" class="labelclass">
                                <bean:message key="label.date.found"/>
                            </label>
                            <bean:message key="month.${personForm.monthMissingOrFound}"/>&nbsp;${personForm.dayMissingOrFound},&nbsp;${personForm.yearMissingOrFound}
                        </p>
                        <p class="contentclass">
                             <bean:message key="label.address.currentlocation"/>
                        </p>
                        <p class="contentclass">
                            <label id="institutionlabel" class="labelclass">
                                <bean:message key="label.institution"/>
                            </label>
                            ${personForm.institution}
                        </p>
                        <p class="contentclass">
                            <label id="institutionstreetlabel" class="labelclass">
                                <bean:message key="label.address.street"/>
                            </label>
                            ${personForm.institutionStreet}
                        </p>
                        <p class="contentclass">
                            <label id="institutioncitylabel" class="labelclass">
                                <bean:message key="label.address.city"/>
                            </label>
                            ${personForm.institutionCity}
                        </p>
                        <p class="contentclass">
                            <label id="institutionprovincelabel" class="labelclass">
                                <bean:message key="label.address.province"/>
                            </label>
                            ${personForm.institutionProvince}
                        </p>
                        <p class="contentclass">
                            <label id="institutioncountrylabel" class="labelclass">
                                <bean:message key="label.address.country"/>
                            </label>
                            ${personForm.institutionCountry}
                        </p>
                        <p class="contentclass">
                            <label id="institutionemaillabel" class="labelclass">
                                <bean:message key="label.email"/>
                            </label>
                            ${personForm.institutionEmail}
                        </p>
                        <p class="contentclass">
                            <label id="institutionnumberlabel" class="labelclass">
                                <bean:message key="label.number"/>
                            </label>
                            ${personForm.institutionNumber}
                        </p>
                    </div>
                    <p class="contentclass">
                        <label id="codisidlabel" class="labelclass">
                            <bean:message key="label.codis.id"/>
                        </label>
                        ${personForm.codisId}
                    </p>
                    <p class="contentclass">
                        <label id="afisidlabel" class="labelclass">
                            <bean:message key="label.afis.id"/>
                        </label>
                        ${personForm.afisId}
                    </p>
                    <p class="contentclass">
                        <label id="dentalidlabel" class="labelclass">
                            <bean:message key="label.dental.id"/>
                        </label>
                        ${personForm.dentalId}
                    </p>
                    <p class="contentclass">
                        <c:if test="${currentuser.groupId == 2}">
                            <bean:message key="case.report" arg0="${reportcount}" arg1="${personForm.id}"/>
                        </c:if>
                    </p>
                    <p class="contentclass">
                        <bean:message key="case.print.poster" arg0="${personForm.id}"/>
                    </p>

                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>