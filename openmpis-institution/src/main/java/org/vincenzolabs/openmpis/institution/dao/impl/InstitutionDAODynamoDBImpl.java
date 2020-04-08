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
package org.vincenzolabs.openmpis.institution.dao.impl;

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
import org.vincenzolabs.openmpis.institution.dao.InstitutionDAO;
import org.vincenzolabs.openmpis.domain.Institution;
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
 * The DynamoDB implementation of {@link InstitutionDAO}.
 *
 * @author Rey Vincent Babilonia
 */
@Repository
public class InstitutionDAODynamoDBImpl
    implements InstitutionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstitutionDAODynamoDBImpl.class);

    private static final String INSTITUTION_TABLE_NAME = "institution";

    private static final String[] INSTITUTION_COLUMN_NAMES = {"uuid", "name", "contactNumber", "streetAddress",
        "emailAddress"};

    private final DynamoDbClient client;

    private final Gson gson;

    /**
     * Default constructor.
     *
     * @param client the {@link DynamoDbClient}
     * @param gson   the {@link Gson}
     */
    @Autowired
    public InstitutionDAODynamoDBImpl(DynamoDbClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @Override
    public Institution createInstitution(final String name, final String contactNumber, StreetAddress streetAddress,
        final String emailAddress) {
        Institution institution = Institution.builder()
            .withUuid(UUID.randomUUID().toString())
            .withName(name)
            .withContactNumber(contactNumber)
            .withStreetAddress(streetAddress)
            .withEmailAddress(emailAddress)
            .build();

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("uuid", AttributeValue.builder().s(institution.getUuid()).build());
        item.put("name", AttributeValue.builder().s(institution.getName()).build());
        item.put("contactNumber", AttributeValue.builder().s(institution.getContactNumber()).build());
        item.put("streetAddress", AttributeValue.builder().s(gson.toJson(institution.getStreetAddress())).build());
        item.put("emailAddress", AttributeValue.builder().s(institution.getEmailAddress()).build());

        PutItemRequest request = PutItemRequest.builder()
            .tableName(INSTITUTION_TABLE_NAME)
            .item(item)
            .build();

        try {
            client.putItem(request);

            return institution;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to create institution: Table [{}] does not exist", INSTITUTION_TABLE_NAME);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to create institution: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Set<Institution> retrieveInstitutions() {
        ScanRequest request = ScanRequest.builder()
            .tableName(INSTITUTION_TABLE_NAME)
            .build();

        Set<Institution> institutions = new HashSet<>();
        try {
            ScanResponse response = client.scan(request);
            response.items().forEach(item -> {
                Institution institution = Institution.builder()
                    .withUuid(item.get("uuid").s())
                    .withName(item.get("name").s())
                    .withContactNumber(item.get("contactNumber").s())
                    .withStreetAddress(gson.fromJson(item.get("streetAddress").s(), StreetAddress.class))
                    .withEmailAddress(item.get("emailAddress").s())
                    .build();

                institutions.add(institution);
            });

            return institutions;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve institutions: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Institution retrieveInstitution(final String institutionUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(institutionUuid).build());

        GetItemRequest request = GetItemRequest.builder()
            .tableName(INSTITUTION_TABLE_NAME)
            .key(key)
            .attributesToGet(INSTITUTION_COLUMN_NAMES)
            .build();

        try {
            Map<String, AttributeValue> item = client.getItem(request).item();

            if (item != null && !item.isEmpty()) {
                return Institution.builder()
                    .withUuid(item.get("uuid").s())
                    .withName(item.get("name").s())
                    .withContactNumber(item.get("contactNumber").s())
                    .withStreetAddress(gson.fromJson(item.get("streetAddress").s(), StreetAddress.class))
                    .withEmailAddress(item.get("emailAddress").s())
                    .build();
            } else {
                LOGGER.error("Failed to retrieve institution: Institution with UUID [{}] does not exist",
                    institutionUuid);

                return null;
            }
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve institution: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Institution updateInstitution(Institution institution) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(institution.getUuid()).build());

        Map<String, AttributeValueUpdate> updatedValues = new HashMap<>();
        if (StringUtils.isNotBlank(institution.getName())) {
            updatedValues.put("name", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(institution.getName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(institution.getContactNumber())) {
            updatedValues.put("contactNumber", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(institution.getContactNumber()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (institution.getStreetAddress() != null) {
            updatedValues.put("streetAddress", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(institution.getStreetAddress())).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(institution.getEmailAddress())) {
            updatedValues.put("emailAddress", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(institution.getEmailAddress()).build())
                .action(AttributeAction.PUT)
                .build());
        }

        UpdateItemRequest request = UpdateItemRequest.builder()
            .tableName(INSTITUTION_TABLE_NAME)
            .key(key)
            .attributeUpdates(updatedValues)
            .build();

        try {
            client.updateItem(request);

            return retrieveInstitution(institution.getUuid());
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to update institution: Institution with UUID [{}] does not exist",
                institution.getUuid());

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to update institution: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public boolean deleteInstitution(final String institutionUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(institutionUuid).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
            .tableName(INSTITUTION_TABLE_NAME)
            .key(key)
            .build();

        try {
            client.deleteItem(request);

            return true;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to delete institution: Institution with UUID [{}] does not exist", institutionUuid);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to delete institution: [{}]", e.getMessage(), e);

            throw e;
        }
    }
}
