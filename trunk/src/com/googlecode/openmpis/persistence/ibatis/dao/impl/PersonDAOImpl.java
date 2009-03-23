/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
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
     * Retrieves all ongoing and missing persons.
     *
     * @param pagination    the pagination context
     * @return              the list of all ongoing and missing persons
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
     * Retrieves the 4 newly-added missing persons.
     *
     * @return              the list of all missing persons
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> listNewMissing() throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("listNewMissing");
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
     * Retrieves all ongoing and found persons.
     *
     * @param pagination    the pagination context
     * @return              the list of all ongoing and found persons
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
     * Retrieves ongoing and found persons.
     *
     * @param pagination    the pagination context
     * @return              the list of ongoing and found persons
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getFound(Pagination pagination) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getFound", pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countFound"));
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
     * Retrieves ongoing cases.
     *
     * @return              the list of ongoing cases
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> listOngoing() throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getOngoing");
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
     * Retrieves solved cases.
     *
     * @return              the list of solved cases
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> listSolved() throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getSolved");
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
     * Retrieves unsolved cases.
     *
     * @return              the list of unsolved cases
     * @throws java.sql.SQLException
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> listUnsolved() throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("getUnsolved");
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
            personList = sqlMap.queryForList("getPersonsByAbductorId", abductorId, pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults((Integer) sqlMap.queryForObject("countPersonsByAbductorId", abductorId));
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return personList;
    }

    /**
     * Searches for persons.
     *
     * @param pagination    the pagination context
     * @param keyword       the search keyword
     * @return              the list of matching persons
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Person> simpleSearch(Pagination pagination, String keyword) throws SQLException {
        List<Person> personList = new ArrayList<Person>();

        try {
            sqlMap.startTransaction();
            personList = sqlMap.queryForList("simpleSearchPerson", keyword, pagination.getSkipResults(), pagination.getMaxResults());
            pagination.setTotalResults(sqlMap.queryForList("simpleSearchPerson", keyword).size());
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
     * Retrieves an ongoing case given a person's ID.
     *
     * @param id            the person ID
     * @return              the person
     * @throws java.sql.SQLException
     */
    @Override
    public Person getOngoingCaseById(Integer id) throws SQLException {
        Person person = null;

        try {
            sqlMap.startTransaction();
            person = (Person) sqlMap.queryForObject("getOngoingCaseById", id);
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
     * Updates a person's encoder ID.
     *
     * @param person        the existing person
     * @return              <code>true</code> if the person's encoder ID was successfully updated; <code>false</code> otherwise
     * @throws java.sql.SQLException
     */
    @Override
    public boolean updatePersonEncoder(Person person) throws SQLException {
        try {
            sqlMap.startTransaction();
            sqlMap.update("updatePersonEncoder", person);
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
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countAllPersons");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of ongoing cases.
     *
     * @return              the total number of ongoing cases
     * @throws java.sql.SQLException
     */
    @Override
    public int countOngoing() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countOngoing");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of solved cases.
     *
     * @return              the total number of solved cases
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolved() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countSolved");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unsolved cases.
     *
     * @return              the total number of unsolved cases
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolved() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnsolved");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of all ongoing and missing persons.
     *
     * @return              the total number of all ongoing and missing persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllMissing() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countAllMissing");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of all solved and missing persons.
     *
     * @return              the total number of all solved and missing persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllSolvedMissing() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countAllSolvedMissing");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of all unsolved and missing persons.
     *
     * @return              the total number of all unsolved and missing persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllUnsolvedMissing() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countAllUnsolvedMissing");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of endangered missing persons.
     *
     * @return              the total number of endangered missing persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countMissing() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countMissing");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of solved and missing persons.
     *
     * @return              the total number of solved and missing persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolvedMissing() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countSolvedMissing");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unsolved and missing persons.
     *
     * @return              the total number of unsolved and missing persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolvedMissing() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnsolvedMissing");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of family abductions.
     *
     * @return              the total number of family abductions
     * @throws java.sql.SQLException
     */
    @Override
    public int countFamilyAbduction() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countFamilyAbduction");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of solved family abductions.
     *
     * @return              the total number of solved family abductions
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolvedFamilyAbduction() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countSolvedFamilyAbduction");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unsolved family abductions.
     *
     * @return              the total number of unsolved family abductions
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolvedFamilyAbduction() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnsolvedFamilyAbduction");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of non-family abductions.
     *
     * @return              the total number of non-family abductions
     * @throws java.sql.SQLException
     */
    @Override
    public int countNonFamilyAbduction() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countNonFamilyAbduction");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of solved non-family abductions.
     *
     * @return              the total number of solved non-family abductions
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolvedNonFamilyAbduction() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countSolvedNonFamilyAbduction");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unsolved non-family abductions.
     *
     * @return              the total number of unsolved non-family abductions
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolvedNonFamilyAbduction() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnsolvedNonFamilyAbduction");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of runaway persons.
     *
     * @return              the total number of runaway persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countRunaway() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countRunaway");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of solved and runaway persons.
     *
     * @return              the total number of solved and runaway persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolvedRunaway() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countSolvedRunaway");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unsolved and runaway persons.
     *
     * @return              the total number of unsolved and runaway persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolvedRunaway() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnsolvedRunaway");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unknown cases.
     *
     * @return              the total number of unknown cases
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnknown() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnknown");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of solved and unknown cases.
     *
     * @return              the total number of solved and unknown cases
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolvedUnknown() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countSolvedUnknown");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unsolved and unknown cases.
     *
     * @return              the total number of unsolved and unknown cases
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolvedUnknown() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnsolvedUnknown");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of all ongoing and found persons.
     *
     * @return              the total number of all ongoing and found persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllFound() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countAllFound");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of all solved and found persons.
     *
     * @return              the total number of all solved and found persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllSolvedFound() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countAllSolvedFound");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of all unsolved and found persons.
     *
     * @return              the total number of all unsolved and found persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAllUnsolvedFound() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countAllUnsolvedFound");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of ongoing and found persons.
     *
     * @return              the total number of ongoing and found persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countFound() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countFound");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of solved and found persons.
     *
     * @return              the total number of solved and found persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolvedFound() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countSolvedFound");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unsolved and found persons.
     *
     * @return              the total number of unsolved and found persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolvedFound() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnsolvedFound");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of abandoned persons.
     *
     * @return              the total number of abandoned persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countAbandoned() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countAbandoned");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of solved and abandoned persons.
     *
     * @return              the total number of solved and abandoned persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolvedAbandoned() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countSolvedAbandoned");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unsolved and abandoned persons.
     *
     * @return              the total number of unsolved and abandoned persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolvedAbandoned() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnsolvedAbandoned");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of throwaway persons.
     *
     * @return              the total number of throwaway persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countThrowaway() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countThrowaway");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of solved and throwaway persons.
     *
     * @return              the total number of solved and throwaway persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolvedThrowaway() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countSolvedThrowaway");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unsolved and throwaway persons.
     *
     * @return              the total number of unsolved and throwaway persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolvedThrowaway() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnsolvedThrowaway");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unidentified persons.
     *
     * @return              the total number of unidentified persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnidentified() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnidentified");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of solved and unidentified persons.
     *
     * @return              the total number of solved and unidentified persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countSolvedUnidentified() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countSolvedUnidentified");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of unsolved and unidentified persons.
     *
     * @return              the total number of unsolved and unidentified persons
     * @throws java.sql.SQLException
     */
    @Override
    public int countUnsolvedUnidentified() throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countUnsolvedUnidentified");
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
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
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countPersonsByEncoderId", encoderId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
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
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countPersonsByInvestigatorId", investigatorId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
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
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countPersonsByAbductorId", abductorId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }

    /**
     * Returns the total number of persons related to a given relative.
     *
     * @param relativeId    the relative ID
     * @return              the total number of persons related to a given relative
     * @throws java.sql.SQLException
     */
    @Override
    public int countPersonsByRelativeId(Integer relativeId) throws SQLException {
        int count = 0;

        try {
            sqlMap.startTransaction();
            count = (Integer) sqlMap.queryForObject("countPersonsByRelativeId", relativeId);
            sqlMap.commitTransaction();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            sqlMap.endTransaction();
        }

        return count;
    }
}