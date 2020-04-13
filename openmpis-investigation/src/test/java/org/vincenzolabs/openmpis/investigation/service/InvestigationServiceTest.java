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
package org.vincenzolabs.openmpis.investigation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Set;
import java.util.UUID;

import by.dev.madhead.aws_junit5.common.AWSClient;
import by.dev.madhead.aws_junit5.common.AWSEndpoint;
import by.dev.madhead.aws_junit5.dynamo.v2.DynamoDB;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.vincenzolabs.openmpis.domain.Investigation;
import org.vincenzolabs.openmpis.domain.Report;
import org.vincenzolabs.openmpis.enumeration.CaseStatus;
import org.vincenzolabs.openmpis.enumeration.CaseType;
import org.vincenzolabs.openmpis.enumeration.Relationship;
import org.vincenzolabs.openmpis.investigation.dao.InvestigationDAO;
import org.vincenzolabs.openmpis.investigation.dao.impl.InvestigationDAODynamoDBImpl;
import org.vincenzolabs.openmpis.investigation.service.impl.InvestigationServiceImpl;
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
 * The test case for {@link InvestigationService}.
 *
 * @author Rey Vincent Babilonia
 */
@ExtendWith(DynamoDB.class)
class InvestigationServiceTest {

    private static final String INVESTIGATION_TABLE_NAME = "investigation";

    @AWSClient(endpoint = Endpoint.class)
    private DynamoDbClient client;

    private InvestigationService investigationService;

    @BeforeEach
    void setUp() {
        CreateTableRequest createTableRequest = CreateTableRequest.builder()
            .tableName(INVESTIGATION_TABLE_NAME)
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

        InvestigationDAO investigationDAO = new InvestigationDAODynamoDBImpl(client, new GsonBuilder().create());

        investigationService = new InvestigationServiceImpl(investigationDAO);
    }

    @AfterEach
    void tearDown() {
        DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder()
            .tableName(INVESTIGATION_TABLE_NAME)
            .build();

        client.deleteTable(deleteTableRequest);
    }

