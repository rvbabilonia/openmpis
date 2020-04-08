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
package org.vincenzolabs.openmpis.enumeration;

import java.util.Set;

/**
 * The enumeration of groups.
 *
 * @author Rey Vincent Babilonia
 */
public enum Group {

    /**
     * The administrator group.
     */
    ADMINISTRATOR(Set.of(Role.CREATE_USER, Role.ARCHIVE_USER)),
    /**
     * The encoder group.
     */
    ENCODER(Set.of(Role.UPDATE_USER, Role.CREATE_CASE, Role.UPDATE_CASE,
        Role.CREATE_CONTACT_PERSON, Role.UPDATE_CONTACT_PERSON, Role.ARCHIVE_CONTACT_PERSON,
        Role.CREATE_ABDUCTOR, Role.UPDATE_ABDUCTOR, Role.ARCHIVE_ABDUCTOR,
        Role.CREATE_AGENCY, Role.UPDATE_AGENCY, Role.DELETE_AGENCY,
        Role.CREATE_INSTITUTION, Role.UPDATE_INSTITUTION, Role.DELETE_INSTITUTION)),
    /**
     * The investigator group.
     */
    INVESTIGATOR(Set.of(Role.UPDATE_USER, Role.CREATE_CASE, Role.UPDATE_CASE, Role.ARCHIVE_CASE,
        Role.CREATE_CONTACT_PERSON, Role.UPDATE_CONTACT_PERSON, Role.ARCHIVE_CONTACT_PERSON,
        Role.CREATE_ABDUCTOR, Role.UPDATE_ABDUCTOR, Role.ARCHIVE_ABDUCTOR,
        Role.CREATE_REPORT, Role.UPDATE_REPORT, Role.ARCHIVE_REPORT, Role.ARCHIVE_TIP));

    private Set<Role> roles;

    /**
     * Default constructor.
     *
     * @param roles the {@link Set} of {@link Role}s
     */
    Group(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Returns the {@link Set} of {@link Role}s assigned to the {@link Group}.
     *
     * @return the {@link Set} of {@link Role}s
     */
    public Set<Role> getRoles() {
        return roles;
    }
}
