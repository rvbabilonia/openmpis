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
package org.vincenzolabs.openmpis.institution.service.impl;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.vincenzolabs.openmpis.domain.Institution;
import org.vincenzolabs.openmpis.domain.StreetAddress;
import org.vincenzolabs.openmpis.institution.dao.InstitutionDAO;
import org.vincenzolabs.openmpis.institution.service.InstitutionService;

/**
 * The implementation of {@link InstitutionService}.
 *
 * @author Rey Vincent Babilonia
 */
@Service
public class InstitutionServiceImpl
    implements InstitutionService {

    private final InstitutionDAO institutionDAO;

    /**
     * Default constructor.
     *
     * @param institutionDAO the {@link InstitutionDAO}
     */
    public InstitutionServiceImpl(InstitutionDAO institutionDAO) {
        this.institutionDAO = institutionDAO;
    }

    @Override
    public Institution createInstitution(final String name, final String contactNumber, StreetAddress streetAddress,
        final String emailAddress) {
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
        if (StringUtils.isBlank(emailAddress)) {
            throw new IllegalArgumentException("Email address must not be blank");
        }
        return institutionDAO.createInstitution(name, contactNumber, streetAddress, emailAddress);
    }

    @Override
    public Set<Institution> retrieveInstitutions() {
        return institutionDAO.retrieveInstitutions();
    }

    @Override
    public Institution retrieveInstitution(final String institutionUuid) {
        return institutionDAO.retrieveInstitution(institutionUuid);
    }

    @Override
    public Institution updateInstitution(Institution institution) {
        if (StringUtils.isBlank(institution.getName())) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        if (StringUtils.isBlank(institution.getContactNumber())) {
            throw new IllegalArgumentException("Contact number must not be blank");
        }
        if (institution.getStreetAddress() == null) {
            throw new IllegalArgumentException("Street address must not be null");
        }
        if (StringUtils.isBlank(institution.getStreetAddress().getStreetNumber())) {
            throw new IllegalArgumentException("Street number must not be blank");
        }
        if (StringUtils.isBlank(institution.getStreetAddress().getCity())) {
            throw new IllegalArgumentException("City or town must not be blank");
        }
        if (StringUtils.isBlank(institution.getStreetAddress().getProvince())) {
            throw new IllegalArgumentException("Province or state must not be blank");
        }
        if (StringUtils.isBlank(institution.getStreetAddress().getCountry())) {
            throw new IllegalArgumentException("Country must not be blank");
        }
        if (StringUtils.isBlank(institution.getEmailAddress())) {
            throw new IllegalArgumentException("Email address must not be blank");
        }
        return institutionDAO.updateInstitution(institution);
    }

    @Override
    public boolean deleteInstitution(final String institutionUuid) {
        return institutionDAO.deleteInstitution(institutionUuid);
    }
}
