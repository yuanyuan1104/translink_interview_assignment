# 1. Requirements
Java
Maven
A valid TransLink API key

# 2. Setup
1. Register at the TransLink developer portal: https://www.translink.ca/about-us/doing-business-with-translink/app-developer-resources
2. You will receive a real API key by email.
3. Create a config.properties and place the API inside like "apikey=sample_key". Follow config.properties.example as an example.

# 3. Run the Application
mvn clean compile
java -cp "target/classes;target/dependency/*" ca.translink.transit.TransitInfoRetrieverApp

# 4. Run the Tests
mvn test

# 5. Test Coverage Summary
Three tests:
| # | Test | Validates | Guards against |
| 1 | testSuccessfulResponse | API returns HTTP 200 | Bad key, endpoint down/moved, no network |
| 2 | testResponseDecode | Bytes decode to a valid FeedMessage | GTFS feed format / schema drift |
| 3 | testNoDuplicateID | Result has no duplicate vehicle IDs | Broken unique logic |

# 6. Assumptions
-routes.txt and directions.txt are the correct documents to use.
-Both route name (i.e. 099) and route ID (i.e. 6641) are unique. They have a one-to-one relationship.

# 7. Tools / External Assistance
-Eclipse IDE, Maven, JUnit 5, and the gtfs-realtime-bindings library.
-Google searches for tutorial sites on how to start eclipse maven project, file reading, https request sending, and other topics.
