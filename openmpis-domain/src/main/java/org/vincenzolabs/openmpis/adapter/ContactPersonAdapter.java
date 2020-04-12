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

import org.vincenzolabs.openmpis.domain.ContactPerson;
import org.vincenzolabs.openmpis.representation.ContactPersonJson;

/**
 * The adapter for contact person.
 *
 * @author Rey Vincent Babilonia
 */
public final class ContactPersonAdapter {

    /**
     * Adapts the {@link ContactPersonJson} to an {@link ContactPerson}.
     *
     * @param json the {@link ContactPersonJson}
     * @return the {@link ContactPerson}
     */
    public static ContactPerson adapt(ContactPersonJson json) {
        return ContactPerson.builder()
            .withUuid(json.getUuid())
            .withFirstName(json.getFirstName())
            .withMiddleName(json.getMiddleName())
            .withLastName(json.getLastName())
            .withEmailAddress(json.getEmailAddress())
            .withContactNumber(json.getContactNumber())
            .withStreetAddress(StreetAddressAdapter.adapt(json.getStreetAddressJson()))
            .build();
    }

    /**
     * Adapts the {@link ContactPerson} to an {@link ContactPersonJson}.
     *
     * @param bean the {@link ContactPerson}
     * @return the {@link ContactPersonJson}
     */
    public static ContactPersonJson adapt(ContactPerson bean) {
        ContactPersonJson json = new ContactPersonJson();
        json.setUuid(bean.getUuid());
        json.setFirstName(bean.getFirstName());
        json.setMiddleName(bean.getMiddleName());
        json.setLastName(bean.getLastName());
        json.setEmailAddress(bean.getEmailAddress());
        json.setContactNumber(bean.getContactNumber());
        json.setStreetAddressJson(StreetAddressAdapter.adapt(bean.getStreetAddress()));

        return json;
    }

    /**
     * Adapts the {@link Set} of {@link ContactPerson} to a {@link Set} of {@link ContactPersonJson}.
     *
     * @param jsonSet the {@link Set} of {@link ContactPerson}
     * @return the {@link Set} of {@link ContactPersonJson}
     */
    public static Set<ContactPersonJson> adaptFromBean(Set<ContactPerson> jsonSet) {
        if (jsonSet.isEmpty()) {
            return Set.of();
        }

        return jsonSet.stream().map(ContactPersonAdapter::adapt).collect(Collectors.toSet());
    }
}
