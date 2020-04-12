/*
 * This file is part of OpenMPIS.
 *
 * Copyright (c) 2019 VincenzoLabs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.vincenzolabs.openmpis.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Set;
import java.util.UUID;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import by.dev.madhead.aws_junit5.dynamo.v2.DynamoDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.vincenzolabs.openmpis.domain.User;
import org.vincenzolabs.openmpis.enumeration.Group;
import org.vincenzolabs.openmpis.user.dao.UserDAO;
import org.vincenzolabs.openmpis.user.dao.impl.UserDAODynamoDBImpl;
import org.vincenzolabs.openmpis.user.service.impl.UserServiceImpl;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.GlobalSecondaryIndex;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.Projection;
import software.amazon.awssdk.services.dynamodb.model.ProjectionType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

/**
 * The test case for {@link UserService}.
 *
 * @author Rey Vincent Babilonia
 */
@ExtendWith(DynamoDB.class)
class UserServiceTest {

    private static final String USER_TABLE_NAME = "user";

    @AWSClient(endpoint = Endpoint.class)
    private DynamoDbClient client;

    private UserService userService;

    @BeforeEach
    void setUp() {
        CreateTableRequest createTableRequest = CreateTableRequest.builder()
            .tableName(USER_TABLE_NAME)
            .keySchema(KeySchemaElement.builder()
                .attributeName("uuid")
                .keyType(KeyType.HASH)
                .build())
            .attributeDefinitions(
                AttributeDefinition.builder()
                    .attributeName("uuid")
                    .attributeType(ScalarAttributeType.S)
                    .build(),
                AttributeDefinition.builder()
                    .attributeName("emailAddress")
                    .attributeType(ScalarAttributeType.S)
                    .build())
            .provisionedThroughput(ProvisionedThroughput.builder()
                .readCapacityUnits(5L)
                .writeCapacityUnits(5L)
                .build())
            .globalSecondaryIndexes(
                GlobalSecondaryIndex.builder()
                    .indexName("emailAddresses")
                    .keySchema(KeySchemaElement.builder()
                        .attributeName("emailAddress")
                        .keyType(KeyType.HASH)
                        .build())
                    .projection(Projection.builder()
                        .projectionType(ProjectionType.KEYS_ONLY)
                        .build())
                    .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(5L)
                        .writeCapacityUnits(5L)
                        .build())
                    .build())
            .build();

        client.createTable(createTableRequest);

        UserDAO userDAO = new UserDAODynamoDBImpl(client);

