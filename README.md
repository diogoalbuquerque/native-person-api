# Spring Native Application
[![Generate Executable](https://github.com/diogoalbuquerque/native-person-api/actions/workflows/generate-artifact.yml/badge.svg)](https://github.com/diogoalbuquerque/native-person-api/actions/workflows/generate-artifact.yml)
## Person API

API for create and find people.

## Goal

Create a POC application for an API using Spring Native, Spring Web, Spring Data and Spring Validation.

With these dependencies it was possible to create an API that create and find a person.

## Composition

The project is composed of the domain module and the application module.

## Tests

Although there are tests, @MockBean is not supported by Spring AOT.

## Build

To build the project is necessary to have GRAALVM correctly configured.

Then run the command below.

```shell
mvn -Pnative -DskipTests clean package
```

❗ Use **-DskipTests** for prevents tests from being called and this way can compile without any problems.

## Run

After the build, the artifact will be available at: **/application/target/**

Just run the application artifact

```shell
./application
```

## Endpoints

### Find person

```
    GET /v1/person/1 HTTP/1.1
    Content-Type: application/json
    Host: localhost:8080
```

### Create person

```
    POST /v1/person HTTP/1.1
    Content-Type: application/json
    Host: localhost:8080
    Content-Length: 37
    
    {
        "name": "User Test2",
        "age": 18
    }
```