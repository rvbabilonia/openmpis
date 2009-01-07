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

package com.googlecode.openmpis.model;

/**
 * 
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class Relative
{
   private int id;
   private String firstName;
   private String lastName;
   private String middleName;
   private String email;
   private String number;
   private String street;
   private String city;
   private String province;
   private String country;
   private int encoderID;

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

   public String getLastName() {
       return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getMiddleName() {
       return middleName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getNumber() {
       return number;
   }

   public void setNumber(String number) {
      this.number = number;
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

   public int getEncoderID() {
       return encoderID;
   }

   public void setEncoderID(int encoderID) {
      this.encoderID = encoderID;
   }
}