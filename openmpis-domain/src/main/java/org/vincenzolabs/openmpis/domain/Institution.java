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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The domain model object for a social service institution.
 *
 * @author Rey Vincent Babilonia
 */
public final class Institution {

    private final String uuid;

    private final String name;

    private final String emailAddress;

    private final String contactNumber;

    private final StreetAddress streetAddress;

    /**
     * Private constructor.
     *
     * @param builder the {@link Builder}
     */
    private Institution(Builder builder) {
        this.uuid = builder.uuid;
        this.name = builder.name;
        this.emailAddress = builder.emailAddress;
        this.contactNumber = builder.contactNumber;
        this.streetAddress = builder.streetAddress;
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
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Returns the contact number.
     *
     * @return the contact number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Returns the {@link StreetAddress}.
     *
     * @return the {@link StreetAddress}
     */
    public StreetAddress getStreetAddress() {
        return streetAddress;
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

        Institution that = (Institution) o;

        return new EqualsBuilder()
            .append(uuid, that.uuid)
            .append(name, that.name)
            .append(emailAddress, that.emailAddress)
            .append(contactNumber, that.contactNumber)
            .append(streetAddress, that.streetAddress)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(uuid)
            .append(name)
            .append(emailAddress)
            .append(contactNumber)
            .append(streetAddress)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("name", name)
            .append("emailAddress", emailAddress)
            .append("contactNumber", contactNumber)
            .append("streetAddress", streetAddress)
            .toString();
    }

    /**
     * The builder.
     */
    public static class Builder {

        private String uuid;

        public String name;

        private String emailAddress;

        public String contactNumber;

        public StreetAddress streetAddress;

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
         * Sets the name.
         *
         * @param name the name
         * @return the {@link Builder}
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the email address.
         *
         * @param emailAddress the email address
         * @return the {@link Builder}
         */
        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        /**
         * Sets the contact number.
         *
         * @param contactNumber the contact number
         * @return the {@link Builder}
         */
        public Builder withContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        /**
         * Sets the {@link StreetAddress}.
         *
         * @param streetAddress the {@link StreetAddress}
         * @return the {@link Builder}
         */
        public Builder withStreetAddress(StreetAddress streetAddress) {
            this.streetAddress = streetAddress;
            return this;
        }

        /**
         * Builds an {@link Institution}.
         *
         * @return the {@link Institution}
         */
        public Institution build() {
            return new Institution(this);
        }
    }
}
