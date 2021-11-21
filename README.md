# account-statement-service

Simple service using [Spring Boot](http://projects.spring.io/spring-boot/).

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally
First of, navigate to both application.yml and application-test.yml >> `jdbc-url` configuration, you will need to put the full path of MS Access repository location in your machine.
Note: if you're using an old version of IDE (eclipse, intelliJ, ..) there're a Lombok plugin needed to be set up in your IDE, refer to the following link: [lombok-ide](https://www.baeldung.com/lombok-ide)

Then, there are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.nagarro.account.statement.AccountStatementServiceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## Running the tests 

You can use maven test or verify
```shell
mvn test
```
```shell
mvn verify
```
