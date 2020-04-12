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
import org.vincenzolabs.openmpis.domain.Report;
import org.vincenzolabs.openmpis.representation.ReportJson;

/**
 * The test case for {@link ReportAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class ReportAdapterTest {

    private static Report reportBean;

    private static ReportJson reportJson;

    @BeforeAll
    static void setUp() {
        OffsetDateTime now = OffsetDateTime.now();

        reportBean = Report.builder()
            .withUuid("dfaf05d3-e41d-483d-a3fa-22ef6f08a1d9")
            .withInvestigatorUuid("d324a72f-0d77-434b-a2ec-90317f5dc0e1")
            .withIpAddress("127.0.0.1")
            .withCreationDate(now)
            .withReport("Got a tip")
            .build();

        reportJson = new ReportJson();
        reportJson.setUuid("dfaf05d3-e41d-483d-a3fa-22ef6f08a1d9");
        reportJson.setInvestigatorUuid("d324a72f-0d77-434b-a2ec-90317f5dc0e1");
        reportJson.setCreationDate(now);
        reportJson.setReport("Got a tip");
        reportJson.setIpAddress("127.0.0.1");
    }

    @Test
    void toBean() {
        Report bean = ReportAdapter.adapt(reportJson);

        assertThat(bean).isEqualTo(reportBean);
    }

    @Test
    void toJson() {
        ReportJson json = ReportAdapter.adapt(reportBean);

        assertThat(json).isEqualTo(reportJson);
    }

    @Test
    void toJsonSet() {
        Set<ReportJson> jsonSet = ReportAdapter.adaptFromBean(Set.of(reportBean));

        assertThat(jsonSet).isEqualTo(Set.of(reportJson));
    }
}
