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

import org.vincenzolabs.openmpis.domain.Description;
import org.vincenzolabs.openmpis.representation.DescriptionJson;

/**
 * The adapter for description.
 *
 * @author Rey Vincent Babilonia
 */
public final class DescriptionAdapter {

    /**
     * Adapts the {@link DescriptionJson} to an {@link Description}.
     *
     * @param json the {@link DescriptionJson}
     * @return the {@link Description}
     */
    public static Description adapt(DescriptionJson json) {
        if (json == null) {
            return null;
        }

        return Description.builder()
            .withSex(json.getSex())
            .withHeight(json.getHeight())
            .withMass(json.getMass())
            .withReligion(json.getReligion())
            .withRace(json.getRace())
            .withEyeColor(json.getEyeColor())
            .withHairColor(json.getHairColor())
            .withMedicalCondition(json.getMedicalCondition())
            .withDistinguishingFeatures(json.getDistinguishingFeatures())
            .withPersonalEffects(json.getPersonalEffects())
            .withRemarks(json.getRemarks())
            .build();
    }

    /**
     * Adapts the {@link Description} to an {@link DescriptionJson}.
     *
     * @param bean the {@link Description}
     * @return the {@link DescriptionJson}
     */
    public static DescriptionJson adapt(Description bean) {
        if (bean == null) {
            return null;
        }

        DescriptionJson json = new DescriptionJson();
        json.setSex(bean.getSex());
        json.setHeight(bean.getHeight());
        json.setMass(bean.getMass());
        json.setReligion(bean.getReligion());
        json.setRace(bean.getRace());
        json.setEyeColor(bean.getEyeColor());
        json.setHairColor(bean.getHairColor());
        json.setMedicalCondition(bean.getMedicalCondition());
        json.setDistinguishingFeatures(bean.getDistinguishingFeatures());
        json.setPersonalEffects(bean.getPersonalEffects());
        json.setRemarks(bean.getRemarks());

        return json;
    }
}
