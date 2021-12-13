# Account Statement Service
> Simple spring boot application that act as module that provide an account back statements for a user by getting it from corresponding database using user account id and other searching filters.

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact)


## General Information

Spring boot java application that will handle request to view bank back statements for sepeficied accounts by performing simple search on date and amount ranges
- The request should specify the account id.
- The request can specify from date and to date (the date range).
- The request can specify from an amount and to amount (the amount range).
- If the request does not specify any parameter, then the search will return as defualt a three months back statement.
- If the parameters are invalid a proper error message should be sent to user.
- The account number hashed before sent to the user.


## Technologies Used
- Java - version 1.8
- Spring Boot - version 2.6
- Maven - version 3.0


## Features
- Application connect to MS Access Database.
- Have an access control using two roles "ADMIN_ROLE" and "USER_ROLE".
- Role has different permission types.
- Two dummy users available for testing:
	- "admin" with ADMIN_ROLE and unrestricted access, and password: admin
	- "user" with USER_ROLE and restricted access, and password: user
- The ‘admin’ can perform all the requests (specify date and amount range)
- The ‘user’ can only do a request without parameters which will return the three months back statement.
- When ‘user’ tries to specify any parameter, then HTTP unauthorized (401) access error will be returned.
- Users cannot login twice (the user should logout before login).
- The session time out is 5 minutes.
- Support pagination to limit the payload returned from querying the statement - with default max limit of 30 page size
- Pagination can be enabled or disabled using preconfigured in the application.



## Setup
1- First of, navigate to both application.yml or application-[spring-profile_name].yml  and application-test.yml
2- Then, `jdbc-url` configuration, you will need to put/refer to the full path of MS Access repository location in your machine.
3- Then, there are several ways to run a Spring Boot application, one way is to execute the `main` method in the `com.nagarro.account.statement.AccountStatementServiceApplication` class from your IDE,
however note if you're using an old version of IDE (eclipse, intelliJ, ..) therefore a Lombok plugin you'll need to set up in your IDE, refer to the following link: [Lombok](https://www.baeldung.com/lombok-ide)
4- Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

5- Running tests, you can use maven test

```shell
mvn test
```

## Usage
The application has only one ```GET | /api/v1/statements``` can be accessed by 'user' and 'admin' users, and will produce ```application/json```
   * USER_ROLE: 'user' to get back statement.
        - Provide the account id as request 'query' parameter in URL as below is mandatory
        - GET | /api/v1/statements?accountId=1
        - Login page will be appeared if user don't have a valid session -> Enter 'user' credentials
        - Response Sample:
	```{
		"filter": {
			"fromDate": "24/08/2021",
			"toDate": null,
			"fromAmount": null,
			"toAmount": null
		},
		"paginationRequest": {
			"pageIndex": null,
			"pageSize": null
		},
		"statements": [],
		"account": {
			"id": 1,
			"accountType": "current",
			"accountNumber": "043728d935f7aae09de8fe67ea56c63f60213133cea0bdb42ff76891fa4b7597"
		}
	}```
	
   * ADMIN_ROLE: 'admin' to get back statement.
        - Provide the account id as request 'query' parameter in URL as below is mandatory
        - GET | /api/v1/statements?accountId=1
        - Optional you can provide more parameters as below:
            - GET | /api/v1/statements?accountId=1&fromDate=2020-11-20&toDate=2021-11-20
            - GET | /api/v1/statements?accountId=1&fromAmount=50.0&toAmount=370.0
            - GET | /api/v1/statements?accountId=1&fromDate=2020-11-20&toDate=2021-11-20&fromAmount=50.0&toAmount=370.0
        - if pagination enabled:
            - GET | /api/v1/statements?accountId=1&size=10&index=1
            - GET | /api/v1/statements?accountId=1&fromDate=2020-11-20&toDate=2021-11-20&size=10&index=1
            - GET | /api/v1/statements?accountId=1&fromAmount=50.0&toAmount=370.0&size=10&index=1
            - GET | /api/v1/statements?accountId=1&fromDate=2020-11-20&toDate=2021-11-20&fromAmount=50.0&toAmount=370.0&size=10&index=1
        - Note: Date Format as (yyyy-MM-dd)
        - Login page will be appeared if user don't have a valid session -> Enter 'user' credentials
        - Response Sample:
    ```{
		"filter": {
			"fromDate": "24/08/2021",
			"toDate": null,
			"fromAmount": null,
			"toAmount": null
		},
		"paginationRequest": {
			"pageIndex": null,
			"pageSize": null
		},
		"statements": [],
		"account": {
			"id": 1,
			"accountType": "current",
			"accountNumber": "043728d935f7aae09de8fe67ea56c63f60213133cea0bdb42ff76891fa4b7597"
		}
	}```


## Project Status
Project is: _no longer being worked on_: as deadline exceeded :(


## Room for Improvement

Room for improvement:
- Implement more mature security provider e.g. JWT, Oauth2
- Code can be more generic and minimized
- Enhance the unit, and the integration tests
- Enhance logging
- Have a way to know which account ids belong to which user to restrict querying other user statements and details
- Have a user interface to test with, and Swagger APIs documentation

To do:
- Testing Users in a database even if H2 database and to be populated in the startup based on spring environment, instead of the hardcoded testing implementation
- Attach a monitoring tool, get use of Spring Actuator endpoints



## Acknowledgements
- This implementation is far away from the perfection and plenty from of improvement can be applied.
- The project considered as a simple service with minimum set of requirements
- This project was inspired by Nagarro Software Team as part of Hiring Process
- Many thanks for this opportunity


## Contact
Created by [@WaelHassan]- feel free to contact me!
