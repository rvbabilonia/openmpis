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
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.vincenzolabs.openmpis.enumeration.CaseStatus;
import org.vincenzolabs.openmpis.enumeration.CaseType;
import org.vincenzolabs.openmpis.enumeration.Relationship;

/**
 * The domain model object for investigation.
 *
 * @author Rey Vincent Babilonia
 */
public final class Investigation {

    private final String uuid;

    private final OffsetDateTime creationDate;

    private final String personUuid;

    private final String contactPersonUuid;

    private final String encoderUuid;

    private final String investigatorUuid;

    private final Set<String> abductorUuids;

    private final Set<Report> reports;

    private final Set<Tip> tips;

    private final String agencyUuid;

    private final Relationship relationshipToContactPerson;

    private final Relationship relationshipToAbductors;

    private final CaseStatus caseStatus;

    private final CaseType caseType;

    private final String circumstance;

    private final Integer reward;

    /**
     * Private constructor.
     *
     * @param builder the {@link Builder}
     */
    private Investigation(Builder builder) {
        this.uuid = builder.uuid;
        this.creationDate = builder.creationDate;
        this.personUuid = builder.personUuid;
        this.contactPersonUuid = builder.contactPersonUuid;
        this.encoderUuid = builder.encoderUuid;
        this.investigatorUuid = builder.investigatorUuid;
        this.abductorUuids = builder.abductorUuids;
        this.reports = builder.reports;
        this.tips = builder.tips;
        this.agencyUuid = builder.agencyUuid;
        this.relationshipToContactPerson = builder.relationshipToContactPerson;
        this.relationshipToAbductors = builder.relationshipToAbductors;
        this.caseStatus = builder.caseStatus;
        this.caseType = builder.caseType;
        this.circumstance = builder.circumstance;
        this.reward = builder.reward;
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
     * Returns the {@link OffsetDateTime} the {@link Investigation} was filed.
     *
     * @return the {@link OffsetDateTime} the {@link Investigation} was filed
     */
    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Returns the {@link Person} UUID.
     *
     * @return the {@link Person} UUID
     */
    public String getPersonUuid() {
        return personUuid;
    }

    /**
     * Returns the {@link ContactPerson} UUID.
     *
     * @return the {@link ContactPerson} UUID
     */
    public String getContactPersonUuid() {
        return contactPersonUuid;
    }

    /**
     * Returns the encoder UUID.
     *
     * @return the encoder UUID
     */
    public String getEncoderUuid() {
        return encoderUuid;
    }

    /**
     * Returns the investigator UUID.
     *
     * @return the investigator UUID
     */
    public String getInvestigatorUuid() {
        return investigatorUuid;
    }

    /**
     * Returns the {@link Set} of alleged abductor UUID's.
     *
     * @return the {@link Set} of alleged abductor UUID's
     */
    public Set<String> getAbductorUuids() {
        return abductorUuids != null ? abductorUuids : new HashSet<>();
    }

    /**
     * Returns the {@link Set} of {@link Report}s.
     *
     * @return the {@link Set} of {@link Report}s
     */
    public Set<Report> getReports() {
        return reports != null ? reports : new HashSet<>();
    }

    /**
     * Returns the {@link Set} of {@link Tip}s.
     *
     * @return the {@link Set} of {@link Tip}s
     */
    public Set<Tip> getTips() {
        return tips != null ? tips : new HashSet<>();
    }

    /**
     * Returns the UUID of the {@link Agency} handling the case.
     *
     * @return the {@link Agency} UUID
     */
    public String getAgencyUuid() {
        return agencyUuid;
    }

    /**
     * Returns the {@link Relationship} of the {@link ContactPerson} to the {@link Person}.
     *
     * @return the {@link Relationship} of the {@link ContactPerson} to the {@link Person}
     */
    public Relationship getRelationshipToContactPerson() {
        return relationshipToContactPerson;
    }

    /**
     * Returns the {@link Relationship} of the alleged {@link Abductor}s to the {@link Person}.
     *
     * @return the {@link Relationship} of the alleged {@link Abductor}s to the {@link Person}
     */
    public Relationship getRelationshipToAbductors() {
        return relationshipToAbductors;
    }

    /**
     * Returns the {@link CaseStatus}.
     *
     * @return the {@link CaseStatus}
     */
    public CaseStatus getCaseStatus() {
        return caseStatus;
    }

    /**
     * Returns the {@link CaseType}.
     *
     * @return the {@link CaseType}
     */
    public CaseType getCaseType() {
        return caseType;
    }

    /**
     * Returns the circumstance.
     *
     * @return the circumstance
     */
    public String getCircumstance() {
        return circumstance;
    }

    /**
     * Returns the reward.
     *
     * @return the reward
     */
    public Integer getReward() {
        return reward;
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
     * @param investigation the {@link Investigation}
     * @return the {@link Builder}
     */
    public static Builder builder(Investigation investigation) {
        return new Builder(investigation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Investigation anInvestigation = (Investigation) o;

        return new EqualsBuilder()
            .append(uuid, anInvestigation.uuid)
            .append(creationDate, anInvestigation.creationDate)
            .append(personUuid, anInvestigation.personUuid)
            .append(contactPersonUuid, anInvestigation.contactPersonUuid)
            .append(encoderUuid, anInvestigation.encoderUuid)
            .append(investigatorUuid, anInvestigation.investigatorUuid)
            .append(abductorUuids, anInvestigation.abductorUuids)
            .append(reports, anInvestigation.reports)
            .append(tips, anInvestigation.tips)
            .append(agencyUuid, anInvestigation.agencyUuid)
            .append(relationshipToContactPerson, anInvestigation.relationshipToContactPerson)
            .append(relationshipToAbductors, anInvestigation.relationshipToAbductors)
            .append(caseStatus, anInvestigation.caseStatus)
            .append(caseType, anInvestigation.caseType)
            .append(circumstance, anInvestigation.circumstance)
            .append(reward, anInvestigation.reward)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(uuid)
            .append(creationDate)
            .append(personUuid)
            .append(contactPersonUuid)
            .append(encoderUuid)
            .append(investigatorUuid)
            .append(abductorUuids)
            .append(reports)
            .append(tips)
            .append(agencyUuid)
            .append(relationshipToContactPerson)
            .append(relationshipToAbductors)
            .append(caseStatus)
            .append(caseType)
            .append(circumstance)
            .append(reward)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("creationDate", creationDate)
            .append("personUuid", personUuid)
            .append("contactPersonUuid", contactPersonUuid)
            .append("encoderUuid", encoderUuid)
            .append("investigatorUuid", investigatorUuid)
            .append("abductorUuids", abductorUuids)
            .append("reports", reports)
            .append("tips", tips)
            .append("agencyUuid", agencyUuid)
            .append("relationshipToContactPerson", relationshipToContactPerson)
            .append("relationshipToAbductors", relationshipToAbductors)
            .append("caseStatus", caseStatus)
            .append("caseType", caseType)
            .append("circumstance", circumstance)
            .append("reward", reward)
            .toString();
    }

    /**
     * The builder.
     */
    public static class Builder {

        private String uuid;

        private OffsetDateTime creationDate;

        private String personUuid;

        private String contactPersonUuid;

        private String encoderUuid;

        private String investigatorUuid;

        private Set<String> abductorUuids;

        private Set<Report> reports;

        private Set<Tip> tips;

        private String agencyUuid;

        private Relationship relationshipToContactPerson;

        private Relationship relationshipToAbductors;

        private CaseStatus caseStatus;

        private CaseType caseType;

        private String circumstance;

        private Integer reward;

        /**
         * Private constructor.
         */
        private Builder() {
            // prevent instantiation
        }

        /**
         * Private constructor with {@link Investigation}.
         *
         * @param investigation the {@link Investigation}
         */
        private Builder(Investigation investigation) {
            uuid = investigation.getUuid();
            creationDate = investigation.getCreationDate();
            personUuid = investigation.getPersonUuid();
            contactPersonUuid = investigation.getContactPersonUuid();
            encoderUuid = investigation.getEncoderUuid();
            investigatorUuid = investigation.getInvestigatorUuid();
            abductorUuids = investigation.getAbductorUuids();
            reports = investigation.getReports();
            tips = investigation.getTips();
            agencyUuid = investigation.getAgencyUuid();
            relationshipToContactPerson = investigation.getRelationshipToContactPerson();
            relationshipToAbductors = investigation.getRelationshipToAbductors();
            caseStatus = investigation.getCaseStatus();
            caseType = investigation.getCaseType();
            circumstance = investigation.getCircumstance();
            reward = investigation.getReward();
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
         * Sets the {@link OffsetDateTime} the {@link Investigation} was filed.
         *
         * @param creationDate the {@link OffsetDateTime} the {@link Investigation} was filed
         * @return the {@link Builder}
         */
        public Builder withCreationDate(OffsetDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        /**
         * Sets the {@link Person} UUID.
         *
         * @param personUuid the {@link Person} UUID
         * @return the {@link Builder}
         */
        public Builder withPersonUuid(String personUuid) {
            this.personUuid = personUuid;
            return this;
        }

        /**
         * Sets the {@link ContactPerson} UUID.
         *
         * @param contactPersonUuid the {@link ContactPerson} UUID
         * @return the {@link Builder}
         */
        public Builder withContactPersonUuid(String contactPersonUuid) {
            this.contactPersonUuid = contactPersonUuid;
            return this;
        }

        /**
         * Sets the encoder UUID.
         *
         * @param encoderUuid the encoder UUID
         * @return the {@link Builder}
         */
        public Builder withEncoderUuid(String encoderUuid) {
            this.encoderUuid = encoderUuid;
            return this;
        }

        /**
         * Sets the investigator UUID.
         *
         * @param investigatorUuid the investigator UUID
         * @return the {@link Builder}
         */
        public Builder withInvestigatorUuid(String investigatorUuid) {
            this.investigatorUuid = investigatorUuid;
            return this;
        }

        /**
         * Sets the {@link Set} of alleged {@link Abductor} UUID's.
         *
         * @param abductorUuids the {@link Set} of alleged {@link Abductor} UUID's
         * @return the {@link Builder}
         */
        public Builder withAbductorUuids(Set<String> abductorUuids) {
            this.abductorUuids = abductorUuids;
            return this;
        }

        /**
         * Sets the {@link Set} of {@link Report}s.
         *
         * @param reports the {@link Set} of {@link Report}s
         * @return the {@link Builder}
         */
        public Builder withReports(Set<Report> reports) {
            this.reports = reports;
            return this;
        }

        /**
         * Sets the {@link Set} of {@link Tip}s.
         *
         * @param tips the {@link Set} of {@link Tip}s
         * @return the {@link Builder}
         */
        public Builder withTips(Set<Tip> tips) {
            this.tips = tips;
            return this;
        }

        /**
         * Sets the {@link Agency} handling the {@link Investigation}.
         *
         * @param agencyUuid the {@link Agency}
         * @return the {@link Builder}
         */
        public Builder withAgencyUuid(String agencyUuid) {
            this.agencyUuid = agencyUuid;
            return this;
        }

        /**
         * Sets the {@link Relationship} of the {@link ContactPerson} to the {@link Person}.
         *
         * @param relationshipToContactPerson the {@link Relationship} of the {@link ContactPerson} to the
         *                                    {@link Person}
         * @return the {@link Builder}
         */
        public Builder withRelationshipToContactPerson(
            Relationship relationshipToContactPerson) {
            this.relationshipToContactPerson = relationshipToContactPerson;
            return this;
        }

        /**
         * Sets the {@link Relationship} of the alleged {@link Abductor}s to the {@link Person}.
         *
         * @param relationshipToAbductors the {@link Relationship} of the alleged {@link Abductor}s to the
         *                                {@link Person}
         * @return the {@link Builder}
         */
        public Builder withRelationshipToAbductors(Relationship relationshipToAbductors) {
            this.relationshipToAbductors = relationshipToAbductors;
            return this;
        }

        /**
         * Sets the {@link CaseStatus}.
         *
         * @param caseStatus the {@link CaseStatus}
         * @return the {@link Builder}
         */
        public Builder withCaseStatus(CaseStatus caseStatus) {
            this.caseStatus = caseStatus;
            return this;
        }

        /**
         * Sets the {@link CaseType}.
         *
         * @param caseType the {@link CaseType}
         * @return the {@link Builder}
         */
        public Builder withCaseType(CaseType caseType) {
            this.caseType = caseType;
            return this;
        }

        /**
         * Sets the circumstance.
         *
         * @param circumstance the circumstance
         * @return the {@link Builder}
         */
        public Builder withCircumstance(String circumstance) {
            this.circumstance = circumstance;
            return this;
        }

        /**
         * Sets the reward.
         *
         * @param reward the reward
         * @return the {@link Builder}
         */
        public Builder withReward(Integer reward) {
            this.reward = reward;
            return this;
        }

        /**
         * Builds a {@link Investigation}.
         *
         * @return the {@link Investigation}
         */
        public Investigation build() {
            return new Investigation(this);
        }
    }
}
