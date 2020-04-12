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
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.vincenzolabs.openmpis.domain.Agency;
import org.vincenzolabs.openmpis.domain.StreetAddress;
import org.vincenzolabs.openmpis.representation.AgencyJson;
import org.vincenzolabs.openmpis.representation.StreetAddressJson;

/**
 * The test case for {@link AgencyAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class AgencyAdapterTest {

    private static Agency agencyBean;

    private static AgencyJson agencyJson;

    @BeforeAll
    static void setUp() {
        agencyBean = Agency.builder()
            .withUuid("53d17125-c77f-4fcd-81fc-90d462dfc159")
            .withName("National Bureau of Investigation")
            .withContactNumber("+63 2 521 9208")
            .withStreetAddress(StreetAddress.builder()
                .withCity("Manila")
                .withCountry("Philippines")
                .build())
            .build();

        agencyJson = new AgencyJson();
        agencyJson.setUuid("53d17125-c77f-4fcd-81fc-90d462dfc159");
        agencyJson.setName("National Bureau of Investigation");
        agencyJson.setContactNumber("+63 2 521 9208");
        StreetAddressJson streetAddressJson = new StreetAddressJson();
        streetAddressJson.setCity("Manila");
        streetAddressJson.setCountry("Philippines");
        agencyJson.setStreetAddressJson(streetAddressJson);
    }

    @Test
    void toBean() {
        Agency bean = AgencyAdapter.adapt(agencyJson);

        assertThat(bean).isEqualTo(agencyBean);
    }

    @Test
    void toJson() {
        AgencyJson json = AgencyAdapter.adapt(agencyBean);

        assertThat(json).isEqualTo(agencyJson);
    }

    @Test
    void toJsonSet() {
        Set<AgencyJson> jsonSet = AgencyAdapter.adaptFromBean(Set.of(agencyBean));

        assertThat(jsonSet).isEqualTo(Set.of(agencyJson));
    }
}
