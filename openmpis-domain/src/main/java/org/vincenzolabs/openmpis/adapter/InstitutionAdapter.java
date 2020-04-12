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

import org.vincenzolabs.openmpis.domain.Institution;
import org.vincenzolabs.openmpis.representation.InstitutionJson;

/**
 * The adapter for institution.
 *
 * @author Rey Vincent Babilonia
 */
public final class InstitutionAdapter {

    /**
     * Adapts the {@link InstitutionJson} to an {@link Institution}.
     *
     * @param json the {@link InstitutionJson}
     * @return the {@link Institution}
     */
    public static Institution adapt(InstitutionJson json) {
        return Institution.builder()
            .withUuid(json.getUuid())
            .withName(json.getName())
            .withStreetAddress(StreetAddressAdapter.adapt(json.getStreetAddressJson()))
            .withContactNumber(json.getContactNumber())
            .withEmailAddress(json.getEmailAddress())
            .build();
    }

    /**
     * Adapts the {@link Institution} to an {@link InstitutionJson}.
     *
     * @param bean the {@link Institution}
     * @return the {@link InstitutionJson}
     */
    public static InstitutionJson adapt(Institution bean) {
        InstitutionJson json = new InstitutionJson();
        json.setUuid(bean.getUuid());
        json.setName(bean.getName());
        json.setStreetAddressJson(StreetAddressAdapter.adapt(bean.getStreetAddress()));
        json.setContactNumber(bean.getContactNumber());
        json.setEmailAddress(bean.getEmailAddress());

        return json;
    }

    /**
     * Adapts the {@link Set} of {@link Institution} to a {@link Set} of {@link InstitutionJson}.
     *
     * @param jsonSet the {@link Set} of {@link Institution}
     * @return the {@link Set} of {@link InstitutionJson}
     */
    public static Set<InstitutionJson> adaptFromBean(Set<Institution> jsonSet) {
        if (jsonSet.isEmpty()) {
            return Set.of();
        }

        return jsonSet.stream().map(InstitutionAdapter::adapt).collect(Collectors.toSet());
    }
}
