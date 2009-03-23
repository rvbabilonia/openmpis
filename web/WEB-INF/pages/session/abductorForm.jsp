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
        <title><bean:message key="abductor.title"/></title>
    </head>
    <body onload="javascript: setCities('${abductorForm.country}', '${abductorForm.province}', '${abductorForm.city}');
        setProvinces('${abductorForm.country}', '${abductorForm.province}');
        setCountry('${abductorForm.country}');">
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
                    <c:otherwise>
                        <bean:message key="home.menu"/>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Content -->
            <div id="content">
                <div id="contentitem">
                    <c:choose>
                        <c:when test="${currentuser.groupId == 1}">
                            <c:choose>
                                <c:when test="${((action == 'newAbductor') && (abductorForm.id == 0)) || (action == 'addAbductor')}">
                                    <bean:message key="abductor.add"/>
                                </c:when>
                                <c:when test="${((action == 'viewAbductor') && (abductorForm.personId != null)) || (action == 'editAbductor') || ((action == 'newAbductor') && (abductorForm.id != 0))}">
                                    <bean:message key="abductor.edit"/>
                                </c:when>
                                <c:when test="${(action == 'viewAbductor') && (abductorForm.personId == null)}">
                                    <bean:message key="abductor.view"/>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <bean:message key="abductor.view"/>
                        </c:otherwise>
                    </c:choose>
                    <noscript>
                        <bean:message key="error.javascript.disabled"/>
                    </noscript>
                    
                    <html:form method="post" action="viewAbductor" styleClass="adduserclass" enctype="multipart/form-data">
                        <p class="contentclass">
                            <c:choose>
                                <c:when test="${((action == 'newAbductor') && (abductorForm.id == 0)) || (action == 'addAbductor')}">
                                    <html:hidden property="action" value="addAbductor"/>
                                    <html:hidden property="personId"/>
                                </c:when>
                                <c:when test="${((action == 'viewAbductor') && (abductorForm.personId != null)) || (action == 'editAbductor') || ((action == 'newAbductor') && (abductorForm.id != 0))}">
                                    <html:hidden property="action" value="editAbductor"/>
                                    <html:hidden property="personId"/>
                                </c:when>
                            </c:choose>
                        </p>
                        <c:if test="${currentuser.groupId == 1}">
                            <p class="contentclass">
                                <label id="abductorlistlabel" class="labelclass" for="abductorlistfield">
                                    <bean:message key="label.abductor.existing"/>
                                </label>
                                <html:select styleId="abductorlistfield" styleClass="selectclass" property="id"
                                    onchange="javascript: if (abductorlistfield.value > 0) {window.location = 'viewAbductor.do?action=viewAbductor&id=' + this.value + '&personid=' + ${abductorForm.personId};} else {window.location = 'viewAbductor.do?action=newAbductor' + '&personid=' + ${abductorForm.personId};}"
                                    onkeyup="javascript: if (abductorlistfield.value > 0) {window.location = 'viewAbductor.do?action=viewAbductor&id=' + this.value + '&personid=' + ${abductorForm.personId};} else {window.location = 'viewAbductor.do?action=newAbductor' + '&personid=' + ${abductorForm.personId};}">
                                    <html:option value="0" styleId="abductorfield0" styleClass="optionclass">----------</html:option>
                                    <c:forEach items="${abductorlist}" var="abductor">
                                        <html:option value="${abductor.id}" styleId="abductorfield${i}" styleClass="optionclass">${abductor.id} - ${abductor.lastName}, ${abductor.firstName}</html:option>
                                    </c:forEach>
                                </html:select>
                            </p>
                        </c:if>
                        <p class="contentclass">
                            <label id="idlabel" class="labelclass" for="idfield">
                                <bean:message key="label.id"/>
                            </label>
                            <html:text styleId="idfield" styleClass="inputclass" property="id" readonly="true"/>
                        </p>
                        <p class="contentclass">
                            <html:img styleClass="photoclass" src="${((abductorForm.photo == null) || (abductorForm.photo == '')) ? 'photo/unknown.png' : abductorForm.photo}" alt="The person's photo" title="Photo"/>
                            <html:img styleClass="photoclass" src="${((abductorForm.agedPhoto == null) || (abductorForm.agedPhoto == '')) ? 'photo/unknown.png' : abductorForm.agedPhoto}" alt="The person's aged-progressed photo" title="Age-progressed photo"/>
                        </p>
                        <c:if test="${currentuser.groupId == 1}">
                            <p class="contentclass">
                                <label id="photolabel" class="labelclass" for="photofile">
                                    <bean:message key="label.photo"/>
                                </label>
                                <html:file styleId="photofile" property="photoFile" accept="image/png,image/jpg,image/gif" styleClass="fileclass" size="33"/>
                                <html:errors property="photofile"/>
                            </p>
                            <p class="contentclass">
                                <label id="agedphotolabel" class="labelclass" for="agedphotofile">
                                    <bean:message key="label.agedphoto"/>
                                </label>
                                <html:file styleId="agedphotofile" property="agedPhotoFile" accept="image/png,image/jpg,image/gif" styleClass="fileclass" size="33"/>
                                <html:errors property="agedphotofile"/>
                            </p>
                        </c:if>
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
                            <c:choose>
                                <c:when test="${abductorForm.knownBirthDate == 'true'}">
                                    <c:set var="isDisabled" value="false"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="isDisabled" value="true"/>
                                </c:otherwise>
                            </c:choose>
                            <html:radio property="knownBirthDate" value="false" onchange="javascript: monthfield.disabled = true; dayfield.disabled = true; yearfield.disabled = true;">Unknown</html:radio>
                            <html:radio property="knownBirthDate" value="true" onchange="javascript: monthfield.disabled = false; dayfield.disabled = false; yearfield.disabled = false;">
                                <html:select styleId="monthfield" styleClass="monthselectclass" property="birthMonth" disabled="${isDisabled}"
                                    onchange="javascript: setAge();"
                                    onkeyup="javascript: setAge();">
                                    <c:forEach begin="1" end="12" step="1" var="i">
                                        <html:option value="${i}" styleId="monthfield${i}" styleClass="monthoptionclass"><bean:message key="month.${i}"/></html:option>
                                    </c:forEach>
                                </html:select>
                                <html:select styleId="dayfield" styleClass="dayselectclass" property="birthDay" disabled="${isDisabled}"
                                    onchange="javascript: setAge();"
                                    onkeyup="javascript: setAge();">
                                    <c:forEach begin="1" end="31" step="1" var="i">
                                        <html:option value="${i}" styleId="dayfield${i}" styleClass="dayoptionclass">${i}</html:option>
                                    </c:forEach>
                                </html:select>
                                <jsp:useBean id="currentDate" class="java.util.Date"/>
                                <fmt:formatDate var="currentYear" value="${currentDate}" pattern="yyyy"/>
                                <html:select styleId="yearfield" styleClass="yearselectclass" property="birthYear" disabled="${isDisabled}"
                                    onchange="javascript: setAge();"
                                    onkeyup="javascript: setAge();">
                                    <c:forEach begin="${currentYear - 80}" end="${currentYear}" step="1" var="i">
                                        <html:option value="${i}" styleId="yearfield${i}" styleClass="yearoptionclass">${i}</html:option>
                                    </c:forEach>
                                </html:select>
                                <bean:message key="label.age"/>
                                <html:text styleId="agefield" styleClass="ageinputclass" property="age" disabled="true"/>
                                <html:errors property="birthdate"/>
                            </html:radio>
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
                            <html:select styleId="cityfield" styleClass="selectclass" property="city">
                            </html:select>
                            <html:text styleId="citytextfield" styleClass="hiddeninputclass" property="city" maxlength="30" disabled="true"/>
                        </p>
                        <p class="contentclass">
                            <label id="provincelabel" class="labelclass" for="provincefield">
                                <bean:message key="label.address.province"/>
                            </label>
                            <html:select styleId="provincefield" styleClass="selectclass" property="province"
                                onchange="javascript: setCitiesOnProvinceChange(this, cityfield);"
                                onkeyup="javascript: setCitiesOnProvinceChange(this, cityfield);">
                            </html:select>
                            <html:text styleId="provincetextfield" styleClass="hiddeninputclass" property="province" maxlength="30" disabled="true"/>
                        </p>
                        <p class="contentclass">
                            <label id="countrylabel" class="labelclass" for="countryfield">
                                <bean:message key="label.address.country"/>
                            </label>
                            <html:select styleId="countryfield" styleClass="selectclass" property="country"
                                onchange="javascript: toggleSelectOrText(this, provincefield, provincetextfield, cityfield, citytextfield);"
                                onkeyup="javascript: toggleSelectOrText(this, provincefield, provincetextfield, cityfield, citytextfield);">
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <label id="sexlabel" class="labelclass" for="sexfield">
                                <bean:message key="label.sex"/>
                            </label>
                            <html:select styleId="sexfield" styleClass="selectclass" property="sex">
                                <html:option value="0" styleId="sexfield0" styleClass="optionclass"><bean:message key="sex.0"/></html:option>
                                <html:option value="1" styleId="sexfield1" styleClass="optionclass"><bean:message key="sex.1"/></html:option>
                                <html:option value="2" styleId="sexfield2" styleClass="optionclass"><bean:message key="sex.2"/></html:option>
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
                                * <bean:message key="label.remarks"/>
                            </label>
                            <html:textarea styleId="remarksfield" styleClass="textareaclass" property="remarks"/>
                            <html:errors property="remarks"/>
                        </p>
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
                            <label id="relationlabel" class="labelclass" for="relationfield">
                                * <bean:message key="label.relation"/>
                            </label>
                            <html:select styleId="relationfield" styleClass="selectclass" property="relationToAbductor">
                                <c:forEach begin="0" end="30" step="1" var="i">
                                    <html:option value="${i}" styleId="relationfield${i}" styleClass="optionclass"><bean:message key="relation.${i}"/></html:option>
                                </c:forEach>
                            </html:select>
                        </p>
                        <p class="contentclass">
                            <c:if test="${abductorForm.personId != 0}">
                                <c:if test="${currentuser.groupId == 1}">
                                    <c:choose>
                                        <c:when test="${((action == 'newAbductor') && (abductorForm.id == 0)) || (action == 'addAbductor')}">
                                            <bean:message key="abductor.add.buttons"/>
                                        </c:when>
                                        <c:when test="${((action == 'viewAbductor') && (abductorForm.personId != null)) || (action == 'editAbductor') || ((action == 'newAbductor') && (abductorForm.id != 0))}">
                                            <bean:message key="abductor.delete.buttons" arg0="${abductorForm.id}"/>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </c:if>
                            <bean:message key="abductor.print.poster" arg0="${abductorForm.id}"/>
                        </p>
                    </html:form>

                </div>
            </div>

            <tag:footer/>
        </div>
    </body>
</html:html>