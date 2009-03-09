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
package com.googlecode.openmpis.form;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * The AbductorForm class is used to represent an abductor.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class AbductorForm extends ActionForm {

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
     * The height of the person in centimeters
     */
    private double cm;
    /**
     * The weight of the person in pounds
     */
    private double weight;
    /**
     * The mass of the person in kilograms
     */
    private double mass;
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
     * The relationship of the abductor to the person
     */
    private int relationToAbductor;
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
     * The filename of the photo of the person
     */
    private String photo;
    /**
     * The photo of the person
     */
    private FormFile photoFile;
    /**
     * The filename of the optional age-progressed photo of the person
     */
    private String agedPhoto;
    /**
     * The optional age-progressed photo of the person
     */
    private FormFile agedPhotoFile;
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
     * The generated confirmation code
     */
    private int code;
    /**
     * The user-inputted confirmation code
     */
    private int userCode;
    /**
     * The current age
     */
    private int age;

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
        this.firstName = firstName.trim();
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
        this.nickname = nickname.trim();
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
        this.middleName = middleName.trim();
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
        this.lastName = lastName.trim();
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
        this.street = street.trim();
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
        this.city = city.trim();
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
        this.province = province.trim();
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
        this.country = country.trim();
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
     * Gets the height of the person in centimeters.
     *
     * @return              the height of the person in centimeters
     */
    public double getCm() {
        return cm;
    }

    /**
     * Sets the height of the person in centimeters.
     *
     * @param cm            the height of the person in centimeters
     */
    public void setCm(double cm) {
        this.cm = cm;
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
     * Gets the mass of the person in kilograms
     *
     * @return              the mass of the person in kilograms
     */
    public double getMass() {
        return mass;
    }

    /**
     * Sets the mass of the person in kilograms
     *
     * @param mass          the mass of the person in kilograms
     */
    public void setMass(double mass) {
        this.mass = mass;
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
        this.marks = marks.trim();
    }

    /**
     * Gets the relationship of the abductor to the person.
     *
     * @return              the relationship of the abductor to the person
     */
    public int getRelationToAbductor() {
        return relationToAbductor;
    }

    /**
     * Sets the relationship of the abductor to the person.
     *
     * @param relationToAbductor the relationship of the abductor to the person
     */
    public void setRelationToAbductor(int relationToAbductor) {
        this.relationToAbductor = relationToAbductor;
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
        this.personalEffects = personalEffects.trim();
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
        this.remarks = remarks.trim();
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
        this.photo = photo.trim();
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
        this.agedPhoto = agedPhoto.trim();
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
        this.codisId = codisId.trim();
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
        this.afisId = afisId.trim();
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
        this.dentalId = dentalId.trim();
    }

    /**
     * Gets the 4-digit randomly-generated confirmation code.
     *
     * @return              the 4-digit randomly-generated confirmation code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the 4-digit randomly-generated confirmation code.
     *
     * @param code          the 4-digit randomly-generated confirmation code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets the user-inputted confirmation code.
     *
     * @return              the user-inputted confirmation code
     */
    public int getUserCode() {
        return userCode;
    }

    /**
     * Sets the user-inputted confirmation code.
     *
     * @param userCode      the user-inputted confirmation code
     */
    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    /**
     * Gets the uploaded optional age-progressed photo.
     *
     * @return              the uploaded optional age-progressed photo
     */
    public FormFile getAgedPhotoFile() {
        return agedPhotoFile;
    }

    /**
     * Sets the optional age-progressed photo.
     *
     * @param agedPhotoFile the optional age-progressed photo
     */
    public void setAgedPhotoFile(FormFile agedPhotoFile) {
        this.agedPhotoFile = agedPhotoFile;
    }

    /**
     * Gets the uploaded photo of the person.
     *
     * @return              the uploaded photo of the person
     */
    public FormFile getPhotoFile() {
        return photoFile;
    }

    /**
     * Sets the uploaded photo of the person.
     *
     * @param photoFile     the uploaded photo of the person
     */
    public void setPhotoFile(FormFile photoFile) {
        this.photoFile = photoFile;
    }

    /**
     * Gets the current age.
     *
     * @return              the current age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the current age.
     *
     * @param age           the current age
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void reset(ActionMapping mapping, ServletRequest request) {
        super.reset(mapping, request);

        this.id = 0;
        this.status = 0;
        this.type = 0;
        this.firstName = "";
        this.nickname = "";
        this.middleName = "";
        this.lastName = "";
        this.birthMonth = 1;
        this.birthDay = 1;
        this.birthYear = 1929;
        this.street = "";
        this.city = "";
        this.province = "";
        this.country = "";
        this.sex = 0;
        this.feet = 1;
        this.inches = 0;
        this.cm = 0;
        this.weight = 0;
        this.mass = 0;
        this.religion = 0;
        this.race = 0;
        this.eyeColor = 0;
        this.hairColor = 0;
        this.relationToAbductor = 0;
        this.marks = "";
        this.personalEffects = "";
        this.remarks = "";
        this.photo = "";
        this.photoFile = null;
        this.agedPhoto = "";
        this.agedPhotoFile = null;
        this.codisId = "";
        this.afisId = "";
        this.dentalId = "";
        this.age = 0;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);

        this.id = 0;
        this.status = 0;
        this.type = 0;
        this.firstName = "";
        this.nickname = "";
        this.middleName = "";
        this.lastName = "";
        this.birthMonth = 1;
        this.birthDay = 1;
        this.birthYear = 1929;
        this.street = "";
        this.city = "";
        this.province = "";
        this.country = "";
        this.sex = 0;
        this.feet = 1;
        this.inches = 0;
        this.cm = 0;
        this.weight = 0;
        this.mass = 0;
        this.religion = 0;
        this.race = 0;
        this.eyeColor = 0;
        this.hairColor = 0;
        this.relationToAbductor = 0;
        this.marks = "";
        this.personalEffects = "";
        this.remarks = "";
        this.photo = "";
        this.photoFile = null;
        this.agedPhoto = "";
        this.agedPhotoFile = null;
        this.codisId = "";
        this.afisId = "";
        this.dentalId = "";
        this.age = 0;
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
        content += "\nDistinguishing Marks: " + marks;
        content += "\nPersonal Effects: " + personalEffects;
        content += "\nRelation: " + relationToAbductor;
        content += "\nRemarks: " + remarks;
        content += "\nPhoto Filename: " + photo;
        content += "\nAged Photo Filename: " + agedPhoto;
        content += "\nCODIS ID: " + codisId;
        content += "\nAFIS ID: " + afisId;
        content += "\nDental ID: " + dentalId;
        return content;
    }
}