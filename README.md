# Person-API
## 
This is a RESTful API built with Java and Spring Boot that receive all data related with people and their phones and provide to the client all this data through a REST API.

## About this Project
##
This project is part of my portfolio, so, i'll be happy if you could provide me a feedback about the project, code, structure or anything that you can report that could make me a better developer!

<a href = "mailto:nadsonlima10ns@gmail.com"><img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>

Connect with me at 
<a href="https://www.linkedin.com/in/nadson-santana-silva-15608b139" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>

Also, you can use this Project as you wish, it's free!

## Getting Started
## 

### Prerequisites
To run this project in development mode, you'll need to have a basic enviroment with Java 11+ and Maven 3.6.0+ installed. To use the database, you'll need to have the MySQL installed and running on your machine at the default port(3306), and a empty database called person_api.

### Installing
Cloning the repository

```shell script
	$ git clone https://github.com/NadsonDev/person-api.git
```	
```shell script	
	$ cd person-api
```	

Installing the dependencies

```shell script
	$ mvn clean install 
```	

## Running the Development environment
Now, you'll need to change to development branch:

```shell script
	$ git checkout development 
```

With all dependencies installed, the Database running and the environment properly configured, you can now run the server:

```shell script
	$ mvn spring-boot:run 
```

## Routes
## 
The base URL is: <a href="http://localhost:8080/api/v1/" target="_blank">http://localhost:8080/api/v1/</a> 

The SwaggerUI URL is : <a href="http://localhost:8080/swagger-ui/index.html" target="_blank">http://localhost:8080/swagger-ui/index.html</a> 

The HealthCheck URL is : <a href="http://localhost:8080/actuator/health" target="_blank">http://localhost:8080/actuator/health</a> 

## Technologies used
## 
* Java 11
* Spring Boot
* Spring MVC
* Spring Data JPA
* Spring HATEOAS
* SwaggerUI
* JUnit
* Maven 3.6.0
