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
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.vincenzolabs.openmpis.enumeration.CaseStatus;
import org.vincenzolabs.openmpis.enumeration.CaseType;
import org.vincenzolabs.openmpis.enumeration.Relationship;

/**
 * The data transfer object for case investigation.
 *
 * @author Rey Vincent Babilonia
 */
public class InvestigationJson {

    private String uuid;

    private OffsetDateTime creationDate;

    private String personUuid;

    private String contactPersonUuid;

    private String encoderUuid;

    private String investigatorUuid;

    private Set<String> abductorUuids;

    private Set<ReportJson> reportJsons;

    private Set<TipJson> tipJsons;

    private String agencyUuid;

    private Relationship relationshipToContactPerson;

    private Relationship relationshipToAbductors;

    private CaseStatus caseStatus;

    private CaseType caseType;

    private String circumstance;

    private Integer reward;

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
     * Returns the {@link OffsetDateTime} the {@link InvestigationJson} was filed.
     *
     * @return the {@link OffsetDateTime} the {@link InvestigationJson} was filed
     */
    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the {@link OffsetDateTime} the {@link InvestigationJson} was filed.
     *
     * @param creationDate the {@link OffsetDateTime} the {@link InvestigationJson} was filed
     */
    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the {@link PersonJson} UUID.
     *
     * @return the {@link PersonJson} UUID
     */
    public String getPersonUuid() {
        return personUuid;
    }

