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
