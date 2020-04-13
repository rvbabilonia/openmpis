# OpenMPIS

[![Build Status](https://travis-ci.org/rvbabilonia/openmpis.svg?branch=3.0)](https://travis-ci.org/rvbabilonia/openmpis)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Frvbabilonia%2Fopenmpis.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Frvbabilonia%2Fopenmpis?ref=badge_shield)

OpenMPIS is the **Open** Source **M**issing **P**ersons **I**nformation **S**ystem. 
It addresses 1.) the lack of a centralized service for disseminating advisories,
2.) the lack of a joint effort for finding missing Filipinos and 3.) the lack of a
free and open source Missing Persons Information System.

The purposes of this research are 1.) to implement an information system
that will integrate and consolidate all government efforts in a.) finding
missing, abducted, runaway, throwaway and abandoned persons and b.)
reporting found and unidentified persons; 2.) to inform and encourage
citizens to help look for these missing persons or abductors; and 3.) to
promote awareness for the public in keeping their relatives safe.

The target beneficiaries of this system are 1.) the proposed Missing Persons
Council or Commission on Missing Persons, 2.) the Philippine National Police,
3.) the National Bureau of Investigation, 4.) the Department of Social
Welfare and Development, 5.) the missing and found persons, 6.) their
relatives and 7.) the general public.

OpenMPIS was developed by Rey Vincent Babilonia in partial fulfillment of
the requirements for the degree Master of Information Systems from the
University of the Philippines - Open University, Los Baños, Laguna.

## For Developers

The latest branch can be cloned or downloaded from the [OpenMPIS Web site](https://github.com/rvbabilonia/openmpis).

### Architecture

OpenMPIS v2 shifted from the original monolithic architecture to a scalable RESTful microservices architecture. An 
embedded Apache Tomcat is now used to start all services. The relational MySQL database server has been replaced by 
the embedded non-relational Berkeley database Java edition. Apache Ant has been replaced by Gradle for dependency 
management and building. Apache Struts was replaced by Spring as the MVC framework.

OpenMPIS v3 is a complete overhaul designed to run on Amazon Web Services. The microservices now run as AWS Lambda
functions. Amazon DynamoDB is now the database service. Frontend is now using React. Other AWS services in use include
Route 53, CloudFront, API Gateway, S3, IAM, Certificate Manager and CloudFormation.

#### Modules

* openmpis-domain

    This shared module contains all the domain model objects, data transfer objects and adapters.

* openmpis-abductor

    This module handles the creation, retrieval, modification and archiving of alleged abductors.

* openmpis-agency

    This module handles the creation, retrieval, modification and deletion of government agencies.

* openmpis-institution

    This module handles the creation, retrieval, modification and deletion of social welfare institutions and
    non-government organizations.
    
* openmpis-investigation

    This module binds the encoder, investigator, person, contact person and abductor, if any, into a single case
    investigation. This module also handles tips and investigation reports.
    
* openmpis-person

    This module handles the creation, retrieval, modification and deletion of persons.
    
* openmpis-user

    This module handles the creation, retrieval, modification and archiving of encoders and investigators.

* openmpis-web

    This module sports a mobile-first single-page application (SPA) utilizing React.

### Development

1. You will need [Corretto 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) or later
to compile and/or run the application locally.

2. Download DynamoDB Local: `docker pull amazon/dynamodb-local`

3. Start DynamoDB Local: `docker run -p 8000:8000 amazon/dynamodb-local`

4. Set environment variables: `DYNAMODB_URL=http://localhost:8000;DYNAMODB_REGION=ap-southeast-2;
DYNAMODB_ACCESS_KEY=accessKey;DYNAMODB_SECRET_KEY=secretKey`

## For End Users

The latest user, developer and system administrator manuals with screenshots can be viewed from the
[OpenMPIS wiki](https://github.com/rvbabilonia/openmpis/wiki).

The application programming interface (API) documentations are available in HTML format in the build/docs directory 
upon running `gradlew clean build generateJavadocs`.

The site is accessible at https://openmpis.vincenzolabs.org or https://missingfilipinos.vincenzolabs.org.

## Licensing

OpenMPIS is now licensed under the MIT License. Details may be found in the file named `LICENSE`.

OpenMPIS also uses the following free and open source software:
* Corretto 11 which is licensed under the GNU General Public License;
* Spring Framework, its subprojects like Spring Boot, and its transitive dependencies, which are 
licensed under the Apache License;
* AWS SDK for Java v2 which is licensed under the Apache License;
* JUnit which is licensed under the Eclipse Public License;
* React which is licensed under the MIT License;
* Apache PDFBox which is licensed under the Apache License.

[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Frvbabilonia%2Fopenmpis.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Frvbabilonia%2Fopenmpis?ref=badge_large)

## Issues

You can submit bug reports or feature requests through the [OpenMPIS Web site](https://github.com/rvbabilonia/openmpis/issues).

You can also send e-mails to the developer.



Thank you very much for using OpenMPIS!

[Rey Vincent Babilonia](mailto:rvbabilonia@gmail.com)
