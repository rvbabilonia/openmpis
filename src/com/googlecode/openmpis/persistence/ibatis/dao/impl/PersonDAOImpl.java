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
package com.googlecode.openmpis.persistence.ibatis.dao.impl;

import com.googlecode.openmpis.dto.Person;
import com.googlecode.openmpis.persistence.ibatis.dao.PersonDAO;
import com.googlecode.openmpis.util.Pagination;
import com.googlecode.openmpis.util.SqlMapConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The PersonDAOImpl class implements the PersonDAO interface.
 * This provides the ability to add, edit, delete, update and
 * retrieve persons.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class PersonDAOImpl implements PersonDAO {

    /**
     * The SQL map client
     */
    private SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();

    /**
     * Retrieves all persons.
     *
     * @param pagination    the pagination context
     * @return              the list of all persons
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getAllPersons(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getAllPersons", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllPersons"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves all missing persons.
     *
     * @param pagination    the pagination context
     * @return              the list of all missing persons
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getAllMissing(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getAllMissing", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllMissing"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves endangered missing persons.
     *
     * @param pagination    the pagination context
     * @return              the list of endangered missing persons
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getMissing(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getMissing", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countMissing"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves family abductions.
     *
     * @param pagination    the pagination context
     * @return              the list of family abductions
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getFamilyAbduction(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getFamilyAbduction", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countFamilyAbduction"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves non-family abductions.
     *
     * @param pagination    the pagination context
     * @return              the list of non-family abductions
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getNonFamilyAbduction(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getNonFamilyAbduction", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countNonFamilyAbduction"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves endangered runaway persons.
     *
     * @param pagination    the pagination context
     * @return              the list of endangered runaway persons
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getRunaway(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getRunaway", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countRunaway"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves cases of unknown classification.
     *
     * @param pagination    the pagination context
     * @return              the list of cases of unknown classification
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getUnknown(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getUnknown", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countUnknown"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves all found persons.
     *
     * @param pagination    the pagination context
     * @return              the list of all found persons
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getAllFound(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getAllFound", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAllFound"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves abandoned persons.
     *
     * @param pagination    the pagination context
     * @return              the list of abandoned persons
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getAbandoned(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getAbandoned", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countAbandoned"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves throwaway persons.
     *
     * @param pagination    the pagination context
     * @return              the list of throwaway persons
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getThrowaway(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getThrowaway", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countThrowaway"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves unidentified persons.
     *
     * @param pagination    the pagination context
     * @return              the list of unidentified persons
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getUnidentified(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getUnidentified", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countUnidentified"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves ongoing cases.
     *
     * @param pagination    the pagination context
     * @return              the list of ongoing cases
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getOngoing(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getOngoing", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countOngoing"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves solved cases.
     *
     * @param pagination    the pagination context
     * @return              the list of solved cases
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getSolved(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getSolved", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countSolved"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves unsolved cases.
     *
     * @param pagination    the pagination context
     * @return              the list of unsolved cases
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getUnsolved(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getUnsolved", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countUnsolved"));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves all persons created by a given encoder.
     *
     * @param pagination    the pagination context
     * @param encoderId     the encoder ID
     * @return              the list of all persons created by a given encoder
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getPersonsByEncoderId(Pagination pagination, Integer encoderId) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getPersonsByEncoderId", encoderId, pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countPersonsByEncoderId", encoderId));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves all persons handled by a given investigator.
     *
     * @param pagination    the pagination context
     * @param investigatorId the investigator ID
     * @return              the list of all persons handled by a given investigator
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getPersonsByInvestigatorId(Pagination pagination, Integer investigatorId) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getPersonsByInvestigatorId", investigatorId, pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countPersonsByInvestigatorId", investigatorId));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves all persons abducted by a given abductor.
     *
     * @param pagination    the pagination context
     * @param abductorId    the abductor ID
     * @return              the list of all persons abducted by a given abductor
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getPersonsByAbductorId(Pagination pagination, Integer abductorId) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getPersonsByInvestigatorId", abductorId, pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countPersonsByInvestigatorId", abductorId));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Retrieves a person given his ID.
     * 
     * @param id            the person ID
     * @return              the person
     * @throws java.sql.SQLException
     */
    @Override
    public Person getPersonById(Integer id) throws SQLException {
        Person person = null;

        try {
            sqlMap.startTransaction();
            person = (Person) sqlMap.queryForObject("getPersonById", id);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return person;
    }

    /**
     * Inserts a new person.
     * 
     * @param person        the new person
     * @return              the last index
     * @throws java.sql.SQLException
     */
    @Override
    public int insertPerson(Person person) throws SQLException {
        int index = 0;

        try {
            sqlMap.startTransaction();
            index = (Integer) sqlMap.insert("insertPerson", person);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return index;
    }

    /**
     * Updates an existing person.
     * 
     * @param person        the existing person
     * @return              <code>true</code> if the person was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updatePerson(Person person) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.update("updatePerson", person);
            sqlMap.commitTransaction();

            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }

    /**
     * Updates a person's abductor ID.
     * 
     * @param person        the existing person
     * @return              <code>true</code> if the person's abductor ID was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updatePersonAbductor(Person person) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.update("updatePersonAbductor", person);
            sqlMap.commitTransaction();

            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }

    /**
     * Updates a person's investigator ID.
     *
     * @param person        the existing person
     * @return              <code>true</code> if the person's investigator ID was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updatePersonInvestigator(Person person) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.update("updatePersonInvestigator", person);
            sqlMap.commitTransaction();

            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }

    /**
     * Updates a person's relative ID.
     *
     * @param person        the existing person
     * @return              <code>true</code> if the person's relative ID was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updatePersonRelative(Person person) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.update("updatePersonRelative", person);
            sqlMap.commitTransaction();

            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }

    /**
     * Deletes a person with the specified ID.
     * 
     * @param id            the ID of the person to be deleted
     * @return              <code>true</code> if the person was successfully deleted; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean deletePerson(Integer id) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.delete("deletePerson", id);
            sqlMap.commitTransaction();

            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }

    /**
     * Checks if a person is unique.
     * 
     * @param person        the existing person
     * @return              <code>true</code> if the person is unique; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean isUniquePerson(Person person) throws SQLException {
        try {
            sqlMap.startTransaction();
            Person p = (Person) sqlMap.queryForObject("checkPerson", person);
            sqlMap.commitTransaction();

            if (p == null) {
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return false;
    }

    /**
     * Returns the total number of persons.
     *
     * @return              the total number of persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllPersons() throws SQLException {
        int personCount = 0;

        try {
            sqlMap.startTransaction();
            personCount = (Integer) sqlMap.queryForObject("countAllPersons");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personCount;
    }

    /**
     * Returns the total number of ongoing cases.
     *
     * @return              the total number of ongoing cases
     * @throws java.sql.SQLException
     */
    @Override
    public int countOngoing() throws SQLException {
        int ongoingCount = 0;

        try {
            sqlMap.startTransaction();
            ongoingCount = (Integer) sqlMap.queryForObject("countOngoing");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return ongoingCount;
    }

    /**
     * Returns the total number of solved cases.
     *
     * @return              the total number of solved cases
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolved() throws SQLException {
        int solvedCount = 0;

        try {
            sqlMap.startTransaction();
            solvedCount = (Integer) sqlMap.queryForObject("countSolved");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return solvedCount;
    }

    /**
     * Returns the total number of unsolved cases.
     *
     * @return              the total number of unsolved cases
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolved() throws SQLException {
        int unsolvedCount = 0;

        try {
            sqlMap.startTransaction();
            unsolvedCount = (Integer) sqlMap.queryForObject("countUnsolved");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return unsolvedCount;
    }

    /**
     * Returns the total number of all missing persons.
     *
     * @return              the total number of missing persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllMissing() throws SQLException {
        int allMissingCount = 0;

        try {
            sqlMap.startTransaction();
            allMissingCount = (Integer) sqlMap.queryForObject("countAllMissing");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return allMissingCount;
    }

    /**
     * Returns the total number of endangered missing persons.
     *
     * @return              the total number of endangered missing persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countMissing() throws SQLException {
        int missingCount = 0;

        try {
            sqlMap.startTransaction();
            missingCount = (Integer) sqlMap.queryForObject("countMissing");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return missingCount;
    }

    /**
     * Returns the total number of family abductions.
     *
     * @return              the total number of family abductions
     * @throws java.sql.SQLException
     */
    @Override
    public int countFamilyAbduction() throws SQLException {
        int familyAbductionCount = 0;

        try {
            sqlMap.startTransaction();
            familyAbductionCount = (Integer) sqlMap.queryForObject("countFamilyAbduction");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return familyAbductionCount;
    }

    /**
     * Returns the total number of non-family abductions.
     *
     * @return              the total number of non-family abductions
     * @throws java.sql.SQLException
     */
    @Override
    public int countNonFamilyAbduction() throws SQLException {
        int nonFamilyAbductionCount = 0;

        try {
            sqlMap.startTransaction();
            nonFamilyAbductionCount = (Integer) sqlMap.queryForObject("countNonFamilyAbduction");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return nonFamilyAbductionCount;
    }

    /**
     * Returns the total number of runaway persons.
     *
     * @return              the total number of runaway persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countRunaway() throws SQLException {
        int runawayCount = 0;

        try {
            sqlMap.startTransaction();
            runawayCount = (Integer) sqlMap.queryForObject("countRunaway");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return runawayCount;
    }

    /**
     * Returns the total number of unknown cases.
     *
     * @return              the total number of unknown cases
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnknown() throws SQLException {
        int unknownCount = 0;

        try {
            sqlMap.startTransaction();
            unknownCount = (Integer) sqlMap.queryForObject("countUnknown");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return unknownCount;
    }

    /**
     * Returns the total number of all found persons.
     *
     * @return              the total number of all found persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllFound() throws SQLException {
        int allFoundCount = 0;

        try {
            sqlMap.startTransaction();
            allFoundCount = (Integer) sqlMap.queryForObject("countAllFound");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return allFoundCount;
    }

    /**
     * Returns the total number of abandoned persons.
     *
     * @return              the total number of abandoned persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAbandoned() throws SQLException {
        int abandonedCount = 0;

        try {
            sqlMap.startTransaction();
            abandonedCount = (Integer) sqlMap.queryForObject("countAbandoned");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return abandonedCount;
    }

    /**
     * Returns the total number of throwaway persons.
     *
     * @return              the total number of throwaway persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countThrowaway() throws SQLException {
        int throwawayCount = 0;

        try {
            sqlMap.startTransaction();
            throwawayCount = (Integer) sqlMap.queryForObject("countThrowaway");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return throwawayCount;
    }

    /**
     * Returns the total number of unidentified persons.
     *
     * @return              the total number of unidentified persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnidentified() throws SQLException {
        int unidentifiedCount = 0;

        try {
            sqlMap.startTransaction();
            unidentifiedCount = (Integer) sqlMap.queryForObject("countUnidentified");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return unidentifiedCount;
    }

    /**
     * Returns the total number of persons created by a given encoder.
     *
     * @param encoderId     the encoder ID
     * @return              the total number of persons created by a given encoder
     * @throws java.sql.SQLException
     */
    @Override
    public int countPersonsByEncoderId(Integer encoderId) throws SQLException {
        int personCount = 0;

        try {
            sqlMap.startTransaction();
            personCount = (Integer) sqlMap.queryForObject("countPersonsByEncoderId", encoderId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personCount;
    }

    /**
     * Returns the total number of persons handled by a given investigator.
     *
     * @param investigatorId the investigator ID
     * @return              the total number of persons handled by a given investigator
     * @throws java.sql.SQLException
     */
    @Override
    public int countPersonsByInvestigatorId(Integer investigatorId) throws SQLException {
        int personCount = 0;

        try {
            sqlMap.startTransaction();
            personCount = (Integer) sqlMap.queryForObject("countPersonsByEncoderId", investigatorId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personCount;
    }

    /**
     * Returns the total number of persons abducted by a given abductor.
     *
     * @param abductorId    the abductor ID
     * @return              the total number of persons abducted by a given abductor
     * @throws java.sql.SQLException
     */
    @Override
    public int countPersonsByAbductorId(Integer abductorId) throws SQLException {
        int personCount = 0;

        try {
            sqlMap.startTransaction();
            personCount = (Integer) sqlMap.queryForObject("countPersonsByEncoderId", abductorId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personCount;
    }
}