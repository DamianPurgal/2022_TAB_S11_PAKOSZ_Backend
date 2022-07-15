
_REST API of the student project responsible for operating the ski station._. 
  
# Project description
The project enables the operation of a ski station by providing the necessary functionalities.
The functionalities are provided by three applications:
* website (ReactJS),
* mobile application (Flutter),
* QR code scanner - ski gate (Unity).
All components are connected to each other via REST API included in this repository.

# Key functionalities of the project
* Login and user registration,
* Authentication with Google account,
* Ability to purchase a tickets using the payment system,
* Scanning of tickets,
* Generating reports.

## Use case diagram
Diagram showing the use cases necessary for the project to work. 
The backend also implements functionality that is not shown in the diagram below.



<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/UseCases.png">
</p>


# Database schema (MySQL)
A database schema created in MySQL.

<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/Database.png">
</p>


# Endpoints
Endpoints of the application shown based on a screenshot from the Postman application.


<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/Postman.png">
</p>


Sample payment API documentation:
[link](https://app.swaggerhub.com/apis-docs/SkiSlope/Payments/1.0.0)

# Description of the implementation of the most important functionalities - Backend

## Authorization and authentication of local users
User authentication is done through JWTs that the user obtains during the authentication process. 
During authentication, the user also obtains a refresh token to extend the life of the access token.


<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/JWT.png">
</p>

## Google Authentication

Google user authentication is done through Spring Security. 
The flow of the user login process through Google is shown below.


<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/GoogleAuthenticationFlow.png">
</p>


## Connection to the payment system
Payments for tickets and passes are made entirely through payPal.

<p align="center">
  <img width="25%" height="25%" src="https://user-images.githubusercontent.com/72508414/178113267-0960ff55-a10b-41a7-8966-e32d7b4fd5cd.png">
</p>

## Generating reports
Reports are generated using JasperReport. There are two types - reports for clients and administrators. 
An example of a report generated for a client:



<p align="center">
  <img width="50%" height="50%" src="https://user-images.githubusercontent.com/72508414/178113397-552b8ec7-e283-4305-a6a4-5aa06c225cd2.png">
</p>



# Technologies used to create the Backend:
* Java,
* Spring Boot,
* Spring Security,
* Spring Data,
* Hibernate,
* Lombok,
* MySQL,
* PayPal API,
* JasperReport.


# Useful applications for creating Backend:
* Postman,
* Github,
* Swagger,
* Trello.

