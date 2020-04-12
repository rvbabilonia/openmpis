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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.vincenzolabs.openmpis.domain.Description;
import org.vincenzolabs.openmpis.enumeration.EyeColor;
import org.vincenzolabs.openmpis.enumeration.HairColor;
import org.vincenzolabs.openmpis.enumeration.Race;
import org.vincenzolabs.openmpis.enumeration.Religion;
import org.vincenzolabs.openmpis.enumeration.Sex;
import org.vincenzolabs.openmpis.representation.DescriptionJson;

/**
 * The test case for {@link DescriptionAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class DescriptionAdapterTest {

    private static Description descriptionBean;

    private static DescriptionJson descriptionJson;

    @BeforeAll
    static void setUp() {
        descriptionBean = Description.builder()
            .withSex(Sex.MALE)
            .withHeight(163D)
            .withMass(74D)
            .withReligion(Religion.CHRISTIANITY)
            .withRace(Race.FILIPINO)
            .withEyeColor(EyeColor.BROWN)
            .withHairColor(HairColor.BLACK)
            .withDistinguishingFeatures("Scar on left cheek")
            .withPersonalEffects("Last seen wearing black shirt and blue jeans")
            .withRemarks("N/A")
            .build();

        descriptionJson = new DescriptionJson();
        descriptionJson.setSex(Sex.MALE);
        descriptionJson.setHeight(163D);
        descriptionJson.setMass(74D);
        descriptionJson.setReligion(Religion.CHRISTIANITY);
        descriptionJson.setRace(Race.FILIPINO);
        descriptionJson.setEyeColor(EyeColor.BROWN);
        descriptionJson.setHairColor(HairColor.BLACK);
        descriptionJson.setDistinguishingFeatures("Scar on left cheek");
        descriptionJson.setPersonalEffects("Last seen wearing black shirt and blue jeans");
        descriptionJson.setRemarks("N/A");
    }

    @Test
    void toBean() {
        DescriptionJson json = DescriptionAdapter.adapt(descriptionBean);

        assertThat(json).isEqualTo(descriptionJson);
    }

    @Test
    void toJson() {
        Description bean = DescriptionAdapter.adapt(descriptionJson);

        assertThat(bean).isEqualTo(descriptionBean);
    }
}
