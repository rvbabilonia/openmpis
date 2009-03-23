<%@ page contentType="text/html" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
        <title><bean:message key="case.title"/></title>
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
                        <bean:message key="case.menu"/>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <div id="casemenu">
                        <bean:message key="case.submenu"/>
                    </div>
                    <bean:message key="case.view"/>

                    <div class="table">
                        <div class="row">
                            <div class="hiddencell">
                                <h2 style="text-align: center;">
                                    ${personForm.firstName}
                                    <c:if test="${personForm.nickname != ''}">
                                        "${personForm.nickname}"
                                    </c:if>
                                    ${personForm.middleName}
                                    ${personForm.lastName}
                                </h2>
                            </div>
                        </div>
                    </div>
                    <div class="table">
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p style="text-align: right;">
                                    <html:img styleClass="photoclass" src="${personForm.photo == null ? '/photo/unknown.png' : personForm.photo}" alt="The person's photo" title="Photo"/>
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p style="text-align: left;">
                                    <html:img styleClass="photoclass" src="${personForm.agedPhoto == null ? '/photo/unknown.png' : personForm.agedPhoto}" alt="The person's aged-progressed photo" title="Age-progressed photo"/>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="statuslabel" class="labelclass">
                                        <bean:message key="label.status"/>
                                    </label>
                                    <bean:message key="status.case.${personForm.status}"/>
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="typelabel" class="labelclass">
                                        <bean:message key="label.case.type"/>
                                    </label>
                                    <bean:message key="type.${personForm.type}"/>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="birthdatelabel" class="labelclass">
                                        <bean:message key="label.date.birth"/>
                                    </label>
                                    <c:choose>
                                        <c:when test="${personForm.knownBirthDate == 'true'}">
                                            <bean:message key="month.${personForm.birthMonth}"/>&nbsp;${personForm.birthDay},&nbsp;${personForm.birthYear}
                                        </c:when>
                                        <c:otherwise>
                                            <bean:message key="label.unknown"/>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="agelabel" class="labelclass">
                                        <bean:message key="label.age"/>
                                    </label>
                                    ${personForm.age}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="addresslabel" class="labelclass">
                                        <bean:message key="label.address"/>
                                    </label>
                                    ${personForm.street}
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    ${personForm.city}, ${personForm.province}, ${personForm.country}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="sexlabel" class="labelclass">
                                        <bean:message key="label.sex"/>
                                    </label>
                                    <bean:message key="sex.${personForm.sex}"/>
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="haircolorlabel" class="labelclass">
                                        <bean:message key="label.color.hair"/>
                                    </label>
                                    <bean:message key="color.hair.${personForm.hairColor}"/>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="heightlabel" class="labelclass">
                                        <bean:message key="label.height"/>
                                    </label>
                                    ${personForm.feet}&nbsp;<bean:message key="label.height.ft"/>
                                    ${personForm.inches}&nbsp;<bean:message key="label.height.in"/>
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="eyecolorlabel" class="labelclass">
                                        <bean:message key="label.color.eye"/>
                                    </label>
                                    <bean:message key="color.eye.${personForm.eyeColor}"/>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="weightlabel" class="labelclass">
                                        <bean:message key="label.weight"/>
                                    </label>
                                    ${personForm.weight}&nbsp;<bean:message key="label.weight.lbs"/>
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="racelabel" class="labelclass">
                                        <bean:message key="label.race"/>
                                    </label>
                                    <bean:message key="race.${personForm.race}"/>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="religionlabel" class="labelclass">
                                        <bean:message key="label.religion"/>
                                    </label>
                                    <bean:message key="religion.${personForm.religion}"/>
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="medicalconditionlabel" class="labelclass">
                                        <bean:message key="label.condition"/>
                                    </label>
                                    ${personForm.medicalCondition}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="markslabel" class="labelclass">
                                        <bean:message key="label.marks"/>
                                    </label>
                                    ${personForm.marks}
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="personaleffectslabel" class="labelclass">
                                        <bean:message key="label.personaleffects"/>
                                    </label>
                                    ${personForm.personalEffects}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="remarkslabel" class="labelclass">
                                        <bean:message key="label.remarks"/>
                                    </label>
                                    ${personForm.remarks}
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="codisidlabel" class="labelclass">
                                        <bean:message key="label.codis.id"/>
                                    </label>
                                    ${personForm.codisId}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="afisidlabel" class="labelclass">
                                        <bean:message key="label.afis.id"/>
                                    </label>
                                    ${personForm.afisId}
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="dentalidlabel" class="labelclass">
                                        <bean:message key="label.dental.id"/>
                                    </label>
                                    ${personForm.dentalId}
                                </p>
                            </div>
                        </div>
                        <c:if test="${currentuser.groupId == 2}">
                            <div class="row">
                                <div class="hiddencell" style="width: 50%;">
                                    <p class="contentclass">
                                        <label id="relativelabel" class="labelclass">
                                            <bean:message key="label.relative.name"/>
                                        </label>
                                        <c:url var="url" scope="page" value="/viewRelative.do">
                                            <c:param name="action" value="viewRelative"/>
                                            <c:param name="id" value="${personForm.relativeId}"/>
                                            <c:param name="personid" value="${personForm.id}"/>
                                        </c:url>
                                        <html:link href="${fn:escapeXml(url)}">${personForm.relativeFirstName} ${personForm.relativeLastName}</html:link>
                                    </p>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <c:choose>
                        <c:when test="${personForm.type > 4}">
                            <c:set var="missing" value="display: none;"/>
                            <c:set var="found" value="display: table; width: 80%; border-spacing: 1px;"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="missing" value="display: table; width: 80%; border-spacing: 1px;"/>
                            <c:set var="found" value="display: none;"/>
                        </c:otherwise>
                    </c:choose>
                    <div id="ifmissing" style="${missing}">
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="datemissinglabel" class="labelclass">
                                        <bean:message key="label.date.missing"/>
                                    </label>
                                    <bean:message key="month.${personForm.monthMissingOrFound}"/>&nbsp;${personForm.dayMissingOrFound},&nbsp;${personForm.yearMissingOrFound}
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    ${personForm.daysMissing}&nbsp;<bean:message key="label.missing.day"/>
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="missingfromcitylabel" class="labelclass">
                                        <bean:message key="label.address.missingfrom"/>
                                    </label>
                                    ${personForm.missingFromCity}, ${personForm.missingFromProvince}, ${personForm.missingFromCountry}
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="possiblecitylabel" class="labelclass">
                                        <bean:message key="label.address.possiblelocation"/>
                                    </label>
                                    ${personForm.possibleCity}, ${personForm.possibleProvince}, ${personForm.possibleCountry}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="circumstancelabel" class="labelclass">
                                        <bean:message key="label.circumstance"/>
                                    </label>
                                    ${personForm.circumstance}
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="rewardlabel" class="labelclass">
                                        <bean:message key="label.reward"/>
                                    </label>
                                    ${personForm.reward}
                                </p>
                            </div>
                        </div>
                        <c:if test="${(personForm.type == 1) || (personForm.type == 2)}">
                            <div class="row">
                                <div class="hiddencell" style="width: 50%;">
                                    <p class="contentclass">
                                        <label id="abductorlabel" class="labelclass">
                                            <bean:message key="label.abductor.name"/>
                                        </label>
                                        <c:url var="url" scope="page" value="/viewAbductor.do">
                                            <c:param name="action" value="viewAbductor"/>
                                            <c:param name="id" value="${personForm.abductorId}"/>
                                            <c:param name="personid" value="${personForm.id}"/>
                                        </c:url>
                                        <html:link href="${fn:escapeXml(url)}">${personForm.abductorId} - ${personForm.abductorFirstName} ${personForm.abductorLastName}</html:link>
                                    </p>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div id="iffound" style="${found}">
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="datefoundlabel" class="labelclass">
                                        <bean:message key="label.date.found"/>
                                    </label>
                                    <bean:message key="month.${personForm.monthMissingOrFound}"/>&nbsp;${personForm.dayMissingOrFound},&nbsp;${personForm.yearMissingOrFound}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="institutionlabel" class="labelclass">
                                        <bean:message key="label.address.currentlocation"/>
                                    </label>
                                    ${personForm.institution}, ${personForm.institutionStreet}
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="institutioncitylabel" class="labelclass">
                                        <bean:message key="label.address.city"/>
                                    </label>
                                    ${personForm.institutionCity}, ${personForm.institutionProvince}, ${personForm.institutionCountry}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="institutionemaillabel" class="labelclass">
                                        <bean:message key="label.email"/>
                                    </label>
                                    ${personForm.institutionEmail}
                                </p>
                            </div>
                            <div class="hiddencell" style="width: 50%;">
                                <p class="contentclass">
                                    <label id="institutionnumberlabel" class="labelclass">
                                        <bean:message key="label.number"/>
                                    </label>
                                    ${personForm.institutionNumber}
                                </p>
                            </div>
                        </div>
                    </div>
                    <c:if test="${personForm.status != 1}">
                        <div class="table">
                            <div class="row">
                                <div class="hiddencell" style="width: 50%;">
                                    <p style="text-align: center;">
                                        <bean:message key="case.print.poster" arg0="${personForm.id}"/>
                                        <c:if test="${currentuser.groupId != 2}">
                                            <bean:message key="case.add.sighting.button" arg0="${personForm.id}"/>
                                        </c:if>
                                        <c:if test="${currentuser.groupId == 2}">
                                            <bean:message key="case.add.progress.button" arg0="${personForm.id}"/>
                                            <bean:message key="case.print.progress.buttons" arg0="${personForm.id}"/>
                                        </c:if>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>