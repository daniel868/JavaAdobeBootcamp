<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="readme-image/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Java Springboot Project</h3>

  <p align="center">
    An introduction demo into Java Springboot concepts
    <br />
    <a href="https://github.com/daniel868/JavaAdobeBootcamp"><strong>Source Code»</strong></a>
    <br />
  </p>

</div>

## About the project

Build as a demo application, this is an introduction into Java Springboot framework which is used for building 
real-time enterprise application.

This application simulate a very basic airline management system application, where you can store
data about the airplane, flights, customer and employees. 

### Build with

* [Springboot Java Framework]()
* [Postgresql Database]()
* [External API](https://airlabs.co/docs/flight)


### Database Schema:

<div align="center">
    <img src="readme-image/DatabaseSchema.jpg" alt="Logo" width="813" height="522">
</div>

#### Relation between tables:
1. Customer - Flight (Many-To-Many)
   1. Created using a middle table (Customer_Flight)
   2. Many customer may book the same flights and a customer may book multiple flights
2. Flight - Aircraft (Many-To-One)
   1. An aircraft could realize multiple flights, but a flight could be realized by one aircraft


## Application Architecture
<div align="center">
    <img src="readme-image/Project_Arhitecture.jpg" alt="Logo" width="882" height="417">
</div>

- External API is used for fetching flights data from external source if there is no available flight into local database, 
matching client expectation 
- Java Springboot application and Local Postgresql Database are running into a docker container

## Functionality
 
### Customer:
  - Display, Insert, Delete
  - Book a flight

### Aircraft:
  - Display, Insert, Delete
  - Match an aircraft with flights

### Employee
 - Display, Insert, Delete

### Flight
 - Display, Insert, Delete
 - Match an aircraft with a flight

## Testing coverage

<div>
    <img src="readme-image/TestingCoverage.jpg" alt="Logo" width="482" height="250">
</div>


