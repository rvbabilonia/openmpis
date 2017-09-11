/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008-2017  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nz.org.vincenzo.openmpis.user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import nz.org.vincenzo.openmpis.user.enumeration.Role;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * The data transfer object for users.
 *
 * @author Rey Vincent Babilonia
 * @since 1.0.0
 */
public class UserDTO extends ResourceSupport {

    @NotNull
    @Email
    private String emailAddress;
    @NotNull
    private Role role;
    @NotNull
    @Email
    private String creator;
    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String lastName;
    @NotNull
    private String designation;
    @NotNull
    private String agency;
    @NotNull
    private String phoneNumber;

    /**
     * Default constructor.
     */
    @JsonCreator
    public UserDTO() {
        // do nothing
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
     * Returns the {@link Role}.
     *
     * @return the {@link Role}
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the {@link Role}.
     *
     * @param role the {@link Role}
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Returns the email address of the creator.
     *
     * @return the email address of the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Sets the email address of the creator/administrator
     *
     * @param creator the email address of the creator/administrator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Sets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the middle name.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name.
     *
     * @param middleName the middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the user designation.
     *
     * @return the user designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the user designation.
     *
     * @param designation the user designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Returns the agency name
     *
     * @return the agency name
     */
    public String getAgency() {
        return agency;
    }

    /**
     * Sets the agency name.
     *
     * @param agency the agency name
     */
    public void setAgency(String agency) {
        this.agency = agency;
    }

    /**
     * Returns the office number.
     *
     * @return the office number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the office number.
     *
     * @param phoneNumber the office number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDTO userDTO = (UserDTO) o;

        if (emailAddress != null ? !emailAddress.equals(userDTO.emailAddress) : userDTO.emailAddress != null) {
            return false;
        }
        if (role != userDTO.role) {
            return false;
        }
        if (firstName != null ? !firstName.equals(userDTO.firstName) : userDTO.firstName != null) {
            return false;
        }
        if (middleName != null ? !middleName.equals(userDTO.middleName) : userDTO.middleName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(userDTO.lastName) : userDTO.lastName != null) {
            return false;
        }
        if (designation != null ? !designation.equals(userDTO.designation) : userDTO.designation != null) {
            return false;
        }
        if (agency != null ? !agency.equals(userDTO.agency) : userDTO.agency != null) {
            return false;
        }
        return phoneNumber != null ? phoneNumber.equals(userDTO.phoneNumber) : userDTO.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = emailAddress != null ? emailAddress.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        result = 31 * result + (agency != null ? agency.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{"
                + "emailAddress='" + emailAddress + '\''
                + ", role=" + role
                + ", firstName='" + firstName + '\''
                + ", middleName='" + middleName + '\''
                + ", lastName='" + lastName + '\''
                + ", designation='" + designation + '\''
                + ", agency='" + agency + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + '}';
    }
}
