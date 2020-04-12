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
import org.vincenzolabs.openmpis.domain.AdditionalDetails;
import org.vincenzolabs.openmpis.representation.AdditionalDetailsJson;

/**
 * The test case for {@link AdditionalDetailsAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class AdditionalDetailsAdapterTest {

    private static AdditionalDetails additionalDetailsBean;

    private static AdditionalDetailsJson additionalDetailsJson;

    @BeforeAll
    static void setUp() {
        additionalDetailsBean = AdditionalDetails.builder()
            .withAgeProgressedPhotoUuids(Set.of("06ee677e-f830-4df4-b1db-1186847516f6",
                "3a6c562d-2f6e-4470-bd53-64e2724b3e68"))
            .withPrimaryAgeProgressedPhotoUuid("06ee677e-f830-4df4-b1db-1186847516f6")
            .withCodisId("CODIS-ID")
            .withAfisId("AFIS-ID")
            .withDentalId("DENTAL-ID")
            .build();

        additionalDetailsJson = new AdditionalDetailsJson();
        additionalDetailsJson.setAgeProgressedPhotoUuids(Set.of("06ee677e-f830-4df4-b1db-1186847516f6",
            "3a6c562d-2f6e-4470-bd53-64e2724b3e68"));
        additionalDetailsJson.setPrimaryAgeProgressedPhotoUuid("06ee677e-f830-4df4-b1db-1186847516f6");
        additionalDetailsJson.setCodisId("CODIS-ID");
        additionalDetailsJson.setAfisId("AFIS-ID");
        additionalDetailsJson.setDentalId("DENTAL-ID");
    }

    @Test
    void toBean() {
        AdditionalDetails bean = AdditionalDetailsAdapter.adapt(additionalDetailsJson);

        assertThat(bean).isEqualTo(additionalDetailsBean);
    }

    @Test
    void toJson() {
        AdditionalDetailsJson json = AdditionalDetailsAdapter.adapt(additionalDetailsBean);

        assertThat(json).isEqualTo(additionalDetailsJson);
    }
}
