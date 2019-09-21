[![Build Status](https://travis-ci.org/brest-java-course-summer-2019/aliaksandr-ramaniuk.svg?branch=master)](https://travis-ci.org/brest-java-course-summer-2019/aliaksandr-ramaniuk) [![Coverage Status](https://coveralls.io/repos/github/brest-java-course-summer-2019/aliaksandr-ramaniuk/badge.svg?branch=master)](https://coveralls.io/github/brest-java-course-summer-2019/aliaksandr-ramaniuk?branch=master)

# aliaksandr-ramaniuk

Topic:
This is an application for accounting internal information about FC Dynamo Brest employees.
 
Goal:
To design a WEB application with a role model for internal use of FC Dynamo Brest employees.

The application realizes the following functions:
1. View, edit and delete the roles assigned to the Users and the System Users themselves;
2. View the entire list of roles in the system and the number of Users in each of the Roles;
3. View a list of System Users;
4. Display the number of all users of the system;
5. Filter Users by last name;
6. Filter Users by registration date in the system.

Requirements: 
jdk11
maven 3+

Installing: 
    mvn clean install

Running the tests: 
    mvn clean test

SERVER test
For server test jetty plugin can be used: 
    mvn jetty:run 
    or maven -> Employees Dynamo Brest WEB-APP -> Plugins -> jetty -> jetty:run
Open http://localhost:8080

REST server
Start REST app:
    cd rest-app mvn jetty:run
    or maven -> Employees Dynamo Brest REST-APP -> Plugins -> jetty -> jetty:run
