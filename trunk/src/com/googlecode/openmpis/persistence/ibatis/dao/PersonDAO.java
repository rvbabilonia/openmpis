/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.googlecode.openmpis.persistence.ibatis.dao;

import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.util.Pagination;

import java.sql.SQLException;
import java.util.List;

/**
 * The PersonDAO interface provides the ability to add, edit, delete, update and
 * retrieve persons.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public interface PersonDAO {

    /**
     * Retrieves all persons.
     *
     * @param pagination    the pagination context
     * @return              the list of all persons
     * @throws java.sql.SQLException
     */
    public List<Person> getAllPersons(Pagination pagination) throws SQLException;

    /**
     * Retrieves all missing persons.
     *
     * @param pagination    the pagination context
     * @return              the list of all missing persons
     * @throws java.sql.SQLException
     */
    public List<Person> getAllMissing(Pagination pagination) throws SQLException;

    /**
     * Retrieves endangered missing persons.
     *
     * @param pagination    the pagination context
     * @return              the list of endangered missing persons
     * @throws java.sql.SQLException
     */
    public List<Person> getMissing(Pagination pagination) throws SQLException;

    /**
     * Retrieves family abductions.
     *
     * @param pagination    the pagination context
     * @return              the list of family abductions
     * @throws java.sql.SQLException
     */
    public List<Person> getFamilyAbduction(Pagination pagination) throws SQLException;

    /**
     * Retrieves non-family abductions.
     *
     * @param pagination    the pagination context
     * @return              the list of non-family abductions
     * @throws java.sql.SQLException
     */
    public List<Person> getNonFamilyAbduction(Pagination pagination) throws SQLException;

    /**
     * Retrieves endangered runaway persons.
     *
     * @param pagination    the pagination context
     * @return              the list of endangered runaway persons
     * @throws java.sql.SQLException
     */
    public List<Person> getRunaway(Pagination pagination) throws SQLException;

    /**
     * Retrieves cases of unknown classification.
     *
     * @param pagination    the pagination context
     * @return              the list of cases of unknown classification
     * @throws java.sql.SQLException
     */
    public List<Person> getUnknown(Pagination pagination) throws SQLException;

    /**
     * Retrieves all found persons.
     *
     * @param pagination    the pagination context
     * @return              the list of all found persons
     * @throws java.sql.SQLException
     */
    public List<Person> getAllFound(Pagination pagination) throws SQLException;

    /**
     * Retrieves abandoned persons.
     *
     * @param pagination    the pagination context
     * @return              the list of abandoned persons
     * @throws java.sql.SQLException
     */
    public List<Person> getAbandoned(Pagination pagination) throws SQLException;

    /**
     * Retrieves throwaway persons.
     *
     * @param pagination    the pagination context
     * @return              the list of throwaway persons
     * @throws java.sql.SQLException
     */
    public List<Person> getThrowaway(Pagination pagination) throws SQLException;

    /**
     * Retrieves unidentified persons.
     *
     * @param pagination    the pagination context
     * @return              the list of unidentified persons
     * @throws java.sql.SQLException
     */
    public List<Person> getUnidentified(Pagination pagination) throws SQLException;

    /**
     * Retrieves ongoing cases.
     *
     * @param pagination    the pagination context
     * @return              the list of ongoing cases
     * @throws java.sql.SQLException
     */
    public List<Person> getOngoing(Pagination pagination) throws SQLException;

    /**
     * Retrieves solved cases.
     *
     * @param pagination    the pagination context
     * @return              the list of solved cases
     * @throws java.sql.SQLException
     */
    public List<Person> getSolved(Pagination pagination) throws SQLException;

    /**
     * Retrieves unsolved cases.
     *
     * @param pagination    the pagination context
     * @return              the list of unsolved cases
     * @throws java.sql.SQLException
     */
    public List<Person> getUnsolved(Pagination pagination) throws SQLException;

    /**
     * Retrieves all persons created by a given encoder.
     *
     * @param pagination    the pagination context
     * @param encoderId     the encoder ID
     * @return              the list of all persons created by a given encoder
     * @throws java.sql.SQLException
     */
    public List<Person> getPersonsByEncoderId(Pagination pagination, Integer encoderId) throws SQLException;

    /**
     * Retrieves all persons handled by a given investigator.
     *
     * @param pagination    the pagination context
     * @param investigatorId the investigator ID
     * @return              the list of all persons handled by a given investigator
     * @throws java.sql.SQLException
     */
    public List<Person> getPersonsByInvestigatorId(Pagination pagination, Integer investigatorId) throws SQLException;

    /**
     * Retrieves all persons abducted by a given abductor.
     *
     * @param pagination    the pagination context
     * @param abductorId    the abductor ID
     * @return              the list of all persons abducted by a given abductor
     * @throws java.sql.SQLException
     */
    public List<Person> getPersonsByAbductorId(Pagination pagination, Integer abductorId) throws SQLException;

    /**
     * Retrieves a person given his ID.
     * 
     * @param id            the person ID
     * @return              the person
     * @throws java.sql.SQLException
     */
    public Person getPersonById(Integer id) throws SQLException;

    /**
     * Inserts a new person.
     * 
     * @param person        the new person
     * @return              <code>true</code> if the person was successfully inserted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean insertPerson(Person person) throws SQLException;

    /**
     * Updates an existing person.
     * 
     * @param person        the existing person
     * @return              <code>true</code> if the person was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean updatePerson(Person person) throws SQLException;

    /**
     * Updates a person's abductor ID.
     * 
     * @param person        the existing person
     * @return              <code>true</code> if the person's abductor ID was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean updatePersonAbductor(Person person) throws SQLException;

    /**
     * Updates a person's investigator ID.
     *
     * @param person        the existing person
     * @return              <code>true</code> if the person's investigator ID was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean updatePersonInvestigator(Person person) throws SQLException;

    /**
     * Updates a person's relative ID.
     *
     * @param person        the existing person
     * @return              <code>true</code> if the person's relative ID was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean updatePersonRelative(Person person) throws SQLException;

    /**
     * Deletes a person with the specified ID.
     * 
     * @param id            the ID of the person to be deleted
     * @return              <code>true</code> if the person was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean deletePerson(Integer id) throws SQLException;

    /**
     * Checks if a person is unique.
     * 
     * @param person        the existing person
     * @return              <code>true</code> if the person is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    public boolean isUniquePerson(Person person) throws SQLException;

    /**
     * Returns the total number of persons.
     *
     * @return              the total number of persons
     * @throws java.sql.SQLException
     */
    public int countAllPersons() throws SQLException;

    /**
     * Returns the total number of ongoing cases.
     *
     * @return              the total number of ongoing cases
     * @throws java.sql.SQLException
     */
    public int countOngoing() throws SQLException;

    /**
     * Returns the total number of solved cases.
     *
     * @return              the total number of solved cases
     * @throws java.sql.SQLException
     */
    public int countSolved() throws SQLException;

    /**
     * Returns the total number of unsolved cases.
     *
     * @return              the total number of unsolved cases
     * @throws java.sql.SQLException
     */
    public int countUnsolved() throws SQLException;

    /**
     * Returns the total number of all missing persons.
     *
     * @return              the total number of missing persons
     * @throws java.sql.SQLException
     */
    public int countAllMissing() throws SQLException;

    /**
     * Returns the total number of endangered missing persons.
     *
     * @return              the total number of endangered missing persons
     * @throws java.sql.SQLException
     */
    public int countMissing() throws SQLException;

    /**
     * Returns the total number of family abductions.
     *
     * @return              the total number of family abductions
     * @throws java.sql.SQLException
     */
    public int countFamilyAbduction() throws SQLException;

    /**
     * Returns the total number of non-family abductions.
     *
     * @return              the total number of non-family abductions
     * @throws java.sql.SQLException
     */
    public int countNonFamilyAbduction() throws SQLException;

    /**
     * Returns the total number of runaway persons.
     *
     * @return              the total number of runaway persons
     * @throws java.sql.SQLException
     */
    public int countRunaway() throws SQLException;

    /**
     * Returns the total number of unknown cases.
     *
     * @return              the total number of unknown cases
     * @throws java.sql.SQLException
     */
    public int countUnknown() throws SQLException;

    /**
     * Returns the total number of all found persons.
     *
     * @return              the total number of all found persons
     * @throws java.sql.SQLException
     */
    public int countAllFound() throws SQLException;

    /**
     * Returns the total number of abandoned persons.
     *
     * @return              the total number of abandoned persons
     * @throws java.sql.SQLException
     */
    public int countAbandoned() throws SQLException;

    /**
     * Returns the total number of throwaway persons.
     *
     * @return              the total number of throwaway persons
     * @throws java.sql.SQLException
     */
    public int countThrowaway() throws SQLException;

    /**
     * Returns the total number of unidentified persons.
     *
     * @return              the total number of unidentified persons
     * @throws java.sql.SQLException
     */
    public int countUnidentified() throws SQLException;

    /**
     * Returns the total number of persons created by a given encoder.
     *
     * @param encoderId     the encoder ID
     * @return              the total number of persons created by a given encoder
     * @throws java.sql.SQLException
     */
    public int countPersonsByEncoderId(Integer encoderId) throws SQLException;

    /**
     * Returns the total number of persons handled by a given investigator.
     *
     * @param investigatorId the investigator ID
     * @return              the total number of persons handled by a given investigator
     * @throws java.sql.SQLException
     */
    public int countPersonsByInvestigatorId(Integer investigatorId) throws SQLException;

    /**
     * Returns the total number of persons abducted by a given abductor.
     *
     * @param abductorId    the abductor ID
     * @return              the total number of persons abducted by a given abductor
     * @throws java.sql.SQLException
     */
    public int countPersonsByAbductorId(Integer abductorId) throws SQLException;
}