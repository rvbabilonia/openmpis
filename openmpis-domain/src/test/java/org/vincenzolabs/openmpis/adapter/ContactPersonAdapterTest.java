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

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.vincenzolabs.openmpis.domain.ContactPerson;
import org.vincenzolabs.openmpis.representation.ContactPersonJson;

/**
 * The test case for {@link ContactPersonAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class ContactPersonAdapterTest {

    private static ContactPerson contactPersonBean;

    private static ContactPersonJson contactPersonJson;

    @BeforeAll
    static void setUp() {
        contactPersonBean = ContactPerson.builder()
            .withUuid("b863c8f9-f6e4-4e24-946e-52ad72748bd1")
            .withFirstName("Rey")
            .withLastName("Babilonia")
            .withContactNumber("+64 27 123 4567")
            .withEmailAddress("rvbabilonia@gmail.com")
            .build();

        contactPersonJson = new ContactPersonJson();
        contactPersonJson.setUuid("b863c8f9-f6e4-4e24-946e-52ad72748bd1");
        contactPersonJson.setFirstName("Rey");
        contactPersonJson.setLastName("Babilonia");
        contactPersonJson.setContactNumber("+64 27 123 4567");
        contactPersonJson.setEmailAddress("rvbabilonia@gmail.com");
    }

    @Test
    void toBean() {
        ContactPerson bean = ContactPersonAdapter.adapt(contactPersonJson);

        assertThat(bean).isEqualTo(contactPersonBean);
    }

    @Test
    void toJson() {
        ContactPersonJson json = ContactPersonAdapter.adapt(contactPersonBean);

        assertThat(json).isEqualTo(contactPersonJson);
    }

    @Test
    void toJsonSet() {
        Set<ContactPersonJson> json = ContactPersonAdapter.adaptFromBean(Set.of(contactPersonBean));

        assertThat(json).isEqualTo(Set.of(contactPersonJson));
    }
}
