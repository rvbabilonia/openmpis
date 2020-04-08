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
package org.vincenzolabs.openmpis.agency.service.impl;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.vincenzolabs.openmpis.agency.dao.AgencyDAO;
import org.vincenzolabs.openmpis.agency.service.AgencyService;
import org.vincenzolabs.openmpis.domain.Agency;
import org.vincenzolabs.openmpis.domain.StreetAddress;

/**
 * The implementation of {@link AgencyService}.
 *
 * @author Rey Vincent Babilonia
 */
@Service
public class AgencyServiceImpl
    implements AgencyService {

    private final AgencyDAO agencyDAO;

    /**
     * Default constructor.
     *
     * @param agencyDAO the {@link AgencyDAO}
     */
    public AgencyServiceImpl(AgencyDAO agencyDAO) {
        this.agencyDAO = agencyDAO;
    }

    @Override
    public Agency createAgency(final String name, final String contactNumber, StreetAddress streetAddress) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        if (StringUtils.isBlank(contactNumber)) {
            throw new IllegalArgumentException("Contact number must not be blank");
        }
        if (streetAddress == null) {
            throw new IllegalArgumentException("Street address must not be null");
        }
        if (StringUtils.isBlank(streetAddress.getStreetNumber())) {
            throw new IllegalArgumentException("Street number must not be blank");
        }
        if (StringUtils.isBlank(streetAddress.getCity())) {
            throw new IllegalArgumentException("City or town must not be blank");
        }
        if (StringUtils.isBlank(streetAddress.getProvince())) {
            throw new IllegalArgumentException("Province or state must not be blank");
        }
        if (StringUtils.isBlank(streetAddress.getCountry())) {
            throw new IllegalArgumentException("Country must not be blank");
        }
        return agencyDAO.createAgency(name, contactNumber, streetAddress);
    }

    @Override
    public Set<Agency> retrieveAgencies() {
        return agencyDAO.retrieveAgencies();
    }

    @Override
    public Agency retrieveAgency(final String agencyUuid) {
        return agencyDAO.retrieveAgency(agencyUuid);
    }

    @Override
    public Agency updateAgency(Agency agency) {
        if (StringUtils.isBlank(agency.getName())) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        if (StringUtils.isBlank(agency.getContactNumber())) {
            throw new IllegalArgumentException("Contact number must not be blank");
        }
        if (agency.getStreetAddress() == null) {
            throw new IllegalArgumentException("Street address must not be null");
        }
        if (StringUtils.isBlank(agency.getStreetAddress().getStreetNumber())) {
            throw new IllegalArgumentException("Street number must not be blank");
        }
        if (StringUtils.isBlank(agency.getStreetAddress().getCity())) {
            throw new IllegalArgumentException("City or town must not be blank");
        }
        if (StringUtils.isBlank(agency.getStreetAddress().getProvince())) {
            throw new IllegalArgumentException("Province or state must not be blank");
        }
        if (StringUtils.isBlank(agency.getStreetAddress().getCountry())) {
            throw new IllegalArgumentException("Country must not be blank");
        }
        return agencyDAO.updateAgency(agency);
    }

    @Override
    public boolean deleteAgency(final String agencyUuid) {
        return agencyDAO.deleteAgency(agencyUuid);
    }
}
