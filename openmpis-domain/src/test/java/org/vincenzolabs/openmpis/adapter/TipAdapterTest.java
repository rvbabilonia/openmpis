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

import java.time.OffsetDateTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.vincenzolabs.openmpis.domain.Tip;
import org.vincenzolabs.openmpis.enumeration.MessageStatus;
import org.vincenzolabs.openmpis.representation.TipJson;

/**
 * The test case for {@link TipAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class TipAdapterTest {

    private static Tip tipBean;

    private static TipJson tipJson;

    @BeforeAll
    static void setUp() {
        OffsetDateTime now = OffsetDateTime.now();

        tipBean = Tip.builder()
            .withUuid("6baa539d-45b4-4c46-a7cb-e11622b5f073")
            .withIpAddress("192.168.1.2")
            .withStatus(MessageStatus.UNREAD)
            .withCreationDate(now.minusHours(1))
            .withFirstName("Tippy")
            .withLastName("Tipster")
            .withEmailAddress("tiptip@gmail.com")
            .withContactNumber("+64 21 123 4567")
            .withMessage("Spotted in CBD")
            .build();

        tipJson = new TipJson();
        tipJson.setUuid("6baa539d-45b4-4c46-a7cb-e11622b5f073");
        tipJson.setIpAddress("192.168.1.2");
        tipJson.setStatus(MessageStatus.UNREAD);
        tipJson.setCreationDate(now.minusHours(1));
        tipJson.setFirstName("Tippy");
        tipJson.setLastName("Tipster");
        tipJson.setEmailAddress("tiptip@gmail.com");
        tipJson.setContactNumber("+64 21 123 4567");
        tipJson.setMessage("Spotted in CBD");
    }

    @Test
    void toBean() {
        Tip bean = TipAdapter.adapt(tipJson);

        assertThat(bean).isEqualTo(tipBean);
    }

    @Test
    void toJson() {
        TipJson json = TipAdapter.adapt(tipBean);

        assertThat(json).isEqualTo(tipJson);
    }

    @Test
    void toJsonSet() {
        Set<TipJson> jsonSet = TipAdapter.adaptFromBean(Set.of(tipBean));

        assertThat(jsonSet).isEqualTo(Set.of(tipJson));
    }
}
