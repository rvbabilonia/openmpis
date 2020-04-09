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
package org.vincenzolabs.openmpis.person.service.impl;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.vincenzolabs.openmpis.person.dao.PersonDAO;
import org.vincenzolabs.openmpis.person.service.PersonService;
import org.vincenzolabs.openmpis.domain.Person;

/**
 * The implementation of {@link PersonService}.
 *
 * @author Rey Vincent Babilonia
 */
@Service
public class PersonServiceImpl
    implements PersonService {

    private final PersonDAO personDAO;

    /**
     * Default constructor.
     *
     * @param personDAO the {@link PersonDAO}
     */
    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public Person createPerson(Person person) {
        if (StringUtils.isBlank(person.getNickname())
            && (StringUtils.isBlank(person.getFirstName())) && StringUtils.isBlank(person.getLastName())) {
            throw new IllegalArgumentException("Nickname must not be blank if first and last names are also blank");
        }
        return personDAO.createPerson(person);
    }

    @Override
    public Set<Person> retrievePersons() {
        return personDAO.retrievePersons();
    }

    @Override
    public Person retrievePerson(final String personUuid) {
        return personDAO.retrievePerson(personUuid);
    }

    @Override
    public Person updatePerson(Person person) {
        if (StringUtils.isBlank(person.getNickname())
            && (StringUtils.isBlank(person.getFirstName())) && StringUtils.isBlank(person.getLastName())) {
            throw new IllegalArgumentException("Nickname must not be blank if first and last names are also blank");
        }
        return personDAO.updatePerson(person);
    }

    @Override
    public boolean deletePerson(final String personUuid) {
        return personDAO.deletePerson(personUuid);
    }
}
