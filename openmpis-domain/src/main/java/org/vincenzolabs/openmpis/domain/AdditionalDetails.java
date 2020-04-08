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

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The domain model object for additional details and advanced features.
 *
 * @author Rey Vincent Babilonia
 */
public final class AdditionalDetails {

    private final Set<String> ageProgressedPhotoUuids;

    private final String primaryAgeProgressedPhotoUuid;

    private final String codisId;

    private final String afisId;

    private final String dentalId;

    /**
     * Private constructor.
     *
     * @param builder the {@link Builder}
     */
    public AdditionalDetails(Builder builder) {
        this.ageProgressedPhotoUuids = builder.ageProgressedPhotoUuids;
        this.primaryAgeProgressedPhotoUuid = builder.primaryAgeProgressedPhotoUuid;
        this.codisId = builder.codisId;
        this.afisId = builder.afisId;
        this.dentalId = builder.dentalId;
    }

    /**
     * Returns the {@link Set} of age-progressed photo UUID's.
     *
     * @return the {@link Set} of age-progressed photo UUID's
     */
    public Set<String> getAgeProgressedPhotoUuids() {
        return ageProgressedPhotoUuids;
    }

    /**
     * Returns the primary age-progressed photo UUID.
     *
     * @return the primary age-progressed photo UUID
     */
    public String getPrimaryAgeProgressedPhotoUuid() {
        return primaryAgeProgressedPhotoUuid;
    }

    /**
     * Returns the combined DNA index system (CODIS) identifier. CODIS contains records from local, state and national
     * DNA index systems.
     *
     * @return the combined DNA index system (CODIS) identifier
     */
    public String getCodisId() {
        return codisId;
    }

    /**
     * Returns the automated fingerprint identification system (AFIS) identifier.
     *
     * @return the automated fingerprint identification system (AFIS) identifier
     */
    public String getAfisId() {
        return afisId;
    }

    /**
     * Returns the dental record identifier.
     *
     * @return the dental record identifier
     */
    public String getDentalId() {
        return dentalId;
    }

    /**
     * Returns a {@link Builder}.
     *
     * @return the {@link Builder}
     */
    public Builder builder() {
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

        AdditionalDetails that = (AdditionalDetails) o;

        return new EqualsBuilder()
            .append(ageProgressedPhotoUuids, that.ageProgressedPhotoUuids)
            .append(primaryAgeProgressedPhotoUuid, that.primaryAgeProgressedPhotoUuid)
            .append(codisId, that.codisId)
            .append(afisId, that.afisId)
            .append(dentalId, that.dentalId)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(ageProgressedPhotoUuids)
            .append(primaryAgeProgressedPhotoUuid)
            .append(codisId)
            .append(afisId)
            .append(dentalId)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("ageProgressedPhotoUuids", ageProgressedPhotoUuids)
            .append("primaryAgeProgressedPhotoUuid", primaryAgeProgressedPhotoUuid)
            .append("codisId", codisId)
            .append("afisId", afisId)
            .append("dentalId", dentalId)
            .toString();
    }

    /**
     * The builder.
     */
    public static class Builder {

        private Set<String> ageProgressedPhotoUuids;

        private String primaryAgeProgressedPhotoUuid;

        private String codisId;

        private String afisId;

        private String dentalId;

        /**
         * Private constructor.
         */
        private Builder() {
            // prevent instantiation
        }

        /**
         * Sets the {@link Set} of age-progressed photo UUID's.
         *
         * @param ageProgressedPhotoUuids the age-progressed photo UUID's
         * @return the {@link Builder}
         */
        public Builder withAgeProgressedPhotoUuids(Set<String> ageProgressedPhotoUuids) {
            this.ageProgressedPhotoUuids = ageProgressedPhotoUuids;
            return this;
        }

        /**
         * Sets the primary age-progressed photo UUID.
         *
         * @param primaryAgeProgressedPhotoUuid the primary age-progressed photo UUID
         * @return the {@link Builder}
         */
        public Builder withPrimaryAgeProgressedPhotoUuid(String primaryAgeProgressedPhotoUuid) {
            this.primaryAgeProgressedPhotoUuid = primaryAgeProgressedPhotoUuid;
            return this;
        }

        /**
         * Sets the combined DNA index system (CODIS) identifier.
         *
         * @param codisId the combined DNA index system (CODIS) identifier
         * @return the {@link Builder}
         */
        public Builder withCodisId(String codisId) {
            this.codisId = codisId;
            return this;
        }

        /**
         * Sets the automated fingerprint identification system (AFIS) identifier.
         *
         * @param afisId the automated fingerprint identification system (AFIS) identifier
         * @return the {@link Builder}
         */
        public Builder withAfisId(String afisId) {
            this.afisId = afisId;
            return this;
        }

        /**
         * Sets the dental record identifier.
         *
         * @param dentalId the dental record identifier
         * @return the {@link Builder}
         */
        public Builder withDentalId(String dentalId) {
            this.dentalId = dentalId;
            return this;
        }

        /**
         * Builds an {@link AdditionalDetails}.
         *
         * @return the {@link AdditionalDetails}
         */
        public AdditionalDetails build() {
            return new AdditionalDetails(this);
        }
    }
}
