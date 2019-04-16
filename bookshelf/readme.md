curl http://localhost:8080/employee
mvn clean spring-boot:run -Dserver.port=8080
mvn -Dserver.port=8081 spring-boot:run
