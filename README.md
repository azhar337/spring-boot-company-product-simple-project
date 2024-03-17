# Project README

## Overview

This project consists of two main functionalities: Company Update and CRUD operations with Products.

## Main Folder Structure

### constant
- **Endpoint.java:** Used to configure endpoints.
- **ErrorCodeEnum.java:** Contains error codes and messages for easier debugging in the future.
- **ResponseStatusEnum.java:** Used to send success or failure responses to the frontend along with data.

### controller
- **BaseController.java:** Configures endpoints for GET, POST, DELETE, and PUT operations for product data.

### model
- **dao**
  - **CompanyModel.java:** Variables required to interact with the database for companies.
  - **ProductModel.java:** Variables used to interact with the database for products.
- **dto**
  - **request**
    - **ProductRequestInsert.java:** Retrieves requests from the frontend, excluding the ID to prevent injection or unnecessary data mapping.
  - **response**
    - **ProductResponse.java:** Returns a reference number when the process is completed to the frontend.
    - **ProductResultResponse.java:** Used when displaying product information to the frontend.
  - **CompanyDto.java:** Used to map between classes for companies.
  - **ProductDto.java:** Used to map between classes for products.

### repository
- Communicates with the database.

### service
- **CompanyService.java:** Adds data into the database for companies.
- **ProductService.java:** Adds, updates, deletes, and retrieves data from the database for products.

### util
- **common**
  - **BaseResponse.java:** Prepares responses.
  - **JsonUtil.java:** Deals with JSON formats.
  - **RefNoGenerator.java:** Generates reference numbers.
- **kafka**
  - **KafkaListener.java**
  - **KafkaProducer.java**

## Company

The company entity consists of ID, first name, last name, and email. It only supports adding functionality. The Kafka listener reads messages from a topic and sends them to the company service to insert into the database.

## Product

The product entity enables users to perform CRUD operations (insert, update, delete, and retrieve) data from the database. It includes a reference number used as a client-side reference.