    /**
     * Sets the {@link PersonJson} UUID.
     *
     * @param personUuid the {@link PersonJson} UUID
     */
    public void setPersonUuid(String personUuid) {
        this.personUuid = personUuid;
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
     * Sets the {@link ContactPerson} UUID.
     *
     * @param contactPersonUuid the {@link ContactPerson} UUID
     */
    public void setContactPersonUuid(String contactPersonUuid) {
        this.contactPersonUuid = contactPersonUuid;
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
     * Sets the encoder UUID.
     *
     * @param encoderUuid the encoder UUID
     */
    public void setEncoderUuid(String encoderUuid) {
        this.encoderUuid = encoderUuid;
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
     * Sets the investigator UUID.
     *
     * @param investigatorUuid the investigator UUID
     */
    public void setInvestigatorUuid(String investigatorUuid) {
        this.investigatorUuid = investigatorUuid;
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
     * Sets the {@link Set} of alleged abductor UUID's.
     *
     * @param abductorUuids the {@link Set} of alleged abductor UUID's
     */
    public void setAbductorUuids(Set<String> abductorUuids) {
        this.abductorUuids = abductorUuids;
    }

    /**
     * Returns the {@link Set} of {@link ReportJson}s.
     *
     * @return the {@link Set} of {@link ReportJson}s
     */
    public Set<ReportJson> getReportJsons() {
        return reportJsons != null ? reportJsons : new HashSet<>();
    }

    /**
     * Sets the {@link Set} of {@link ReportJson}s.
     *
     * @param reportJsons the {@link Set} of {@link ReportJson}s
     */
    public void setReportJsons(Set<ReportJson> reportJsons) {
        this.reportJsons = reportJsons;
    }

    /**
     * Returns the {@link Set} of {@link TipJson}s.
     *
     * @return the {@link Set} of {@link TipJson}s
     */
    public Set<TipJson> getTipJsons() {
        return tipJsons != null ? tipJsons : new HashSet<>();
    }

    /**
     * Sets the {@link Set} of {@link TipJson}s.
     *
     * @param tipJsons the {@link Set} of {@link TipJson}s
     */
    public void setTipJsons(Set<TipJson> tipJsons) {
        this.tipJsons = tipJsons;
    }

    /**
     * Returns the UUID of the {@link AgencyJson} handling the case.
     *
     * @return the {@link AgencyJson} UUID
     */
    public String getAgencyUuid() {
        return agencyUuid;
    }

    /**
     * Sets the UUID of the {@link AgencyJson} handling the case.
     *
     * @param agencyUuid the UUID of the {@link AgencyJson} handling the case
     */
    public void setAgencyUuid(String agencyUuid) {
        this.agencyUuid = agencyUuid;
    }

    /**
     * Returns the {@link Relationship} of the {@link ContactPerson} to the {@link PersonJson}.
     *
     * @return the {@link Relationship} of the {@link ContactPerson} to the {@link PersonJson}
     */
    public Relationship getRelationshipToContactPerson() {
        return relationshipToContactPerson;
    }

    /**
     * Sets the {@link Relationship} of the {@link ContactPerson} to the {@link PersonJson}.
     *
     * @param relationshipToContactPerson the {@link Relationship} of the {@link ContactPerson} to the
     *                                    {@link PersonJson}
     */
    public void setRelationshipToContactPerson(Relationship relationshipToContactPerson) {
        this.relationshipToContactPerson = relationshipToContactPerson;
    }

    /**
     * Returns the {@link Relationship} of the alleged {@link AbductorJson}s to the {@link PersonJson}.
     *
     * @return the {@link Relationship} of the alleged {@link AbductorJson}s to the {@link PersonJson}
     */
    public Relationship getRelationshipToAbductors() {
        return relationshipToAbductors;
    }

    /**
     * Sets the {@link Relationship} of the alleged {@link AbductorJson}s to the {@link PersonJson}.
     *
     * @param relationshipToAbductors the {@link Relationship} of the alleged {@link AbductorJson}s to the
     *                                {@link PersonJson}
     */
    public void setRelationshipToAbductors(Relationship relationshipToAbductors) {
        this.relationshipToAbductors = relationshipToAbductors;
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
     * Sets the {@link CaseStatus}.
     *
     * @param caseStatus the {@link CaseStatus}
     */
    public void setCaseStatus(CaseStatus caseStatus) {
        this.caseStatus = caseStatus;
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
     * Sets the {@link CaseType}.
     *
     * @param caseType the {@link CaseType}
     */
    public void setCaseType(CaseType caseType) {
        this.caseType = caseType;
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
     * Sets the circumstance.
     *
     * @param circumstance the circumstance
     */
    public void setCircumstance(String circumstance) {
        this.circumstance = circumstance;
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
     * Sets the reward.
     *
     * @param reward the reward
     */
    public void setReward(Integer reward) {
        this.reward = reward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvestigationJson anInvestigationJson = (InvestigationJson) o;

        return new EqualsBuilder()
            .append(uuid, anInvestigationJson.uuid)
            .append(creationDate, anInvestigationJson.creationDate)
            .append(personUuid, anInvestigationJson.personUuid)
            .append(contactPersonUuid, anInvestigationJson.contactPersonUuid)
            .append(encoderUuid, anInvestigationJson.encoderUuid)
            .append(investigatorUuid, anInvestigationJson.investigatorUuid)
            .append(abductorUuids, anInvestigationJson.abductorUuids)
            .append(reportJsons, anInvestigationJson.reportJsons)
            .append(tipJsons, anInvestigationJson.tipJsons)
            .append(agencyUuid, anInvestigationJson.agencyUuid)
            .append(relationshipToContactPerson, anInvestigationJson.relationshipToContactPerson)
            .append(relationshipToAbductors, anInvestigationJson.relationshipToAbductors)
            .append(caseStatus, anInvestigationJson.caseStatus)
            .append(caseType, anInvestigationJson.caseType)
            .append(circumstance, anInvestigationJson.circumstance)
            .append(reward, anInvestigationJson.reward)
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
            .append(reportJsons)
            .append(tipJsons)
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
            .append("reportJsons", reportJsons)
            .append("tipJsons", tipJsons)
            .append("agencyUuid", agencyUuid)
            .append("relationshipToContactPerson", relationshipToContactPerson)
            .append("relationshipToAbductors", relationshipToAbductors)
            .append("caseStatus", caseStatus)
            .append("caseType", caseType)
            .append("circumstance", circumstance)
            .append("reward", reward)
            .toString();
    }
}
