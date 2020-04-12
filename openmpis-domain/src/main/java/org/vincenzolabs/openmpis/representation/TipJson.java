/*
 * This file is part of OpenMPIS.
 *
 * Copyright (c) 2019 VincenzoLabs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.vincenzolabs.openmpis.representation;

import java.time.OffsetDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.vincenzolabs.openmpis.enumeration.MessageStatus;

/**
 * The data transfer object for tip (formerly message).
 *
 * @author Rey Vincent Babilonia
 */
public class TipJson {

    private String uuid;

    private OffsetDateTime creationDate;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String contactNumber;

    private String message;

    private MessageStatus status;

    private String ipAddress;

    /**
     * Returns the UUID.
     *
     * @return the UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the UUID.
     *
     * @param uuid the UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the {@link OffsetDateTime} the tip was filed.
     *
     * @return the {@link OffsetDateTime} the {@link TipJson} was filed
     */
    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the {@link OffsetDateTime} the {@link TipJson} was filed.
     *
     * @param creationDate the {@link OffsetDateTime} the {@link TipJson} was filed
     */
    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the optional first name of the person filing a tip.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the optional first name of the person filing a tip.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the optional last name of the person filing a tip.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the optional last name of the person filing a tip.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email address of the person filing a tip.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address of the person filing a tip.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the contact number of the person filing a tip.
     *
     * @return the contact number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the contact number of the person filing a tip.
     *
     * @param contactNumber the contact number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Returns the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the {@link MessageStatus}.
     *
     * @return the {@link MessageStatus}
     */
    public MessageStatus getStatus() {
        return status;
    }

    /**
     * Sets the {@link MessageStatus}.
     *
     * @param status the {@link MessageStatus}
     */
    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    /**
     * Returns the client IP address of the person filing a tip.
     *
     * @return the client IP address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the client IP address of the person filing a tip.
     *
     * @param ipAddress the client IP address
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TipJson tipJson = (TipJson) o;

        return new EqualsBuilder()
            .append(uuid, tipJson.uuid)
            .append(creationDate, tipJson.creationDate)
            .append(firstName, tipJson.firstName)
            .append(lastName, tipJson.lastName)
            .append(emailAddress, tipJson.emailAddress)
            .append(contactNumber, tipJson.contactNumber)
            .append(message, tipJson.message)
            .append(status, tipJson.status)
            .append(ipAddress, tipJson.ipAddress)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(uuid)
            .append(creationDate)
            .append(firstName)
            .append(lastName)
            .append(emailAddress)
            .append(contactNumber)
            .append(message)
            .append(status)
            .append(ipAddress)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("creationDate", creationDate)
            .append("firstName", firstName)
            .append("lastName", lastName)
            .append("emailAddress", emailAddress)
            .append("contactNumber", contactNumber)
            .append("message", message)
            .append("status", status)
            .append("ipAddress", ipAddress)
            .toString();
    }
}
