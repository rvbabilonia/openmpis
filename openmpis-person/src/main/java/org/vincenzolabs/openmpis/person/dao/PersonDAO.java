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
package org.vincenzolabs.openmpis.person.dao;

import java.util.Set;

import org.vincenzolabs.openmpis.domain.Person;

/**
 * The data access object for person.
 *
 * @author Rey Vincent Babilonia
 */
public interface PersonDAO {

    /**
     * Creates an {@link Person}.
     *
     * @param person the {@link Person}
     * @return the {@link Person}
     */
    Person createPerson(Person person);

    /**
     * Returns the {@link Set} of {@link Person}s.
     *
     * @return the {@link Set} of {@link Person}s
     */
    Set<Person> retrievePersons();

    /**
     * Returns the {@link Person} matching the given UUID.
     *
     * @param personUuid the {@link Person} UUID
     * @return the {@link Person}
     */
    Person retrievePerson(String personUuid);

    /**
     * @param person the {@link Person}
     * @return the {@link Person}
     */
    Person updatePerson(Person person);

    /**
     * Deletes a {@link Person}.
     *
     * @param personUuid the {@link Person} UUID
     * @return {@code true} if the {@link Person} has been deleted; {@code false} otherwise
     */
    boolean deletePerson(String personUuid);
}
