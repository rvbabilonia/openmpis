package org.vincenzolabs.openmpis.representation;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The data transfer object for a social service institution.
 *
 * @author Rey Vincent Babilonia
 */
public class InstitutionJson {

    private String uuid;

    private String name;

    private String emailAddress;

    private String contactNumber;

    private StreetAddressJson streetAddressJson;

    /**
     * Returns the UUID.
     *
     * @return the UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the UUID.
     *
     * @param uuid the UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the contact number.
     *
     * @return the contact number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the contact number.
     *
     * @param contactNumber the contact number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Returns the {@link StreetAddressJson}.
     *
     * @return the {@link StreetAddressJson}
     */
    public StreetAddressJson getStreetAddressJson() {
        return streetAddressJson;
    }

    /**
     * Sets the {@link StreetAddressJson}.
     *
     * @param streetAddressJson the {@link StreetAddressJson}
     */
    public void setStreetAddressJson(StreetAddressJson streetAddressJson) {
        this.streetAddressJson = streetAddressJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InstitutionJson that = (InstitutionJson) o;

        return new EqualsBuilder()
            .append(uuid, that.uuid)
            .append(name, that.name)
            .append(emailAddress, that.emailAddress)
            .append(contactNumber, that.contactNumber)
            .append(streetAddressJson, that.streetAddressJson)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(uuid)
            .append(name)
            .append(emailAddress)
            .append(contactNumber)
            .append(streetAddressJson)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("name", name)
            .append("emailAddress", emailAddress)
            .append("contactNumber", contactNumber)
            .append("streetAddressJson", streetAddressJson)
            .toString();
    }
}
