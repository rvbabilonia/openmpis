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

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The domain model object for alleged abductor.
 *
 * @author Rey Vincent Babilonia
 */
public final class Abductor {

    private final String uuid;

    private final String firstName;

    private final String middleName;

    private final String lastName;

    private final String nickname;

    private final LocalDate birthDate;

    private final OffsetDateTime lastSeenDate;

    private final StreetAddress lastSeenLocation;

    private final StreetAddress possibleLocation;

    private final Set<String> photoUuids;

    private final String primaryPhotoUuid;

    private final Description description;

    private final AdditionalDetails additionalDetails;

    /**
     * Private constructor.
     *
     * @param builder the {@link Builder}
     */
    private Abductor(Builder builder) {
        this.uuid = builder.uuid;
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
        this.nickname = builder.nickname;
        this.birthDate = builder.birthDate;
        this.lastSeenDate = builder.lastSeenDate;
        this.lastSeenLocation = builder.lastSeenLocation;
        this.possibleLocation = builder.possibleLocation;
        this.photoUuids = builder.photoUuids;
        this.primaryPhotoUuid = builder.primaryPhotoUuid;
        this.description = builder.description;
        this.additionalDetails = builder.additionalDetails;
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
     * Returns the nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Returns the birth date.
     *
     * @return the birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Returns the age using a {@link ZoneId}.
     *
     * @return the age
     */
    public Integer getAge(ZoneId zoneId) {
        if (birthDate == null) {
            return null;
        }
        if (zoneId == null) {
            zoneId = ZoneId.of("Pacific/Auckland");
        }

        return Period.between(LocalDate.from(birthDate.atStartOfDay(zoneId)),
            LocalDate.now(zoneId)).getYears();
    }

    /**
     * Returns the {@link OffsetDateTime} the alleged {@link Abductor} was last seen.
     *
     * @return the {@link OffsetDateTime} the alleged {@link Abductor} was last seen
     */
    public OffsetDateTime getLastSeenDate() {
        return lastSeenDate;
    }

    /**
     * Returns the location the alleged {@link Abductor} was last seen.
     *
     * @return the location the alleged {@link Abductor} was last seen
     */
    public StreetAddress getLastSeenLocation() {
        return lastSeenLocation;
    }

    /**
     * Returns the possible location.
     *
     * @return the possible location
     */
    public StreetAddress getPossibleLocation() {
        return possibleLocation;
    }

    /**
     * Returns the {@link Set} of photo UUID's.
     *
     * @return the {@link Set} of photo UUID's
     */
    public Set<String> getPhotoUuids() {
        return photoUuids != null ? photoUuids : new HashSet<>();
    }

    /**
     * Returns the primary photo UUID.
     *
     * @return the primary photo UUID
     */
    public String getPrimaryPhotoUuid() {
        return primaryPhotoUuid;
    }

    /**
     * Returns the {@link Description}.
     *
     * @return the {@link Description}
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Returns the {@link AdditionalDetails}.
     *
     * @return the {@link AdditionalDetails}
     */
    public AdditionalDetails getAdditionalDetails() {
        return additionalDetails;
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

        Abductor abductor = (Abductor) o;

        return new EqualsBuilder()
            .append(uuid, abductor.uuid)
            .append(firstName, abductor.firstName)
            .append(middleName, abductor.middleName)
            .append(lastName, abductor.lastName)
            .append(nickname, abductor.nickname)
            .append(birthDate, abductor.birthDate)
            .append(lastSeenDate, abductor.lastSeenDate)
            .append(lastSeenLocation, abductor.lastSeenLocation)
            .append(possibleLocation, abductor.possibleLocation)
            .append(photoUuids, abductor.photoUuids)
            .append(primaryPhotoUuid, abductor.primaryPhotoUuid)
            .append(description, abductor.description)
            .append(additionalDetails, abductor.additionalDetails)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(uuid)
            .append(firstName)
            .append(middleName)
            .append(lastName)
            .append(nickname)
            .append(birthDate)
            .append(lastSeenDate)
            .append(lastSeenLocation)
            .append(possibleLocation)
            .append(photoUuids)
            .append(primaryPhotoUuid)
            .append(description)
            .append(additionalDetails)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("firstName", firstName)
            .append("middleName", middleName)
            .append("lastName", lastName)
            .append("nickname", nickname)
            .append("birthDate", birthDate)
            .append("lastSeenOrFoundDate", lastSeenDate)
            .append("lastSeenOrFoundLocation", lastSeenLocation)
            .append("possibleLocation", possibleLocation)
            .append("photoUuids", photoUuids)
            .append("primaryPhotoUuid", primaryPhotoUuid)
            .append("description", description)
            .append("additionalDetails", additionalDetails)
            .toString();
    }

    /**
     * The builder.
     */
    public static class Builder {

        private String uuid;

        private String firstName;

        private String middleName;

        private String lastName;

        private String nickname;

        private LocalDate birthDate;

        private OffsetDateTime lastSeenDate;

        private StreetAddress lastSeenLocation;

        private StreetAddress possibleLocation;

        private Set<String> photoUuids;

        private String primaryPhotoUuid;

        private Description description;

        private AdditionalDetails additionalDetails;

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
         * Sets the nickname.
         *
         * @param nickname the nickname
         * @return the {@link Builder}
         */
        public Builder withNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        /**
         * Sets the birth date.
         *
         * @param birthDate the birth date
         * @return the {@link Builder}
         */
        public Builder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        /**
         * Sets the {@link OffsetDateTime} the alleged {@link Abductor} was last seen.
         *
         * @param lastSeenDate the {@link OffsetDateTime} the alleged {@link Abductor} was last seen
         * @return the {@link Builder}
         */
        public Builder withLastSeenDate(OffsetDateTime lastSeenDate) {
            this.lastSeenDate = lastSeenDate;
            return this;
        }

        /**
         * Sets the location the alleged {@link Abductor} was last seen.
         *
         * @param lastSeenLocation the location the alleged {@link Abductor} was last seen
         * @return the {@link Builder}
         */
        public Builder withLastSeenLocation(StreetAddress lastSeenLocation) {
            this.lastSeenLocation = lastSeenLocation;
            return this;
        }

        /**
         * Sets the possible location.
         *
         * @param possibleLocation the possible location
         * @return the {@link Builder}
         */
        public Builder withPossibleLocation(StreetAddress possibleLocation) {
            this.possibleLocation = possibleLocation;
            return this;
        }

        /**
         * Sets the {@link Set} of photo UUID's.
         *
         * @param photoUuids the photoUuids
         * @return the {@link Builder}
         */
        public Builder withPhotoUuids(Set<String> photoUuids) {
            this.photoUuids = photoUuids;
            return this;
        }

        /**
         * Sets the primaryPhotoUuid.
         *
         * @param primaryPhotoUuid the primaryPhotoUuid
         * @return the {@link Builder}
         */
        public Builder withPrimaryPhotoUuid(String primaryPhotoUuid) {
            this.primaryPhotoUuid = primaryPhotoUuid;
            return this;
        }

        /**
         * Sets the description.
         *
         * @param description the description
         * @return the {@link Builder}
         */
        public Builder withDescription(Description description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the additionalDetails.
         *
         * @param additionalDetails the additionalDetails
         * @return the {@link Builder}
         */
        public Builder withAdditionalDetails(AdditionalDetails additionalDetails) {
            this.additionalDetails = additionalDetails;
            return this;
        }

        /**
         * Builds an alleged {@link Abductor}.
         *
         * @return the alleged {@link Abductor}
         */
        public Abductor build() {
            return new Abductor(this);
        }
    }
}
