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
package com.googlecode.openmpis.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The Mail class provides the method to send an email.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class Mail {

    /**
     * The configuration
     */
    private Configuration config;
    /**
     * The session
     */
    private Session session;
    /**
     * The email
     */
    private MimeMessage email;
    
    /**
     * Creates a new mail.
     * 
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public Mail() throws FileNotFoundException, IOException {
        // Load configuration from mail.properties
        config = new Configuration("mail.properties");

        // Create mail session
        session = Session.getDefaultInstance(config.getProperties());

        // Create email
        email = new MimeMessage(session);
    }

    /**
     * Sends the email.
     * 
     * @param senderFirstName   the first name of the sender
     * @param senderLastName    the last name of the sender
     * @param senderAddress     the email address of the sender
     * @param recipientAddress  the email address of the recipient
     * @param subject           the subject of the email
     * @param messageBody       the message body of the email
     * @throws javax.mail.MessagingException
     * @throws java.io.UnsupportedEncodingException
     */
    public void send(String senderFirstName, String senderLastName, String senderAddress,
            String recipientAddress, String subject, String messageBody)
            throws UnsupportedEncodingException, MessagingException {
        // Set up the email
        email.setFrom(new InternetAddress(senderAddress, senderFirstName + " " + senderLastName));
        email.setReplyTo(email.getFrom());
        email.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipientAddress));
        email.setSubject(subject);
        email.setText(messageBody);

        // Create transport and send email
        Transport transport = session.getTransport();
        // Check if email server is SMTPS or SMTP
        if (config.getProperty("mail.smtps.host") != null) {
            transport.connect(config.getProperty("mail.smtps.host"), Integer.parseInt(config.getProperty("mail.smtp.port")), config.getProperty("mail.smtp.user"), config.getProperty("mail.smtp.user.password"));
        } else {
            transport.connect(config.getProperty("mail.smtp.host"), Integer.parseInt(config.getProperty("mail.smtp.port")), config.getProperty("mail.smtp.user"), config.getProperty("mail.smtp.user.password"));
        }
        transport.sendMessage(email, email.getRecipients(RecipientType.TO));
        transport.close();
    }

    /**
     * Returns a String representation of this object.
     * 
     * @return              a string representation of this object
     */
    @Override
    public String toString() {
        String content = "";
        try {
            content += "\nFrom: " + email.getFrom()[0].toString();
            content += "\nReply-To: " + email.getReplyTo()[0].toString();
            content += "\nTo: " + email.getRecipients(RecipientType.TO)[0].toString();
            content += "\nSubject: " + email.getSubject();
            content += "\nMessage: " + email.getContent().toString();
            content += "\nHost: " + config.getProperty("mail.smtps.host");
            content += "\nPort: " + config.getProperty("mail.smtp.port");
            content += "\nUsername: " + config.getProperty("mail.smtp.user");
            content += "\nPassword: " + config.getProperty("mail.smtp.password");
        } catch (MessagingException me) {
            return me.getMessage();
        } catch (IOException ioe) {
            return ioe.getMessage();
        }
        return content;
    }
}