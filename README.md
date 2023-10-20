# Sales-Manager

## Product and Sales Management

## Overview

This Spring Boot application provides a REST API for managing a list of products and sales. It includes features like adding, updating, and deleting products and sales, calculating total revenue, and retrieving revenue by specific products. The application also includes authentication using Spring Security, allowing only authenticated users with the role "ADMIN" to perform operations related to products and sales. Additionally, the API supports pagination for product listing and handles exceptions gracefully, returning appropriate error messages and status codes.

## Setup

1.Clone the repository:

    git clone https://github.com/ashiqy04/Sales-Manager.git

2.Navigate to the project directory:

    cd Sales-Manager

3.Configure the database:

    Edit the application.properties file to set up the database connection:

    spring.datasource.url=jdbc:mysql://localhost:3306/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password

4.Build and run the application

## Authentication

Authentication is required to access certain endpoints. Only authenticated users with the role "ADMIN" can add, update, and delete products and sales.

## Exception Handling

The API handles exceptions gracefully, returning appropriate error messages and status codes.