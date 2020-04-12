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

import org.vincenzolabs.openmpis.domain.Report;
import org.vincenzolabs.openmpis.representation.ReportJson;

/**
 * The adapter for report.
 *
 * @author Rey Vincent Babilonia
 */
public final class ReportAdapter {

    /**
     * Adapts the {@link ReportJson} to an {@link Report}.
     *
     * @param json the {@link ReportJson}
     * @return the {@link Report}
     */
    public static Report adapt(ReportJson json) {
        return Report.builder()
            .withUuid(json.getUuid())
            .withCreationDate(json.getCreationDate())
            .withInvestigatorUuid(json.getInvestigatorUuid())
            .withReport(json.getReport())
            .withIpAddress(json.getIpAddress())
            .build();
    }

    /**
     * Adapts the {@link Set} of {@link ReportJson} to a {@link Set} of {@link Report}.
     *
     * @param jsonSet the {@link Set} of {@link ReportJson}
     * @return the {@link Set} of {@link Report}
     */
    public static Set<Report> adaptFromJson(Set<ReportJson> jsonSet) {
        if (jsonSet.isEmpty()) {
            return Set.of();
        }

        return jsonSet.stream().map(ReportAdapter::adapt).collect(Collectors.toSet());
    }

    /**
     * Adapts the {@link Report} to an {@link ReportJson}.
     *
     * @param bean the {@link Report}
     * @return the {@link ReportJson}
     */
    public static ReportJson adapt(Report bean) {
        ReportJson json = new ReportJson();
        json.setUuid(bean.getUuid());
        json.setCreationDate(bean.getCreationDate());
        json.setInvestigatorUuid(bean.getInvestigatorUuid());
        json.setReport(bean.getReport());
        json.setIpAddress(bean.getIpAddress());

        return json;
    }

    /**
     * Adapts the {@link Set} of {@link Report} to a {@link Set} of {@link ReportJson}.
     *
     * @param jsonSet the {@link Set} of {@link Report}
     * @return the {@link Set} of {@link ReportJson}
     */
    public static Set<ReportJson> adaptFromBean(Set<Report> jsonSet) {
        if (jsonSet.isEmpty()) {
            return Set.of();
        }

        return jsonSet.stream().map(ReportAdapter::adapt).collect(Collectors.toSet());
    }
}
