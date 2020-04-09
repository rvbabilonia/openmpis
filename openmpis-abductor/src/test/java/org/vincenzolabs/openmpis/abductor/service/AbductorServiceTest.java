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
package org.vincenzolabs.openmpis.abductor.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;

import java.time.LocalDate;
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
import org.vincenzolabs.openmpis.abductor.dao.AbductorDAO;
import org.vincenzolabs.openmpis.abductor.dao.impl.AbductorDAODynamoDBImpl;
import org.vincenzolabs.openmpis.abductor.service.impl.AbductorServiceImpl;
import org.vincenzolabs.openmpis.domain.Abductor;
import org.vincenzolabs.openmpis.domain.Description;
import org.vincenzolabs.openmpis.domain.StreetAddress;
import org.vincenzolabs.openmpis.enumeration.EyeColor;
import org.vincenzolabs.openmpis.enumeration.HairColor;
import org.vincenzolabs.openmpis.enumeration.Sex;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

/**
 * The test case for {@link AbductorService}.
 *
 * @author Rey Vincent Babilonia
 */
@ExtendWith(DynamoDB.class)
class AbductorServiceTest {

    private static final String ABDUCTOR_TABLE_NAME = "abductor";

    @AWSClient(endpoint = Endpoint.class)
    private DynamoDbClient client;

    private AbductorService abductorService;

    @BeforeEach
    void setUp() {
        CreateTableRequest createTableRequest = CreateTableRequest.builder()
            .tableName(ABDUCTOR_TABLE_NAME)
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

        AbductorDAO abductorDAO = new AbductorDAODynamoDBImpl(client, new GsonBuilder().create());

        abductorService = new AbductorServiceImpl(abductorDAO);
    }

    @AfterEach
    void tearDown() {
        DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder()
            .tableName(ABDUCTOR_TABLE_NAME)
            .build();

        client.deleteTable(deleteTableRequest);
    }

    @Test
    void createAbductor() {
        String primaryPhotoUuid = UUID.randomUUID().toString();
        OffsetDateTime lastSeenDate = OffsetDateTime.now().minusDays(1);
        Abductor abductor = abductorService.createAbductor(Abductor.builder()
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withLastSeenDate(lastSeenDate)
            .withPrimaryPhotoUuid(primaryPhotoUuid)
            .withPhotoUuids(Set.of(primaryPhotoUuid, UUID.randomUUID().toString()))
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build());

        assertThat(abductor.getUuid()).isNotNull();
        assertThat(abductor)
            .extracting("firstName", "lastName", "lastSeenDate", "primaryPhotoUuid", "description.sex",
                "description.remarks")
            .containsExactly("Nick", "Ruskin", lastSeenDate, primaryPhotoUuid, Sex.MALE,
                "Ruskin is a police detective. Considered armed and dangerous.");
        assertThat(abductor.getPhotoUuids())
            .hasSize(2)
            .containsOnlyOnce(primaryPhotoUuid);
    }

    @Test
    void createAbductorWithoutNickname() {
        IllegalArgumentException exception = catchThrowableOfType(() ->
            abductorService.createAbductor(Abductor.builder().build()), IllegalArgumentException.class);

        assertThat(exception)
            .hasMessage("Nickname must not be blank if first and last names are also null");
    }

    @Test
    void retrieveAbductor() {
        OffsetDateTime lastSeenDate = OffsetDateTime.now().minusDays(1);
        Abductor abductor = abductorService.createAbductor(Abductor.builder()
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withLastSeenDate(lastSeenDate)
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build());

        Abductor actual = abductorService.retrieveAbductor(abductor.getUuid());

        assertThat(actual)
            .extracting("firstName", "lastName", "lastSeenDate", "primaryPhotoUuid", "description.sex",
                "description.remarks")
            .containsExactly("Nick", "Ruskin", lastSeenDate, null, Sex.MALE,
                "Ruskin is a police detective. Considered armed and dangerous.");
        assertThat(actual.getPhotoUuids()).isEmpty();
    }

    @Test
    void retrieveNonExistentAbductor() {
        assertThat(abductorService.retrieveAbductor("uuid")).isNull();
    }

    @Test
    void retrieveAbductors() {
        String primaryPhotoUuid = UUID.randomUUID().toString();
        OffsetDateTime lastSeenDate = OffsetDateTime.now().minusDays(1);
        abductorService.createAbductor(Abductor.builder()
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withLastSeenDate(lastSeenDate)
            .withPrimaryPhotoUuid(primaryPhotoUuid)
            .withPhotoUuids(Set.of(primaryPhotoUuid, UUID.randomUUID().toString()))
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build());

        Set<Abductor> actual = abductorService.retrieveAbductors();

        assertThat(actual)
            .hasSize(1)
            .first()
            .extracting("firstName", "lastName", "lastSeenDate", "primaryPhotoUuid", "description.sex",
                "description.remarks")
            .containsExactly("Nick", "Ruskin", lastSeenDate, primaryPhotoUuid, Sex.MALE,
                "Ruskin is a police detective. Considered armed and dangerous.");
    }

    @Test
    void retrieveNonExistentAgencies() {
        assertThat(abductorService.retrieveAbductors()).isEmpty();
    }

