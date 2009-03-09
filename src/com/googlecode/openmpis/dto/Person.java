/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.googlecode.openmpis.dto;

/**
 * The Person class is used to represent a missing or found person.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class Person {

    /**
     * The person ID
     */
    private int id;
    /**
     * The status of this case
     */
    private int status;
    /**
     * The type of this case
     */
    private int type;
    /**
     * The first name of the person
     */
    private String firstName;
    /**
     * The nickname of the person
     */
    private String nickname;
    /**
     * The middle name of the person
     */
    private String middleName;
    /**
     * The last name of the person
     */
    private String lastName;
    /**
     * The birth month
     */
    private int birthMonth;
    /**
     * The birth day
     */
    private int birthDay;
    /**
     * The birth year
     */
    private int birthYear;
    /**
     * The street address of the person
     */
    private String street;
    /**
     * The home city of the person
     */
    private String city;
    /**
     * The home province of the person
     */
    private String province;
    /**
     * The home country of the person
     */
    private String country;
    /**
     * The sex of the person
     */
    private int sex;
    /**
     * The height of the person in feet
     */
    private int feet;
    /**
     * The additional inches to the person's height
     */
    private int inches;
    /**
     * The weight of the person in pounds
     */
    private double weight;
    /**
     * The religion of the person
     */
    private int religion;
    /**
     * The race of the person
     */
    private int race;
    /**
     * The eye color of the person
     */
    private int eyeColor;
    /**
     * The hair color of the person
     */
    private int hairColor;
    /**
     * The medical condition of the person
     */
    private String medicalCondition;
    /**
     * The distinguishing marks of the person
     */
    private String marks;
    /**
     * The personal effects worn by the person
     */
    private String personalEffects;
    /**
     * The remarks to the person
     */
    private String remarks;
    /**
     * The month the person has gone missing or was found
     */
    private int monthMissingOrFound;
    /**
     * The day the person has gone missing or was found
     */
    private int dayMissingOrFound;
    /**
     * The year the person has gone missing or was found
     */
    private int yearMissingOrFound;
    /**
     * The city the person disappeared from
     */
    private String missingFromCity;
    /**
     * The province the person disappeared from
     */
    private String missingFromProvince;
    /**
     * The country the person disappeared from
     */
    private String missingFromCountry;
    /**
     * The possible city the person is in
     */
    private String possibleCity;
    /**
     * The possible province the person is in
     */
    private String possibleProvince;
    /**
     * The possible country the person is in
     */
    private String possibleCountry;
    /**
     * The circumstance of disappearance
     */
    private String circumstance;
    /**
     * The reward money
     */
    private Integer reward;
    /**
     * The institution which has custody of the person
     */
    private String institution;
    /**
     * The street address of the institution
     */
    private String institutionStreet;
    /**
     * The city of the institution
     */
    private String institutionCity;
    /**
     * The province of the institution
     */
    private String institutionProvince;
    /**
     * The country of the institution
     */
    private String institutionCountry;
    /**
     * The email address of the institution
     */
    private String institutionEmail;
    /**
     * The contact number of the institution
     */
    private String institutionNumber;
    /**
     * The photo of the person
     */
    private String photo;
    /**
     * The optional age-progressed photo of the person
     */
    private String agedPhoto;
    /**
     * The optional DNA ID of the person
     */
    private String codisId;
    /**
     * The optional fingerprint ID of the person
     */
    private String afisId;
    /**
     * The optional dental ID of the person
     */
    private String dentalId;
    /**
     * The ID of the relative
     */
    private Integer relativeId;
    /**
     * The ID of the investigator
     */
    private Integer investigatorId;
    /**
     * The ID of the abductor
     */
    private Integer abductorId;
    /**
     * The date this case was created
     */
    private String date;
    /**
     * The ID of the encoder
     */
    private int encoderId;
    /**
     * The relationship of the relative to the person
     */
    private Integer relationToRelative;
    /**
     * The relationship of the abductor to the person
     */
    private Integer relationToAbductor;

    /**
     * Gets the ID of the person.
     *
     * @return               the ID of the person
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     *
     * @param id            the ID of the person
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the status of the case.
     * 
     * @return              the status of the case
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the status of the case.
     * 
     * @param status        the status of the case
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the type of the case.
     *
     * @return              the type of the case
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the type of the case.
     *
     * @param type          the type of the case
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets the first name of the person.
     *
     * @return               the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName     the first name of the person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the nickname of the person.
     *
     * @return              the nickname of the person
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the nickname of the person.
     *
     * @param nickname      the nickname of the person
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Gets the middle name of the person.
     *
     * @return               the middle name of the person
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name of the person.
     *
     * @param middleName    the middle name of the person
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the last name of the person.
     *
     * @return               the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName      the last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the birth day.
     *
     * @return              the birth day
     */
    public int getBirthDay() {
        return birthDay;
    }

    /**
     * Sets the birth day.
     *
     * @param birthDay      the birth day
     */
    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * Gets the birth month.
     *
     * @return              the birth month
     */
    public int getBirthMonth() {
        return birthMonth;
    }

    /**
     * Sets the birth month.
     *
     * @param birthMonth    the birth month
     */
    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    /**
     * Gets the birth year.
     *
     * @return              the birth year
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Sets the birth year.
     *
     * @param birthYear     the birth year
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * Gets the street address of the person.
     *
     * @return              the street address of the person
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street address of the person.
     *
     * @param street        the street address of the person
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Sets the home city of the person.
     *
     * @return              the home city of the person
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the home city of the person.
     *
     * @param city          the home city of the person
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the home province of the person.
     *
     * @return              the home province of the person
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the home province of the person.
     *
     * @param province      the home province of the person
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Gets the home country of the person.
     *
     * @return              the home country of the person
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the home country of the person.
     *
     * @param country       the home country of the person
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the sex of the person.
     *
     * @return              the sex of the person
     */
    public int getSex() {
        return sex;
    }

    /**
     * Sets the sex of the person.
     *
     * @param sex           the sex of the person
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * Gets the height of the person in feet.
     *
     * @return              the height of the person in feet
     */
    public int getFeet() {
        return feet;
    }

    /**
     * Sets the height of the person in feet.
     *
     * @param feet          he height of the person in feet
     */
    public void setFeet(int feet) {
        this.feet = feet;
    }

    /**
     * Gets the additional inches to the person's height.
     *
     * @return              the additional inches to the person's height
     */
    public int getInches() {
        return inches;
    }

    /**
     * Sets the additional inches to the person's height.
     *
     * @param inches        the additional inches to the person's height
     */
    public void setInches(int inches) {
        this.inches = inches;
    }

    /**
     * Gets the weight of the person.
     *
     * @return              the weight of the person
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the person.
     *
     * @param weight        the weight of the person
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets the religion of the person.
     *
     * @return              the religion of the person
     */
    public int getReligion() {
        return religion;
    }

    /**
     * Sets the religion of the person.
     *
     * @param religion      the religion of the person
     */
    public void setReligion(int religion) {
        this.religion = religion;
    }

    /**
     * Gets the race of the person.
     *
     * @return              the race of the person
     */
    public int getRace() {
        return race;
    }

    /**
     * Sets the race of the person.
     *
     * @param race          the race of the person
     */
    public void setRace(int race) {
        this.race = race;
    }

    /**
     * Gets the eye color of the person.
     *
     * @return              the eye color of the person
     */
    public int getEyeColor() {
        return eyeColor;
    }

    /**
     * Sets the eye color of the person.
     *
     * @param eyeColor      the eye color of the person
     */
    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Gets the hair color of the person.
     *
     * @return              the hair color of the person
     */
    public int getHairColor() {
        return hairColor;
    }

    /**
     * Sets the hair color of the person.
     *
     * @param hairColor     the hair color of the person
     */
    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Gets the medical condition of the person.
     *
     * @return              the medical condition of the person
     */
    public String getMedicalCondition() {
        return medicalCondition;
    }

    /**
     * Sets the medical condition of the person.
     *
     * @param medicalCondition      the medical condition of the person
     */
    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    /**
     * Gets the distinguishing marks of the person.
     *
     * @return              the distinguishing marks of the person
     */
    public String getMarks() {
        return marks;
    }

    /**
     * Sets the distinguishing marks of the person.
     *
     * @param marks         the distinguishing marks of the person
     */
    public void setMarks(String marks) {
        this.marks = marks;
    }

    /**
     * Gets the personal effects worn by the person.
     *
     * @return              the personal effects worn by the person
     */
    public String getPersonalEffects() {
        return personalEffects;
    }

    /**
     * Sets the personal effects worn by the person.
     *
     * @param personalEffects the personal effects worn by the person
     */
    public void setPersonalEffects(String personalEffects) {
        this.personalEffects = personalEffects;
    }

    /**
     * Gets the remarks to the person.
     *
     * @return              the remarks to the person
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the remarks to the person.
     *
     * @param remarks       the remarks to the person
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Gets the month the person has gone missing or was found.
     *
     * @return              the month the person has gone missing or was found
     */
    public int getMonthMissingOrFound() {
        return monthMissingOrFound;
    }

    /**
     * Sets the month the person has gone missing or was found.
     *
     * @param monthMissingOrFound the month the person has gone missing or was found
     */
    public void setMonthMissingOrFound(int monthMissingOrFound) {
        this.monthMissingOrFound = monthMissingOrFound;
    }

    /**
     * Gets the day the person has gone missing or was found.
     *
     * @return              the day the person has gone missing or was found
     */
    public int getDayMissingOrFound() {
        return dayMissingOrFound;
    }

    /**
     * Sets the day the person has gone missing or was found.
     *
     * @param dayMissingOrFound the day the person has gone missing or was found
     */
    public void setDayMissingOrFound(int dayMissingOrFound) {
        this.dayMissingOrFound = dayMissingOrFound;
    }

    /**
     * Gets the year the person has gone missing or was found.
     *
     * @return              the year the person has gone missing or was found
     */
    public int getYearMissingOrFound() {
        return yearMissingOrFound;
    }

    /**
     * Sets the year the person has gone missing or was found.
     *
     * @param yearMissingOrFound the year the person has gone missing or was found
     */
    public void setYearMissingOrFound(int yearMissingOrFound) {
        this.yearMissingOrFound = yearMissingOrFound;
    }

    /**
     * Gets the city the person disappeared from.
     *
     * @return              the city the person disappeared from
     */
    public String getMissingFromCity() {
        return missingFromCity;
    }

    /**
     * Sets the city the person disappeared from.
     *
     * @param missingFromCity the city the person disappeared from
     */
    public void setMissingFromCity(String missingFromCity) {
        this.missingFromCity = missingFromCity;
    }

    /**
     * Gets the province the person disappeared from.
     *
     * @return              the province the person disappeared from
     */
    public String getMissingFromProvince() {
        return missingFromProvince;
    }

    /**
     * Sets the province the person disappeared from.
     *
     * @param missingFromProvince the province the person disappeared from
     */
    public void setMissingFromProvince(String missingFromProvince) {
        this.missingFromProvince = missingFromProvince;
    }

    /**
     * Gets the country the person disappeared from.
     *
     * @return              the country the person disappeared from
     */
    public String getMissingFromCountry() {
        return missingFromCountry;
    }

    /**
     * Sets the country the person disappeared from.
     *
     * @param missingFromCountry the country the person disappeared from
     */
    public void setMissingFromCountry(String missingFromCountry) {
        this.missingFromCountry = missingFromCountry;
    }

    /**
     * Gets the possible city the person is in.
     *
     * @return              the possible city the person is in
     */
    public String getPossibleCity() {
        return possibleCity;
    }

    /**
     * Sets the possible city the person is in.
     *
     * @param possibleCity  the possible city the person is in
     */
    public void setPossibleCity(String possibleCity) {
        this.possibleCity = possibleCity;
    }

    /**
     * Gets the possible province the person is in.
     *
     * @return              the possible province the person is in
     */
    public String getPossibleProvince() {
        return possibleProvince;
    }

    /**
     * Sets the possible province the person is in.
     *
     * @param possibleProvince the possible province the person is in
     */
    public void setPossibleProvince(String possibleProvince) {
        this.possibleProvince = possibleProvince;
    }

    /**
     * Gets the possible country the person is in.
     *
     * @return              the possible country the person is in
     */
    public String getPossibleCountry() {
        return possibleCountry;
    }

    /**
     * Sets the possible country the person is in.
     *
     * @param possibleCountry the possible country the person is in
     */
    public void setPossibleCountry(String possibleCountry) {
        this.possibleCountry = possibleCountry;
    }

    /**
     * Gets the circumstance of disappearance.
     *
     * @return              the circumstance of disappearance
     */
    public String getCircumstance() {
        return circumstance;
    }

    /**
     * Sets the circumstance of disappearance.
     *
     * @param circumstance  the circumstance of disappearance
     */
    public void setCircumstance(String circumstance) {
        this.circumstance = circumstance;
    }

    /**
     * Gets the reward money.
     *
     * @return              the reward money
     */
    public Integer getReward() {
        return reward;
    }

    /**
     * Sets the reward money.
     *
     * @param reward        the reward money
     */
    public void setReward(Integer reward) {
        this.reward = reward;
    }

    /**
     * Gets the institution which has custody of the person.
     * @return              the institution which has custody of the person
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * Sets the institution which has custody of the person.
     *
     * @param institution   the institution which has custody of the person
     */
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    /**
     * Gets the street address of the institution.
     *
     * @return              the street address of the institution
     */
    public String getInstitutionStreet() {
        return institutionStreet;
    }

    /**
     * Sets the street address of the institution.
     *
     * @param institutionStreet the street address of the institution
     */
    public void setInstitutionStreet(String institutionStreet) {
        this.institutionStreet = institutionStreet;
    }

    /**
     * Gets the city of the institution.
     *
     * @return              the city of the institution
     */
    public String getInstitutionCity() {
        return institutionCity;
    }

    /**
     * Sets the city of the institution.
     *
     * @param institutionCity the city of the institution
     */
    public void setInstitutionCity(String institutionCity) {
        this.institutionCity = institutionCity;
    }

    /**
     * Gets the province of the institution.
     *
     * @return              the province of the institution
     */
    public String getInstitutionProvince() {
        return institutionProvince;
    }

    /**
     * Sets the province of the institution.
     *
     * @param institutionProvince the province of the institution
     */
    public void setInstitutionProvince(String institutionProvince) {
        this.institutionProvince = institutionProvince;
    }

    /**
     * Gets the country of the institution.
     *
     * @return              the country of the institution
     */
    public String getInstitutionCountry() {
        return institutionCountry;
    }

    /**
     * Sets the country of the institution.
     *
     * @param institutionCountry the country of the institution
     */
    public void setInstitutionCountry(String institutionCountry) {
        this.institutionCountry = institutionCountry;
    }

    /**
     * Gets the email address of the institution.
     *
     * @return              the email address of the institution
     */
    public String getInstitutionEmail() {
        return institutionEmail;
    }

    /**
     * Sets the email address of the institution.
     *
     * @param institutionEmail the email address of the institution
     */
    public void setInstitutionEmail(String institutionEmail) {
        this.institutionEmail = institutionEmail;
    }

    /**
     * Gets the contact number of the institution.
     *
     * @return              the contact number of the institution
     */
    public String getInstitutionNumber() {
        return institutionNumber;
    }

    /**
     * Sets the contact number of the institution.
     *
     * @param institutionNumber the contact number of the institution
     */
    public void setInstitutionNumber(String institutionNumber) {
        this.institutionNumber = institutionNumber;
    }


    /**
     * Gets the photo of the person.
     *
     * @return              the photo of the person
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets the photo of the person.
     *
     * @param photo         the photo of the person
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Gets the optional age-progressed photo of the person.
     *
     * @return              the optional age-progressed photo of the person
     */
    public String getAgedPhoto() {
        return agedPhoto;
    }

    /**
     * Sets the optional age-progressed photo of the person.
     *
     * @param agedPhoto     the optional age-progressed photo of the person
     */
    public void setAgedPhoto(String agedPhoto) {
        this.agedPhoto = agedPhoto;
    }

    /**
     * Gets the optional DNA ID of the person.
     *
     * @return              the optional DNA ID of the person
     */
    public String getCodisId() {
        return codisId;
    }

    /**
     * Sets the optional DNA ID of the person.
     *
     * @param codisId       the optional DNA ID of the person
     */
    public void setCodisId(String codisId) {
        this.codisId = codisId;
    }

    /**
     * Gets the optional fingerprint ID of the person.
     *
     * @return              the optional fingerprint ID of the person
     */
    public String getAfisId() {
        return afisId;
    }

    /**
     * Sets the optional fingerprint ID of the person.
     *
     * @param afisId        the optional fingerprint ID of the person
     */
    public void setAfisId(String afisId) {
        this.afisId = afisId;
    }

    /**
     * Gets the optional dental ID of the person.
     *
     * @return              the optional dental ID of the person
     */
    public String getDentalId() {
        return dentalId;
    }

    /**
     * Sets the optional dental ID of the person.
     *
     * @param dentalId      the optional dental ID of the person
     */
    public void setDentalId(String dentalId) {
        this.dentalId = dentalId;
    }

    /**
     * Gets the ID of the relative.
     *
     * @return              the ID of the relative
     */
    public Integer getRelativeId() {
        return relativeId;
    }

    /**
     * Sets the ID of the relative.
     *
     * @param relativeId    the ID of the relative
     */
    public void setRelativeId(Integer relativeId) {
        this.relativeId = relativeId;
    }

    /**
     * Gets the ID of the investigator.
     *
     * @return              the ID of the investigator
     */
    public Integer getInvestigatorId() {
        return investigatorId;
    }

    /**
     * Sets the ID of the investigator.
     *
     * @param investigatorId the ID of the investigator
     */
    public void setInvestigatorId(Integer investigatorId) {
        this.investigatorId = investigatorId;
    }

    /**
     * Gets the ID of the abductor.
     *
     * @return              the ID of the abductor
     */
    public Integer getAbductorId() {
        return abductorId;
    }

    /**
     * Sets the ID of the abductor.
     *
     * @param abductorId    the ID of the abductor
     */
    public void setAbductorId(Integer abductorId) {
        this.abductorId = abductorId;
    }

    /**
     * Gets the date this case was created.
     *
     * @return              the date this case was created
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date this case was created.
     *
     * @param date          the date this case was created
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the ID of the encoder.
     *
     * @return              the ID of the encoder
     */
    public int getEncoderId() {
        return encoderId;
    }

    /**
     * Sets the ID of the encoder.
     *
     * @param encoderId     the ID of the encoder
     */
    public void setEncoderId(int encoderId) {
        this.encoderId = encoderId;
    }

    /**
     * Gets the relationship of the relative to the person.
     *
     * @return              the relationship of the relative to the person
     */
    public Integer getRelationToRelative() {
        return relationToRelative;
    }

    /**
     * Sets the relationship of the relative to the person.
     *
     * @param relationToRelative the relationship of the relative to the person
     */
    public void setRelationToRelative(Integer relationToRelative) {
        this.relationToRelative = relationToRelative;
    }

    /**
     * Gets the relationship of the abductor to the person.
     *
     * @return              the relationship of the abductor to the person
     */
    public Integer getRelationToAbductor() {
        return relationToAbductor;
    }

    /**
     * Sets the relationship of the abductor to the person.
     *
     * @param relationToAbductor the relationship of the abductor to the person
     */
    public void setRelationToAbductor(Integer relationToAbductor) {
        this.relationToAbductor = relationToAbductor;
    }

    /**
     * Returns a String representation of this data transfer object.
     *
     * @return              the String representation of this data transfer object
     */
    @Override
    public String toString() {
        String content = "";
        content += "\nID: " + id;
        content += "\nCase Status: " + status;
        content += "\nCase Type: " + type;
        content += "\nFirst Name: " + firstName;
        content += "\nNickname: " + nickname;
        content += "\nMiddle Name: " + middleName;
        content += "\nLast Name: " + lastName;
        content += "\nBirth Month: " + birthMonth;
        content += "\nBirth Day: " + birthDay;
        content += "\nBirth Year: " + birthYear;
        content += "\nStreet: " + street;
        content += "\nCity: " + city;
        content += "\nProvince: " + province;
        content += "\nCountry: " + country;
        content += "\nSex: " + sex;
        content += "\nFeet: " + feet;
        content += "\nInches: " + inches;
        content += "\nWeight: " + weight;
        content += "\nReligion: " + religion;
        content += "\nRace: " + race;
        content += "\nEye Color: " + eyeColor;
        content += "\nHair Color: " + hairColor;
        content += "\nMedical Condition: " + medicalCondition;
        content += "\nDistinguishing Marks: " + marks;
        content += "\nPersonal Effects: " + personalEffects;
        content += "\nRemarks: " + remarks;
        content += "\nMonth Missing or Found: " + monthMissingOrFound;
        content += "\nDay Missing or Found: " + dayMissingOrFound;
        content += "\nYear Missing or Found: " + yearMissingOrFound;
        content += "\nMissing from City: " + missingFromCity;
        content += "\nMissing from Province: " + missingFromProvince;
        content += "\nMissing from Country: " + missingFromCountry;
        content += "\nPossible City: " + possibleCity;
        content += "\nPossible Province: " + possibleProvince;
        content += "\nPossible Country: " + possibleCountry;
        content += "\nCircumstance of Disappearance: " + circumstance;
        content += "\nReward Money: " + reward;
        content += "\nInstitution: " + institution;
        content += "\nStreet: " + institutionStreet;
        content += "\nCity: " + institutionCity;
        content += "\nProvince: " + institutionProvince;
        content += "\nCountry: " + institutionCountry;
        content += "\nEmail: " + institutionEmail;
        content += "\nNumber: " + institutionNumber;
        content += "\nPhoto Filename: " + photo;
        content += "\nAged Photo Filename: " + agedPhoto;
        content += "\nCODIS ID: " + codisId;
        content += "\nAFIS ID: " + afisId;
        content += "\nDental ID: " + dentalId;
        content += "\nRelative ID: " + relativeId;
        content += "\nInvestigator ID: " + investigatorId;
        content += "\nAbductor ID: " + abductorId;
        content += "\nRelation to Relative: " + relationToRelative;
        content += "\nRelation to Abductor: " + relationToAbductor;
        content += "\nDate: " + date;
        content += "\nEncoder ID: " + encoderId;
        return content;
    }
}