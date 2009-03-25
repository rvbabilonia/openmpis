-- ======================================================================
-- ===   Sql Script for Database : OpenMPIS
-- ===
-- === Build : 135
-- ======================================================================

BEGIN WORK;

CREATE DATABASE IF NOT EXISTS openmpis;

USE openmpis;
-- ======================================================================

CREATE TABLE abductor
  (
    id               serial,
    firstName        varchar(30),
    nickname         varchar(30),
    middleName       varchar(30),
    lastName         varchar(30),
    birthMonth       int2,
    birthDay         int2,
    birthYear        int2,
    street           varchar(60),
    city             varchar(30),
    province         varchar(30),
    country          varchar(30),
    sex              int2          not null default 0,
    feet             int2,
    inches           int2,
    weight           int2,
    religion         int2          not null default 0,
    race             int2          not null default 0,
    eyeColor         int2          not null default 0,
    hairColor        int2          not null default 0,
    marks            text,
    personalEffects  text,
    remarks          text          not null,
    photo            text,
    agedPhoto        text,
    codisID          varchar(30)   unique,
    afisID           varchar(30)   unique,
    dentalID         varchar(30)   unique,

    primary key(id)
  )
 TYPE = InnoDB;

CREATE INDEX openmpis.abductorIDX1 ON abductor(id);

-- ======================================================================

CREATE TABLE log
  (
    id    serial,
    log   text     not null,
    date  date     not null,

    primary key(id)
  )
 TYPE = InnoDB;

CREATE INDEX openmpis.logIDX1 ON log(id);

-- ======================================================================

CREATE TABLE message
  (
    id         serial,
    date       date          not null,
    email      varchar(60)   not null,
    firstName  varchar(30)   not null,
    lastName   varchar(30)   not null,
    subject    varchar(60)   not null,
    message    text          not null,
    status     int2          not null default 0,
    ipAddress  varchar(30)   not null,
    personID   int4,
    userID     int4          not null,

    primary key(id)
  )
 TYPE = InnoDB;

CREATE INDEX openmpis.messageIDX1 ON message(id);

-- ======================================================================

CREATE TABLE person
  (
    id                   serial,
    status               int2          not null default 0,
    type                 int2          not null,
    firstName            varchar(30),
    nickname             varchar(30),
    middleName           varchar(30),
    lastName             varchar(30),
    birthMonth           int2,
    birthDay             int2,
    birthYear            int2,
    street               varchar(60),
    city                 varchar(30),
    province             varchar(30),
    country              varchar(30),
    sex                  int2          not null,
    feet                 int2          not null,
    inches               int2          not null,
    weight               int2          not null,
    religion             int2          not null default 0,
    race                 int2          not null default 0,
    eyeColor             int2          not null default 0,
    hairColor            int2          not null default 0,
    medicalCondition     text,
    marks                text          not null,
    personalEffects      text          not null,
    remarks              text,
    monthMissingOrFound  int2          not null,
    dayMissingOrFound    int2          not null,
    yearMissingOrFound   int2          not null,
    missingFromCity      varchar(30),
    missingFromProvince  varchar(30),
    missingFromCountry   varchar(30),
    possibleCity         varchar(30),
    possibleProvince     varchar(30),
    possibleCountry      varchar(30),
    circumstance         text,
    reward               int4          default 0,
    institution          varchar(60),
    institutionStreet    varchar(60),
    institutionCity      varchar(30),
    institutionProvince  varchar(30),
    institutionCountry   varchar(30),
    institutionEmail     varchar(60),
    institutionNumber    varchar(30),
    photo                text,
    agedPhoto            text,
    codisID              varchar(30)   unique,
    afisID               varchar(30)   unique,
    dentalID             varchar(30)   unique,
    date                 date          not null,
    encoderID            int4          not null,
    investigatorID       int4,
    relativeID           int4,
    abductorID           int4,
    relationToRelative   int2,
    relationToAbductor   int2,

    primary key(id)
  )
 TYPE = InnoDB;

CREATE INDEX openmpis.personIDX1 ON person(id);

-- ======================================================================

CREATE TABLE relative
  (
    id          serial,
    firstName   varchar(30)   not null,
    middleName  varchar(30)   not null,
    lastName    varchar(30)   not null,
    street      varchar(60)   not null,
    city        varchar(30)   not null,
    province    varchar(30)   not null,
    country     varchar(30)   not null,
    email       varchar(60),
    number      varchar(30)   unique not null,

    primary key(id)
  )
 TYPE = InnoDB;

CREATE INDEX openmpis.relativeIDX1 ON relative(id);

-- ======================================================================

CREATE TABLE report
  (
    id              serial,
    personID        int4     not null,
    investigatorID  int4     not null,
    report          text     not null,
    date            date     not null,

    primary key(id)
  )
 TYPE = InnoDB;

CREATE INDEX openmpis.reportIDX1 ON report(id);

-- ======================================================================

CREATE TABLE user
  (
    id           serial,
    groupID      int2          not null,
    username     varchar(6)    unique not null,
    password     varchar(32)   not null,
    firstName    varchar(30)   not null,
    middleName   varchar(30)   not null,
    lastName     varchar(30)   not null,
    birthMonth   int2,
    birthDay     int2,
    birthYear    int2,
    email        varchar(60)   unique not null,
    designation  varchar(30)   not null,
    agency       varchar(60)   not null,
    number       varchar(30)   not null,
    ipAddress    varchar(30),
    lastLogin    date,
    date         date          not null,
    creatorID    int4          not null,
    status       int2          not null default 0,
    question     int2          not null,
    answer       varchar(30),

    primary key(id)
  )
 TYPE = InnoDB;

CREATE INDEX openmpis.userIDX1 ON user(id);

-- ======================================================================

INSERT INTO user
(GROUPID, USERNAME, PASSWORD, FIRSTNAME, MIDDLENAME, LASTNAME, BIRTHMONTH, BIRTHDAY, BIRTHYEAR,
EMAIL, DESIGNATION, AGENCY, NUMBER,
IPADDRESS, LASTLOGIN, DATE,
STATUS, QUESTION, ANSWER, CREATORID)
VALUES
(0, 'RB0106', MD5('admin[123]'), 'Rey Vincent', 'Potencio', 'Babilonia', '01', '06', '1982',
'rvbabilonia@gmail.com', 'SRSI', 'Advanced Science and Technology Institute', '+63 2 426 9760 ext. 1302',
'127.0.0.1', '2009-02-15', '2009-02-15',
0, 0, 'green', 0);

COMMIT;
-- ======================================================================

