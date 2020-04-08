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
package org.vincenzolabs.openmpis.agency.dao.impl;

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
import org.vincenzolabs.openmpis.agency.dao.AgencyDAO;
import org.vincenzolabs.openmpis.domain.Agency;
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
 * The DynamoDB implementation of {@link AgencyDAO}.
 *
 * @author Rey Vincent Babilonia
 */
@Repository
public class AgencyDAODynamoDBImpl
    implements AgencyDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgencyDAODynamoDBImpl.class);

    private static final String AGENCY_TABLE_NAME = "agency";

    private static final String[] AGENCY_COLUMN_NAMES = {"uuid", "name", "contactNumber", "streetAddress"};

    private final DynamoDbClient client;

    private final Gson gson;

    /**
     * Default constructor.
     *
     * @param client the {@link DynamoDbClient}
     * @param gson   the {@link Gson}
     */
    @Autowired
    public AgencyDAODynamoDBImpl(DynamoDbClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @Override
    public Agency createAgency(final String name, final String contactNumber, StreetAddress streetAddress) {
        Agency agency = Agency.builder()
            .withUuid(UUID.randomUUID().toString())
            .withName(name)
            .withContactNumber(contactNumber)
            .withStreetAddress(streetAddress)
            .build();

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("uuid", AttributeValue.builder().s(agency.getUuid()).build());
        item.put("name", AttributeValue.builder().s(agency.getName()).build());
        item.put("contactNumber", AttributeValue.builder().s(agency.getContactNumber()).build());
        item.put("streetAddress", AttributeValue.builder().s(gson.toJson(agency.getStreetAddress())).build());

        PutItemRequest request = PutItemRequest.builder()
            .tableName(AGENCY_TABLE_NAME)
            .item(item)
            .build();

        try {
            client.putItem(request);

            return agency;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to create agency: Table [{}] does not exist", AGENCY_TABLE_NAME);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to create agency: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Set<Agency> retrieveAgencies() {
        ScanRequest request = ScanRequest.builder()
            .tableName(AGENCY_TABLE_NAME)
            .build();

        Set<Agency> agencies = new HashSet<>();
        try {
            ScanResponse response = client.scan(request);
            response.items().forEach(item -> {
                Agency agency = Agency.builder()
                    .withUuid(item.get("uuid").s())
                    .withName(item.get("name").s())
                    .withContactNumber(item.get("contactNumber").s())
                    .withStreetAddress(gson.fromJson(item.get("streetAddress").s(), StreetAddress.class))
                    .build();

                agencies.add(agency);
            });

            return agencies;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve agencies: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Agency retrieveAgency(final String agencyUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(agencyUuid).build());

        GetItemRequest request = GetItemRequest.builder()
            .tableName(AGENCY_TABLE_NAME)
            .key(key)
            .attributesToGet(AGENCY_COLUMN_NAMES)
            .build();

        try {
            Map<String, AttributeValue> item = client.getItem(request).item();

            if (item != null && !item.isEmpty()) {
                return Agency.builder()
                    .withUuid(item.get("uuid").s())
                    .withName(item.get("name").s())
                    .withContactNumber(item.get("contactNumber").s())
                    .withStreetAddress(gson.fromJson(item.get("streetAddress").s(), StreetAddress.class))
                    .build();
            } else {
                LOGGER.error("Failed to retrieve agency: Agency with UUID [{}] does not exist", agencyUuid);

                return null;
            }
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve agency: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Agency updateAgency(Agency agency) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(agency.getUuid()).build());

        Map<String, AttributeValueUpdate> updatedValues = new HashMap<>();
        if (StringUtils.isNotBlank(agency.getName())) {
            updatedValues.put("name", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(agency.getName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(agency.getContactNumber())) {
            updatedValues.put("contactNumber", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(agency.getContactNumber()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (agency.getStreetAddress() != null) {
            updatedValues.put("streetAddress", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(agency.getStreetAddress())).build())
                .action(AttributeAction.PUT)
                .build());
        }

        UpdateItemRequest request = UpdateItemRequest.builder()
            .tableName(AGENCY_TABLE_NAME)
            .key(key)
            .attributeUpdates(updatedValues)
            .build();

        try {
            client.updateItem(request);

            return retrieveAgency(agency.getUuid());
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to update agency: Agency with UUID [{}] does not exist", agency.getUuid());

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to update agency: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public boolean deleteAgency(final String agencyUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(agencyUuid).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
            .tableName(AGENCY_TABLE_NAME)
            .key(key)
            .build();

        try {
            client.deleteItem(request);

            return true;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to delete agency: Agency with UUID [{}] does not exist", agencyUuid);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to delete agency: [{}]", e.getMessage(), e);

            throw e;
        }
    }
}
