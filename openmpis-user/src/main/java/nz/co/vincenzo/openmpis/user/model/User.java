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
package nz.co.vincenzo.openmpis.user.model;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;
import nz.co.vincenzo.openmpis.user.enumeration.Role;

import java.util.Date;

/**
 * The domain model object for users.
 *
 * @author Rey Vincent Babilonia
 * @since 1.0.0
 */
@Entity(version = 1)
public class User {

    @PrimaryKey
    private String emailAddress;
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private Role role;
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private String creator;
    private Date dateCreated;
    private String firstName;
    private String middleName;
    private String lastName;
    private String designation;
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private String agency;
    private String phoneNumber;
    private String ipAddress;
    private Date lastLogin;
    private Date lastUpdated;
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private boolean active;

    /**
     * Default constructor.
     */
    public User() {
        // do nothing
    }

    /**
     * Constructor with email address.
     *
     * @param emailAddress the email address
     */
    public User(final String emailAddress) {
        this.emailAddress = emailAddress;
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
     * Returns the date the user was created.
     *
     * @return the date the user was created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the date the user was created.
     *
     * @param dateCreated the date the user was created
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    /**
     * Returns the IP address where the user logged in.
     *
     * @return the IP address where the user logged in
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the IP address where the user logged in.
     *
     * @param ipAddress the IP address where the user logged in
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Returns the date the user last logged in.
     *
     * @return the date the user last logged in
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * Sets the date the user last logged in.
     *
     * @param lastLogin the date the user last logged in
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Returns the date the user profile was last updated.
     *
     * @return the date the user profile was last updated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the date the user profile was last updated.
     *
     * @param lastUpdated the date the user profile was last updated
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * Returns the status of the user.
     *
     * @return {@code true} if the user is active; {@code false} otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the status of the user.
     *
     * @param active {@code true} if the user is active; {@code false} otherwise
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (active != user.active) {
            return false;
        }
        if (emailAddress != null ? !emailAddress.equals(user.emailAddress) : user.emailAddress != null) {
            return false;
        }
        if (role != user.role) {
            return false;
        }
        if (creator != null ? !creator.equals(user.creator) : user.creator != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) {
            return false;
        }
        if (middleName != null ? !middleName.equals(user.middleName) : user.middleName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) {
            return false;
        }
        if (designation != null ? !designation.equals(user.designation) : user.designation != null) {
            return false;
        }
        if (agency != null ? !agency.equals(user.agency) : user.agency != null) {
            return false;
        }
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) {
            return false;
        }
        return ipAddress != null ? ipAddress.equals(user.ipAddress) : user.ipAddress == null;
    }

    @Override
    public int hashCode() {
        int result = emailAddress != null ? emailAddress.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        result = 31 * result + (agency != null ? agency.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{"
                + "emailAddress='" + emailAddress + '\''
                + ", role=" + role
                + ", creator='" + creator + '\''
                + ", dateCreated=" + dateCreated
                + ", firstName='" + firstName + '\''
                + ", middleName='" + middleName + '\''
                + ", lastName='" + lastName + '\''
                + ", designation='" + designation + '\''
                + ", agency='" + agency + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", ipAddress='" + ipAddress + '\''
                + ", lastLogin=" + lastLogin
                + ", lastUpdated=" + lastUpdated
                + ", active=" + active
                + '}';
    }
}