        userService = new UserServiceImpl(userDAO);
    }

    @AfterEach
    void tearDown() {
        DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder()
            .tableName(USER_TABLE_NAME)
            .build();

        client.deleteTable(deleteTableRequest);
    }

    @Test
    void createUser() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        User user = userService.createUser(User.builder()
                .withFirstName("Alex")
                .withLastName("Cross")
                .withGroup(Group.INVESTIGATOR)
                .withEmailAddress("alex.cross@gmail.com")
                .withRank("Special Agent")
                .withDesignation("Homicide Division")
                .withAgencyUuid(agencyUuid)
                .withCreatorUuid(creatorUuid)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        assertThat(user.getUuid()).isNotBlank();
        assertThat(user.getCreationDate()).isBefore(OffsetDateTime.now(ZoneId.of("Pacific/Auckland")));
        assertThat(user)
            .extracting("firstName", "middleName", "lastName", "active", "emailAddress", "password", "rank",
                "designation", "agencyUuid", "creatorUuid", "lastLoginDate", "ipAddress", "group")
            .containsExactly("Alex", null, "Cross", true, "alex.cross@gmail.com", null, "Special Agent",
                "Homicide Division", agencyUuid, creatorUuid, null, null, Group.INVESTIGATOR);
    }

    @Test
    void createUserWithoutNickname() {
        IllegalArgumentException exception = catchThrowableOfType(() ->
                userService.createUser(User.builder().build(), ZoneId.of("Pacific/Auckland")),
            IllegalArgumentException.class);

        assertThat(exception)
            .hasMessage("Email address must not be blank");
    }

    @Test
    void retrieveUser() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        User user = userService.createUser(User.builder()
                .withFirstName("Rey Vincent")
                .withLastName("Babilonia")
                .withGroup(Group.ADMINISTRATOR)
                .withEmailAddress("rvbabilonia@gmail.com")
                .withDesignation("N/A")
                .withAgencyUuid(agencyUuid)
                .withCreatorUuid(creatorUuid)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        User actual = userService.retrieveUser(user.getUuid());

        assertThat(actual.getCreationDate()).isBefore(OffsetDateTime.now(ZoneId.of("Pacific/Auckland")));
        assertThat(actual)
            .extracting("firstName", "middleName", "lastName", "active", "emailAddress", "password", "rank",
                "designation", "agencyUuid", "creatorUuid", "lastLoginDate", "ipAddress", "group")
            .containsExactly("Rey Vincent", null, "Babilonia", true, "rvbabilonia@gmail.com", null, null,
                "N/A", agencyUuid, creatorUuid, null, null, Group.ADMINISTRATOR);
    }

    @Test
    void retrieveNonExistentUser() {
        assertThat(userService.retrieveUser("uuid")).isNull();
    }

    @Test
    void retrieveUsers() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        userService.createUser(User.builder()
                .withFirstName("Alex")
                .withLastName("Cross")
                .withGroup(Group.INVESTIGATOR)
                .withEmailAddress("alex.cross@gmail.com")
                .withRank("Special Agent")
                .withDesignation("Homicide Division")
                .withAgencyUuid(agencyUuid)
                .withCreatorUuid(creatorUuid)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        Set<User> actual = userService.retrieveUsers();

        assertThat(actual)
            .hasSize(1)
            .first()
            .extracting("firstName", "middleName", "lastName", "active", "emailAddress", "password", "rank",
                "designation", "agencyUuid", "creatorUuid", "lastLoginDate", "ipAddress", "group")
            .containsExactly("Alex", null, "Cross", true, "alex.cross@gmail.com", null, "Special Agent",
                "Homicide Division", agencyUuid, creatorUuid, null, null, Group.INVESTIGATOR);
    }

    @Test
    void retrieveNonExistentAgencies() {
        assertThat(userService.retrieveUsers()).isEmpty();
    }

    @Test
    void updateUser() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        User user = userService.createUser(User.builder()
                .withFirstName("Alex")
                .withLastName("Cross")
                .withGroup(Group.INVESTIGATOR)
                .withEmailAddress("alex.cross@gmail.com")
                .withRank("Special Agent")
                .withDesignation("Homicide Division")
                .withAgencyUuid(agencyUuid)
                .withCreatorUuid(creatorUuid)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        OffsetDateTime lastLoginDate = OffsetDateTime.now().minusHours(1);
        User actual = userService.updateUser(User.builder(user)
            .withActive(false)
            .withIpAddress("127.0.0.1")
            .withLastLoginDate(lastLoginDate)
            .withGroup(Group.ENCODER)
            .build());

        assertThat(actual.getCreationDate()).isBefore(OffsetDateTime.now(ZoneId.of("Pacific/Auckland")));
        assertThat(actual)
            .extracting("firstName", "middleName", "lastName", "active", "emailAddress", "password", "rank",
                "designation", "agencyUuid", "creatorUuid", "lastLoginDate", "ipAddress", "group")
            .containsExactly("Alex", null, "Cross", false, "alex.cross@gmail.com", null, "Special Agent",
                "Homicide Division", agencyUuid, creatorUuid, lastLoginDate, "127.0.0.1", Group.ENCODER);
    }

    @Test
    void updateUserWithoutName() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        User user = userService.createUser(User.builder()
                .withFirstName("Alex")
                .withLastName("Cross")
                .withGroup(Group.INVESTIGATOR)
                .withEmailAddress("alex.cross@gmail.com")
                .withRank("Special Agent")
                .withDesignation("Homicide Division")
                .withAgencyUuid(agencyUuid)
                .withCreatorUuid(creatorUuid)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        IllegalArgumentException exception = catchThrowableOfType(() ->
            userService.updateUser(User.builder(user).withFirstName(null).build()), IllegalArgumentException.class);

        assertThat(exception)
            .hasMessage("First name must not be blank");
    }

    @Test
    void deleteUser() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        User user = userService.createUser(User.builder()
                .withFirstName("Alex")
                .withLastName("Cross")
                .withGroup(Group.INVESTIGATOR)
                .withEmailAddress("alex.cross@gmail.com")
                .withRank("Special Agent")
                .withDesignation("Homicide Division")
                .withAgencyUuid(agencyUuid)
                .withCreatorUuid(creatorUuid)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        assertThat(userService.deleteUser(user.getUuid())).isTrue();
        assertThat(userService.retrieveUser(user.getUuid())).isNull();
    }

    @Test
    void login() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        userService.createUser(User.builder()
                .withFirstName("Marcello")
                .withLastName("Babilonia")
                .withGroup(Group.ENCODER)
                .withEmailAddress("mvbabilonia@gmail.com")
                .withPassword("password123")
                .withDesignation("Encoder I")
                .withAgencyUuid(agencyUuid)
                .withCreatorUuid(creatorUuid)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        User actual = userService.login("mvbabilonia@gmail.com", "password123", ZoneId.of("Pacific/Auckland"),
            "127.0.0.1");

        assertThat(actual).isNotNull();
        assertThat(actual.getUuid()).isNotBlank();
        assertThat(actual.getCreationDate()).isBefore(OffsetDateTime.now());
        assertThat(actual.getLastLoginDate()).isBefore(OffsetDateTime.now());
        assertThat(actual)
            .extracting("group", "emailAddress", "password", "rank", "firstName", "middleName", "lastName",
                "designation", "agencyUuid", "ipAddress", "active", "creatorUuid")
            .containsExactly(Group.ENCODER, "mvbabilonia@gmail.com", null, null, "Marcello", null, "Babilonia",
                "Encoder I", agencyUuid, "127.0.0.1", true, creatorUuid);
    }

    @Test
    void loginWithoutEmailAddress() {
        IllegalArgumentException exception = catchThrowableOfType(
            () -> userService.login("", "password123", ZoneId.of("Pacific/Auckland"), "127.0.0.1"),
            IllegalArgumentException.class);

        assertThat(exception).hasMessage("Email address must not be blank");
    }

    @Test
    void loginWithoutPassword() {
        IllegalArgumentException exception = catchThrowableOfType(
            () -> userService.login("mvbabilonia@gmail.com", null, ZoneId.of("Pacific/Auckland"), "127.0.0.1"),
            IllegalArgumentException.class);

        assertThat(exception).hasMessage("Password must not be blank");
    }

    @Test
    void loginWithInvalidEmailAddress() {
        assertThat(
            userService.login("mvbabilonia@gmail.com", "password123", ZoneId.of("Pacific/Auckland"), "127.0.0.1"))
            .isNull();
    }

    @Test
    void loginWithInvalidPassword() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        userService.createUser(User.builder()
                .withFirstName("Marcello")
                .withLastName("Babilonia")
                .withGroup(Group.ENCODER)
                .withEmailAddress("mvbabilonia@gmail.com")
                .withPassword("password123")
                .withDesignation("Encoder I")
                .withAgencyUuid(agencyUuid)
                .withCreatorUuid(creatorUuid)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        assertThat(userService.login("mvbabilonia@gmail.com", "password", ZoneId.of("Pacific/Auckland"), "127.0.0.1"))
            .isNull();
    }

    @Test
    void archiveUser() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        User user = userService.createUser(User.builder()
                .withFirstName("Alex")
                .withLastName("Cross")
                .withGroup(Group.INVESTIGATOR)
                .withEmailAddress("alex.cross@gmail.com")
                .withRank("Special Agent")
                .withDesignation("Homicide Division")
                .withAgencyUuid(agencyUuid)
                .withCreatorUuid(creatorUuid)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        User actual = userService.archiveUser(user.getUuid());

        assertThat(actual.getCreationDate()).isBefore(OffsetDateTime.now(ZoneId.of("Pacific/Auckland")));
        assertThat(actual)
            .extracting("firstName", "middleName", "lastName", "active", "emailAddress", "password", "rank",
                "designation", "agencyUuid", "creatorUuid", "lastLoginDate", "ipAddress", "group")
            .containsExactly("Alex", null, "Cross", false, "alex.cross@gmail.com", null, "Special Agent",
                "Homicide Division", agencyUuid, creatorUuid, null, null, Group.INVESTIGATOR);
    }

    /**
     * The implementation of {@link AWSEndpoint}.
     */
    public static class Endpoint
        implements AWSEndpoint {

        @Override
        public String url() {
            return System.getenv("DYNAMODB_URL");
        }

        @Override
        public String region() {
            return System.getenv("DYNAMODB_REGION");
        }

        @Override
        public String accessKey() {
            return System.getenv("DYNAMODB_ACCESS_KEY");
        }

        @Override
        public String secretKey() {
            return System.getenv("DYNAMODB_SECRET_KEY");
        }
    }
}
