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

/**
 * 
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class Report
{
   private int id;
   private int personID;
   private int investigatorID;
   private String report;
   private Date date;

   public int getId() {
       return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getPersonID() {
       return personID;
   }

   public void setPersonID(int personID) {
      this.personID = personID;
   }

   public int getInvestigatorID() {
       return investigatorID;
   }

   public void setInvestigatorID(int investigatorID) {
      this.investigatorID = investigatorID;
   }

   public String getReport() {
       return report;
   }

   public void setReport(String report) {
      this.report = report;
   }

   public Date getDate() {
       return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }
}