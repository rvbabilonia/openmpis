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
package org.vincenzolabs.openmpis.enumeration;

/**
 * The enumeration of roles assigned to groups.
 *
 * @author Rey Vincent Babilonia
 */
public enum Role {

    /**
     * Only the administrator can create users.
     */
    CREATE_USER,
    /**
     * Users can update their rank, designation, agency, location, contact number and password.
     */
    UPDATE_USER,
    /**
     * Only the administrator can archive a user.
     */
    ARCHIVE_USER,
    /**
     * Only the encoder and investigator can create cases.
     */
    CREATE_CASE,
    /**
     * Only the encoder and investigator assigned to the case can update it.
     */
    UPDATE_CASE,
    /**
     * Only the encoder and investigator assigned to the case can archive it.
     */
    ARCHIVE_CASE,
    /**
     * Only the investigator assigned to the case can create reports.
     */
    CREATE_REPORT,
    /**
     * Only the investigator assigned to the case can update the report.
     */
    UPDATE_REPORT,
    /**
     * Only the investigator assigned to the case can archive the report.
     */
    ARCHIVE_REPORT,
    /**
     * Only the encoder or investigator assigned to the case can create the contact person.
     */
    CREATE_CONTACT_PERSON,
    /**
     * Only the encoder or investigator assigned to the case can update the contact person.
     */
    UPDATE_CONTACT_PERSON,
    /**
     * Only the encoder or investigator assigned to the case can archive the contact person.
     */
    ARCHIVE_CONTACT_PERSON,
    /**
     * Only the encoder or investigator assigned to the case can create the alleged abductor.
     */
    CREATE_ABDUCTOR,
    /**
     * Only the encoder or investigator assigned to the case can update the alleged abductor.
     */
    UPDATE_ABDUCTOR,
    /**
     * Only the encoder or investigator assigned to the case can archive the alleged abductor.
     */
    ARCHIVE_ABDUCTOR,
    /**
     * Only the investigator assigned to the case can archive the tip.
     */
    ARCHIVE_TIP,
    /**
     * Only the encoder can create an agency.
     */
    CREATE_AGENCY,
    /**
     * Only the encoder can update an agency.
     */
    UPDATE_AGENCY,
    /**
     * Only the encoder can delete an agency.
     */
    DELETE_AGENCY,
    /**
     * Only the encoder can create an institution.
     */
    CREATE_INSTITUTION,
    /**
     * Only the encoder can update an institution.
     */
    UPDATE_INSTITUTION,
    /**
     * Only the encoder can delete an institution.
     */
    DELETE_INSTITUTION
}
