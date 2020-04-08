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
package org.vincenzolabs.openmpis.institution.dao;

import java.util.Set;

import org.vincenzolabs.openmpis.domain.Institution;
import org.vincenzolabs.openmpis.domain.StreetAddress;

/**
 * The data access object for institution.
 *
 * @author Rey Vincent Babilonia
 */
public interface InstitutionDAO {

    /**
     * Creates an {@link Institution}.
     *
     * @param name          the name
     * @param contactNumber the contact number
     * @param streetAddress the {@link StreetAddress}
     * @param emailAddress  the email address
     * @return the {@link Institution}
     */
    Institution createInstitution(String name, String contactNumber, StreetAddress streetAddress,
        String emailAddress);

    /**
     * Returns the {@link Set} of {@link Institution}s.
     *
     * @return the {@link Set} of {@link Institution}s
     */
    Set<Institution> retrieveInstitutions();

    /**
     * Returns the {@link Institution} matching the given UUID.
     *
     * @param institutionUuid the {@link Institution} UUID
     * @return the {@link Institution}
     */
    Institution retrieveInstitution(String institutionUuid);

    /**
     * @param institution the {@link Institution}
     * @return the {@link Institution}
     */
    Institution updateInstitution(Institution institution);

    /**
     * Deletes an {@link Institution}.
     *
     * @param institutionUuid the {@link Institution} UUID
     * @return {@code true} if the {@link Institution} has been deleted; {@code false} otherwise
     */
    boolean deleteInstitution(String institutionUuid);
}
