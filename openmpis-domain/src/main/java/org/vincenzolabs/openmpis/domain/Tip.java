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
package org.vincenzolabs.openmpis.domain;

import java.time.OffsetDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.vincenzolabs.openmpis.enumeration.MessageStatus;

/**
 * The domain model object for tip (formerly message).
 *
 * @author Rey Vincent Babilonia
 */
public final class Tip {

    private final String uuid;

    private final OffsetDateTime creationDate;

    private final String firstName;

    private final String lastName;

    private final String emailAddress;

    private final String contactNumber;

    private final String message;

    private final MessageStatus status;

    private final String ipAddress;

    private final String caseUuid;

    /**
     * Private constructor.
     *
     * @param builder the {@link Builder}
     */
    private Tip(Builder builder) {
        this.uuid = builder.uuid;
        this.creationDate = builder.creationDate;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.contactNumber = builder.contactNumber;
        this.message = builder.message;
        this.status = builder.status;
        this.ipAddress = builder.ipAddress;
        this.caseUuid = builder.caseUuid;
    }

    /**
     * Returns the UUID.
     *
     * @return the UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Returns the {@link OffsetDateTime} the tip was filed.
     *
     * @return the {@link OffsetDateTime} the {@link Tip} was filed
     */
    public OffsetDateTime getCreationDate() {
        return creationDate;
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
     * Returns the optional last name of the person filing a tip.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
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
     * Returns the contact number of the person filing a tip.
     *
     * @return the contact number
     */
    public String getContactNumber() {
        return emailAddress;
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
     * Returns the {@link MessageStatus}.
     *
     * @return the {@link MessageStatus}
     */
    public MessageStatus getStatus() {
        return status;
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
     * Returns the case UUID.
     *
     * @return the case UUID
     */
    public String getCaseUuid() {
        return caseUuid;
    }

    /**
     * Returns a {@link Builder}.
     *
     * @return the {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tip tip = (Tip) o;

        return new EqualsBuilder()
            .append(uuid, tip.uuid)
            .append(creationDate, tip.creationDate)
            .append(firstName, tip.firstName)
            .append(lastName, tip.lastName)
            .append(emailAddress, tip.emailAddress)
            .append(contactNumber, tip.contactNumber)
            .append(message, tip.message)
            .append(status, tip.status)
            .append(ipAddress, tip.ipAddress)
            .append(caseUuid, tip.caseUuid)
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
            .append(caseUuid)
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
            .append("caseUuid", caseUuid)
            .toString();
    }

    /**
     * The builder.
     */
    public static class Builder {

        private String uuid;

        private OffsetDateTime creationDate;

        private String firstName;

        private String lastName;

        private String emailAddress;

        private String contactNumber;

        private String message;

        private MessageStatus status;

        private String ipAddress;

        private String caseUuid;

        /**
         * Private constructor.
         */
        private Builder() {
            // prevent instantiation
        }

        /**
         * Sets the UUID.
         *
         * @param uuid the UUID
         * @return the {@link Builder}
         */
        public Builder withUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        /**
         * Sets the {@link OffsetDateTime} the {@link Tip} was filed.
         *
         * @param creationDate the {@link OffsetDateTime} the {@link Tip} was filed
         * @return the {@link Builder}
         */
        public Builder withCreationDate(OffsetDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        /**
         * Sets the optional first name of the person filing a tip.
         *
         * @param firstName the first name
         * @return the {@link Builder}
         */
        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the optional last name of the person filing a tip.
         *
         * @param lastName the last name
         * @return the {@link Builder}
         */
        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Sets the email address of the person filing a tip.
         *
         * @param emailAddress the email address
         * @return the {@link Builder}
         */
        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        /**
         * Sets the contact number of the person filing a tip.
         *
         * @param contactNumber the contact number
         * @return the {@link Builder}
         */
        public Builder withContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        /**
         * Sets the message.
         *
         * @param message the message
         * @return the {@link Builder}
         */
        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Sets the {@link MessageStatus}.
         *
         * @param status the {@link MessageStatus}
         * @return the {@link Builder}
         */
        public Builder withStatus(MessageStatus status) {
            this.status = status;
            return this;
        }

        /**
         * Sets the client IP address of the person filing a tip.
         *
         * @param ipAddress the client IP address
         * @return the {@link Builder}
         */
        public Builder withIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        /**
         * Sets the case UUID.
         *
         * @param caseUuid the case UUID
         * @return the {@link Builder}
         */
        public Builder withCaseUuid(String caseUuid) {
            this.caseUuid = caseUuid;
            return this;
        }

        /**
         * Builds a {@link Tip}.
         *
         * @return the {@link Tip}
         */
        public Tip build() {
            return new Tip(this);
        }
    }
}
