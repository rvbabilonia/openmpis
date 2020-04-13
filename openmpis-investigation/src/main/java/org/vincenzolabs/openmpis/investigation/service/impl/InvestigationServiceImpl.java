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
package org.vincenzolabs.openmpis.investigation.service.impl;

import java.time.ZoneId;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.vincenzolabs.openmpis.domain.Investigation;
import org.vincenzolabs.openmpis.investigation.dao.InvestigationDAO;
import org.vincenzolabs.openmpis.investigation.service.InvestigationService;

/**
 * The implementation of {@link InvestigationService}.
 *
 * @author Rey Vincent Babilonia
 */
@Service
public class InvestigationServiceImpl
    implements InvestigationService {

    private final InvestigationDAO investigationDAO;

    /**
     * Default constructor.
     *
     * @param investigationDAO the {@link InvestigationDAO}
     */
    public InvestigationServiceImpl(InvestigationDAO investigationDAO) {
        this.investigationDAO = investigationDAO;
    }

    @Override
    public Investigation createInvestigation(Investigation investigation, ZoneId zoneId) {
        if (StringUtils.isBlank(investigation.getPersonUuid())) {
            throw new IllegalArgumentException("Person UUID must not be blank");
        }
        if (StringUtils.isBlank(investigation.getEncoderUuid())) {
            throw new IllegalArgumentException("Encoder UUID must not be blank");
        }
        if (StringUtils.isBlank(investigation.getAgencyUuid())) {
            throw new IllegalArgumentException("Agency UUID must not be blank");
        }
        if (investigation.getCaseType() == null) {
            throw new IllegalArgumentException("Case type must not be blank");
        }
        if (StringUtils.isBlank(investigation.getCircumstance())) {
            throw new IllegalArgumentException("Circumstance must not be blank");
        }
        return investigationDAO.createInvestigation(investigation, zoneId);
    }

    @Override
    public Set<Investigation> retrieveInvestigations() {
        return investigationDAO.retrieveInvestigations();
    }

    @Override
    public Investigation retrieveInvestigation(final String personUuid) {
        return investigationDAO.retrieveInvestigation(personUuid);
    }

    @Override
    public Investigation updateInvestigation(Investigation investigation) {
        return investigationDAO.updateInvestigation(investigation);
    }

    @Override
    public boolean deleteInvestigation(final String investigationUuid) {
        return investigationDAO.deleteInvestigation(investigationUuid);
    }
}
