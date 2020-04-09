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
package org.vincenzolabs.openmpis.person.service;

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
import org.vincenzolabs.openmpis.domain.Person;
import org.vincenzolabs.openmpis.domain.Description;
import org.vincenzolabs.openmpis.domain.StreetAddress;
import org.vincenzolabs.openmpis.enumeration.EyeColor;
import org.vincenzolabs.openmpis.enumeration.HairColor;
import org.vincenzolabs.openmpis.enumeration.Sex;
import org.vincenzolabs.openmpis.person.dao.PersonDAO;
import org.vincenzolabs.openmpis.person.dao.impl.PersonDAODynamoDBImpl;
import org.vincenzolabs.openmpis.person.service.impl.PersonServiceImpl;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

/**
 * The test case for {@link PersonService}.
 *
 * @author Rey Vincent Babilonia
 */
@ExtendWith(DynamoDB.class)
class PersonServiceTest {

    private static final String ABDUCTOR_TABLE_NAME = "person";

    @AWSClient(endpoint = Endpoint.class)
    private DynamoDbClient client;

    private PersonService personService;

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

        PersonDAO personDAO = new PersonDAODynamoDBImpl(client, new GsonBuilder().create());

        personService = new PersonServiceImpl(personDAO);
    }

    @AfterEach
    void tearDown() {
        DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder()
            .tableName(ABDUCTOR_TABLE_NAME)
            .build();

        client.deleteTable(deleteTableRequest);
    }

    @Test
    void createPerson() {
        String primaryPhotoUuid = UUID.randomUUID().toString();
        OffsetDateTime lastSeenOrFoundDate = OffsetDateTime.now().minusDays(1);
        Person person = personService.createPerson(Person.builder()
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withLastSeenOrFoundDate(lastSeenOrFoundDate)
            .withPrimaryPhotoUuid(primaryPhotoUuid)
            .withPhotoUuids(Set.of(primaryPhotoUuid, UUID.randomUUID().toString()))
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build());

        assertThat(person.getUuid()).isNotNull();
        assertThat(person)
            .extracting("firstName", "lastName", "lastSeenOrFoundDate", "primaryPhotoUuid", "description.sex",
                "description.remarks")
            .containsExactly("Nick", "Ruskin", lastSeenOrFoundDate, primaryPhotoUuid, Sex.MALE,
                "Ruskin is a police detective. Considered armed and dangerous.");
        assertThat(person.getPhotoUuids())
            .hasSize(2)
            .containsOnlyOnce(primaryPhotoUuid);
    }

    @Test
    void createPersonWithoutNickname() {
        IllegalArgumentException exception = catchThrowableOfType(() ->
            personService.createPerson(Person.builder().build()), IllegalArgumentException.class);

        assertThat(exception)
            .hasMessage("Nickname must not be blank if first and last names are also null");
    }

    @Test
    void retrievePerson() {
        OffsetDateTime lastSeenOrFoundDate = OffsetDateTime.now().minusDays(1);
        Person person = personService.createPerson(Person.builder()
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withLastSeenOrFoundDate(lastSeenOrFoundDate)
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build());

        Person actual = personService.retrievePerson(person.getUuid());

        assertThat(actual)
            .extracting("firstName", "lastName", "lastSeenOrFoundDate", "primaryPhotoUuid", "description.sex",
                "description.remarks")
            .containsExactly("Nick", "Ruskin", lastSeenOrFoundDate, null, Sex.MALE,
                "Ruskin is a police detective. Considered armed and dangerous.");
        assertThat(actual.getPhotoUuids()).isEmpty();
    }

    @Test
    void retrieveNonExistentPerson() {
        assertThat(personService.retrievePerson("uuid")).isNull();
    }

    @Test
    void retrievePersons() {
        String primaryPhotoUuid = UUID.randomUUID().toString();
        OffsetDateTime lastSeenOrFoundDate = OffsetDateTime.now().minusDays(1);
        personService.createPerson(Person.builder()
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withLastSeenOrFoundDate(lastSeenOrFoundDate)
            .withPrimaryPhotoUuid(primaryPhotoUuid)
            .withPhotoUuids(Set.of(primaryPhotoUuid, UUID.randomUUID().toString()))
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build());

        Set<Person> actual = personService.retrievePersons();

        assertThat(actual)
            .hasSize(1)
            .first()
            .extracting("firstName", "lastName", "lastSeenOrFoundDate", "primaryPhotoUuid", "description.sex",
                "description.remarks")
            .containsExactly("Nick", "Ruskin", lastSeenOrFoundDate, primaryPhotoUuid, Sex.MALE,
                "Ruskin is a police detective. Considered armed and dangerous.");
    }

    @Test
    void retrieveNonExistentAgencies() {
        assertThat(personService.retrievePersons()).isEmpty();
    }

    @Test
    void updatePerson() {
        String primaryPhotoUuid = UUID.randomUUID().toString();
        OffsetDateTime lastSeenOrFoundDate = OffsetDateTime.now().minusDays(1);
        LocalDate birthDate = LocalDate.of(1980, 7, 4);
        Person person = personService.createPerson(Person.builder()
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

        Person actual = personService.updatePerson(Person.builder()
            .withUuid(person.getUuid())
            .withNickname("Nick")
            .withLastSeenOrFoundDate(lastSeenOrFoundDate)
            .withLastSeenOrFoundLocation(StreetAddress.builder()
                .withCity("Los Angeles")
                .withProvince("California")
                .withCountry("United States of America")
                .build())
            .withPossibleLocation(StreetAddress.builder()
                .withCity("Las Vegas")
                .withProvince("Nevada")
                .withCountry("United States of America")
                .build())
            .withPrimaryPhotoUuid(person.getPrimaryPhotoUuid())
            .withPhotoUuids(person.getPhotoUuids())
            .withDescription(Description.builder()
                .withEyeColor(EyeColor.BLUE)
                .withHairColor(HairColor.BROWN)
                .withPersonalEffects("Glock handgun, badge")
                .withSex(person.getDescription().getSex())
                .withRemarks(person.getDescription().getRemarks())
                .build())
            .build());

        assertThat(actual)
            .extracting("firstName", "lastName", "lastSeenOrFoundDate", "primaryPhotoUuid", "description.sex",
                "description.remarks", "description.eyeColor", "description.hairColor",
                "description.personalEffects", "birthDate", "lastSeenOrFoundLocation", "possibleLocation")
            .containsExactly("Nick", "Ruskin", lastSeenOrFoundDate, primaryPhotoUuid, Sex.MALE,
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
    void updatePersonWithoutName() {
        String primaryPhotoUuid = UUID.randomUUID().toString();
        OffsetDateTime lastSeenOrFoundDate = OffsetDateTime.now().minusDays(1);
        LocalDate birthDate = LocalDate.of(1980, 7, 4);
        Person person = personService.createPerson(Person.builder()
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
            personService.updatePerson(Person.builder()
                .withUuid(person.getUuid())
                .withLastSeenOrFoundDate(lastSeenOrFoundDate)
                .withLastSeenOrFoundLocation(StreetAddress.builder()
                    .withCity("Los Angeles")
                    .withProvince("California")
                    .withCountry("United States of America")
                    .build())
                .withPossibleLocation(StreetAddress.builder()
                    .withCity("Las Vegas")
                    .withProvince("Nevada")
                    .withCountry("United States of America")
                    .build())
                .withPrimaryPhotoUuid(person.getPrimaryPhotoUuid())
                .withPhotoUuids(person.getPhotoUuids())
                .withDescription(Description.builder()
                    .withEyeColor(EyeColor.BLUE)
                    .withHairColor(HairColor.BROWN)
                    .withPersonalEffects("Glock handgun, badge")
                    .withSex(person.getDescription().getSex())
                    .withRemarks(person.getDescription().getRemarks())
                    .build())
                .build()), IllegalArgumentException.class);

        assertThat(exception)
            .hasMessage("Nickname must not be blank if first and last names are also null");
    }

    @Test
    void deletePerson() {
        String primaryPhotoUuid = UUID.randomUUID().toString();
        Person person = personService.createPerson(Person.builder()
            .withFirstName("Nick")
            .withLastName("Ruskin")
            .withLastSeenOrFoundDate(OffsetDateTime.now().minusDays(1))
            .withPrimaryPhotoUuid(primaryPhotoUuid)
            .withPhotoUuids(Set.of(primaryPhotoUuid, UUID.randomUUID().toString()))
            .withDescription(Description.builder()
                .withSex(Sex.MALE)
                .withRemarks("Ruskin is a police detective. Considered armed and dangerous.")
                .build())
            .build());

        assertThat(personService.deletePerson(person.getUuid())).isTrue();
        assertThat(personService.retrievePerson(person.getUuid())).isNull();
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
