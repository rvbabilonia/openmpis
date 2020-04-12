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
import org.vincenzolabs.openmpis.enumeration.Group;

/**
 * The domain model object for user (can be an administrator, an encoder or an investigator).
 *
 * @author Rey Vincent Babilonia
 */
public final class User {

    private final String uuid;

    private final Group group;

    private final String emailAddress;

    private final String password;

    private final String rank;

    private final String firstName;

    private final String middleName;

    private final String lastName;

    private final String designation;

    private final String agencyUuid;

    private final String ipAddress;

    private final OffsetDateTime lastLoginDate;

    private final OffsetDateTime creationDate;

    private final boolean active;

    private final String creatorUuid;

    /**
     * Private constructor.
     *
     * @param builder the {@link Builder}
     */
    private User(Builder builder) {
        this.uuid = builder.uuid;
        this.group = builder.group;
        this.emailAddress = builder.emailAddress;
        this.password = builder.password;
        this.rank = builder.rank;
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
        this.designation = builder.designation;
        this.agencyUuid = builder.agencyUuid;
        this.ipAddress = builder.ipAddress;
        this.lastLoginDate = builder.lastLoginDate;
        this.creationDate = builder.creationDate;
        this.active = builder.active;
        this.creatorUuid = builder.creatorUuid;
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
     * Returns the {@link Group}.
     *
     * @return the {@link Group}
     */
    public Group getGroup() {
        return group;
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
     * Returns the scrypt-hashed password.
     *
     * @return the scrypt-hashed password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the rank.
     *
     * @return the rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * Returns the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the middle name.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the designation.
     *
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Returns the {@link Agency} UUID.
     *
     * @return the {@link Agency} UUID
     */
    public String getAgencyUuid() {
        return agencyUuid;
    }

    /**
     * Returns the client IP address.
     *
     * @return the client IP address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Returns the last login {@link OffsetDateTime}.
     *
     * @return the last login {@link OffsetDateTime}
     */
    public OffsetDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * Returns the {@link OffsetDateTime} the {@link User} was created.
     *
     * @return the {@link OffsetDateTime} the {@link User} was created
     */
    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Checks if the {@link User} is in active service.
     *
     * @return {@code true} if the {@link User} is active; {@code false} otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns the creator UUID.
     *
     * @return the creator UUID
     */
    public String getCreatorUuid() {
        return creatorUuid;
    }

    /**
     * Returns a {@link Builder}.
     *
     * @return the {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a {@link Builder}.
     *
     * @param user the {@link User}
     * @return the {@link Builder}
     */
    public static Builder builder(User user) {
        return new Builder(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return new EqualsBuilder()
            .append(active, user.active)
            .append(uuid, user.uuid)
            .append(group, user.group)
            .append(emailAddress, user.emailAddress)
            .append(password, user.password)
            .append(rank, user.rank)
            .append(firstName, user.firstName)
            .append(middleName, user.middleName)
            .append(lastName, user.lastName)
            .append(designation, user.designation)
            .append(agencyUuid, user.agencyUuid)
            .append(ipAddress, user.ipAddress)
            .append(lastLoginDate, user.lastLoginDate)
            .append(creationDate, user.creationDate)
            .append(creatorUuid, user.creatorUuid)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(uuid)
            .append(group)
            .append(emailAddress)
            .append(password)
            .append(rank)
            .append(firstName)
            .append(middleName)
            .append(lastName)
            .append(designation)
            .append(agencyUuid)
            .append(ipAddress)
            .append(lastLoginDate)
            .append(creationDate)
            .append(active)
            .append(creatorUuid)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("group", group)
            .append("emailAddress", emailAddress)
            .append("password", "********")
            .append("rank", rank)
            .append("firstName", firstName)
            .append("middleName", middleName)
            .append("lastName", lastName)
            .append("designation", designation)
            .append("agencyUuid", agencyUuid)
            .append("ipAddress", ipAddress)
            .append("lastLoginDate", lastLoginDate)
            .append("creationDate", creationDate)
            .append("active", active)
            .append("creatorUuid", creatorUuid)
            .toString();
    }

    /**
     * The builder.
     */
    public static class Builder {

        private String uuid;

        private Group group;

        private String emailAddress;

        private String password;

        private String rank;

        private String firstName;

        private String middleName;

        private String lastName;

        private String designation;

        private String agencyUuid;

        private String ipAddress;

        private OffsetDateTime lastLoginDate;

        private OffsetDateTime creationDate;

        private boolean active;

        private String creatorUuid;

        /**
         * Private constructor.
         */
        private Builder() {
            // prevent instantiation
        }

        /**
         * Private constructor with {@link User}.
         *
         * @param user the {@link User}
         */
        private Builder(User user) {
            uuid = user.getUuid();
            group = user.getGroup();
            emailAddress = user.getEmailAddress();
            password = user.getPassword();
            rank = user.getRank();
            firstName = user.getFirstName();
            middleName = user.getMiddleName();
            lastName = user.getLastName();
            designation = user.getDesignation();
            agencyUuid = user.getAgencyUuid();
            ipAddress = user.getIpAddress();
            lastLoginDate = user.getLastLoginDate();
            creationDate = user.getCreationDate();
            active = user.isActive();
            creatorUuid = user.getCreatorUuid();
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
         * Sets the {@link Group}.
         *
         * @param group the {@link Group}
         * @return the {@link Builder}
         */
        public Builder withGroup(Group group) {
            this.group = group;
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
         * Sets the scrypt-hashed password.
         *
         * @param password the scrypt-hashed password
         * @return the {@link Builder}
         */
        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Sets the rank.
         *
         * @param rank the rank
         * @return the {@link Builder}
         */
        public Builder withRank(String rank) {
            this.rank = rank;
            return this;
        }

        /**
         * Sets the first name.
         *
         * @param firstName the first name
         * @return the {@link Builder}
         */
        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the middle name.
         *
         * @param middleName the middle name
         * @return the {@link Builder}
         */
        public Builder withMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        /**
         * Sets the last name.
         *
         * @param lastName the last name
         * @return the {@link Builder}
         */
        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Sets the designation.
         *
         * @param designation the designation
         * @return the {@link Builder}
         */
        public Builder withDesignation(String designation) {
            this.designation = designation;
            return this;
        }

        /**
         * Sets the {@link Agency} UUID.
         *
         * @param agencyUuid the {@link Agency} UUID
         * @return the {@link Builder}
         */
        public Builder withAgencyUuid(String agencyUuid) {
            this.agencyUuid = agencyUuid;
            return this;
        }

        /**
         * Sets the client IP address.
         *
         * @param ipAddress the client IP address
         * @return the {@link Builder}
         */
        public Builder withIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        /**
         * Sets the last login {@link OffsetDateTime}.
         *
         * @param lastLoginDate the last login {@link OffsetDateTime}
         * @return the {@link Builder}
         */
        public Builder withLastLoginDate(OffsetDateTime lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
            return this;
        }

        /**
         * Sets the {@link OffsetDateTime} the {@link User} was created.
         *
         * @param creationDate the {@link OffsetDateTime} the {@link User} was created
         * @return the {@link Builder}
         */
        public Builder withCreationDate(OffsetDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        /**
         * Sets the active flag. If {@code true}, the {@link User} can perform the assigned roles. If {@code false},
         * the {@link User} is probably suspended or no longer in active service.
         *
         * @param active {@code true} if the {@link User} is active; {@code false} otherwise
         * @return the {@link Builder}
         */
        public Builder withActive(boolean active) {
            this.active = active;
            return this;
        }

        /**
         * Sets the creator UUID.
         *
         * @param creatorUuid the creator UUID
         * @return the {@link Builder}
         */
        public Builder withCreatorUuid(String creatorUuid) {
            this.creatorUuid = creatorUuid;
            return this;
        }

        /**
         * Builds a {@link User}.
         *
         * @return the {@link User}
         */
        public User build() {
            return new User(this);
        }
    }
}
