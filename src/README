# S4(Super Simple Scheduling System) project
this project is intended to show how we can create a simple rest services to provide an interface to register student, classes and students to classes

### technologies involved
we are using Spring boot 2.1.4, spring data jpa, postgresql.

Spring boot is a framework help us to develop APIs in a faster way, this framework is based in annotations that will help us to avoid xml configurations, this will embed a container according to pur preferences(tomcat by default) and is ready to be deployed.
Spring data jpa is an JPA implementation that help us to interact with DB data easily, we need some object class to represent a data table configured with some annotations according to our needs, because spring data use repository pattern to access data we need to implement an interface that will extend *JpaRepository* from spring data.
PostgreSQL is an open source database with a leading attributes to handle all the requirements to manage our data. postgres has an active community and great support or commercial support if required, can handle big data volumes in a good way

### architecture
we are using a simple mvc architecture do deploy our API

The Model will handle the data management(domain) package will contain the needed classes to we can interact with our data base
The View will be handled by DTO pattern to we can send all the information as needed/required/requested by our clients
The Controllers will retrieve data about what is needed and to we can query to model and send it with our DTOs