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

import org.vincenzolabs.openmpis.domain.AdditionalDetails;
import org.vincenzolabs.openmpis.representation.AdditionalDetailsJson;

/**
 * The adapter for additional details.
 *
 * @author Rey Vincent Babilonia
 */
public final class AdditionalDetailsAdapter {

    /**
     * Adapts the {@link AdditionalDetailsJson} to an {@link AdditionalDetails}.
     *
     * @param json the {@link AdditionalDetailsJson}
     * @return the {@link AdditionalDetails}
     */
    public static AdditionalDetails adapt(AdditionalDetailsJson json) {
        if (json == null) {
            return null;
        }

        return AdditionalDetails.builder()
            .withAgeProgressedPhotoUuids(json.getAgeProgressedPhotoUuids())
            .withPrimaryAgeProgressedPhotoUuid(json.getPrimaryAgeProgressedPhotoUuid())
            .withCodisId(json.getCodisId())
            .withAfisId(json.getAfisId())
            .withDentalId(json.getDentalId())
            .build();
    }

    /**
     * Adapts the {@link AdditionalDetails} to an {@link AdditionalDetailsJson}.
     *
     * @param bean the {@link AdditionalDetails}
     * @return the {@link AdditionalDetailsJson}
     */
    public static AdditionalDetailsJson adapt(AdditionalDetails bean) {
        if (bean == null) {
            return null;
        }

        AdditionalDetailsJson json = new AdditionalDetailsJson();
        json.setAgeProgressedPhotoUuids(bean.getAgeProgressedPhotoUuids());
        json.setPrimaryAgeProgressedPhotoUuid(bean.getPrimaryAgeProgressedPhotoUuid());
        json.setCodisId(bean.getCodisId());
        json.setAfisId(bean.getAfisId());
        json.setDentalId(bean.getDentalId());

        return json;
    }
}
