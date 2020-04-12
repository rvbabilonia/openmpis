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
package org.vincenzolabs.openmpis.adapter;

import java.util.Set;
import java.util.stream.Collectors;

import org.vincenzolabs.openmpis.domain.Investigation;
import org.vincenzolabs.openmpis.representation.InvestigationJson;

/**
 * The adapter for investigation.
 *
 * @author Rey Vincent Babilonia
 */
public final class InvestigationAdapter {

    /**
     * Adapts the {@link InvestigationJson} to an {@link Investigation}.
     *
     * @param json the {@link InvestigationJson}
     * @return the {@link Investigation}
     */
    public static Investigation adapt(InvestigationJson json) {
        return Investigation.builder()
            .withUuid(json.getUuid())
            .withCreationDate(json.getCreationDate())
            .withPersonUuid(json.getPersonUuid())
            .withContactPersonUuid(json.getContactPersonUuid())
            .withEncoderUuid(json.getEncoderUuid())
            .withInvestigatorUuid(json.getInvestigatorUuid())
            .withAbductorUuids(json.getAbductorUuids())
            .withReports(ReportAdapter.adaptFromJson(json.getReportJsons()))
            .withTips(TipAdapter.adaptFromJson(json.getTipJsons()))
            .withAgencyUuid(json.getAgencyUuid())
            .withRelationshipToAbductors(json.getRelationshipToAbductors())
            .withRelationshipToContactPerson(json.getRelationshipToContactPerson())
            .withCaseStatus(json.getCaseStatus())
            .withCaseType(json.getCaseType())
            .withCircumstance(json.getCircumstance())
            .withReward(json.getReward())
            .build();
    }

    /**
     * Adapts the {@link Investigation} to an {@link InvestigationJson}.
     *
     * @param bean the {@link Investigation}
     * @return the {@link InvestigationJson}
     */
    public static InvestigationJson adapt(Investigation bean) {
        InvestigationJson json = new InvestigationJson();
        json.setUuid(bean.getUuid());
        json.setCreationDate(bean.getCreationDate());
        json.setPersonUuid(bean.getPersonUuid());
        json.setContactPersonUuid(bean.getContactPersonUuid());
        json.setEncoderUuid(bean.getEncoderUuid());
        json.setInvestigatorUuid(bean.getInvestigatorUuid());
        json.setAbductorUuids(bean.getAbductorUuids());
        json.setReportJsons(ReportAdapter.adaptFromBean(bean.getReports()));
        json.setTipJsons(TipAdapter.adaptFromBean(bean.getTips()));
        json.setAgencyUuid(bean.getAgencyUuid());
        json.setRelationshipToAbductors(bean.getRelationshipToAbductors());
        json.setRelationshipToContactPerson(bean.getRelationshipToContactPerson());
        json.setCaseStatus(bean.getCaseStatus());
        json.setCaseType(bean.getCaseType());
        json.setCircumstance(bean.getCircumstance());
        json.setReward(bean.getReward());

        return json;
    }

    /**
     * Adapts the {@link Set} of {@link Investigation} to a {@link Set} of {@link InvestigationJson}.
     *
     * @param jsonSet the {@link Set} of {@link Investigation}
     * @return the {@link Set} of {@link InvestigationJson}
     */
    public static Set<InvestigationJson> adaptFromBean(Set<Investigation> jsonSet) {
        if (jsonSet.isEmpty()) {
            return Set.of();
        }

        return jsonSet.stream().map(InvestigationAdapter::adapt).collect(Collectors.toSet());
    }
}
