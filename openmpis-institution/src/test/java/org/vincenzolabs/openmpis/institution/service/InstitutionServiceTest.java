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
package org.vincenzolabs.openmpis.institution.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import by.dev.madhead.aws_junit5.dynamo.v2.DynamoDB;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.vincenzolabs.openmpis.domain.Institution;
import org.vincenzolabs.openmpis.domain.StreetAddress;
import org.vincenzolabs.openmpis.institution.dao.InstitutionDAO;
import org.vincenzolabs.openmpis.institution.dao.impl.InstitutionDAODynamoDBImpl;
import org.vincenzolabs.openmpis.institution.service.impl.InstitutionServiceImpl;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

/**
 * The test case for {@link InstitutionService}.
 *
 * @author Rey Vincent Babilonia
 */
@ExtendWith(DynamoDB.class)
class InstitutionServiceTest {

    private static final String AGENCY_TABLE_NAME = "institution";

    @AWSClient(endpoint = Endpoint.class)
    private DynamoDbClient client;

    private InstitutionService institutionService;

    @BeforeEach
    void setUp() {
        CreateTableRequest createTableRequest = CreateTableRequest.builder()
            .tableName(AGENCY_TABLE_NAME)
            .keySchema(KeySchemaElement.builder()
                .attributeName("uuid")
                .keyType(KeyType.HASH)
                .build())
            .attributeDefinitions(AttributeDefinition.builder()
                .attributeName("uuid")
                .attributeType(ScalarAttributeType.S)
                .build())
            .provisionedThroughput(ProvisionedThroughput.builder()
                .readCapacityUnits(5L)
                .writeCapacityUnits(5L)
                .build())
            .build();

        client.createTable(createTableRequest);

        InstitutionDAO institutionDAO = new InstitutionDAODynamoDBImpl(client, new GsonBuilder().create());

        institutionService = new InstitutionServiceImpl(institutionDAO);
    }

    @AfterEach
    void tearDown() {
        DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder()
            .tableName(AGENCY_TABLE_NAME)
            .build();

        client.deleteTable(deleteTableRequest);
    }

    @Test
    void createInstitution() {
        Institution institution = institutionService.createInstitution(
            "The Foundation Of Fatima Center For Human Development Inc.",
            "+63 54 299 2289",
            StreetAddress.builder()
                .withStreetNumber("Pinaglabanan St")
                .withSuburb("San Agustin")
                .withCity("Iriga City")
                .withProvince("Camarines Sur")
                .withCountry("Philippines")
                .build(),
            "rvbabilonia@gmail.com");

        assertThat(institution.getUuid()).isNotNull();
        assertThat(institution)
            .extracting("name", "contactNumber", "emailAddress")
            .containsExactly("The Foundation Of Fatima Center For Human Development Inc.", "+63 54 299 2289",
                "rvbabilonia@gmail.com");
        assertThat(institution.getStreetAddress())
            .extracting("streetNumber", "suburb", "city", "province", "country")
            .containsExactly("Pinaglabanan St", "San Agustin", "Iriga City", "Camarines Sur", "Philippines");
    }

    @Test
    void retrieveInstitution() {
        Institution institution = institutionService.createInstitution(
            "The Foundation Of Fatima Center For Human Development Inc.",
            "+63 54 299 2289",
            StreetAddress.builder()
                .withStreetNumber("Pinaglabanan St")
                .withSuburb("San Agustin")
                .withCity("Iriga City")
                .withProvince("Camarines Sur")
                .withCountry("Philippines")
                .build(),
            "rvbabilonia@gmail.com");

        Institution actual = institutionService.retrieveInstitution(institution.getUuid());

        assertThat(actual)
            .extracting("name", "contactNumber", "emailAddress")
            .containsExactly("The Foundation Of Fatima Center For Human Development Inc.", "+63 54 299 2289",
                "rvbabilonia@gmail.com");
        assertThat(actual.getStreetAddress())
            .extracting("streetNumber", "suburb", "city", "province", "country")
            .containsExactly("Pinaglabanan St", "San Agustin", "Iriga City", "Camarines Sur", "Philippines");
    }

