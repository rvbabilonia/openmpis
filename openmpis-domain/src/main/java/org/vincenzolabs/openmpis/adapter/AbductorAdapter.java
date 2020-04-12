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

import org.vincenzolabs.openmpis.domain.Abductor;
import org.vincenzolabs.openmpis.representation.AbductorJson;

/**
 * The adapter for abductor.
 *
 * @author Rey Vincent Babilonia
 */
public final class AbductorAdapter {

    /**
     * Adapts the {@link AbductorJson} to an {@link Abductor}.
     *
     * @param json the {@link AbductorJson}
     * @return the {@link Abductor}
     */
    public static Abductor adapt(AbductorJson json) {
        return Abductor.builder()
            .withUuid(json.getUuid())
            .withFirstName(json.getFirstName())
            .withMiddleName(json.getMiddleName())
            .withLastName(json.getLastName())
            .withNickname(json.getNickname())
            .withBirthDate(json.getBirthDate())
            .withLastSeenDate(json.getLastSeenDate())
            .withLastSeenLocation(StreetAddressAdapter.adapt(json.getLastSeenLocationJson()))
            .withPossibleLocation(StreetAddressAdapter.adapt(json.getPossibleLocationJson()))
            .withPhotoUuids(json.getPhotoUuids())
            .withPrimaryPhotoUuid(json.getPrimaryPhotoUuid())
            .withDescription(DescriptionAdapter.adapt(json.getDescriptionJson()))
            .withAdditionalDetails(AdditionalDetailsAdapter.adapt(json.getAdditionalDetailsJson()))
            .build();
    }

    /**
     * Adapts the {@link Abductor} to an {@link AbductorJson}.
     *
     * @param bean the {@link Abductor}
     * @return the {@link AbductorJson}
     */
    public static AbductorJson adapt(Abductor bean) {
        AbductorJson json = new AbductorJson();
        json.setUuid(bean.getUuid());
        json.setFirstName(bean.getFirstName());
        json.setMiddleName(bean.getMiddleName());
        json.setLastName(bean.getLastName());
        json.setNickname(bean.getNickname());
        json.setBirthDate(bean.getBirthDate());
        json.setLastSeenDate(bean.getLastSeenDate());
        json.setLastSeenLocationJson(StreetAddressAdapter.adapt(bean.getLastSeenLocation()));
        json.setPossibleLocationJson(StreetAddressAdapter.adapt(bean.getPossibleLocation()));
        json.setPhotoUuids(bean.getPhotoUuids());
        json.setPrimaryPhotoUuid(bean.getPrimaryPhotoUuid());
        json.setDescriptionJson(DescriptionAdapter.adapt(bean.getDescription()));
        json.setAdditionalDetailsJson(AdditionalDetailsAdapter.adapt(bean.getAdditionalDetails()));

        return json;
    }

    /**
     * Adapts the {@link Set} of {@link Abductor} to a {@link Set} of {@link AbductorJson}.
     *
     * @param abductors the {@link Set} of {@link Abductor}
     * @return the {@link Set} of {@link AbductorJson}
     */
    public static Set<AbductorJson> adaptFromBean(Set<Abductor> abductors) {
        if (abductors.isEmpty()) {
            return Set.of();
        }

        return abductors.stream().map(AbductorAdapter::adapt).collect(Collectors.toSet());
    }
}
