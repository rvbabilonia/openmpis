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
import org.vincenzolabs.openmpis.domain.User;
import org.vincenzolabs.openmpis.enumeration.Group;
import org.vincenzolabs.openmpis.representation.UserJson;

/**
 * The test case for {@link UserAdapter}.
 *
 * @author Rey Vincent Babilonia
 */
class UserAdapterTest {

    private static User userBean;

    private static UserJson userJson;

    @BeforeAll
    static void setUp() {
        OffsetDateTime now = OffsetDateTime.now();
        userBean = User.builder()
            .withUuid("e7398a39-d42f-4023-ab7e-16d22b9d6322")
            .withFirstName("Sherlock")
            .withLastName("Holmes")
            .withGroup(Group.INVESTIGATOR)
            .withDesignation("Consulting Detective")
            .withActive(true)
            .withLastLoginDate(now)
            .withIpAddress("127.0.0.1")
            .withEmailAddress("sherlock.holmes@gmail.com")
            .withPassword("3l3m3nt@ry")
            .withAgencyUuid("f2bad30a-db75-4799-8c4e-0d5d24bbea87")
            .withCreatorUuid("d28d0e6c-5ea2-4ffb-9c60-be2be3d124e2")
            .withCreationDate(now.minusMonths(2))
            .build();

        userJson = new UserJson();
        userJson.setUuid("e7398a39-d42f-4023-ab7e-16d22b9d6322");
        userJson.setFirstName("Sherlock");
        userJson.setLastName("Holmes");
        userJson.setGroup(Group.INVESTIGATOR);
        userJson.setDesignation("Consulting Detective");
        userJson.setActive(true);
        userJson.setLastLoginDate(now);
        userJson.setIpAddress("127.0.0.1");
        userJson.setEmailAddress("sherlock.holmes@gmail.com");
        userJson.setPassword("3l3m3nt@ry");
        userJson.setAgencyUuid("f2bad30a-db75-4799-8c4e-0d5d24bbea87");
        userJson.setCreatorUuid("d28d0e6c-5ea2-4ffb-9c60-be2be3d124e2");
        userJson.setCreationDate(now.minusMonths(2));
    }

    @Test
    void toBean() {
        User bean = UserAdapter.adapt(userJson);

        assertThat(bean).isEqualTo(userBean);
    }

    @Test
    void toJson() {
        UserJson json = UserAdapter.adapt(userBean);

        assertThat(json).isEqualTo(userJson);
    }

    @Test
    void toJsonSet() {
        Set<UserJson> jsonSet = UserAdapter.adaptFromBean(Set.of(userBean));

        assertThat(jsonSet).isEqualTo(Set.of(userJson));
    }
}