    @Test
    void updateAbductor() {
        String primaryPhotoUuid = UUID.randomUUID().toString();
        OffsetDateTime lastSeenDate = OffsetDateTime.now().minusDays(1);
        LocalDate birthDate = LocalDate.of(1980, 7, 4);
        Abductor abductor = abductorService.createAbductor(Abductor.builder()
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withBirthDate(birthDate)
            .withPrimaryPhotoUuid(primaryPhotoUuid)
            .withPhotoUuids(Set.of(primaryPhotoUuid, UUID.randomUUID().toString()))
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build());

        Abductor actual = abductorService.updateAbductor(Abductor.builder()
            .withUuid(abductor.getUuid())
            .withNickname("Nick")
            .withLastSeenDate(lastSeenDate)
            .withLastSeenLocation(StreetAddress.builder()
                .withCity("Los Angeles")
                .withProvince("California")
                .withCountry("United States of America")
                .build())
            .withPossibleLocation(StreetAddress.builder()
                .withCity("Las Vegas")
                .withProvince("Nevada")
                .withCountry("United States of America")
                .build())
            .withPrimaryPhotoUuid(abductor.getPrimaryPhotoUuid())
            .withPhotoUuids(abductor.getPhotoUuids())
            .withDescription(Description.builder()
                .withEyeColor(EyeColor.BLUE)
                .withHairColor(HairColor.BROWN)
                .withPersonalEffects("Glock handgun, badge")
                .withSex(abductor.getDescription().getSex())
                .withRemarks(abductor.getDescription().getRemarks())
                .build())
            .build());

        assertThat(actual)
            .extracting("firstName", "lastName", "lastSeenDate", "primaryPhotoUuid", "description.sex",
                "description.remarks", "description.eyeColor", "description.hairColor",
                "description.personalEffects", "birthDate", "lastSeenLocation", "possibleLocation")
            .containsExactly("Nick", "Ruskin", lastSeenDate, primaryPhotoUuid, Sex.MALE,
                "Ruskin is a police detective. Considered armed and dangerous.", EyeColor.BLUE, HairColor.BROWN,
                "Glock handgun, badge", birthDate,
                StreetAddress.builder()
                    .withCity("Los Angeles")
                    .withProvince("California")
                    .withCountry("United States of America")
                    .build(),
                StreetAddress.builder()
                    .withCity("Las Vegas")
                    .withProvince("Nevada")
                    .withCountry("United States of America")
                    .build());
        assertThat(actual.getPhotoUuids())
            .hasSize(2)
            .containsOnlyOnce(primaryPhotoUuid);
        assertThat(actual.getAge(ZoneId.of("Pacific/Auckland"))).isGreaterThanOrEqualTo(39);
    }

    @Test
    void updateAbductorWithoutName() {
        String primaryPhotoUuid = UUID.randomUUID().toString();
        OffsetDateTime lastSeenDate = OffsetDateTime.now().minusDays(1);
        LocalDate birthDate = LocalDate.of(1980, 7, 4);
        Abductor abductor = abductorService.createAbductor(Abductor.builder()
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withBirthDate(birthDate)
            .withPrimaryPhotoUuid(primaryPhotoUuid)
            .withPhotoUuids(Set.of(primaryPhotoUuid, UUID.randomUUID().toString()))
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build());

        IllegalArgumentException exception = catchThrowableOfType(() ->
            abductorService.updateAbductor(Abductor.builder()
                .withUuid(abductor.getUuid())
                .withLastSeenDate(lastSeenDate)
                .withLastSeenLocation(StreetAddress.builder()
                    .withCity("Los Angeles")
                    .withProvince("California")
                    .withCountry("United States of America")
                    .build())
                .withPossibleLocation(StreetAddress.builder()
                    .withCity("Las Vegas")
                    .withProvince("Nevada")
                    .withCountry("United States of America")
                    .build())
                .withPrimaryPhotoUuid(abductor.getPrimaryPhotoUuid())
                .withPhotoUuids(abductor.getPhotoUuids())
                .withDescription(Description.builder()
                    .withEyeColor(EyeColor.BLUE)
                    .withHairColor(HairColor.BROWN)
                    .withPersonalEffects("Glock handgun, badge")
                    .withSex(abductor.getDescription().getSex())
                    .withRemarks(abductor.getDescription().getRemarks())
                    .build())
                .build()), IllegalArgumentException.class);

        assertThat(exception)
            .hasMessage("Nickname must not be blank if first and last names are also null");
    }

    @Test
    void deleteAbductor() {
        String primaryPhotoUuid = UUID.randomUUID().toString();
        Abductor abductor = abductorService.createAbductor(Abductor.builder()
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withLastSeenDate(OffsetDateTime.now().minusDays(1))
            .withPrimaryPhotoUuid(primaryPhotoUuid)
            .withPhotoUuids(Set.of(primaryPhotoUuid, UUID.randomUUID().toString()))
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build());

        assertThat(abductorService.deleteAbductor(abductor.getUuid())).isTrue();
        assertThat(abductorService.retrieveAbductor(abductor.getUuid())).isNull();
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
