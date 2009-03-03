/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.googlecode.openmpis.dto;

/**
 * The Abductor class is used to represent an abductor.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class Abductor {

    /**
     * The person ID
     */
    private int id;
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
        content += "\nRemarks: " + remarks;
        content += "\nPhoto Filename: " + photo;
        content += "\nAged Photo Filename: " + agedPhoto;
        content += "\nCODIS ID: " + codisId;
        content += "\nAFIS ID: " + afisId;
        content += "\nDental ID: " + dentalId;
        return content;
    }
}