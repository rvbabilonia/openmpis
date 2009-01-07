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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Date;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * The Mail class provides the method to send an email.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class Mail {
    /**
     * Sole constructor.
     */
    public Mail() {
        super();
    }
    
    /**
     * Sends the email.
     * 
     * @param   senderFirstName     the first name of the sender
     * @param   senderLastName      the last name of the sender
     * @param   senderAddress       the email address of the sender
     * @param   recipientAddress    the email address of the recipient
     * @param   subject             the subject of the email
     * @param   messageBody         the message body of the email
     * @param   ipAddress           the IP address of the client
     */
    public void send(String senderFirstName, String senderLastName, String senderAddress,
            String recipientAddress, String subject, String messageBody, String ipAddress)
            throws AddressException, MessagingException, FileNotFoundException, Exception {
        Properties properties = new Properties();

        // Retrieve email server FQDN and administrator's email address
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("mail.properties");
            properties.load(is);
            is.close();
        } catch (FileNotFoundException fnfe){
            throw new FileNotFoundException("Cannot find mail.properties file.");
        }

        // Create mail session
        Session session = Session.getInstance(properties, null);
        
        // Create email
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(senderAddress, senderFirstName + " " + senderLastName));
        email.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));
        email.setSubject(subject);
        email.setText(messageBody);

        // Send email
        Transport.send(email);
        
        // Create message
        com.googlecode.openmpis.model.Message message = new com.googlecode.openmpis.model.Message();
        message.setFirstName(senderFirstName);
        message.setLastName(senderLastName);
        message.setEmail(senderAddress);
        message.setSubject(subject);
        message.setMessage(messageBody);
        message.setDate(new Date(System.currentTimeMillis()));
        message.setIpAddress(ipAddress);
        
        SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();
        sqlMap.insert("insertMessage", message);
    }
}