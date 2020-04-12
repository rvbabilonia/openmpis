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

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.vincenzolabs.openmpis.domain.Investigation;
import org.vincenzolabs.openmpis.domain.Report;
import org.vincenzolabs.openmpis.domain.Tip;
import org.vincenzolabs.openmpis.enumeration.CaseStatus;
import org.vincenzolabs.openmpis.enumeration.CaseType;
import org.vincenzolabs.openmpis.enumeration.MessageStatus;
import org.vincenzolabs.openmpis.enumeration.Relationship;
import org.vincenzolabs.openmpis.representation.InvestigationJson;
import org.vincenzolabs.openmpis.representation.ReportJson;
import org.vincenzolabs.openmpis.representation.TipJson;

/**
 * The test case for {@link InvestigationAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class InvestigationAdapterTest {

    private static Investigation investigationBean;

    private static InvestigationJson investigationJson;

    @BeforeAll
    static void setUp() {
        OffsetDateTime now = OffsetDateTime.now();

        investigationBean = Investigation.builder()
            .withUuid("58860904-70a3-4030-ab3b-c4d00eb3899f")
            .withCreationDate(now.minusDays(1))
            .withPersonUuid("47be9d25-39ad-4d26-bcf9-f6c417c559c7")
            .withContactPersonUuid("2fa5dd46-d243-4843-982b-5cc4b3938289")
            .withAgencyUuid("45bebec8-64aa-4986-91b1-bf3f8771e8a3")
            .withEncoderUuid("f50805f5-7167-4f3e-bfbe-b41ad049bdf1")
            .withInvestigatorUuid("d324a72f-0d77-434b-a2ec-90317f5dc0e1")
            .withAbductorUuids(Set.of("944e1aa-4243-420d-a84e-1711bb6be3ae"))
            .withReward(1000)
            .withCircumstance("Abducted in Albany")
            .withCaseType(CaseType.NON_FAMILY_ABDUCTION)
            .withCaseStatus(CaseStatus.ONGOING)
            .withRelationshipToContactPerson(Relationship.STEPFATHER)
            .withRelationshipToAbductors(Relationship.UNKNOWN)
            .withTips(Set.of(Tip.builder()
                .withUuid("6baa539d-45b4-4c46-a7cb-e11622b5f073")
                .withIpAddress("192.168.1.2")
                .withStatus(MessageStatus.UNREAD)
                .withCreationDate(now.minusHours(1))
                .withFirstName("Tippy")
                .withLastName("Tipster")
                .withEmailAddress("tiptip@gmail.com")
                .withContactNumber("+64 21 123 4567")
                .withMessage("Spotted in CBD")
                .build()))
            .withReports(Set.of(Report.builder()
                .withUuid("dfaf05d3-e41d-483d-a3fa-22ef6f08a1d9")
                .withInvestigatorUuid("d324a72f-0d77-434b-a2ec-90317f5dc0e1")
                .withIpAddress("127.0.0.1")
                .withCreationDate(now)
                .withReport("Got a tip")
                .build()))
            .build();

        investigationJson = new InvestigationJson();
        investigationJson.setUuid("58860904-70a3-4030-ab3b-c4d00eb3899f");
        investigationJson.setCreationDate(now.minusDays(1));
        investigationJson.setPersonUuid("47be9d25-39ad-4d26-bcf9-f6c417c559c7");
        investigationJson.setContactPersonUuid("2fa5dd46-d243-4843-982b-5cc4b3938289");
        investigationJson.setAgencyUuid("45bebec8-64aa-4986-91b1-bf3f8771e8a3");
        investigationJson.setEncoderUuid("f50805f5-7167-4f3e-bfbe-b41ad049bdf1");
        investigationJson.setInvestigatorUuid("d324a72f-0d77-434b-a2ec-90317f5dc0e1");
        investigationJson.setAbductorUuids(Set.of("944e1aa-4243-420d-a84e-1711bb6be3ae"));
        investigationJson.setReward(1000);
        investigationJson.setCircumstance("Abducted in Albany");
        investigationJson.setCaseType(CaseType.NON_FAMILY_ABDUCTION);
        investigationJson.setCaseStatus(CaseStatus.ONGOING);
        investigationJson.setRelationshipToContactPerson(Relationship.STEPFATHER);
        investigationJson.setRelationshipToAbductors(Relationship.UNKNOWN);
        TipJson tipJson = new TipJson();
        tipJson.setUuid("6baa539d-45b4-4c46-a7cb-e11622b5f073");
        tipJson.setIpAddress("192.168.1.2");
        tipJson.setStatus(MessageStatus.UNREAD);
        tipJson.setCreationDate(now.minusHours(1));
        tipJson.setFirstName("Tippy");
        tipJson.setLastName("Tipster");
        tipJson.setEmailAddress("tiptip@gmail.com");
        tipJson.setContactNumber("+64 21 123 4567");
        tipJson.setMessage("Spotted in CBD");
        investigationJson.setTipJsons(Set.of(tipJson));
        ReportJson reportJson = new ReportJson();
        reportJson.setUuid("dfaf05d3-e41d-483d-a3fa-22ef6f08a1d9");
        reportJson.setInvestigatorUuid("d324a72f-0d77-434b-a2ec-90317f5dc0e1");
        reportJson.setCreationDate(now);
        reportJson.setReport("Got a tip");
        reportJson.setIpAddress("127.0.0.1");
        investigationJson.setReportJsons(Set.of(reportJson));
    }

    @Test
    void toBean() {
        Investigation bean = InvestigationAdapter.adapt(investigationJson);

        assertThat(bean).isEqualTo(investigationBean);
    }

    @Test
    void toJson() {
        InvestigationJson json = InvestigationAdapter.adapt(investigationBean);

        assertThat(json).isEqualTo(investigationJson);
    }

    @Test
    void toJsonSet() {
        Set<InvestigationJson> json = InvestigationAdapter.adaptFromBean(Set.of(investigationBean));

        assertThat(json).isEqualTo(Set.of(investigationJson));
    }
}