    @Test
    void createInvestigation() {
        String personUuid = UUID.randomUUID().toString();
        String contactPersonUuid = UUID.randomUUID().toString();
        String encoderUuid = UUID.randomUUID().toString();
        Set<String> abductorUuids = Set.of(UUID.randomUUID().toString());
        String agencyUuid = UUID.randomUUID().toString();
        Relationship relationshipToContactPerson = Relationship.MOTHER;
        Relationship relationshipToAbductors = Relationship.FATHER;
        CaseType caseType = CaseType.FAMILY_ABDUCTION;
        String circumstance = "Father took the daughter last 4 April 2020 from their house";
        Integer reward = 10000;

        Investigation investigation = investigationService.createInvestigation(Investigation.builder()
                .withPersonUuid(personUuid)
                .withContactPersonUuid(contactPersonUuid)
                .withEncoderUuid(encoderUuid)
                .withAbductorUuids(abductorUuids)
                .withAgencyUuid(agencyUuid)
                .withRelationshipToAbductors(relationshipToAbductors)
                .withRelationshipToContactPerson(relationshipToContactPerson)
                .withCaseType(caseType)
                .withCircumstance(circumstance)
                .withReward(reward)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        assertThat(investigation.getUuid()).isNotBlank();
        assertThat(investigation.getCreationDate()).isBefore(OffsetDateTime.now(ZoneId.of("Pacific/Auckland")));
        assertThat(investigation)
            .extracting("caseStatus", "personUuid", "contactPersonUuid", "encoderUuid", "investigatorUuid",
                "agencyUuid", "circumstance", "reward", "relationshipToContactPerson", "relationshipToAbductors",
                "caseType", "abductorUuids")
            .containsExactly(CaseStatus.OPEN, personUuid, contactPersonUuid, encoderUuid, null,
                agencyUuid, circumstance, reward, relationshipToContactPerson, relationshipToAbductors,
                caseType, abductorUuids);
    }

    @Test
    void createInvestigationWithPersonUuid() {
        IllegalArgumentException exception = catchThrowableOfType(() -> investigationService.createInvestigation(
            Investigation.builder().build(), ZoneId.of("Pacific/Auckland")), IllegalArgumentException.class);

        assertThat(exception)
            .hasMessage("Person UUID must not be blank");
    }

    @Test
    void retrieveInvestigation() {
        String personUuid = UUID.randomUUID().toString();
        String contactPersonUuid = UUID.randomUUID().toString();
        String encoderUuid = UUID.randomUUID().toString();
        String investigatorUuid = UUID.randomUUID().toString();
        Set<String> abductorUuids = Set.of(UUID.randomUUID().toString());
        String agencyUuid = UUID.randomUUID().toString();
        Relationship relationshipToContactPerson = Relationship.WIFE;
        CaseType caseType = CaseType.NON_FAMILY_ABDUCTION;
        String circumstance = "Husband did not return home";
        Integer reward = 10000;

        Investigation investigation = investigationService.createInvestigation(Investigation.builder()
                .withPersonUuid(personUuid)
                .withContactPersonUuid(contactPersonUuid)
                .withEncoderUuid(encoderUuid)
                .withInvestigatorUuid(investigatorUuid)
                .withAbductorUuids(abductorUuids)
                .withAgencyUuid(agencyUuid)
                .withRelationshipToContactPerson(relationshipToContactPerson)
                .withCaseType(caseType)
                .withCircumstance(circumstance)
                .withReward(reward)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        Investigation actual = investigationService.retrieveInvestigation(investigation.getUuid());

        assertThat(actual.getCreationDate()).isBefore(OffsetDateTime.now(ZoneId.of("Pacific/Auckland")));
        assertThat(actual)
            .extracting("caseStatus", "personUuid", "contactPersonUuid", "encoderUuid", "investigatorUuid",
                "agencyUuid", "circumstance", "reward", "relationshipToContactPerson", "relationshipToAbductors",
                "caseType", "abductorUuids")
            .containsExactly(CaseStatus.OPEN, personUuid, contactPersonUuid, encoderUuid, investigatorUuid,
                agencyUuid, circumstance, reward, relationshipToContactPerson, null,
                caseType, abductorUuids);
    }

    @Test
    void retrieveNonExistentInvestigation() {
        assertThat(investigationService.retrieveInvestigation("uuid")).isNull();
    }

    @Test
    void retrieveInvestigations() {
        String personUuid = UUID.randomUUID().toString();
        String contactPersonUuid = UUID.randomUUID().toString();
        String encoderUuid = UUID.randomUUID().toString();
        String investigatorUuid = UUID.randomUUID().toString();
        Set<String> abductorUuids = Set.of(UUID.randomUUID().toString());
        String agencyUuid = UUID.randomUUID().toString();
        Relationship relationshipToContactPerson = Relationship.WIFE;
        CaseType caseType = CaseType.NON_FAMILY_ABDUCTION;
        String circumstance = "Husband did not return home";
        Integer reward = 10000;

        investigationService.createInvestigation(Investigation.builder()
                .withPersonUuid(personUuid)
                .withContactPersonUuid(contactPersonUuid)
                .withEncoderUuid(encoderUuid)
                .withInvestigatorUuid(investigatorUuid)
                .withAbductorUuids(abductorUuids)
                .withAgencyUuid(agencyUuid)
                .withRelationshipToContactPerson(relationshipToContactPerson)
                .withCaseType(caseType)
                .withCircumstance(circumstance)
                .withReward(reward)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        Set<Investigation> actual = investigationService.retrieveInvestigations();

        assertThat(actual)
            .hasSize(1)
            .first()
            .extracting("caseStatus", "personUuid", "contactPersonUuid", "encoderUuid", "investigatorUuid",
                "agencyUuid", "circumstance", "reward", "relationshipToContactPerson", "relationshipToAbductors",
                "caseType", "abductorUuids")
            .containsExactly(CaseStatus.OPEN, personUuid, contactPersonUuid, encoderUuid, investigatorUuid,
                agencyUuid, circumstance, reward, relationshipToContactPerson, null,
                caseType, abductorUuids);
    }

    @Test
    void retrieveNonExistentAgencies() {
        assertThat(investigationService.retrieveInvestigations()).isEmpty();
    }

    @Test
    void updateInvestigation() {
        String personUuid = UUID.randomUUID().toString();
        String contactPersonUuid = UUID.randomUUID().toString();
        String encoderUuid = UUID.randomUUID().toString();
        Set<String> abductorUuids = Set.of(UUID.randomUUID().toString());
        String agencyUuid = UUID.randomUUID().toString();
        Relationship relationshipToContactPerson = Relationship.MOTHER;
        Relationship relationshipToAbductors = Relationship.FATHER;
        CaseType caseType = CaseType.FAMILY_ABDUCTION;
        String circumstance = "Father took the daughter last 4 April 2020 from their house";
        Integer reward = 10000;
        String investigatorUuid = UUID.randomUUID().toString();
        String reportUuid = UUID.randomUUID().toString();

        Investigation investigation = investigationService.createInvestigation(Investigation.builder()
                .withPersonUuid(personUuid)
                .withContactPersonUuid(contactPersonUuid)
                .withEncoderUuid(encoderUuid)
                .withAbductorUuids(abductorUuids)
                .withAgencyUuid(agencyUuid)
                .withRelationshipToAbductors(relationshipToAbductors)
                .withRelationshipToContactPerson(relationshipToContactPerson)
                .withCaseType(caseType)
                .withCircumstance(circumstance)
                .withReward(reward)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        OffsetDateTime now = OffsetDateTime.now();
        Investigation actual = investigationService.updateInvestigation(Investigation.builder()
            .withUuid(investigation.getUuid())
            .withPersonUuid(personUuid)
            .withContactPersonUuid(contactPersonUuid)
            .withEncoderUuid(encoderUuid)
            .withAbductorUuids(abductorUuids)
            .withAgencyUuid(agencyUuid)
            .withRelationshipToAbductors(relationshipToAbductors)
            .withRelationshipToContactPerson(relationshipToContactPerson)
            .withCaseType(caseType)
            .withInvestigatorUuid(investigatorUuid)
            .withCaseStatus(CaseStatus.CLOSED)
            .withCircumstance(circumstance)
            .withReward(reward)
            .withReports(Set.of(Report.builder()
                .withUuid(reportUuid)
                .withCreationDate(now)
                .withIpAddress("127.0.0.1")
                .withReport("Person was found")
                .withInvestigatorUuid(investigatorUuid)
                .build()))
            .build());

        assertThat(actual.getCreationDate()).isBefore(OffsetDateTime.now(ZoneId.of("Pacific/Auckland")));
        assertThat(actual)
            .extracting("caseStatus", "personUuid", "contactPersonUuid", "encoderUuid", "investigatorUuid",
                "agencyUuid", "circumstance", "reward", "relationshipToContactPerson", "relationshipToAbductors",
                "caseType", "abductorUuids")
            .containsExactly(CaseStatus.CLOSED, personUuid, contactPersonUuid, encoderUuid, investigatorUuid,
                agencyUuid, circumstance, reward, relationshipToContactPerson, relationshipToAbductors,
                caseType, abductorUuids);
        assertThat(actual.getTips()).isEmpty();
        assertThat(actual.getReports())
            .hasSize(1)
            .first()
            .extracting("uuid", "creationDate", "ipAddress", "report", "investigatorUuid")
            .containsExactly(reportUuid, now, "127.0.0.1", "Person was found", investigatorUuid);
    }

    @Test
    void deleteInvestigation() {
        String personUuid = UUID.randomUUID().toString();
        String contactPersonUuid = UUID.randomUUID().toString();
        String encoderUuid = UUID.randomUUID().toString();
        Set<String> abductorUuids = Set.of(UUID.randomUUID().toString());
        String agencyUuid = UUID.randomUUID().toString();
        Relationship relationshipToContactPerson = Relationship.MOTHER;
        Relationship relationshipToAbductors = Relationship.FATHER;
        CaseType caseType = CaseType.FAMILY_ABDUCTION;
        String circumstance = "Father took the daughter last 4 April 2020 from their house";
        Integer reward = 10000;

        Investigation investigation = investigationService.createInvestigation(Investigation.builder()
                .withPersonUuid(personUuid)
                .withContactPersonUuid(contactPersonUuid)
                .withEncoderUuid(encoderUuid)
                .withAbductorUuids(abductorUuids)
                .withAgencyUuid(agencyUuid)
                .withRelationshipToAbductors(relationshipToAbductors)
                .withRelationshipToContactPerson(relationshipToContactPerson)
                .withCaseType(caseType)
                .withCircumstance(circumstance)
                .withReward(reward)
                .build(),
            ZoneId.of("Pacific/Auckland"));

        assertThat(investigationService.deleteInvestigation(investigation.getUuid())).isTrue();
        assertThat(investigationService.retrieveInvestigation(investigation.getUuid())).isNull();
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
