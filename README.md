# OpenMPIS
[![Build Status](https://travis-ci.org/rvbabilonia/openmpis.svg?branch=master)](https://travis-ci.org/rvbabilonia/openmpis)

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

## Requirements

You will need Java 1.8 or later to compile and/or run the application.

## For Developers

The latest branch can be cloned or downloaded from the [OpenMPIS Web site](https://github.com/rvbabilonia/openmpis).

#### Architecture

OpenMPIS v2 shifted from the original monolithic architecture to a scalable RESTful
microservices architecture using Spring framework. An embedded Apache Tomcat is now used to start all services.
Relational MySQL database server has been replaced by embedded non-relational Berkeley database Java edition.
Apache Ant has been replaced by Gradle for dependency management and building.

#### Modules

* openmpis-web-thymeleaf

    This module offers a new and improved Web interface using Thymeleaf natural templating engine. It retains the 
    look-and-feel of the original frontend.

* openmpis-web-angular

    This module sports a mobile-first single-page application (SPA) utilizing AngularJS.
    
* openmpis-rest

    This module exposes all applicable RESTful APIs and invokes the various microservices. It is being used by both 
    openmpis-web-thymeleaf and openmpis-web-angular modules. You can even create your own frontend.
            
* openmpis-eureka

    This module runs the Netflix Eureka server for service registration and discovery.
    
* openmpis-user

    This module handles the creation, modification and retrieval of users. It can authenticate users via LDAP or 
    OpenID. You can even implement other authentication mechanisms such as X.509 certificates.
    
* openmpis-person

    This module handles the creation, modification and retrieval of persons, relatives and abductors, if any.
    
* openmpis-image

    This module stores and displays the picture of the person. You can even introduce an age progression library.
    
* openmpis-case

    This module binds the encoder, investigator, person, relative and abductor, if any, into a single case.

## For System Administrators

#### Server Installation (Bare Metal or Virtual Machine)

1. Download the latest release from the [OpenMPIS Web site](https://github.com/rvbabilonia/openmpis/releases).

2. Uncompress openmpis-<version>.zip or openmpis-<version>.tar.gz.

3. On your MS-DOS prompt or Linux terminal, go to the extracted directory.

4. Configure...

5. Start the microservices...

6. Go to http://localhost:8080/openmpis to start using the system. The default username is `admin` and the default 
password is `password`.

#### Docker Containerization

1. Go to docker/release folder.

2. Configure...

3. Build the image by running `docker build -f Dockerfile .`.

4. Deploy the image into the Docker container.
    
## Documentation

The latest user, developer and system administrator manuals with screenshots can be viewed from the
[OpenMPIS wiki](https://github.com/rvbabilonia/openmpis/wiki).

The application programming interface (API) documentations are available in HTML format in the build/docs directory 
upon running `gradlew clean build generateJavadocs`.

## Licensing

OpenMPIS is licensed under the GNU Lesser General Public License. Details may be found in the file named "LICENSE.md".

OpenMPIS also uses the following free and open source software:
* Java which is licensed under the GNU General Public License;
* Spring Framework, its subprojects like Spring Boot, and its transitive dependies like Netflix Eureka, which are 
licensed under the Apache License;
* Oracle Berkeley Database Java Edition which is licensed under the Apache License;
* Logback which is dual-licensed under the Eclipse Public License and the Lesser GNU General Public License;
* JUnit which is licensed under Eclipse Public License;
* AspectJ which is licensed under the Eclipse Public License;
* Thymeleaf which is licensed under the Apache License;
* AngularJS which is licensed under the MIT License;
* jQuery which is licensed under the MIT License;
* Swagger and Springfox which are licensed under the Apache License;
* JavaMail which is licensed under the Common Development and Distribution License and the GNU General Public License.
* iText which is licensed under the Affero General Public License.
* Apache Tomcat which is licensed under the Apache License;

## Issues

You can submit bug reports or feature requests through the [OpenMPIS Web site](https://github.com/rvbabilonia/openmpis/issues).

You can also send e-mails to the developer.



Thank you very much for using OpenMPIS!

[Rey Vincent Babilonia](mailto:rvbabilonia@gmail.com)
