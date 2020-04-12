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

import org.vincenzolabs.openmpis.domain.StreetAddress;
import org.vincenzolabs.openmpis.representation.StreetAddressJson;

/**
 * The adapter for street address.
 *
 * @author Rey Vincent Babilonia
 */
public final class StreetAddressAdapter {

    /**
     * Adapts the {@link StreetAddressJson} to an {@link StreetAddress}.
     *
     * @param json the {@link StreetAddressJson}
     * @return the {@link StreetAddress}
     */
    public static StreetAddress adapt(StreetAddressJson json) {
        if (json == null) {
            return null;
        }

        return StreetAddress.builder()
            .withStreetNumber(json.getStreetNumber())
            .withSuburb(json.getSuburb())
            .withCity(json.getCity())
            .withProvince(json.getProvince())
            .withCountry(json.getCountry())
            .build();
    }

    /**
     * Adapts the {@link StreetAddress} to an {@link StreetAddressJson}.
     *
     * @param bean the {@link StreetAddress}
     * @return the {@link StreetAddressJson}
     */
    public static StreetAddressJson adapt(StreetAddress bean) {
        if (bean == null) {
            return null;
        }

        StreetAddressJson json = new StreetAddressJson();
        json.setStreetNumber(bean.getStreetNumber());
        json.setSuburb(bean.getSuburb());
        json.setCity(bean.getCity());
        json.setProvince(bean.getProvince());
        json.setCountry(bean.getCountry());

        return json;
    }
}
