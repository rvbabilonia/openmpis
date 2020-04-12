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

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.vincenzolabs.openmpis.domain.AdditionalDetails;
import org.vincenzolabs.openmpis.domain.Description;
import org.vincenzolabs.openmpis.domain.Person;
import org.vincenzolabs.openmpis.domain.StreetAddress;
import org.vincenzolabs.openmpis.enumeration.EyeColor;
import org.vincenzolabs.openmpis.enumeration.HairColor;
import org.vincenzolabs.openmpis.enumeration.Sex;
import org.vincenzolabs.openmpis.representation.AdditionalDetailsJson;
import org.vincenzolabs.openmpis.representation.DescriptionJson;
import org.vincenzolabs.openmpis.representation.PersonJson;
import org.vincenzolabs.openmpis.representation.StreetAddressJson;

/**
 * The test case for {@link PersonAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class PersonAdapterTest {

    private static Person personBean;

    private static PersonJson personJson;

    @BeforeAll
    static void setUp() {
        personBean = Person.builder()
            .withUuid("4c36f2ab-7a86-431e-b032-436702a5e913")
            .withFirstName("Kirsten Chloe")
            .withLastName("Dy")
            .withBirthDate(LocalDate.of(1999, 2, 1))
            .withLastSeenOrFoundDate(OffsetDateTime.of(2015, 2, 3, 16, 45, 0, 0, ZoneOffset.of("+08:00")))
            .withLastSeenOrFoundLocation(StreetAddress.builder()
                .withCity("Tagaytay City")
                .withProvince("Cavite")
                .withCountry("Philippines")
                .build())
            .withPrimaryPhotoUuid("e05da057-9fd0-472e-98ca-251536fef9bc")
            .withPhotoUuids(Set.of("e05da057-9fd0-472e-98ca-251536fef9bc"))
            .withDescription(Description.builder()
                .withSex(Sex.FEMALE)
                .withEyeColor(EyeColor.BROWN)
                .withHairColor(HairColor.BLACK)
                .withHeight(150D)
                .withMass(45D)
                .withDistinguishingFeatures("Has long black thick hair")
                .build())
            .withAdditionalDetails(AdditionalDetails.builder()
                .withPrimaryAgeProgressedPhotoUuid("750c5e0c-1f68-4068-b1f9-d4b4a4e69093")
                .withAgeProgressedPhotoUuids(Set.of("750c5e0c-1f68-4068-b1f9-d4b4a4e69093"))
                .build())
            .build();

        personJson = new PersonJson();
        personJson.setUuid("4c36f2ab-7a86-431e-b032-436702a5e913");
        personJson.setFirstName("Kirsten Chloe");
        personJson.setLastName("Dy");
        personJson.setBirthDate(LocalDate.of(1999, 2, 1));
        personJson.setAge(21);
        personJson.setLastSeenOrFoundDate(OffsetDateTime.of(2015, 2, 3, 16, 45, 0, 0, ZoneOffset.of("+08:00")));
        StreetAddressJson lastSeenLocation = new StreetAddressJson();
        lastSeenLocation.setCity("Tagaytay City");
        lastSeenLocation.setProvince("Cavite");
        lastSeenLocation.setCountry("Philippines");
        personJson.setLastSeenOrFoundLocationJson(lastSeenLocation);
        personJson.setPrimaryPhotoUuid("e05da057-9fd0-472e-98ca-251536fef9bc");
        personJson.setPhotoUuids(Set.of("e05da057-9fd0-472e-98ca-251536fef9bc"));
        DescriptionJson descriptionJson = new DescriptionJson();
        descriptionJson.setSex(Sex.FEMALE);
        descriptionJson.setEyeColor(EyeColor.BROWN);
        descriptionJson.setHairColor(HairColor.BLACK);
        descriptionJson.setHeight(150D);
        descriptionJson.setMass(45D);
        descriptionJson.setDistinguishingFeatures("Has long black thick hair");
        personJson.setDescriptionJson(descriptionJson);
        AdditionalDetailsJson additionalDetailsJson = new AdditionalDetailsJson();
        additionalDetailsJson.setAgeProgressedPhotoUuids(Set.of("750c5e0c-1f68-4068-b1f9-d4b4a4e69093"));
        additionalDetailsJson.setPrimaryAgeProgressedPhotoUuid("750c5e0c-1f68-4068-b1f9-d4b4a4e69093");
        personJson.setAdditionalDetailsJson(additionalDetailsJson);
    }

    @Test
    void toBean() {
        Person bean = PersonAdapter.adapt(personJson);

        assertThat(bean).isEqualTo(personBean);
        assertThat(bean.getAge()).isGreaterThanOrEqualTo(21);
    }

    @Test
    void toJson() {
        PersonJson json = PersonAdapter.adapt(personBean);

        assertThat(json).isEqualTo(personJson);
        assertThat(json.getAge()).isGreaterThanOrEqualTo(16);
    }

    @Test
    void toJsonSet() {
        Set<PersonJson> jsonSet = PersonAdapter.adaptFromBean(Set.of(personBean));

        assertThat(jsonSet).isEqualTo(Set.of(personJson));
    }
}
