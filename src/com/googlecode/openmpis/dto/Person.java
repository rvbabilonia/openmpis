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

import java.sql.Date;

/**
 * 
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class Person {

    private int id;
    private String firstName;
    private String nickName;
    private String middleName;
    private String lastName;
    private Date birthday;
    private String street;
    private String city;
    private String province;
    private String country;
    private String circumstance;
    private String personalEffects;
    private String marks;
    private String photo;
    private String agedPhoto;
    private String codisId;
    private String afisId;
    private String dentalId;
    private Date date;
    private Date dateMissing;
    private String status;
    private String type;
    private int feet;
    private int inches;
    private int weight;
    private int religion;
    private int race;
    private int eyeColor;
    private int hairColor;
    private int gender;
    private int reward;
    private int encoderId;
    private int investigatorId;
    private int relativeId;
    private int abductorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCircumstance() {
        return circumstance;
    }

    public void setCircumstance(String circumstance) {
        this.circumstance = circumstance;
    }

    public String getPersonalEffects() {
        return personalEffects;
    }

    public void setPersonalEffects(String personalEffects) {
        this.personalEffects = personalEffects;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAgedPhoto() {
        return agedPhoto;
    }

    public void setAgedPhoto(String agedPhoto) {
        this.agedPhoto = agedPhoto;
    }

    public String getCodisId() {
        return codisId;
    }

    public void setCodisId(String codisId) {
        this.codisId = codisId;
    }

    public String getAfisId() {
        return afisId;
    }

    public void setAfisId(String afisId) {
        this.afisId = afisId;
    }

    public String getDentalId() {
        return dentalId;
    }

    public void setDentalId(String dentalId) {
        this.dentalId = dentalId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateMissing() {
        return dateMissing;
    }

    public void setDateMissing(Date dateMissing) {
        this.dateMissing = dateMissing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFeet() {
        return feet;
    }

    public void setFeet(int feet) {
        this.feet = feet;
    }

    public int getInches() {
        return inches;
    }

    public void setInches(int inches) {
        this.inches = inches;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReligion() {
        return religion;
    }

    public void setReligion(int religion) {
        this.religion = religion;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public int getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getEncoderId() {
        return encoderId;
    }

    public void setEncoderId(int encoderId) {
        this.encoderId = encoderId;
    }

    public int getInvestigatorId() {
        return investigatorId;
    }

    public void setInvestigatorId(int investigatorId) {
        this.investigatorId = investigatorId;
    }

    public int getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(int relativeId) {
        this.relativeId = relativeId;
    }

    public int getAbductorId() {
        return abductorId;
    }

    public void setAbductorId(int abductorId) {
        this.abductorId = abductorId;
    }
}