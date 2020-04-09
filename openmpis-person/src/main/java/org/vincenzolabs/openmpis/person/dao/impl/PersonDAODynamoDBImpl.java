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
package org.vincenzolabs.openmpis.person.dao.impl;

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
import org.vincenzolabs.openmpis.person.dao.PersonDAO;
import org.vincenzolabs.openmpis.domain.Person;
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
 * The DynamoDB implementation of {@link PersonDAO}.
 *
 * @author Rey Vincent Babilonia
 */
@Repository
public class PersonDAODynamoDBImpl
    implements PersonDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAODynamoDBImpl.class);

    private static final String PERSON_TABLE_NAME = "person";

    private static final String[] PERSON_COLUMN_NAMES = { "uuid", "firstName", "middleName", "lastName", "nickname",
        "birthDate", "lastSeenOrFoundDate", "lastSeenOrFoundLocation", "possibleLocation", "institutionUuid",
        "photoUuids", "primaryPhotoUuid", "description", "additionalDetails" };

    private final DynamoDbClient client;

    private final Gson gson;

    /**
     * Default constructor.
     *
     * @param client the {@link DynamoDbClient}
     * @param gson   the {@link Gson}
     */
    @Autowired
    public PersonDAODynamoDBImpl(DynamoDbClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    @Override
    public Person createPerson(Person person) {
        Person newPerson = Person.builder()
            .withUuid(UUID.randomUUID().toString())
            .withFirstName(person.getFirstName())
            .withMiddleName(person.getMiddleName())
            .withLastName(person.getLastName())
            .withNickname(person.getNickname())
            .withBirthDate(person.getBirthDate())
            .withLastSeenOrFoundDate(person.getLastSeenOrFoundDate())
            .withLastSeenOrFoundLocation(person.getLastSeenOrFoundLocation())
            .withPossibleLocation(person.getPossibleLocation())
            .withInstitutionUuid(person.getInstitutionUuid())
            .withPhotoUuids(person.getPhotoUuids())
            .withPrimaryPhotoUuid(person.getPrimaryPhotoUuid())
            .withDescription(person.getDescription())
            .withAdditionalDetails(person.getAdditionalDetails())
            .build();

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("uuid", AttributeValue.builder().s(newPerson.getUuid()).build());
        if (StringUtils.isNotBlank(newPerson.getFirstName())) {
            item.put("firstName", AttributeValue.builder().s(newPerson.getFirstName()).build());
        }
        if (StringUtils.isNotBlank(newPerson.getMiddleName())) {
            item.put("middleName", AttributeValue.builder().s(newPerson.getMiddleName()).build());
        }
        if (StringUtils.isNotBlank(newPerson.getLastName())) {
            item.put("lastName", AttributeValue.builder().s(newPerson.getLastName()).build());
        }
        if (StringUtils.isNotBlank(newPerson.getNickname())) {
            item.put("nickname", AttributeValue.builder().s(newPerson.getNickname()).build());
        }
        if (newPerson.getBirthDate() != null) {
            item.put("birthDate", AttributeValue.builder().s(newPerson.getBirthDate().toString()).build());
        }
        if (newPerson.getLastSeenOrFoundDate() != null) {
            item.put("lastSeenOrFoundDate", AttributeValue.builder().s(newPerson.getLastSeenOrFoundDate().toString()).build());
        }
        if (newPerson.getPossibleLocation() != null) {
            item.put("possibleLocation", AttributeValue.builder()
                .s(gson.toJson(newPerson.getPossibleLocation()))
                .build());
        }
        if (StringUtils.isNotBlank(newPerson.getInstitutionUuid())) {
            item.put("institutionUuid", AttributeValue.builder().s(newPerson.getInstitutionUuid()).build());
        }
        if (newPerson.getPhotoUuids() != null && !newPerson.getPhotoUuids().isEmpty()) {
            item.put("photoUuids", AttributeValue.builder().ss(newPerson.getPhotoUuids()).build());
        }
        if (StringUtils.isNotBlank(newPerson.getPrimaryPhotoUuid())) {
            item.put("primaryPhotoUuid", AttributeValue.builder().s(newPerson.getPrimaryPhotoUuid()).build());
        }
        if (newPerson.getDescription() != null) {
            item.put("description", AttributeValue.builder().s(gson.toJson(newPerson.getDescription())).build());
        }
        if (newPerson.getAdditionalDetails() != null) {
            item.put("additionalDetails", AttributeValue.builder()
                .s(gson.toJson(newPerson.getAdditionalDetails()))
                .build());
        }

        PutItemRequest request = PutItemRequest.builder()
            .tableName(PERSON_TABLE_NAME)
            .item(item)
            .build();

        try {
            client.putItem(request);

            return newPerson;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to create person: Table [{}] does not exist", PERSON_TABLE_NAME);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to create person: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Set<Person> retrievePersons() {
        ScanRequest request = ScanRequest.builder()
            .tableName(PERSON_TABLE_NAME)
            .build();

        Set<Person> persons = new HashSet<>();
        try {
            ScanResponse response = client.scan(request);
            response.items().forEach(item -> persons.add(getPerson(item.get("uuid").s(), item)));

            return persons;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve persons: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Person retrievePerson(final String personUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(personUuid).build());

        GetItemRequest request = GetItemRequest.builder()
            .tableName(PERSON_TABLE_NAME)
            .key(key)
            .attributesToGet(PERSON_COLUMN_NAMES)
            .build();

        try {
            Map<String, AttributeValue> item = client.getItem(request).item();

            return getPerson(personUuid, item);
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to retrieve person: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public Person updatePerson(Person person) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(person.getUuid()).build());

        Map<String, AttributeValueUpdate> updatedValues = new HashMap<>();
        if (StringUtils.isNotBlank(person.getFirstName())) {
            updatedValues.put("firstName", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(person.getFirstName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(person.getMiddleName())) {
            updatedValues.put("middleName", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(person.getMiddleName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(person.getLastName())) {
            updatedValues.put("lastName", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(person.getLastName()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(person.getNickname())) {
            updatedValues.put("nickname", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(person.getNickname()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (person.getBirthDate() != null) {
            updatedValues.put("birthDate", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(person.getBirthDate().toString()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (person.getLastSeenOrFoundDate() != null) {
            updatedValues.put("lastSeenOrFoundDate", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(person.getLastSeenOrFoundDate().toString()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (person.getLastSeenOrFoundLocation() != null) {
            updatedValues.put("lastSeenOrFoundLocation", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(person.getLastSeenOrFoundLocation())).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (person.getPossibleLocation() != null) {
            updatedValues.put("possibleLocation", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(person.getPossibleLocation())).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(person.getInstitutionUuid())) {
            updatedValues.put("institutionUuid", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(person.getInstitutionUuid()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (!person.getPhotoUuids().isEmpty()) {
            updatedValues.put("photoUuids", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().ss(person.getPhotoUuids()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (StringUtils.isNotBlank(person.getPrimaryPhotoUuid())) {
            updatedValues.put("primaryPhotoUuid", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(person.getPrimaryPhotoUuid()).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (person.getDescription() != null) {
            updatedValues.put("description", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(person.getDescription())).build())
                .action(AttributeAction.PUT)
                .build());
        }
        if (person.getAdditionalDetails() != null) {
            updatedValues.put("additionalDetails", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(gson.toJson(person.getAdditionalDetails())).build())
                .action(AttributeAction.PUT)
                .build());
        }

        UpdateItemRequest request = UpdateItemRequest.builder()
            .tableName(PERSON_TABLE_NAME)
            .key(key)
            .attributeUpdates(updatedValues)
            .build();

        try {
            client.updateItem(request);

            return retrievePerson(person.getUuid());
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to update person: Person with UUID [{}] does not exist",
                person.getUuid());

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to update person: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    @Override
    public boolean deletePerson(final String personUuid) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("uuid", AttributeValue.builder().s(personUuid).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
            .tableName(PERSON_TABLE_NAME)
            .key(key)
            .build();

        try {
            client.deleteItem(request);

            return true;
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to delete person: Person with UUID [{}] does not exist", personUuid);

            throw e;
        } catch (DynamoDbException e) {
            LOGGER.error("Failed to delete person: [{}]", e.getMessage(), e);

            throw e;
        }
    }

    private Person getPerson(String personUuid, Map<String, AttributeValue> item) {
        if (item != null && !item.isEmpty()) {
            Person.Builder builder = Person.builder()
                .withUuid(getValue(item.get("uuid")))
                .withFirstName(getValue(item.get("firstName")))
                .withMiddleName(getValue(item.get("middleName")))
                .withLastName(getValue(item.get("lastName")))
                .withNickname(getValue(item.get("nickname")))
                .withInstitutionUuid(getValue(item.get("institutionUuid")))
                .withPrimaryPhotoUuid(getValue(item.get("primaryPhotoUuid")));

            if (item.get("photoUuids") != null) {
                builder.withPhotoUuids(new HashSet<>(item.get("photoUuids").ss()));
            }
            if (item.get("birthDate") != null) {
                builder.withBirthDate(LocalDate.parse(getValue(item.get("birthDate"))));
            }
            if (item.get("lastSeenOrFoundDate") != null) {
                builder.withLastSeenOrFoundDate(OffsetDateTime.parse(getValue(item.get("lastSeenOrFoundDate"))));
            }
            if (item.get("lastSeenOrFoundLocation") != null) {
                builder.withLastSeenOrFoundLocation(gson.fromJson(getValue(item.get("lastSeenOrFoundLocation")),
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
            LOGGER.error("Failed to retrieve person: Person with UUID [{}] does not exist",
                personUuid);

            return null;
        }
    }

    private String getValue(AttributeValue value) {
        return value == null ? null : value.s();
    }
}
