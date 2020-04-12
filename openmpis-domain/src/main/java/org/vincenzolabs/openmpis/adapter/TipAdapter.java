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

import org.vincenzolabs.openmpis.domain.Tip;
import org.vincenzolabs.openmpis.representation.TipJson;

/**
 * The adapter for tip.
 *
 * @author Rey Vincent Babilonia
 */
public final class TipAdapter {

    /**
     * Adapts the {@link TipJson} to an {@link Tip}.
     *
     * @param json the {@link TipJson}
     * @return the {@link Tip}
     */
    public static Tip adapt(TipJson json) {
        return Tip.builder()
            .withUuid(json.getUuid())
            .withCreationDate(json.getCreationDate())
            .withFirstName(json.getFirstName())
            .withLastName(json.getLastName())
            .withEmailAddress(json.getEmailAddress())
            .withContactNumber(json.getContactNumber())
            .withMessage(json.getMessage())
            .withStatus(json.getStatus())
            .withIpAddress(json.getIpAddress())
            .build();
    }

    /**
     * Adapts the {@link Set} of {@link TipJson} to a {@link Set} of {@link Tip}.
     *
     * @param jsonSet the {@link Set} of {@link TipJson}
     * @return the {@link Set} of {@link Tip}
     */
    public static Set<Tip> adaptFromJson(Set<TipJson> jsonSet) {
        if (jsonSet.isEmpty()) {
            return Set.of();
        }

        return jsonSet.stream().map(TipAdapter::adapt).collect(Collectors.toSet());
    }

    /**
     * Adapts the {@link Tip} to an {@link TipJson}.
     *
     * @param bean the {@link Tip}
     * @return the {@link TipJson}
     */
    public static TipJson adapt(Tip bean) {
        TipJson json = new TipJson();
        json.setUuid(bean.getUuid());
        json.setCreationDate(bean.getCreationDate());
        json.setFirstName(bean.getFirstName());
        json.setLastName(bean.getLastName());
        json.setEmailAddress(bean.getEmailAddress());
        json.setContactNumber(bean.getContactNumber());
        json.setMessage(bean.getMessage());
        json.setStatus(bean.getStatus());
        json.setIpAddress(bean.getIpAddress());

        return json;
    }

    /**
     * Adapts the {@link Set} of {@link Tip} to a {@link Set} of {@link TipJson}.
     *
     * @param jsonSet the {@link Set} of {@link Tip}
     * @return the {@link Set} of {@link TipJson}
     */
    public static Set<TipJson> adaptFromBean(Set<Tip> jsonSet) {
        if (jsonSet.isEmpty()) {
            return Set.of();
        }

        return jsonSet.stream().map(TipAdapter::adapt).collect(Collectors.toSet());
    }
}
