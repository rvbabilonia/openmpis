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
package org.vincenzolabs.openmpis.agency.dao;

import java.util.Set;

import org.vincenzolabs.openmpis.domain.Agency;
import org.vincenzolabs.openmpis.domain.StreetAddress;

/**
 * The data access object for agency.
 *
 * @author Rey Vincent Babilonia
 */
public interface AgencyDAO {

    /**
     * Creates an {@link Agency}.
     *
     * @param name          the name
     * @param contactNumber the contact number
     * @param streetAddress the {@link StreetAddress}
     * @return the {@link Agency}
     */
    Agency createAgency(String name, String contactNumber, StreetAddress streetAddress);

    /**
     * Returns the {@link Set} of agencies.
     *
     * @return the {@link Set} of agencies
     */
    Set<Agency> retrieveAgencies();

    /**
     * Returns the {@link Agency} matching the given UUID.
     *
     * @param agencyUuid the {@link Agency} UUID
     * @return the {@link Agency}
     */
    Agency retrieveAgency(String agencyUuid);

    /**
     * @param agency the {@link Agency}
     * @return the {@link Agency}
     */
    Agency updateAgency(Agency agency);

    /**
     * Deletes an {@link Agency}.
     *
     * @param agencyUuid the {@link Agency} UUID
     * @return {@code true} if the {@link Agency} has been deleted; {@code false} otherwise
     */
    boolean deleteAgency(String agencyUuid);
}
