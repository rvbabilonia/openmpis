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
package org.vincenzolabs.openmpis.abductor.service;

import java.util.Set;

import org.vincenzolabs.openmpis.domain.Abductor;

/**
 * The service for abductor.
 *
 * @author Rey Vincent Babilonia
 */
public interface AbductorService {

    /**
     * Creates an {@link Abductor}.
     *
     * @param abductor the {@link Abductor}
     * @return the {@link Abductor}
     */
    Abductor createAbductor(Abductor abductor);

    /**
     * Returns the {@link Set} of {@link Abductor}s.
     *
     * @return the {@link Set} of {@link Abductor}s
     */
    Set<Abductor> retrieveAbductors();

    /**
     * Returns the {@link Abductor} matching the given UUID.
     *
     * @param abductorUuid the {@link Abductor} UUID
     * @return the {@link Abductor}
     */
    Abductor retrieveAbductor(String abductorUuid);

    /**
     * @param abductor the {@link Abductor}
     * @return the {@link Abductor}
     */
    Abductor updateAbductor(Abductor abductor);

    /**
     * Deletes an {@link Abductor}.
     *
     * @param abductorUuid the {@link Abductor} UUID
     * @return {@code true} if the {@link Abductor} has been deleted; {@code false} otherwise
     */
    boolean deleteAbductor(String abductorUuid);
}
