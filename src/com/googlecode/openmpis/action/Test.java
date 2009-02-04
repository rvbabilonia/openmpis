/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.openmpis.action;

import com.googlecode.openmpis.dto.User;
import com.googlecode.openmpis.persistence.ibatis.dao.impl.UserDAOImpl;
import com.googlecode.openmpis.persistence.ibatis.service.UserService;
import com.googlecode.openmpis.persistence.ibatis.service.impl.UserServiceImpl;
import java.sql.SQLException;

/**
 *
 * @author rey
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UserService userService = new UserServiceImpl(new UserDAOImpl());
        int id = 4;
        
        try {
            User user1 = (User) userService.getUserById(id);
            System.out.println(user1.toString());
            
            User user2 = new User();
            user2.setId(id);
            user2.setGroupId(user1.getGroupId());
            user2.setUsername(user1.getUsername());
            user2.setPassword(user1.getPassword());
            user2.setFirstName(user1.getFirstName());
            user2.setMiddleName(user1.getMiddleName());
            user2.setLastName(user1.getLastName());
            user2.setDesignation(user1.getDesignation());
            user2.setAgency(user1.getAgency());
            user2.setEmail("marvic.orejo@thomsonreuters.com.ph");
            user2.setNumber(user1.getNumber());
            user2.setStatus(user1.getStatus());
            user2.setQuestion(user1.getQuestion());
            user2.setAnswer(null);
            //user2.setAnswer(user1.getAnswer());
            userService.updateUser(user2);
            
            User user3 = (User) userService.getUserById(id);
            System.out.println(user3.toString());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}