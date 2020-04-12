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
import org.vincenzolabs.openmpis.enumeration.Group;

/**
 * The data transfer object for user (can be an administrator, an encoder or an investigator).
 *
 * @author Rey Vincent Babilonia
 */
public class UserJson {

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
     * Returns the {@link Group}.
     *
     * @return the {@link Group}
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets the {@link Group}.
     *
     * @param group the {@link Group}
     */
    public void setGroup(Group group) {
        this.group = group;
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
     * Sets the email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the plain-text password.
     *
     * @return the plain-text password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the plain-text password.
     *
     * @param password the scrypt-hashed password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Sets the rank.
     *
     * @param rank the rank
     */
    public void setRank(String rank) {
        this.rank = rank;
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
     * Sets the first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
     * Sets the middle name.
     *
     * @param middleName the middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
     * Sets the last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * Sets the designation.
     *
     * @param designation the designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Returns the {@link AgencyJson} UUID.
     *
     * @return the {@link AgencyJson} UUID
     */
    public String getAgencyUuid() {
        return agencyUuid;
    }

    /**
     * Sets the {@link AgencyJson} UUID.
     *
     * @param agencyUuid the {@link AgencyJson} UUID
     */
    public void setAgencyUuid(String agencyUuid) {
        this.agencyUuid = agencyUuid;
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
     * Sets the client IP address.
     *
     * @param ipAddress the client IP address
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
     * Sets the last login {@link OffsetDateTime}.
     *
     * @param lastLoginDate the last login {@link OffsetDateTime}
     */
    public void setLastLoginDate(OffsetDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * Returns the {@link OffsetDateTime} the {@link UserJson} was created.
     *
     * @return the {@link OffsetDateTime} the {@link UserJson} was created
     */
    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the {@link OffsetDateTime} the {@link UserJson} was created.
     *
     * @param creationDate the {@link OffsetDateTime} the {@link UserJson} was created
     */
    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Checks if the {@link UserJson} is in active service.
     *
     * @return {@code true} if the {@link UserJson} is active; {@code false} otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the active flag. If {@code true}, the {@link UserJson} can perform the assigned roles. If {@code false},
     * the {@link UserJson} is probably suspended or no longer in active service.
     *
     * @param active {@code true} if the {@link UserJson} is active; {@code false} otherwise
     */
    public void setActive(boolean active) {
        this.active = active;
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
     * Sets the creator UUID.
     *
     * @param creatorUuid the creator UUID
     */
    public void setCreatorUuid(String creatorUuid) {
        this.creatorUuid = creatorUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserJson userJson = (UserJson) o;

        return new EqualsBuilder()
            .append(active, userJson.active)
            .append(uuid, userJson.uuid)
            .append(group, userJson.group)
            .append(emailAddress, userJson.emailAddress)
            .append(password, userJson.password)
            .append(rank, userJson.rank)
            .append(firstName, userJson.firstName)
            .append(middleName, userJson.middleName)
            .append(lastName, userJson.lastName)
            .append(designation, userJson.designation)
            .append(agencyUuid, userJson.agencyUuid)
            .append(ipAddress, userJson.ipAddress)
            .append(lastLoginDate, userJson.lastLoginDate)
            .append(creationDate, userJson.creationDate)
            .append(creatorUuid, userJson.creatorUuid)
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
}
