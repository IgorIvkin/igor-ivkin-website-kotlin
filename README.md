# A web-site backed by Spring Boot + Kotlin

This small application demonstrates a web-site that build on the top of 
Spring Boot + Kotlin.

Currently there is nothing special happened except of basic service test
that attempts to add model of the `User`-type to database. By default test
configuration uses H2-in-memory database with the following configuration.

    spring.datasource.url=jdbc:h2:mem:test_database
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=admin
    spring.datasource.password=admin_password
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

### Starting points
* [How to generate Boot Spring project on Kotlin](https://start.spring.io/)
* [An example of MapStruct + Kotlin](https://github.com/mapstruct/mapstruct-examples/tree/master/mapstruct-kotlin-gradle/src/main/kotlin/org/mapstruct/example/kotlin) can be found here
