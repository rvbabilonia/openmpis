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
package org.vincenzolabs.openmpis.user.dao.impl;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.lambdaworks.crypto.SCryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.vincenzolabs.openmpis.domain.User;
import org.vincenzolabs.openmpis.enumeration.Group;
import org.vincenzolabs.openmpis.user.dao.UserDAO;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

/**
 * The DynamoDB implementation of {@link UserDAO}.
 *
 * @author Rey Vincent Babilonia
 */
@Repository
public class UserDAODynamoDBImpl
    implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAODynamoDBImpl.class);

    private static final String USER_TABLE_NAME = "user";

    // hashed password is hidden
    private static final String[] USER_COLUMN_NAMES = { "uuid", "firstName", "middleName", "lastName", "group",
        "emailAddress", "rank", "designation", "agencyUuid", "creationDate", "active", "creatorUuid",
        "ipAddress", "lastLoginDate" };

    private static final int COST_FACTOR = 32;

    private static final int BLOCK_SIZE_FACTOR = 8;

    private static final int PARALLELIZATION_FACTOR = 4;

    private final DynamoDbClient client;

    /**
     * Default constructor.
     *
     * @param client the {@link DynamoDbClient}
     */
    public UserDAODynamoDBImpl(DynamoDbClient client) {
        this.client = client;
    }

    @Override
    public User createUser(User user, ZoneId zoneId) {
        User newUser = User.builder(user)
            .withUuid(UUID.randomUUID().toString())
            .withCreationDate(OffsetDateTime.now(zoneId))
            .withActive(true)
            .build();

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("uuid", AttributeValue.builder().s(newUser.getUuid()).build());
        item.put("password", AttributeValue.builder().s(getPasswordHash(user.getPassword())).build());
        item.put("creationDate", AttributeValue.builder().s(newUser.getCreationDate().toString()).build());
        item.put("active", AttributeValue.builder().bool(newUser.isActive()).build());
        if (newUser.getGroup() != null) {
            item.put("group", AttributeValue.builder().s(newUser.getGroup().toString()).build());
        }
        if (StringUtils.isNotBlank(newUser.getEmailAddress())) {
            item.put("emailAddress", AttributeValue.builder().s(newUser.getEmailAddress()).build());
        }
        if (StringUtils.isNotBlank(newUser.getRank())) {
            item.put("rank", AttributeValue.builder().s(newUser.getRank()).build());
        }
        if (StringUtils.isNotBlank(newUser.getFirstName())) {
            item.put("firstName", AttributeValue.builder().s(newUser.getFirstName()).build());
        }
        if (StringUtils.isNotBlank(newUser.getMiddleName())) {
            item.put("middleName", AttributeValue.builder().s(newUser.getMiddleName()).build());
        }
        if (StringUtils.isNotBlank(newUser.getLastName())) {
            item.put("lastName", AttributeValue.builder().s(newUser.getLastName()).build());
        }
        if (StringUtils.isNotBlank(newUser.getDesignation())) {
            item.put("designation", AttributeValue.builder().s(newUser.getDesignation()).build());
        }
        if (StringUtils.isNotBlank(newUser.getAgencyUuid())) {
            item.put("agencyUuid", AttributeValue.builder().s(newUser.getAgencyUuid()).build());
        }
        if (StringUtils.isNotBlank(newUser.getCreatorUuid())) {
            item.put("creatorUuid", AttributeValue.builder().s(newUser.getCreatorUuid()).build());
        }

        PutItemRequest request = PutItemRequest.builder()
            .tableName(USER_TABLE_NAME)
            .item(item)
            .build();

        try {
            client.putItem(request);

            return newUser;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to create user: Table [{}] does not exist", USER_TABLE_NAME);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to create user: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Set<User> retrieveUsers() {
        ScanRequest request = ScanRequest.builder()
            .tableName(USER_TABLE_NAME)
            .build();

        Set<User> persons = new HashSet<>();
        try {
            ScanResponse response = client.scan(request);
            response.items().forEach(item -> persons.add(getUser(item.get("uuid").s(), item)));

            return persons;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve persons: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public User retrieveUser(final String personUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(personUuid).build());

        GetItemRequest request = GetItemRequest.builder()
            .tableName(USER_TABLE_NAME)
            .key(key)
            .attributesToGet(USER_COLUMN_NAMES)
            .build();

        try {
            Map<String, AttributeValue> item = client.getItem(request).item();

            return getUser(personUuid, item);
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve user: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public User login(final String emailAddress, final String password, ZoneId zoneId, final String ipAddress) {
        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":emailAddress", AttributeValue.builder().s(emailAddress).build());

        QueryRequest request = QueryRequest.builder()
            .tableName(USER_TABLE_NAME)
            .projectionExpression("#uuid, password")
            .indexName("emailAddresses")
            .keyConditionExpression("emailAddress = :emailAddress")
            .expressionAttributeValues(expressionAttributeValues)
            .expressionAttributeNames(Map.of("#uuid", "uuid"))
            .consistentRead(false)
            .build();

        try {
            QueryResponse response = client.query(request);

            if (response.count() == 0) {
                LOGGER.warn("Failed to retrieve user: User with email address [{}] does not exist", emailAddress);

                return null;
            }

            List<Map<String, AttributeValue>> items = response.items();
            Map<String, AttributeValue> item = items.get(0);

            String userUuid = getValue(item.get("uuid"), String.class);
            String hashedPassword = getValue(item.get("password"), String.class);

            if (SCryptUtil.check(password, hashedPassword)) {
                User user = retrieveUser(userUuid);
                if (zoneId == null) {
                    zoneId = ZoneId.of("Pacific/Auckland");
                }

                // log last login date and IP address
                return updateUser(User.builder(user)
                    .withLastLoginDate(OffsetDateTime.now(zoneId))
                    .withIpAddress(ipAddress)
                    .build());
            }

            LOGGER.warn("Failed to authenticate user: Password is invalid for user with email address [{}]",
                emailAddress);

            return null;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve user by email address: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public User updateUser(User user) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(user.getUuid()).build());

        Map<String, AttributeValueUpdate> updatedValues = new HashMap<>();
        if (user.getGroup() != null) {
            updatedValues.put("group", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getGroup().toString()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(user.getPassword())) {
            updatedValues.put("password", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(getPasswordHash(user.getPassword())).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(user.getFirstName())) {
            updatedValues.put("firstName", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getFirstName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(user.getMiddleName())) {
            updatedValues.put("middleName", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getMiddleName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(user.getLastName())) {
            updatedValues.put("lastName", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getLastName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(user.getRank())) {
            updatedValues.put("rank", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getRank()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(user.getDesignation())) {
            updatedValues.put("designation", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getDesignation()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(user.getAgencyUuid())) {
            updatedValues.put("agencyUuid", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getAgencyUuid()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        updatedValues.put("active", AttributeValueUpdate.builder()
            .value(AttributeValue.builder().bool(user.isActive()).build())
            .action(AttributeAction.PUT)
            .build());
        if (user.getLastLoginDate() != null) {
            updatedValues.put("lastLoginDate", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getLastLoginDate().toString()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(user.getIpAddress())) {
            updatedValues.put("ipAddress", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getIpAddress()).build())
                .action(AttributeAction.PUT)
                .build());
        }

        UpdateItemRequest request = UpdateItemRequest.builder()
            .tableName(USER_TABLE_NAME)
            .key(key)
            .attributeUpdates(updatedValues)
            .build();

        try {
            client.updateItem(request);

            return retrieveUser(user.getUuid());
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to update user: User with UUID [{}] does not exist",
                user.getUuid());

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to update user: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public boolean deleteUser(final String personUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(personUuid).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
            .tableName(USER_TABLE_NAME)
            .key(key)
            .build();

        try {
            client.deleteItem(request);

            return true;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to delete user: User with UUID [{}] does not exist", personUuid);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to delete user: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    private User getUser(String personUuid, Map<String, AttributeValue> item) {
        if (item != null && !item.isEmpty()) {
            User.Builder builder = User.builder()
                .withUuid(getValue(item.get("uuid"), String.class))
                .withEmailAddress(getValue(item.get("emailAddress"), String.class))
                .withFirstName(getValue(item.get("firstName"), String.class))
                .withMiddleName(getValue(item.get("middleName"), String.class))
                .withLastName(getValue(item.get("lastName"), String.class))
                .withRank(getValue(item.get("rank"), String.class))
                .withDesignation(getValue(item.get("designation"), String.class))
                .withAgencyUuid(getValue(item.get("agencyUuid"), String.class))
                .withIpAddress(getValue(item.get("ipAddress"), String.class))
                .withCreationDate(OffsetDateTime.parse(getValue(item.get("creationDate"), String.class)))
                .withCreatorUuid(getValue(item.get("creatorUuid"), String.class))
                .withActive(getValue(item.get("active"), Boolean.class));

            if (item.get("group") != null) {
                builder.withGroup(Group.valueOf(getValue(item.get("group"), String.class)));
            }
            if (item.get("lastLoginDate") != null) {
                builder.withLastLoginDate(OffsetDateTime.parse(getValue(item.get("lastLoginDate"), String.class)));
            }

            return builder.build();
        } else {
            LOGGER.error("Failed to retrieve user: User with UUID [{}] does not exist",
                personUuid);

            return null;
        }
    }

    private <T> T getValue(AttributeValue value, Class<T> type) {
        if (value == null) {
            return null;
        }

        if (Boolean.class == type) {
            return type.cast(value.bool());
        }

        return type.cast(value.s());
    }

    private String getPasswordHash(String plainTextPassword) {
        return SCryptUtil.scrypt(plainTextPassword, COST_FACTOR, BLOCK_SIZE_FACTOR, PARALLELIZATION_FACTOR);
    }
}
