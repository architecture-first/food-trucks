# food-trucks
A repository related to the assessement: https://github.com/peck/engineering-assessment

## Overview

This is a time-boxed response to the assessment mentioned above.
The goal as stated in the requirements is to build a production ready application within the time constraints.
In doing so, there are many choices that were made in order to produce the working application.

### Result

The resulting application consists of a backend microservice that has two endpoints as described later in this document.
This microservice is public and allows anonymous users due to the fact that the originating data is public.
The data returned only contains results pertaining to approved foot trucks.

### Main Focus

Due to the time limitation both for the project and in personal life, the most important items were addressed.
1. Ensure that the data is correctly represented in the application.
   2. This includes treating strings as strings and numbers as numbers, especially the coordinates for the precise location of the Food Trucks.
3. Ensure that the application handles any missing data gracefully.
4. Add a basic ability to filter the data.
5. Add some form of protection to prevent command injections.

### Design Decisions
The solution is based on Spring Boot Java.
The overall design treats the CSV input as database and uses a driver that can support SQL commands against that data source.
With this approach there is no need to build parsing and filtering logic into the service.

Note: Another approach considered after the fact was to use the exposed endpoint https://data.sfgov.org/resource/rqzj-sfat.json to make calls against the original source directly and not convert to a CSV file.
The advantage of that approach is the data would always be up-to-date as opposed with the need to replace a CSV file periodically.

### Design Constraints
With an iterative approach to produce a Minimal Viable Product, some constraints exist in the first version.
1. The solution to periodically replace the CSV file when the backend contents have changed has not been handled.
2. The filter capability to the data is minimal and does not support sophisticated and/or statements.
3. The data returned from the service contains all the columns instead of allowing for a selection of desired columns.
4. The error handling is basic because it removes Checked Exceptions, but it does not consider cross microservice logs by using tools such as Sleuth.
5. There is only basic command injection prevention at this time.
6. There is no use of tools such as Redis for caching performance, such as used in my open source project: https://github.com/architecture-first/boa-showcase
7. There is no Swagger API setup for endpoint definitions.
8. There is no UI to demonstrate the backend.


## Building the project (from a Linux perspective)
1. Navigate to a Linux shell directory
2. Execute: git clone https://github.com/architecture-first/food-trucks.git
3. Execute: ./mvnw clean package

    note: mvnw can be replaced with mvn if it has been installed already
4. Execute: java -jar target/food-truck-0.0.1-SNAPSHOT.jar
5. Execute a sample request: curl http://localhost:8080/trucks/byfooditem/rice%20plates

## Endpoints

### Find Trucks by Food Item

GET http://localhost:8080/trucks/byfooditem/{{foodItem}}

For instance: 
```bash
curl http://localhost:8080/trucks/byfooditem/rice%20plates
```

### Find Trucks by basic filter

#### URL
POST http://localhost:8080/trucks/findtrucks
Content-Type: application/json

#### Payload
```json
{
    "applicant": "",
    "facilityType": "",
    "locationDescription": "",
    "address": "",
    "blocklot": "",
    "block": "",
    "lot": "",
    "permit": "",
    "status": "",
    "foodItems": ""
}
```

#### JSON Schema

```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "properties": {
    "applicant": {
      "type": "string"
    },
    "facilityType": {
      "type": "string"
    },
    "locationDescription": {
      "type": "string"
    },
    "address": {
      "type": "string"
    },
    "blocklot": {
      "type": "string"
    },
    "block": {
      "type": "string"
    },
    "lot": {
      "type": "string"
    },
    "permit": {
      "type": "string"
    },
    "status": {
      "type": "string"
    },
    "foodItems": {
      "type": "string"
    }
  }
}
```

#### Example
```bash
curl -X POST -H "Content-Type: application/json" -d '{"block": "5216", "foodItems": "hot dogs"}' http://localhost:8080/trucks/findtrucks
```
