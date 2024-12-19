# Customer Storage Console Application

A console application for managing customer lists with data validation and logging.

## Features

* Adding customers with data format validation
* Viewing list of all customers
* Finding a customer by name
* Removing customers
* Logging of all operations and errors

## Data Format Requirements

* First and Last Name: letters only
* Email: must contain @ symbol
* Phone: format +7XXXXXXXXXX (10 digits after +7)

## Logging

The application maintains two logs:
* logs/quieris.log - information about all operations
* logs/errors.log - error information

## Technologies

* Java
* Maven
* Log4j2
* JUnit for tests

