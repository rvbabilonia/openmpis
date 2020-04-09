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
package org.vincenzolabs.openmpis.user.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Set;
import java.util.UUID;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import by.dev.madhead.aws_junit5.dynamo.v2.DynamoDB;
import com.lambdaworks.crypto.SCryptUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.vincenzolabs.openmpis.domain.User;
import org.vincenzolabs.openmpis.enumeration.Group;
import org.vincenzolabs.openmpis.user.dao.impl.UserDAODynamoDBImpl;
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
 * The test case for {@link UserDAO}.
 *
 * @author Rey Vincent Babilonia
 */
@ExtendWith(DynamoDB.class)
class UserDAOTest {

    private static final String USER_TABLE_NAME = "user";

    @AWSClient(endpoint = Endpoint.class)
    private DynamoDbClient client;

    private UserDAO userDAO;

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

        userDAO = new UserDAODynamoDBImpl(client);
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
        User user = userDAO.createUser(User.builder()
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
    void retrieveUser() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        User user = userDAO.createUser(User.builder()
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

        User actual = userDAO.retrieveUser(user.getUuid());

        assertThat(actual.getCreationDate()).isBefore(OffsetDateTime.now(ZoneId.of("Pacific/Auckland")));
        assertThat(actual)
            .extracting("firstName", "middleName", "lastName", "active", "emailAddress", "password", "rank",
                "designation", "agencyUuid", "creatorUuid", "lastLoginDate", "ipAddress", "group")
            .containsExactly("Alex", null, "Cross", true, "alex.cross@gmail.com", null, "Special Agent",
                "Homicide Division", agencyUuid, creatorUuid, null, null, Group.INVESTIGATOR);
    }

    @Test
    void retrieveNonExistentUser() {
        assertThat(userDAO.retrieveUser("uuid")).isNull();
    }

    @Test
    void retrieveUsers() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        userDAO.createUser(User.builder()
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

        Set<User> actual = userDAO.retrieveUsers();

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
        assertThat(userDAO.retrieveUsers()).isEmpty();
    }

    @Test
    void updateUser() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        User user = userDAO.createUser(User.builder()
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
        User actual = userDAO.updateUser(User.builder(user)
            .withActive(false)
            .withIpAddress("127.0.0.1")
            .withLastLoginDate(lastLoginDate)
            .build());

        assertThat(actual.getCreationDate()).isBefore(OffsetDateTime.now(ZoneId.of("Pacific/Auckland")));
        assertThat(actual)
            .extracting("firstName", "middleName", "lastName", "active", "emailAddress", "password", "rank",
                "designation", "agencyUuid", "creatorUuid", "lastLoginDate", "ipAddress", "group")
            .containsExactly("Alex", null, "Cross", false, "alex.cross@gmail.com", null, "Special Agent",
                "Homicide Division", agencyUuid, creatorUuid, lastLoginDate, "127.0.0.1", Group.INVESTIGATOR);
    }

    @Test
    void deleteUser() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        User user = userDAO.createUser(User.builder()
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

        assertThat(userDAO.deleteUser(user.getUuid())).isTrue();
        assertThat(userDAO.retrieveUser(user.getUuid())).isNull();
    }

    @Test
    void encrypt() {
        assertThat(SCryptUtil.scrypt("password", 32, 8, 4)).hasSize(79);
    }

    @Test
    void login() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        userDAO.createUser(User.builder()
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

        User actual = userDAO.login("mvbabilonia@gmail.com", "password123", ZoneId.of("Pacific/Auckland"), "127.0.0.1");

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
    void loginWithInvalidEmailAddress() {
        assertThat(userDAO.login("mvbabilonia@gmail.com", "password123", ZoneId.of("Pacific/Auckland"), "127.0.0.1"))
            .isNull();
    }

    @Test
    void loginWithInvalidPassword() {
        String agencyUuid = UUID.randomUUID().toString();
        String creatorUuid = UUID.randomUUID().toString();
        userDAO.createUser(User.builder()
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

        assertThat(userDAO.login("mvbabilonia@gmail.com", "password", ZoneId.of("Pacific/Auckland"), "127.0.0.1"))
            .isNull();
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
