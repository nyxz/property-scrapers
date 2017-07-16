Prerequisites
===

* Have Java 8+ installed
* Install PostgreSQL 9.3+
* Initialized the PostgreSQL database with username/password of your choice and created schema called *realestate*.


Setup
===

The application have the basic setup ready, but the DB username and password are externalized meaning you need to create some properties file say *realestate.properties* containing:

```
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
```

Run
===

You may notice the application have some presets for various websites in the src/main/resources/scrapers folder. You can use any of them per run.
Remember to pass the path to the configuration you just created and the query with your criteria.

Execute in CLI:

```
java -Drealestate.config.location=/path/to/realestate.properties -jar target/real-estate-scrapers-1.0.jar 'https://realestate.com/some/params?go=here'
```

Note: if the provider you are searching in have no configuration available in src/main/resources/scrapers you may add one for it and try again. The name should be matching the name of the provider's domain name.