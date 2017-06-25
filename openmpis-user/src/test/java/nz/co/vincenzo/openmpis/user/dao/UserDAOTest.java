package nz.co.vincenzo.openmpis.user.dao;

import nz.co.vincenzo.openmpis.user.application.TestUserApplication;
import nz.co.vincenzo.openmpis.user.enumeration.Role;
import nz.co.vincenzo.openmpis.user.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The test case for {@link UserDAO}.
 *
 * @author Rey Vincent Babilonia
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestUserApplication.class)
@Transactional
@Rollback
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void retrieveUsers() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveUsers().isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        User administrator = new User("name1@domain");
        administrator.setRole(Role.ADMINISTRATOR);
        Assert.assertTrue("Value must be true", userDAO.updateUser(administrator));

        Assert.assertTrue("Value must be true", userDAO.createUser("name2@domain", "superadmin@domain"));

        User investigator = new User("name2@domain");
        investigator.setRole(Role.INVESTIGATOR);
        Assert.assertTrue("Value must be true", userDAO.updateUser(investigator));

        Assert.assertTrue("Value must be true", userDAO.createUser("name3@domain", "superadmin@domain"));

        User investigator2 = new User("name3@domain");
        investigator2.setRole(Role.INVESTIGATOR);
        Assert.assertTrue("Value must be true", userDAO.updateUser(investigator2));

        // ordered by email address, the primary key
        Assert.assertEquals("Lists must be equal", Arrays.asList(administrator, investigator, investigator2),
                userDAO.retrieveUsers());
    }

    @Test
    public void retrieveInvestigators() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveInvestigators().isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name2@domain", "superadmin@domain"));

        User investigator = new User();
        investigator.setEmailAddress("name2@domain");
        investigator.setRole(Role.INVESTIGATOR);
        Assert.assertTrue("Value must be true", userDAO.updateUser(investigator));

        Assert.assertTrue("Value must be true", userDAO.createUser("name3@domain", "superadmin@domain"));

        User investigator2 = new User();
        investigator2.setEmailAddress("name3@domain");
        investigator2.setRole(Role.INVESTIGATOR);
        Assert.assertTrue("Value must be true", userDAO.updateUser(investigator2));

        Assert.assertEquals("Lists must be equal", Arrays.asList(investigator, investigator2),
                userDAO.retrieveInvestigators());
    }

    @Test
    public void retrieveActiveInvestigators() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveActiveInvestigators().isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name2@domain", "superadmin@domain"));

        User investigator = new User();
        investigator.setEmailAddress("name2@domain");
        investigator.setRole(Role.INVESTIGATOR);
        investigator.setActive(true);
        Assert.assertTrue("Value must be true", userDAO.updateUser(investigator));

        Assert.assertEquals("Lists must be equal", Collections.singletonList(investigator),
                userDAO.retrieveActiveInvestigators());
    }

    @Test
    public void retrieveInactiveInvestigators() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveInactiveInvestigators().isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name2@domain", "superadmin@domain"));

        User investigator = new User();
        investigator.setEmailAddress("name2@domain");
        investigator.setRole(Role.INVESTIGATOR);
        Assert.assertTrue("Value must be true", userDAO.updateUser(investigator));

        Assert.assertTrue("Value must be true", userDAO.deactivateUser("name2@domain"));

        investigator.setActive(false);
        List<User> expected = new ArrayList<>();
        expected.add(investigator);
        Assert.assertEquals("Lists must be equal", expected, userDAO.retrieveInactiveInvestigators());
    }

    @Test
    public void retrieveAdministrators() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveAdministrators().isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        User administrator = new User("name1@domain");
        administrator.setRole(Role.ADMINISTRATOR);
        Assert.assertTrue("Value must be true", userDAO.updateUser(administrator));

        Assert.assertEquals("Lists must be equal", Collections.singletonList(administrator),
                userDAO.retrieveAdministrators());
    }

    @Test
    public void retrieveActiveAdministrators() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveActiveAdministrators().isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        User administrator = new User("name1@domain");
        administrator.setRole(Role.ADMINISTRATOR);
        administrator.setActive(true);
        Assert.assertTrue("Value must be true", userDAO.updateUser(administrator));

        Assert.assertEquals("Lists must be equal", Collections.singletonList(administrator),
                userDAO.retrieveActiveAdministrators());
    }

    @Test
    public void retrieveInactiveAdministrators() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveInactiveAdministrators().isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        User administrator = new User("name1@domain");
        administrator.setRole(Role.ADMINISTRATOR);
        Assert.assertTrue("Value must be true", userDAO.updateUser(administrator));

        Assert.assertTrue("Value must be true", userDAO.deactivateUser("name1@domain"));

        administrator.setActive(false);
        List<User> expected = new ArrayList<>();
        expected.add(administrator);
        Assert.assertEquals("Lists must be equal", expected, userDAO.retrieveInactiveAdministrators());
    }

    @Test
    public void retrieveEncoders() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveEncoders().isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        Assert.assertTrue("Value must be true", userDAO.createUser("name2@domain", "superadmin@domain"));

        Assert.assertTrue("Value must be true", userDAO.createUser("name3@domain", "superadmin@domain"));

        User encoder1 = new User("name1@domain");
        encoder1.setActive(true);
        encoder1.setRole(Role.ENCODER);
        encoder1.setCreator("superadmin@domain");

        User encoder2 = new User("name2@domain");
        encoder2.setActive(true);
        encoder2.setRole(Role.ENCODER);
        encoder2.setCreator("superadmin@domain");

        User encoder3 = new User("name3@domain");
        encoder3.setActive(true);
        encoder3.setRole(Role.ENCODER);
        encoder3.setCreator("superadmin@domain");

        // ordered by email address, the primary key
        Assert.assertEquals("Lists must be equal", Arrays.asList(encoder1, encoder2, encoder3),
                userDAO.retrieveEncoders());
    }

    @Test
    public void retrieveActiveEncoders() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveActiveEncoders().isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name3@domain", "superadmin@domain"));

        User encoder = new User("name3@domain");
        encoder.setRole(Role.ENCODER);
        encoder.setActive(true);
        encoder.setCreator("superadmin@domain");
        Assert.assertEquals("Lists must be equal", Collections.singletonList(encoder),
                userDAO.retrieveActiveEncoders());
    }

    @Test
    public void retrieveInactiveEncoders() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveInactiveEncoders().isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name3@domain", "superadmin@domain"));

        Assert.assertTrue("Value must be true", userDAO.deactivateUser("name3@domain"));

        User encoder = new User("name3@domain");
        encoder.setRole(Role.ENCODER);
        encoder.setActive(false);
        encoder.setCreator("superadmin@domain");
        List<User> expected = new ArrayList<>();
        expected.add(encoder);
        Assert.assertEquals("Lists must be equal", expected, userDAO.retrieveInactiveEncoders());
    }

    @Test
    public void retrieveUsersByAgency() throws Exception {
        Assert.assertTrue("List must be empty", userDAO.retrieveUsersByAgency("PNP").isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        Assert.assertTrue("Value must be true", userDAO.createUser("name2@domain", "superadmin@domain"));

        Assert.assertTrue("Value must be true", userDAO.createUser("name3@domain", "superadmin@domain"));

        User encoder1 = new User("name1@domain");
        encoder1.setAgency("PNP");
        encoder1.setActive(true);
        Assert.assertTrue("Value must be true", userDAO.updateUser(encoder1));

        User encoder2 = new User("name2@domain");
        encoder2.setAgency("PNP");
        encoder2.setActive(true);
        Assert.assertTrue("Value must be true", userDAO.updateUser(encoder2));

        User encoder3 = new User("name3@domain");
        encoder3.setAgency("NBI");
        Assert.assertTrue("Value must be true", userDAO.updateUser(encoder3));

        Assert.assertEquals("Lists must be equal", Arrays.asList(encoder1, encoder2),
                userDAO.retrieveUsersByAgency("PNP"));
    }

    @Test
    public void retrieveUsersByCreator() throws Exception {
        Assert.assertTrue("List must be empty",
                userDAO.retrieveUsersByCreator("name1@domain").isEmpty());

        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        Assert.assertTrue("Value must be true", userDAO.createUser("name2@domain", "name1@domain"));

        Assert.assertTrue("Value must be true", userDAO.createUser("name3@domain", "name2@domain"));

        User encoder2 = new User("name2@domain");
        encoder2.setCreator("name1@domain");
        encoder2.setActive(true);
        encoder2.setRole(Role.ENCODER);
        Assert.assertEquals("Lists must be equal", Collections.singletonList(encoder2),
                userDAO.retrieveUsersByCreator("name1@domain"));
    }

    @Test
    public void retrieveNonExistentUser() throws Exception {
        Assert.assertNull("Value must be null", userDAO.retrieveUser("name1@domain"));
    }

    @Test
    public void retrieveUser() throws Exception {
        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        User encoder = new User("name1@domain");
        encoder.setRole(Role.ENCODER);
        encoder.setActive(true);
        encoder.setCreator("superadmin@domain");
        Assert.assertEquals("Users must be equal", encoder, userDAO.retrieveUser("name1@domain"));
    }

    @Test
    public void updateUser() throws Exception {
        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        User encoder = new User("name1@domain");
        encoder.setFirstName("first name");
        encoder.setMiddleName("middle");
        encoder.setLastName("surname");
        encoder.setAgency("agency");
        encoder.setDesignation("designation");
        encoder.setPhoneNumber("+64271111111");
        Assert.assertTrue("Value must be true", userDAO.updateUser(encoder));

        Assert.assertEquals("Users must be equal", encoder, userDAO.retrieveUser("name1@domain"));
    }

    @Test
    public void updateLastLogin() throws Exception {
        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        User administrator = new User("name1@domain");
        administrator.setIpAddress("127.0.0.1");
        Date today = new Date();
        Assert.assertTrue("Value must be true", userDAO.updateLastLogin(administrator));

        User actual = userDAO.retrieveUser("name1@domain");
        Assert.assertEquals("IP addresses must be equal", "127.0.0.1", actual.getIpAddress());

        Assert.assertTrue("Elapsed time must be less than or equal to 2",
                actual.getLastLogin().getTime() - today.getTime() <= 2);
    }

    @Test
    public void updateLastLoginNonExistentUser() throws Exception {
        User user = new User("name1@domain");

        Assert.assertFalse("Value must be false", userDAO.updateLastLogin(user));
    }

    @Test
    public void isUniqueEmail() throws Exception {
        Assert.assertTrue("Value must be true", userDAO.isUniqueEmail("name1@domain"));
    }

    @Test
    public void isDuplicateEmail() throws Exception {
        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        Assert.assertFalse("Value must be false", userDAO.isUniqueEmail("name1@domain"));
    }

    @Test
    public void countUsers() throws Exception {
        Assert.assertTrue("Value must be true", userDAO.createUser("name1@domain", "superadmin@domain"));

        Assert.assertTrue("Value must be true", userDAO.createUser("name2@domain", "superadmin@domain"));

        Assert.assertTrue("Value must be true", userDAO.createUser("name3@domain", "superadmin@domain"));

        Assert.assertEquals("Values must be equal", 3, userDAO.countUsers());
    }
}