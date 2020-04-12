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

import org.vincenzolabs.openmpis.domain.Person;
import org.vincenzolabs.openmpis.representation.PersonJson;

/**
 * The adapter for person.
 *
 * @author Rey Vincent Babilonia
 */
public final class PersonAdapter {

    /**
     * Adapts the {@link PersonJson} to an {@link Person}.
     *
     * @param json the {@link PersonJson}
     * @return the {@link Person}
     */
    public static Person adapt(PersonJson json) {
        return Person.builder()
            .withUuid(json.getUuid())
            .withFirstName(json.getFirstName())
            .withMiddleName(json.getMiddleName())
            .withLastName(json.getLastName())
            .withNickname(json.getNickname())
            .withBirthDate(json.getBirthDate())
            .withLastSeenOrFoundDate(json.getLastSeenOrFoundDate())
            .withLastSeenOrFoundLocation(StreetAddressAdapter.adapt(json.getLastSeenOrFoundLocationJson()))
            .withPossibleLocation(StreetAddressAdapter.adapt(json.getPossibleLocationJson()))
            .withInstitutionUuid(json.getInstitutionUuid())
            .withPhotoUuids(json.getPhotoUuids())
            .withPrimaryPhotoUuid(json.getPrimaryPhotoUuid())
            .withDescription(DescriptionAdapter.adapt(json.getDescriptionJson()))
            .withAdditionalDetails(AdditionalDetailsAdapter.adapt(json.getAdditionalDetailsJson()))
            .build();
    }

    /**
     * Adapts the {@link Person} to an {@link PersonJson}.
     *
     * @param bean the {@link Person}
     * @return the {@link PersonJson}
     */
    public static PersonJson adapt(Person bean) {
        PersonJson json = new PersonJson();
        json.setUuid(bean.getUuid());
        json.setFirstName(bean.getFirstName());
        json.setMiddleName(bean.getMiddleName());
        json.setLastName(bean.getLastName());
        json.setNickname(bean.getNickname());
        json.setBirthDate(bean.getBirthDate());
        json.setLastSeenOrFoundDate(bean.getLastSeenOrFoundDate());
        json.setLastSeenOrFoundLocationJson(StreetAddressAdapter.adapt(bean.getLastSeenOrFoundLocation()));
        json.setPossibleLocationJson(StreetAddressAdapter.adapt(bean.getPossibleLocation()));
        json.setPhotoUuids(bean.getPhotoUuids());
        json.setPrimaryPhotoUuid(bean.getPrimaryPhotoUuid());
        json.setDescriptionJson(DescriptionAdapter.adapt(bean.getDescription()));
        json.setAdditionalDetailsJson(AdditionalDetailsAdapter.adapt(bean.getAdditionalDetails()));

        return json;
    }

    /**
     * Adapts the {@link Set} of {@link Person} to a {@link Set} of {@link PersonJson}.
     *
     * @param jsonSet the {@link Set} of {@link Person}
     * @return the {@link Set} of {@link PersonJson}
     */
    public static Set<PersonJson> adaptFromBean(Set<Person> jsonSet) {
        if (jsonSet.isEmpty()) {
            return Set.of();
        }

        return jsonSet.stream().map(PersonAdapter::adapt).collect(Collectors.toSet());
    }
}
