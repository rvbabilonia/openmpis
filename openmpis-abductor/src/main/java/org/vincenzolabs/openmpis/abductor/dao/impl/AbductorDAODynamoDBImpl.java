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
package org.vincenzolabs.openmpis.abductor.dao.impl;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vincenzolabs.openmpis.abductor.dao.AbductorDAO;
import org.vincenzolabs.openmpis.domain.Abductor;
import org.vincenzolabs.openmpis.domain.AdditionalDetails;
import org.vincenzolabs.openmpis.domain.Description;
import org.vincenzolabs.openmpis.domain.StreetAddress;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

/**
 * The DynamoDB implementation of {@link AbductorDAO}.
 *
 * @author Rey Vincent Babilonia
 */
@Repository
public class AbductorDAODynamoDBImpl
    implements AbductorDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbductorDAODynamoDBImpl.class);

    private static final String ABDUCTOR_TABLE_NAME = "abductor";

    private static final String[] ABDUCTOR_COLUMN_NAMES = { "uuid", "firstName", "middleName", "lastName", "nickname",
        "birthDate", "lastSeenDate", "lastSeenLocation", "possibleLocation", "photoUuids", "primaryPhotoUuid",
        "description", "additionalDetails" };

    private final DynamoDbClient client;

    private final Gson gson;

    /**
     * Default constructor.
     *
     * @param client the {@link DynamoDbClient}
     * @param gson   the {@link Gson}
     */
    @Autowired
    public AbductorDAODynamoDBImpl(DynamoDbClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @Override
    public Abductor createAbductor(Abductor abductor) {
        Abductor newAbductor = Abductor.builder()
            .withUuid(UUID.randomUUID().toString())
            .withFirstName(abductor.getFirstName())
            .withMiddleName(abductor.getMiddleName())
            .withLastName(abductor.getLastName())
            .withNickname(abductor.getNickname())
            .withBirthDate(abductor.getBirthDate())
            .withLastSeenDate(abductor.getLastSeenDate())
            .withLastSeenLocation(abductor.getLastSeenLocation())
            .withPossibleLocation(abductor.getPossibleLocation())
            .withPhotoUuids(abductor.getPhotoUuids())
            .withPrimaryPhotoUuid(abductor.getPrimaryPhotoUuid())
            .withDescription(abductor.getDescription())
            .withAdditionalDetails(abductor.getAdditionalDetails())
            .build();

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("uuid", AttributeValue.builder().s(newAbductor.getUuid()).build());
        if (StringUtils.isNotBlank(newAbductor.getFirstName())) {
            item.put("firstName", AttributeValue.builder().s(newAbductor.getFirstName()).build());
        }
        if (StringUtils.isNotBlank(newAbductor.getMiddleName())) {
            item.put("middleName", AttributeValue.builder().s(newAbductor.getMiddleName()).build());
        }
        if (StringUtils.isNotBlank(newAbductor.getLastName())) {
            item.put("lastName", AttributeValue.builder().s(newAbductor.getLastName()).build());
        }
        if (StringUtils.isNotBlank(newAbductor.getNickname())) {
            item.put("nickname", AttributeValue.builder().s(newAbductor.getNickname()).build());
        }
        if (newAbductor.getBirthDate() != null) {
            item.put("birthDate", AttributeValue.builder().s(newAbductor.getBirthDate().toString()).build());
        }
        if (newAbductor.getLastSeenDate() != null) {
            item.put("lastSeenDate", AttributeValue.builder().s(newAbductor.getLastSeenDate().toString()).build());
        }
        if (newAbductor.getPossibleLocation() != null) {
            item.put("possibleLocation", AttributeValue.builder()
                .s(gson.toJson(newAbductor.getPossibleLocation()))
                .build());
        }
        if (newAbductor.getPhotoUuids() != null && !newAbductor.getPhotoUuids().isEmpty()) {
            item.put("photoUuids", AttributeValue.builder().ss(newAbductor.getPhotoUuids()).build());
        }
        if (StringUtils.isNotBlank(newAbductor.getPrimaryPhotoUuid())) {
            item.put("primaryPhotoUuid", AttributeValue.builder().s(newAbductor.getPrimaryPhotoUuid()).build());
        }
        if (newAbductor.getDescription() != null) {
            item.put("description", AttributeValue.builder().s(gson.toJson(newAbductor.getDescription())).build());
        }
        if (newAbductor.getAdditionalDetails() != null) {
            item.put("additionalDetails", AttributeValue.builder()
                .s(gson.toJson(newAbductor.getAdditionalDetails()))
                .build());
        }

        PutItemRequest request = PutItemRequest.builder()
            .tableName(ABDUCTOR_TABLE_NAME)
            .item(item)
            .build();

        try {
            client.putItem(request);

            return newAbductor;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to create abductor: Table [{}] does not exist", ABDUCTOR_TABLE_NAME);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to create abductor: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Set<Abductor> retrieveAbductors() {
        ScanRequest request = ScanRequest.builder()
            .tableName(ABDUCTOR_TABLE_NAME)
            .build();

        Set<Abductor> abductors = new HashSet<>();
        try {
            ScanResponse response = client.scan(request);
            response.items().forEach(item -> abductors.add(getAbductor(item.get("uuid").s(), item)));

            return abductors;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve abductors: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Abductor retrieveAbductor(final String abductorUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(abductorUuid).build());

        GetItemRequest request = GetItemRequest.builder()
            .tableName(ABDUCTOR_TABLE_NAME)
            .key(key)
            .attributesToGet(ABDUCTOR_COLUMN_NAMES)
            .build();

        try {
            Map<String, AttributeValue> item = client.getItem(request).item();

            return getAbductor(abductorUuid, item);
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve abductor: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Abductor updateAbductor(Abductor abductor) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(abductor.getUuid()).build());

        Map<String, AttributeValueUpdate> updatedValues = new HashMap<>();
        if (StringUtils.isNotBlank(abductor.getFirstName())) {
            updatedValues.put("firstName", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(abductor.getFirstName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(abductor.getMiddleName())) {
            updatedValues.put("middleName", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(abductor.getMiddleName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(abductor.getLastName())) {
            updatedValues.put("lastName", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(abductor.getLastName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(abductor.getNickname())) {
            updatedValues.put("nickname", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(abductor.getNickname()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (abductor.getBirthDate() != null) {
            updatedValues.put("birthDate", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(abductor.getBirthDate().toString()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (abductor.getLastSeenDate() != null) {
            updatedValues.put("lastSeenDate", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(abductor.getLastSeenDate().toString()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (abductor.getLastSeenLocation() != null) {
            updatedValues.put("lastSeenLocation", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(abductor.getLastSeenLocation())).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (abductor.getPossibleLocation() != null) {
            updatedValues.put("possibleLocation", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(abductor.getPossibleLocation())).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (!abductor.getPhotoUuids().isEmpty()) {
            updatedValues.put("photoUuids", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().ss(abductor.getPhotoUuids()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(abductor.getPrimaryPhotoUuid())) {
            updatedValues.put("primaryPhotoUuid", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(abductor.getPrimaryPhotoUuid()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (abductor.getDescription() != null) {
            updatedValues.put("description", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(abductor.getDescription())).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (abductor.getAdditionalDetails() != null) {
            updatedValues.put("additionalDetails", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(abductor.getAdditionalDetails())).build())
                .action(AttributeAction.PUT)
                .build());
        }

        UpdateItemRequest request = UpdateItemRequest.builder()
            .tableName(ABDUCTOR_TABLE_NAME)
            .key(key)
            .attributeUpdates(updatedValues)
            .build();

        try {
            client.updateItem(request);

            return retrieveAbductor(abductor.getUuid());
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to update abductor: Abductor with UUID [{}] does not exist",
                abductor.getUuid());

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to update abductor: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public boolean deleteAbductor(final String abductorUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(abductorUuid).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
            .tableName(ABDUCTOR_TABLE_NAME)
            .key(key)
            .build();

        try {
            client.deleteItem(request);

            return true;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to delete abductor: Abductor with UUID [{}] does not exist", abductorUuid);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to delete abductor: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    private Abductor getAbductor(String abductorUuid, Map<String, AttributeValue> item) {
        if (item != null && !item.isEmpty()) {
            Abductor.Builder builder = Abductor.builder()
                .withUuid(getValue(item.get("uuid")))
                .withFirstName(getValue(item.get("firstName")))
                .withMiddleName(getValue(item.get("middleName")))
                .withLastName(getValue(item.get("lastName")))
                .withNickname(getValue(item.get("nickname")))
                .withPrimaryPhotoUuid(getValue(item.get("primaryPhotoUuid")));

            if (item.get("photoUuids") != null) {
                builder.withPhotoUuids(new HashSet<>(item.get("photoUuids").ss()));
            }
            if (item.get("birthDate") != null) {
                builder.withBirthDate(LocalDate.parse(getValue(item.get("birthDate"))));
            }
            if (item.get("lastSeenDate") != null) {
                builder.withLastSeenDate(OffsetDateTime.parse(getValue(item.get("lastSeenDate"))));
            }
            if (item.get("lastSeenLocation") != null) {
                builder.withLastSeenLocation(gson.fromJson(getValue(item.get("lastSeenLocation")),
                    StreetAddress.class));
            }
            if (item.get("possibleLocation") != null) {
                builder.withPossibleLocation(gson.fromJson(getValue(item.get("possibleLocation")),
                    StreetAddress.class));
            }
            if (item.get("description") != null) {
                builder.withDescription(gson.fromJson(getValue(item.get("description")),
                    Description.class));
            }
            if (item.get("additionalDetails") != null) {
                builder.withAdditionalDetails(gson.fromJson(getValue(item.get("additionalDetails")),
                    AdditionalDetails.class));
            }

            return builder.build();
        } else {
            LOGGER.error("Failed to retrieve abductor: Abductor with UUID [{}] does not exist",
                abductorUuid);

            return null;
        }
    }

    private String getValue(AttributeValue value) {
        return value == null ? null : value.s();
    }
}
