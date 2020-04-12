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
import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.vincenzolabs.openmpis.domain.Abductor;
import org.vincenzolabs.openmpis.domain.Description;
import org.vincenzolabs.openmpis.enumeration.Sex;
import org.vincenzolabs.openmpis.representation.AbductorJson;
import org.vincenzolabs.openmpis.representation.DescriptionJson;

/**
 * The test case for {@link StreetAddressAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class StreetAddressAdapterTest {

    private static Abductor abductorBean;

    private static AbductorJson abductorJson;

    @BeforeAll
    static void setUp() {
        abductorBean = Abductor.builder()
            .withUuid("4c36f2ab-7a86-431e-b032-436702a5e913")
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withLastSeenDate(OffsetDateTime.of(2020, 4, 12, 16, 45, 0, 0, ZoneOffset.of("+12:00")))
            .withPrimaryPhotoUuid("e05da057-9fd0-472e-98ca-251536fef9bc")
            .withPhotoUuids(Set.of("e05da057-9fd0-472e-98ca-251536fef9bc"))
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build();

        abductorJson = new AbductorJson();
        abductorJson.setUuid("4c36f2ab-7a86-431e-b032-436702a5e913");
        abductorJson.setFirstName("Nick");
        abductorJson.setLastName("Ruskin");
        abductorJson.setLastSeenDate(OffsetDateTime.of(2020, 4, 12, 16, 45, 0, 0, ZoneOffset.of("+12:00")));
        abductorJson.setPrimaryPhotoUuid("e05da057-9fd0-472e-98ca-251536fef9bc");
        abductorJson.setPhotoUuids(Set.of("e05da057-9fd0-472e-98ca-251536fef9bc"));
        DescriptionJson descriptionJson = new DescriptionJson();
        descriptionJson.setSex(Sex.MALE);
        descriptionJson.setRemarks("Ruskin is a police detective. Considered armed and dangerous.");
        abductorJson.setDescriptionJson(descriptionJson);
    }

    @Test
    void toBean() {
        Abductor bean = AbductorAdapter.adapt(abductorJson);

        assertThat(bean).isEqualTo(abductorBean);
    }

    @Test
    void toJson() {
        AbductorJson json = AbductorAdapter.adapt(abductorBean);

        assertThat(json).isEqualTo(abductorJson);
    }

    @Test
    void toJsonSet() {
        Set<AbductorJson> jsonSet = AbductorAdapter.adaptFromBean(Set.of(abductorBean));

        assertThat(jsonSet).isEqualTo(Set.of(abductorJson));
    }
}
