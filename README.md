# schedule-api

How to start the schedule-api application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/schedule-api-1.0-SNAPSHOT.jar server schedule-api/config.yaml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