    @Test
    void retrieveNonExistentInstitution() {
        assertThat(institutionService.retrieveInstitution("uuid")).isNull();
    }

    @Test
    void retrieveAgencies() {
        institutionService.createInstitution(
            "The Foundation Of Fatima Center For Human Development Inc.",
            "+63 54 299 2289",
            StreetAddress.builder()
                .withStreetNumber("Pinaglabanan St")
                .withSuburb("San Agustin")
                .withCity("Iriga City")
                .withProvince("Camarines Sur")
                .withCountry("Philippines")
                .build(),
            "rvbabilonia@gmail.com");

        Set<Institution> actual = institutionService.retrieveInstitutions();

        assertThat(actual)
            .hasSize(1)
            .first()
            .extracting("name", "contactNumber", "emailAddress")
            .containsExactly("The Foundation Of Fatima Center For Human Development Inc.", "+63 54 299 2289",
                "rvbabilonia@gmail.com");
        assertThat(actual)
            .first()
            .extracting("streetAddress.streetNumber", "streetAddress.suburb", "streetAddress.city",
                "streetAddress.province", "streetAddress.country")
            .containsExactly("Pinaglabanan St", "San Agustin", "Iriga City", "Camarines Sur", "Philippines");
    }

    @Test
    void retrieveNonExistentAgencies() {
        assertThat(institutionService.retrieveInstitutions()).isEmpty();
    }

    @Test
    void updateInstitution() {
        Institution institution = institutionService.createInstitution(
            "The Foundation Of Fatima Center For Human Development Inc.",
            "+63 54 299 2289",
            StreetAddress.builder()
                .withStreetNumber("Pinaglabanan St")
                .withSuburb("San Agustin")
                .withCity("Iriga City")
                .withProvince("Camarines Sur")
                .withCountry("Philippines")
                .build(),
            "rvbabilonia@gmail.com");

        Institution actual = institutionService.updateInstitution(Institution.builder()
            .withUuid(institution.getUuid())
            .withName(institution.getName())
            .withContactNumber(institution.getContactNumber())
            .withStreetAddress(StreetAddress.builder()
                .withStreetNumber("55 Pinaglabanan St.")
                .withSuburb(institution.getStreetAddress().getSuburb())
                .withCity(institution.getStreetAddress().getCity())
                .withProvince(institution.getStreetAddress().getProvince())
                .withCountry(institution.getStreetAddress().getCountry())
                .build())
            .withEmailAddress(institution.getEmailAddress())
            .build());

        assertThat(actual)
            .extracting("name", "contactNumber", "emailAddress")
            .containsExactly("The Foundation Of Fatima Center For Human Development Inc.", "+63 54 299 2289",
                "rvbabilonia@gmail.com");
        assertThat(actual.getStreetAddress())
            .extracting("streetNumber", "suburb", "city", "province", "country")
            .containsExactly("55 Pinaglabanan St.", "San Agustin", "Iriga City", "Camarines Sur", "Philippines");
    }

    @Test
    void deleteInstitution() {
        Institution institution = institutionService.createInstitution(
            "The Foundation Of Fatima Center For Human Development Inc.",
            "+63 54 299 2289",
            StreetAddress.builder()
                .withStreetNumber("Pinaglabanan St")
                .withSuburb("San Agustin")
                .withCity("Iriga City")
                .withProvince("Camarines Sur")
                .withCountry("Philippines")
                .build(),
            "rvbabilonia@gmail.com");

        assertThat(institutionService.deleteInstitution(institution.getUuid())).isTrue();
        assertThat(institutionService.retrieveInstitution(institution.getUuid())).isNull();
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
