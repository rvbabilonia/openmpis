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
package nz.org.vincenzo.openmpis.user.dao.impl;

import com.sleepycat.je.LockMode;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityIndex;
import com.sleepycat.persist.EntityJoin;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.ForwardCursor;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import nz.org.vincenzo.openmpis.common.database.BerkeleyTransactionManager;
import nz.org.vincenzo.openmpis.user.dao.UserDAO;
import nz.org.vincenzo.openmpis.user.enumeration.Role;
import nz.org.vincenzo.openmpis.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The implementation of {@link UserDAO}.
 *
 * @author Rey Vincent Babilonia
 * @since 2.0.0
 */
@Repository
@Transactional
public class UserDAOBerkeleyImpl implements UserDAO {

    private final BerkeleyTransactionManager transactionManager;
    private final PrimaryIndex<String, User> userIndex;
    private final SecondaryIndex<Role, String, User> roleIndex;
    private final SecondaryIndex<String, String, User> creatorIndex;
    private final SecondaryIndex<String, String, User> agencyIndex;
    private final SecondaryIndex<Boolean, String, User> activeIndex;

    /**
     * Default constructor.
     *
     * @param entityStore the {@link EntityStore}
     */
    @Autowired
    public UserDAOBerkeleyImpl(EntityStore entityStore, BerkeleyTransactionManager transactionManager) {
        this.transactionManager = transactionManager;

        userIndex = entityStore.getPrimaryIndex(String.class, User.class);

        roleIndex = entityStore.getSecondaryIndex(userIndex, Role.class, "role");

        creatorIndex = entityStore.getSecondaryIndex(userIndex, String.class, "creator");

        agencyIndex = entityStore.getSecondaryIndex(userIndex, String.class, "agency");

        activeIndex = entityStore.getSecondaryIndex(userIndex, Boolean.class, "active");
    }

    @Override
    public List<User> retrieveUsers() {
        return retrieveUsers(userIndex);
    }

    @Override
    public List<User> retrieveInvestigators() {
        return retrieveUsers(roleIndex.subIndex(Role.INVESTIGATOR));
    }

    @Override
    public List<User> retrieveActiveInvestigators() {
        return retrieveUsers(Role.INVESTIGATOR, true);
    }

    @Override
    public boolean createUser(String emailAddress, String creator) {
        User user = new User(emailAddress);
        user.setRole(Role.ENCODER);
        user.setDateCreated(new Date());
        user.setActive(true);
        user.setCreator(creator);

        return userIndex.putNoOverwrite(transactionManager.getTransaction(), user);
    }

    @Override
    public List<User> retrieveInactiveInvestigators() {
        return retrieveUsers(Role.INVESTIGATOR, false);
    }

    @Override
    public List<User> retrieveAdministrators() {
        return retrieveUsers(roleIndex.subIndex(Role.ADMINISTRATOR));
    }

    @Override
    public List<User> retrieveActiveAdministrators() {
        return retrieveUsers(Role.ADMINISTRATOR, true);
    }

    @Override
    public List<User> retrieveInactiveAdministrators() {
        return retrieveUsers(Role.ADMINISTRATOR, false);
    }

    @Override
    public List<User> retrieveEncoders() {
        return retrieveUsers(roleIndex.subIndex(Role.ENCODER));
    }

    @Override
    public List<User> retrieveActiveEncoders() {
        return retrieveUsers(Role.ENCODER, true);
    }

    @Override
    public List<User> retrieveInactiveEncoders() {
        return retrieveUsers(Role.ENCODER, false);
    }

    @Override
    public List<User> retrieveUsersByAgency(final String agency) {
        return retrieveUsers(agencyIndex.subIndex(agency));
    }

    @Override
    public List<User> retrieveUsersByCreator(final String creator) {
        return retrieveUsers(creatorIndex.subIndex(creator));
    }

    @Override
    public User retrieveUser(final String emailAddress) {
        return userIndex.get(transactionManager.getTransaction(), emailAddress, LockMode.READ_UNCOMMITTED);
    }

    @Override
    public boolean isUniqueEmail(final String emailAddress) {
        return retrieveUser(emailAddress) == null;
    }

    @Override
    public long countUsers() {
        return userIndex.count();
    }

    @Override
    public boolean updateUser(User user) {
        User u = retrieveUser(user.getEmailAddress());
        if (u != null) {
            user.setLastUpdated(new Date());

            userIndex.putNoReturn(transactionManager.getTransaction(), user);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateLastLogin(User user) {
        User u = retrieveUser(user.getEmailAddress());
        if (u != null) {
            user.setLastLogin(new Date());

            userIndex.putNoReturn(transactionManager.getTransaction(), user);
            return true;
        }

        return false;
    }

    @Override
    public boolean deactivateUser(final String emailAddress) {
        User user = retrieveUser(emailAddress);
        if (user != null) {
            user.setActive(false);
            user.setLastUpdated(new Date());

            userIndex.putNoReturn(transactionManager.getTransaction(), user);
            return true;
        }

        return false;
    }

    private List<User> retrieveUsers(Role role, final boolean active) {
        List<User> users = new ArrayList<>();

        EntityJoin<String, User> join = new EntityJoin<>(userIndex);
        join.addCondition(roleIndex, role);
        join.addCondition(activeIndex, active);

        try (ForwardCursor<User> forwardCursor = join.entities(transactionManager.getTransaction(), null)) {
            for (User user : forwardCursor) {
                users.add(user);
            }
        }

        return users;
    }

    private List<User> retrieveUsers(EntityIndex<String, User> entityIndex) {
        List<User> users = new ArrayList<>();

        try (EntityCursor<User> entityCursor = entityIndex.entities(transactionManager.getTransaction(), null)) {
            for (User user : entityCursor) {
                users.add(user);
            }
        }

        return users;
    }
}
