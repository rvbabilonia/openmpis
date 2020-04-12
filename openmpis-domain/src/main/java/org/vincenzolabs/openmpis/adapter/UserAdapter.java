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

import java.util.Set;
import java.util.stream.Collectors;

import org.vincenzolabs.openmpis.domain.User;
import org.vincenzolabs.openmpis.representation.UserJson;

/**
 * The adapter for user.
 *
 * @author Rey Vincent Babilonia
 */
public final class UserAdapter {

    /**
     * Adapts the {@link UserJson} to an {@link User}.
     *
     * @param json the {@link UserJson}
     * @return the {@link User}
     */
    public static User adapt(UserJson json) {
        return User.builder()
            .withUuid(json.getUuid())
            .withGroup(json.getGroup())
            .withEmailAddress(json.getEmailAddress())
            .withPassword(json.getPassword())
            .withRank(json.getRank())
            .withFirstName(json.getFirstName())
            .withMiddleName(json.getMiddleName())
            .withLastName(json.getLastName())
            .withDesignation(json.getDesignation())
            .withAgencyUuid(json.getAgencyUuid())
            .withIpAddress(json.getIpAddress())
            .withLastLoginDate(json.getLastLoginDate())
            .withCreationDate(json.getCreationDate())
            .withActive(json.isActive())
            .withCreatorUuid(json.getCreatorUuid())
            .build();
    }

    /**
     * Adapts the {@link User} to an {@link UserJson}.
     *
     * @param bean the {@link User}
     * @return the {@link UserJson}
     */
    public static UserJson adapt(User bean) {
        UserJson json = new UserJson();
        json.setUuid(bean.getUuid());
        json.setUuid(bean.getUuid());
        json.setGroup(bean.getGroup());
        json.setEmailAddress(bean.getEmailAddress());
        json.setPassword(bean.getPassword());
        json.setRank(bean.getRank());
        json.setFirstName(bean.getFirstName());
        json.setMiddleName(bean.getMiddleName());
        json.setLastName(bean.getLastName());
        json.setDesignation(bean.getDesignation());
        json.setAgencyUuid(bean.getAgencyUuid());
        json.setIpAddress(bean.getIpAddress());
        json.setLastLoginDate(bean.getLastLoginDate());
        json.setCreationDate(bean.getCreationDate());
        json.setActive(bean.isActive());
        json.setCreatorUuid(bean.getCreatorUuid());

        return json;
    }

    /**
     * Adapts the {@link Set} of {@link User} to a {@link Set} of {@link UserJson}.
     *
     * @param jsonSet the {@link Set} of {@link User}
     * @return the {@link Set} of {@link UserJson}
     */
    public static Set<UserJson> adaptFromBean(Set<User> jsonSet) {
        if (jsonSet.isEmpty()) {
            return Set.of();
        }

        return jsonSet.stream().map(UserAdapter::adapt).collect(Collectors.toSet());
    }
}
