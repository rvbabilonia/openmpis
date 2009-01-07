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

import java.sql.Date;
//import java.net.InetAddress;

/**
 * 
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class User
{
   private int id;
   private int groupID;
   private String username;
   private String password;
   private String firstName;
   private String lastName;
   private String middleName;
   private String email;
   private String designation;
   private String agency;
   private String number;
   private String ipAddress;
   private Date lastLogin;
   private Date date;
   private int creatorID;
   private int status;
   private int question;
   private String answer;

   public int getId() {
       return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getGroupID() {
       return groupID;
   }

   public void setGroupID(int groupID) {
      this.groupID = groupID;
   }

   public String getUsername() {
       return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
       return password;
   }

   public void setPassword(String password) {
      this.password = password;
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

   public String getDesignation() {
       return designation;
   }

   public void setDesignation(String designation) {
      this.designation = designation;
   }

   public String getAgency() {
       return agency;
   }

   public void setAgency(String agency) {
      this.agency = agency;
   }

   public String getNumber() {
       return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   public String getIpAddress() {
       return ipAddress;
   }

   public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
   }

   public Date getLastLogin() {
       return lastLogin;
   }

   public void setLastLogin(Date lastLogin) {
      this.lastLogin = lastLogin;
   }

   public Date getDate() {
       return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public int getCreatorID() {
       return creatorID;
   }

   public void setCreatorID(int creatorID) {
      this.creatorID = creatorID;
   }
   
   public int getStatus() {
       return status;
   }
   
   public void setStatus(int status) {
       this.status = status;
   }
   
   public int getQuestion() {
       return question;
   }
   
   public void setQuestion(int question) {
       this.question = question;
   }
   
   public String getAnswer() {
       return answer;
   }
   
   public void setAnswer(String answer) {
       this.answer = answer;
   }
}