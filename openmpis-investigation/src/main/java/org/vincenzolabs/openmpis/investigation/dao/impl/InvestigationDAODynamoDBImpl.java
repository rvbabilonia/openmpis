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
package org.vincenzolabs.openmpis.investigation.dao.impl;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.vincenzolabs.openmpis.domain.Investigation;
import org.vincenzolabs.openmpis.domain.Report;
import org.vincenzolabs.openmpis.enumeration.CaseStatus;
import org.vincenzolabs.openmpis.enumeration.CaseType;
import org.vincenzolabs.openmpis.enumeration.Relationship;
import org.vincenzolabs.openmpis.investigation.dao.InvestigationDAO;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;
import software.amazon.awssdk.services.dynamodb.model.ReturnValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;

/**
 * The DynamoDB implementation of {@link InvestigationDAO}.
 *
 * @author Rey Vincent Babilonia
 */
@Repository
public class InvestigationDAODynamoDBImpl
    implements InvestigationDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvestigationDAODynamoDBImpl.class);

    private static final String INVESTIGATION_TABLE_NAME = "investigation";

    private static final String[] INVESTIGATION_COLUMN_NAMES = { "uuid", "creationDate", "personUuid",
        "contactPersonUuid", "encoderUuid", "abductorUuids", "agencyUuid", "relationshipToContactPerson",
        "relationshipToAbductors", "caseStatus", "caseType", "circumstance", "reward", "investigatorUuid",
        "reports", "tips" };

    private static final Type REPORT_TYPE = new TypeToken<Set<Report>>() {
    }.getType();

    private static final Type TIP_TYPE = new TypeToken<Set<Report>>() {
    }.getType();

    private final DynamoDbClient client;

    private final Gson gson;

    /**
     * Default constructor.
     *
     * @param client the {@link DynamoDbClient}
     * @param gson   the {@link Gson}
     */
    public InvestigationDAODynamoDBImpl(DynamoDbClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @Override
    public Investigation createInvestigation(Investigation investigation, ZoneId zoneId) {
        Investigation newInvestigation = Investigation.builder(investigation)
            .withUuid(UUID.randomUUID().toString())
            .withCreationDate(OffsetDateTime.now(zoneId))
            .withCaseStatus(CaseStatus.OPEN)
            .build();

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("uuid", AttributeValue.builder().s(newInvestigation.getUuid()).build());
        item.put("creationDate", AttributeValue.builder().s(newInvestigation.getCreationDate().toString()).build());
        item.put("caseStatus", AttributeValue.builder().s(newInvestigation.getCaseStatus().name()).build());
        if (StringUtils.isNotBlank(newInvestigation.getPersonUuid())) {
            item.put("personUuid", AttributeValue.builder().s(newInvestigation.getPersonUuid()).build());
        }
        if (StringUtils.isNotBlank(newInvestigation.getContactPersonUuid())) {
            item.put("contactPersonUuid", AttributeValue.builder().s(newInvestigation.getContactPersonUuid()).build());
        }
        if (StringUtils.isNotBlank(newInvestigation.getEncoderUuid())) {
            item.put("encoderUuid", AttributeValue.builder().s(newInvestigation.getEncoderUuid()).build());
        }
        if (newInvestigation.getAbductorUuids() != null && !newInvestigation.getAbductorUuids().isEmpty()) {
            item.put("abductorUuids", AttributeValue.builder().ss(newInvestigation.getAbductorUuids()).build());
        }
        if (StringUtils.isNotBlank(newInvestigation.getAgencyUuid())) {
            item.put("agencyUuid", AttributeValue.builder().s(newInvestigation.getAgencyUuid()).build());
        }
        if (StringUtils.isNotBlank(newInvestigation.getCircumstance())) {
            item.put("circumstance", AttributeValue.builder().s(newInvestigation.getCircumstance()).build());
        }
        if (newInvestigation.getReward() != null) {
            item.put("reward", AttributeValue.builder().s(newInvestigation.getReward().toString()).build());
        }
        if (StringUtils.isNotBlank(newInvestigation.getInvestigatorUuid())) {
            item.put("investigatorUuid", AttributeValue.builder().s(newInvestigation.getInvestigatorUuid()).build());
        }
        if (StringUtils.isNotBlank(newInvestigation.getAgencyUuid())) {
            item.put("agencyUuid", AttributeValue.builder().s(newInvestigation.getAgencyUuid()).build());
        }
        if (newInvestigation.getRelationshipToContactPerson() != null) {
            item.put("relationshipToContactPerson",
                AttributeValue.builder()
                    .s(newInvestigation.getRelationshipToContactPerson().name())
                    .build());
        }
        if (newInvestigation.getRelationshipToAbductors() != null) {
            item.put("relationshipToAbductors",
                AttributeValue.builder()
                    .s(newInvestigation.getRelationshipToAbductors().name())
                    .build());
        }
        if (newInvestigation.getCaseType() != null) {
            item.put("caseType", AttributeValue.builder().s(newInvestigation.getCaseType().name()).build());
        }

        PutItemRequest request = PutItemRequest.builder()
            .tableName(INVESTIGATION_TABLE_NAME)
            .item(item)
            .build();

        try {
            client.putItem(request);

            return newInvestigation;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to create investigation: Table [{}] does not exist", INVESTIGATION_TABLE_NAME);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to create investigation: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Set<Investigation> retrieveInvestigations() {
        ScanRequest request = ScanRequest.builder()
            .tableName(INVESTIGATION_TABLE_NAME)
            .build();

        Set<Investigation> persons = new HashSet<>();
        try {
            ScanResponse response = client.scan(request);
            response.items().forEach(item -> persons.add(getInvestigation(item.get("uuid").s(), item)));

            return persons;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve persons: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Investigation retrieveInvestigation(final String personUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(personUuid).build());

        GetItemRequest request = GetItemRequest.builder()
            .tableName(INVESTIGATION_TABLE_NAME)
            .key(key)
            .attributesToGet(INVESTIGATION_COLUMN_NAMES)
            .build();

        try {
            Map<String, AttributeValue> item = client.getItem(request).item();

            return getInvestigation(personUuid, item);
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve investigation: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Investigation updateInvestigation(Investigation investigation) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(investigation.getUuid()).build());

        Map<String, AttributeValueUpdate> updatedValues = new HashMap<>();
        if (investigation.getAbductorUuids() != null && !investigation.getAbductorUuids().isEmpty()) {
            updatedValues.put("abductorUuids", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().ss(investigation.getAbductorUuids()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(investigation.getContactPersonUuid())) {
            updatedValues.put("contactPersonUuid", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(investigation.getContactPersonUuid()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(investigation.getAgencyUuid())) {
            updatedValues.put("agencyUuid", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(investigation.getAgencyUuid()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(investigation.getCircumstance())) {
            updatedValues.put("circumstance", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(investigation.getCircumstance()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(investigation.getInvestigatorUuid())) {
            updatedValues.put("investigatorUuid", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(investigation.getInvestigatorUuid()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (investigation.getRelationshipToContactPerson() != null) {
            updatedValues.put("relationshipToContactPerson", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(investigation.getRelationshipToContactPerson().name()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (investigation.getRelationshipToAbductors() != null) {
            updatedValues.put("relationshipToAbductors", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(investigation.getRelationshipToAbductors().name()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (investigation.getCaseStatus() != null) {
            updatedValues.put("caseStatus", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(investigation.getCaseStatus().name()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (investigation.getCaseType() != null) {
            updatedValues.put("caseType", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(investigation.getCaseType().name()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (investigation.getReward() != null) {
            updatedValues.put("reward", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(investigation.getReward().toString()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (investigation.getReports() != null && !investigation.getReports().isEmpty()) {
            updatedValues.put("reports", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(investigation.getReports())).build())
                .action(AttributeAction.ADD)
                .build());
        }
        if (investigation.getTips() != null && !investigation.getTips().isEmpty()) {
            updatedValues.put("tips", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(investigation.getTips())).build())
                .action(AttributeAction.ADD)
                .build());
        }

        UpdateItemRequest request = UpdateItemRequest.builder()
            .tableName(INVESTIGATION_TABLE_NAME)
            .key(key)
            .attributeUpdates(updatedValues)
            .returnValues(ReturnValue.ALL_NEW)
            .build();

        try {
            UpdateItemResponse response = client.updateItem(request);

            return getInvestigation(investigation.getUuid(), response.attributes());
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to update investigation: Investigation with UUID [{}] does not exist",
                investigation.getUuid());

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to update investigation: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public boolean deleteInvestigation(final String personUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(personUuid).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
            .tableName(INVESTIGATION_TABLE_NAME)
            .key(key)
            .build();

        try {
            client.deleteItem(request);

            return true;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to delete investigation: Investigation with UUID [{}] does not exist", personUuid);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to delete investigation: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    private Investigation getInvestigation(String personUuid, Map<String, AttributeValue> item) {
        if (item != null && !item.isEmpty()) {

            Investigation.Builder builder = Investigation.builder()
                .withUuid(getValue(item.get("uuid")))
                .withCreationDate(OffsetDateTime.parse(getValue(item.get("creationDate"))))
                .withPersonUuid(getValue(item.get("personUuid")))
                .withContactPersonUuid(getValue(item.get("contactPersonUuid")))
                .withEncoderUuid(getValue(item.get("encoderUuid")))
                .withAgencyUuid(getValue(item.get("agencyUuid")))
                .withInvestigatorUuid(getValue(item.get("investigatorUuid")))
                .withCircumstance(getValue(item.get("circumstance")));

            if (item.get("relationshipToContactPerson") != null) {
                builder.withRelationshipToContactPerson(
                    Relationship.valueOf(getValue(item.get("relationshipToContactPerson"))));
            }
            if (item.get("relationshipToAbductors") != null) {
                builder.withRelationshipToAbductors(
                    Relationship.valueOf(getValue(item.get("relationshipToAbductors"))));
            }
            if (item.get("caseStatus") != null) {
                builder.withCaseStatus(CaseStatus.valueOf(getValue(item.get("caseStatus"))));
            }
            if (item.get("caseType") != null) {
                builder.withCaseType(CaseType.valueOf(getValue(item.get("caseType"))));
            }
            if (item.get("abductorUuids") != null) {
                builder.withAbductorUuids(new HashSet<>(item.get("abductorUuids").ss()));
            }
            if (item.get("reports") != null) {
                builder.withReports(gson.fromJson(item.get("reports").s(), REPORT_TYPE));
            }
            if (item.get("tips") != null) {
                builder.withTips(gson.fromJson(item.get("tips").s(), TIP_TYPE));
            }
            if (item.get("reward") != null) {
                builder.withReward(Integer.parseInt(getValue(item.get("reward"))));
            }

            return builder.build();
        } else {
            LOGGER.error("Failed to retrieve investigation: Investigation with UUID [{}] does not exist",
                personUuid);

            return null;
        }
    }

    private String getValue(AttributeValue value) {
        return value == null ? null : value.s();
    }
}
