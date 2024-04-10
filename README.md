# Graduation Project (Backend)

This repository contains the backend part for my graduation project. 

Tags: `Java`, `Spring Boot`, `GraphQL`, `PostgreSQL`, `Mapstruct`, `Liquibase`, `Lombok`

### ER Diagram

![Project ER-Diagram](/er-diagram.svg)

### Available actions

- `Collection<PlaceProjection> searchPlaces(PlaceFilter filter)`
- `Place getPlace(UUID id)`
- `OperationResult createPlace(PlaceInput place)`
- `OperationResult updatePlace(PlaceInput place)`
- `OperationResult deletePlace(UUID id)`
- `OperationResult ratePlace(RatingInput rating)`
- `Collection<Rating> searchRatings()`

Visit [controller package](src/main/java/com/veekhere/rentrate/controller), [repository package](src/main/java/com/veekhere/rentrate/domain/repository) and [service package](src/main/java/com/veekhere/rentrate/service) for more information. Implemintation of `searchPlaces` SQL query can be found in [v1_search_places.sql](src/main/resources/db.migration/v1/functions/v1_search_places.sql)

### See also

- [Frontend part](https://github.com/veekhere/gp-frontend)
- [GraphQL schema](https://github.com/veekhere/gp-schema)
