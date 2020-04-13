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
package org.vincenzolabs.openmpis.investigation.dao;

import java.time.ZoneId;
import java.util.Set;

import org.vincenzolabs.openmpis.domain.Investigation;

/**
 * The data access object for investigation.
 *
 * @author Rey Vincent Babilonia
 */
public interface InvestigationDAO {

    /**
     * Creates an {@link Investigation}.
     *
     * @param investigation the {@link Investigation}
     * @param zoneId        the {@link ZoneId}
     * @return the {@link Investigation}
     */
    Investigation createInvestigation(Investigation investigation, ZoneId zoneId);

    /**
     * Returns the {@link Set} of {@link Investigation}s.
     *
     * @return the {@link Set} of {@link Investigation}s
     */
    Set<Investigation> retrieveInvestigations();

    /**
     * Returns the {@link Investigation} matching the given UUID.
     *
     * @param investigationUuid the {@link Investigation} UUID
     * @return the {@link Investigation}
     */
    Investigation retrieveInvestigation(String investigationUuid);

    /**
     * @param investigation the {@link Investigation}
     * @return the {@link Investigation}
     */
    Investigation updateInvestigation(Investigation investigation);

    /**
     * Deletes a {@link Investigation}.
     *
     * @param investigationUuid the {@link Investigation} UUID
     * @return {@code true} if the {@link Investigation} has been deleted; {@code false} otherwise
     */
    boolean deleteInvestigation(String investigationUuid);
}
