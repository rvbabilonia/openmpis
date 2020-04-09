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
package org.vincenzolabs.openmpis.abductor.service.impl;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.vincenzolabs.openmpis.abductor.dao.AbductorDAO;
import org.vincenzolabs.openmpis.abductor.service.AbductorService;
import org.vincenzolabs.openmpis.domain.Abductor;

/**
 * The implementation of {@link AbductorService}.
 *
 * @author Rey Vincent Babilonia
 */
@Service
public class AbductorServiceImpl
    implements AbductorService {

    private final AbductorDAO abductorDAO;

    /**
     * Default constructor.
     *
     * @param abductorDAO the {@link AbductorDAO}
     */
    public AbductorServiceImpl(AbductorDAO abductorDAO) {
        this.abductorDAO = abductorDAO;
    }

    @Override
    public Abductor createAbductor(Abductor abductor) {
        if (StringUtils.isBlank(abductor.getNickname())
            && (StringUtils.isBlank(abductor.getFirstName())) && StringUtils.isBlank(abductor.getLastName())) {
            throw new IllegalArgumentException("Nickname must not be blank if first and last names are also null");
        }
        return abductorDAO.createAbductor(abductor);
    }

    @Override
    public Set<Abductor> retrieveAbductors() {
        return abductorDAO.retrieveAbductors();
    }

    @Override
    public Abductor retrieveAbductor(final String abductorUuid) {
        return abductorDAO.retrieveAbductor(abductorUuid);
    }

    @Override
    public Abductor updateAbductor(Abductor abductor) {
        if (StringUtils.isBlank(abductor.getNickname())
            && (StringUtils.isBlank(abductor.getFirstName())) && StringUtils.isBlank(abductor.getLastName())) {
            throw new IllegalArgumentException("Nickname must not be blank if first and last names are also null");
        }
        return abductorDAO.updateAbductor(abductor);
    }

    @Override
    public boolean deleteAbductor(final String abductorUuid) {
        return abductorDAO.deleteAbductor(abductorUuid);
    }
}
