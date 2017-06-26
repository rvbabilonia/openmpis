package nz.org.vincenzo.openmpis.user.service;

import nz.org.vincenzo.openmpis.user.application.TestUserApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * The test case for {@link UserService}.
 *
 * @author Rey Vincent Babilonia
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestUserApplication.class)
@Transactional
@Rollback
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUser() throws Exception {
    }

    @Test
    public void retrieveUsers() throws Exception {
    }

    @Test
    public void retrieveInvestigators() throws Exception {
    }

    @Test
    public void retrieveActiveInvestigators() throws Exception {
    }

    @Test
    public void retrieveInactiveInvestigators() throws Exception {
    }

    @Test
    public void retrieveAdministrators() throws Exception {
    }

    @Test
    public void retrieveActiveAdministrators() throws Exception {
    }

    @Test
    public void retrieveInactiveAdministrators() throws Exception {
    }

    @Test
    public void retrieveEncoders() throws Exception {
    }

    @Test
    public void retrieveActiveEncoders() throws Exception {
    }

    @Test
    public void retrieveInactiveEncoders() throws Exception {
    }

    @Test
    public void retrieveUsersByAgency() throws Exception {
    }

    @Test
    public void retrieveUsersByCreator() throws Exception {
    }

    @Test
    public void retrieveUser() throws Exception {
    }

    @Test
    public void isUniqueEmail() throws Exception {
    }

    @Test
    public void countUsers() throws Exception {
    }

    @Test
    public void updateUser() throws Exception {
    }

    @Test
    public void updateLastLogin() throws Exception {
    }

    @Test
    public void deactivateUser() throws Exception {
    }

}