# S4(Super Simple Scheduling System) project
This project is intended to show how we can create a simple rest services to provide an interface to register student, classes and students to classes.

Because class is a word reserved in so many languages and applications we will use course instead of class in most of the cases.

### technologies involved
We are using Spring boot 2.1.4, spring data jpa, spring-fox for swagger UI and postgresql.

Spring boot is a framework help us to develop APIs in a faster way, this framework is based in annotations that will help us to avoid xml configurations, this will embed a container according to our preferences(tomcat by default) and is ready to be deployed.

Spring data jpa is an JPA implementation that help us to interact with DB data easily, we need some object classes to represent a data table configured with some annotations according to our needs, because spring data use repository pattern to access data we need to implement an interface that will extend *JpaRepository* from spring data.

PostgreSQL is an open source database with a leading attributes to handle all the requirements to manage our data. postgres has an active community and great support or commercial support if required, can handle big data volumes in a good way

### architecture
We are using a simple mvc architecture do deploy our API

The Model will handle the data management(domain) package will contain the needed classes to we can interact with our data base.

The View will be handled by DTO pattern to we can send all the information as needed/required/requested by our clients.

The Controllers will retrieve data about what is needed and to we can query to model and send it with our DTOs.

for the database we are using sequencial IDs for student and classes.
### how to run

We need start postgres container please run this command
```
sudo docker run -p 5432:5432 -e POSTGRES_USER=s4project -e POSTGRES_PASSWORD=s4project -e POSTGRES_DB=s4 -d postgres
```
once postgres started start build the application
``` mvn clean package ```
once the package complete you can run the application with
``` java -jar target/s4-0.0.1-SNAPSHOT.jar ```
### how to see API documentation
With the application UP you can go to http://localhost:8088/restapi/swagger-ui.html and the api documentation will be available.

### how to query
In the root folder of this project you have s4.postman_collection.json file, this can be imported into postman,
there you can found the needed request.
* Student registry
* Course registry  -- this is the class
* add student to a class  will register a student into a class
* list students
* list courses
* ...

You need register a student, later a class(course) and later you will need register a student to a class(course).