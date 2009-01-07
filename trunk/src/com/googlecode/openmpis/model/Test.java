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

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;

/**
 * 
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class Test {
    
    public Test() {
        SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
        
        User user = null;
        
        String username = "admin";
        String password = "66c218d3569a0f0f62ff7ca613077e14";
        
        try {
            user = (User) sqlMap.queryForObject("getUser", username);
        } catch (SQLException se)  {
            se.printStackTrace();
        }
        
        if (user == null) {
            System.out.println("null");
        } else {
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
            if (user.getPassword().equals(password)) {
                System.out.println("Success!");
            }
        }
    }
    
    public static void main(String[] args) {
        new Test();
    }
    
    /*
        Writing Objects to the Database
        We now have a Person object from the database. Let’s modify some data. We’ll change the person’s
        height and weight.
        …
        person.setHeightInMeters(1.83); // person as read above
        person.setWeightInKilograms(86.36);
        … sqlMap.update(“updatePerson”, person);
        …
        If we wanted to delete this Person, it’s just as easy.
        … sqlMap.delete (“deletePerson”, person);
        …
        Inserting a new Person is similar.
        Person newPerson = new Person();
        newPerson.setId(11); // you would normally get the ID from a sequence or custom table
        newPerson.setFirstName(“Clinton”);
        newPerson.setLastName(“Begin”);
        newPerson.setBirthDate (null);
        newPerson.setHeightInMeters(1.83);
        newPerson.setWeightInKilograms(86.36);
        
        sqlMap.insert (“insertPerson”, newPerson);
     */
}
