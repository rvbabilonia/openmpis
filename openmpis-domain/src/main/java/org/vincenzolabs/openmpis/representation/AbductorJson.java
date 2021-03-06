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

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The data transfer object for alleged abductor.
 *
 * @author Rey Vincent Babilonia
 */
public class AbductorJson {

    private String uuid;

    private String firstName;

    private String middleName;

    private String lastName;

    private String nickname;

    private LocalDate birthDate;

    private Integer age;

    private OffsetDateTime lastSeenDate;

    private StreetAddressJson lastSeenLocationJson;

    private StreetAddressJson possibleLocationJson;

    private Set<String> photoUuids;

    private String primaryPhotoUuid;

    private DescriptionJson descriptionJson;

    private AdditionalDetailsJson additionalDetailsJson;

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
     * Returns the nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets the nickname.
     *
     * @param nickname the nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
     * Sets the birth date.
     *
     * @param birthDate the birth date
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Returns the age.
     *
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age the age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Returns the {@link OffsetDateTime} the alleged {@link AbductorJson} was last seen.
     *
     * @return the {@link OffsetDateTime} the alleged {@link AbductorJson} was last seen
     */
    public OffsetDateTime getLastSeenDate() {
        return lastSeenDate;
    }

    /**
     * Sets the {@link OffsetDateTime} the alleged {@link AbductorJson} was last seen.
     *
     * @param lastSeenDate the {@link OffsetDateTime} the alleged {@link AbductorJson} was last seen
     */
    public void setLastSeenDate(OffsetDateTime lastSeenDate) {
        this.lastSeenDate = lastSeenDate;
    }

    /**
     * Returns the location the alleged {@link AbductorJson} was last seen.
     *
     * @return the location the alleged {@link AbductorJson} was last seen
     */
    public StreetAddressJson getLastSeenLocationJson() {
        return lastSeenLocationJson;
    }

    /**
     * Sets the location the alleged {@link AbductorJson} was last seen.
     *
     * @param lastSeenLocationJson the location
     */
    public void setLastSeenLocationJson(StreetAddressJson lastSeenLocationJson) {
        this.lastSeenLocationJson = lastSeenLocationJson;
    }

    /**
     * Returns the possible location.
     *
     * @return the possible location
     */
    public StreetAddressJson getPossibleLocationJson() {
        return possibleLocationJson;
    }

    /**
     * Sets the possible location.
     *
     * @param possibleLocationJson the possible location
     */
    public void setPossibleLocationJson(StreetAddressJson possibleLocationJson) {
        this.possibleLocationJson = possibleLocationJson;
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
     * Sets the {@link Set} of photo UUID's.
     *
     * @param photoUuids the {@link Set} of photo UUID's
     */
    public void setPhotoUuids(Set<String> photoUuids) {
        this.photoUuids = photoUuids;
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
     * Sets the primary photo UUID.
     *
     * @param primaryPhotoUuid the primary photo UUID
     */
    public void setPrimaryPhotoUuid(String primaryPhotoUuid) {
        this.primaryPhotoUuid = primaryPhotoUuid;
    }

    /**
     * Returns the {@link DescriptionJson}.
     *
     * @return the {@link DescriptionJson}
     */
    public DescriptionJson getDescriptionJson() {
        return descriptionJson;
    }

    /**
     * Sets the {@link DescriptionJson}.
     *
     * @param descriptionJson the {@link DescriptionJson}
     */
    public void setDescriptionJson(DescriptionJson descriptionJson) {
        this.descriptionJson = descriptionJson;
    }

    /**
     * Returns the {@link AdditionalDetailsJson}.
     *
     * @return the {@link AdditionalDetailsJson}
     */
    public AdditionalDetailsJson getAdditionalDetailsJson() {
        return additionalDetailsJson;
    }

    /**
     * Sets the {@link AdditionalDetailsJson}.
     *
     * @param additionalDetailsJson the {@link AdditionalDetailsJson}
     */
    public void setAdditionalDetailsJson(AdditionalDetailsJson additionalDetailsJson) {
        this.additionalDetailsJson = additionalDetailsJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbductorJson that = (AbductorJson) o;

        return new EqualsBuilder()
            .append(uuid, that.uuid)
            .append(firstName, that.firstName)
            .append(middleName, that.middleName)
            .append(lastName, that.lastName)
            .append(nickname, that.nickname)
            .append(birthDate, that.birthDate)
            .append(age, that.age)
            .append(lastSeenDate, that.lastSeenDate)
            .append(lastSeenLocationJson, that.lastSeenLocationJson)
            .append(possibleLocationJson, that.possibleLocationJson)
            .append(photoUuids, that.photoUuids)
            .append(primaryPhotoUuid, that.primaryPhotoUuid)
            .append(descriptionJson, that.descriptionJson)
            .append(additionalDetailsJson, that.additionalDetailsJson)
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
            .append(age)
            .append(lastSeenDate)
            .append(lastSeenLocationJson)
            .append(possibleLocationJson)
            .append(photoUuids)
            .append(primaryPhotoUuid)
            .append(descriptionJson)
            .append(additionalDetailsJson)
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
            .append("age", age)
            .append("lastSeenDate", lastSeenDate)
            .append("lastSeenLocationJson", lastSeenLocationJson)
            .append("possibleLocationJson", possibleLocationJson)
            .append("photoUuids", photoUuids)
            .append("primaryPhotoUuid", primaryPhotoUuid)
            .append("descriptionJson", descriptionJson)
            .append("additionalDetailsJson", additionalDetailsJson)
            .toString();
    }
}
