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
package org.vincenzolabs.openmpis.agency.dao;

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
import org.vincenzolabs.openmpis.agency.dao.impl.AgencyDAODynamoDBImpl;
import org.vincenzolabs.openmpis.domain.Agency;
import org.vincenzolabs.openmpis.domain.StreetAddress;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

/**
 * The test case for {@link AgencyDAO}.
 *
 * @author Rey Vincent Babilonia
 */
@ExtendWith(DynamoDB.class)
class AgencyDAOTest {

    private static final String AGENCY_TABLE_NAME = "agency";

    @AWSClient(endpoint = Endpoint.class)
    private DynamoDbClient client;

    private AgencyDAO agencyDAO;

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

        agencyDAO = new AgencyDAODynamoDBImpl(client, new GsonBuilder().create());
    }

    @AfterEach
    void tearDown() {
        DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder()
            .tableName(AGENCY_TABLE_NAME)
            .build();

        client.deleteTable(deleteTableRequest);
    }

    @Test
    void createAgency() {
        Agency agency = agencyDAO.createAgency("PNP Regional Office 5", "+63 52 820 6460", StreetAddress.builder()
            .withStreetNumber("Camp Gen. Simeo A. Ola")
            .withCity("Legazpi City")
            .withProvince("Albay")
            .withCountry("Philippines")
            .build());

        assertThat(agency.getUuid()).isNotNull();
        assertThat(agency)
            .extracting("name", "contactNumber")
            .containsExactly("PNP Regional Office 5", "+63 52 820 6460");
        assertThat(agency.getStreetAddress())
            .extracting("streetNumber", "suburb", "city", "province", "country")
            .containsExactly("Camp Gen. Simeo A. Ola", null, "Legazpi City", "Albay", "Philippines");
    }

    @Test
    void retrieveAgency() {
        Agency agency = agencyDAO.createAgency("PNP Regional Office 5", "+63 52 820 6460", StreetAddress.builder()
            .withStreetNumber("Camp Gen. Simeo A. Ola")
            .withCity("Legazpi City")
            .withProvince("Albay")
            .withCountry("Philippines")
            .build());

        Agency actual = agencyDAO.retrieveAgency(agency.getUuid());

        assertThat(actual)
            .extracting("name", "contactNumber")
            .containsExactly("PNP Regional Office 5", "+63 52 820 6460");
        assertThat(actual.getStreetAddress())
            .extracting("streetNumber", "suburb", "city", "province", "country")
            .containsExactly("Camp Gen. Simeo A. Ola", null, "Legazpi City", "Albay", "Philippines");
    }

    @Test
    void retrieveNonExistentAgency() {
        assertThat(agencyDAO.retrieveAgency("uuid")).isNull();
    }

    @Test
    void retrieveAgencies() {
        agencyDAO.createAgency("PNP Regional Office 5", "+63 52 820 6460", StreetAddress.builder()
            .withStreetNumber("Camp Gen. Simeo A. Ola")
            .withCity("Legazpi City")
            .withProvince("Albay")
            .withCountry("Philippines")
            .build());

        Set<Agency> actual = agencyDAO.retrieveAgencies();

        assertThat(actual)
            .hasSize(1)
            .first()
            .extracting("name", "contactNumber")
            .containsExactly("PNP Regional Office 5", "+63 52 820 6460");
        assertThat(actual)
            .first()
            .extracting("streetAddress.streetNumber", "streetAddress.suburb", "streetAddress.city",
                "streetAddress.province", "streetAddress.country")
            .containsExactly("Camp Gen. Simeo A. Ola", null, "Legazpi City", "Albay", "Philippines");
    }

    @Test
    void retrieveNonExistentAgencies() {
        assertThat(agencyDAO.retrieveAgencies()).isEmpty();
    }

    @Test
    void updateAgency() {
        Agency agency = agencyDAO.createAgency("PNP Regional Office 5", "+63 52 820 6460", StreetAddress.builder()
            .withStreetNumber("Camp Gen. Simeo A. Ola")
            .withCity("Legazpi City")
            .withProvince("Albay")
            .withCountry("Philippines")
            .build());

        Agency actual = agencyDAO.updateAgency(Agency.builder()
            .withUuid(agency.getUuid())
            .withName(agency.getName())
            .withContactNumber(agency.getContactNumber())
            .withStreetAddress(StreetAddress.builder()
                .withStreetNumber(agency.getStreetAddress().getStreetNumber())
                .withSuburb("Sagpon")
                .withCity(agency.getStreetAddress().getCity())
                .withProvince(agency.getStreetAddress().getProvince())
                .withCountry(agency.getStreetAddress().getCountry())
                .build())
            .build());

        assertThat(actual)
            .extracting("name", "contactNumber")
            .containsExactly("PNP Regional Office 5", "+63 52 820 6460");
        assertThat(actual.getStreetAddress())
            .extracting("streetNumber", "suburb", "city", "province", "country")
            .containsExactly("Camp Gen. Simeo A. Ola", "Sagpon", "Legazpi City", "Albay", "Philippines");
    }

    @Test
    void deleteAgency() {
        Agency agency = agencyDAO.createAgency("PNP Regional Office 5", "+63 52 820 6460", StreetAddress.builder()
            .withStreetNumber("Camp Gen. Simeo A. Ola")
            .withCity("Legazpi City")
            .withProvince("Albay")
            .withCountry("Philippines")
            .build());

        assertThat(agencyDAO.deleteAgency(agency.getUuid())).isTrue();
        assertThat(agencyDAO.retrieveAgency(agency.getUuid())).isNull();
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
