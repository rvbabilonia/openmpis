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
import org.vincenzolabs.openmpis.domain.Institution;
import org.vincenzolabs.openmpis.domain.StreetAddress;
import org.vincenzolabs.openmpis.representation.InstitutionJson;
import org.vincenzolabs.openmpis.representation.StreetAddressJson;

/**
 * The test case for {@link InstitutionAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class InstitutionAdapterTest {

    private static Institution institutionBean;

    private static InstitutionJson institutionJson;

    @BeforeAll
    static void setUp() {
        institutionBean = Institution.builder()
            .withUuid("062701f7-6a51-4978-bb3a-3ddcbdcb132e")
            .withName("Department of Social Welfare and Development")
            .withContactNumber("+63 2 931 8101")
            .withEmailAddress("inquiry@dswd.gov.ph")
            .withStreetAddress(StreetAddress.builder()
                .withStreetNumber("DSWD Building, Batasan Complex")
                .withSuburb("Batasan Hills")
                .withCity("Quezon City")
                .withCountry("Philippines")
                .build())
            .build();

        institutionJson = new InstitutionJson();
        institutionJson.setUuid("062701f7-6a51-4978-bb3a-3ddcbdcb132e");
        institutionJson.setName("Department of Social Welfare and Development");
        institutionJson.setContactNumber("+63 2 931 8101");
        institutionJson.setEmailAddress("inquiry@dswd.gov.ph");
        StreetAddressJson streetAddressJson = new StreetAddressJson();
        streetAddressJson.setStreetNumber("DSWD Building, Batasan Complex");
        streetAddressJson.setSuburb("Batasan Hills");
        streetAddressJson.setCity("Quezon City");
        streetAddressJson.setCountry("Philippines");
        institutionJson.setStreetAddressJson(streetAddressJson);
    }

    @Test
    void toBean() {
        Institution bean = InstitutionAdapter.adapt(institutionJson);

        assertThat(bean).isEqualTo(institutionBean);
    }

    @Test
    void toJson() {
        InstitutionJson json = InstitutionAdapter.adapt(institutionBean);

        assertThat(json).isEqualTo(institutionJson);
    }

    @Test
    void toJsonSet() {
        Set<InstitutionJson> json = InstitutionAdapter.adaptFromBean(Set.of(institutionBean));

        assertThat(json).isEqualTo(Set.of(institutionJson));
    }
}
