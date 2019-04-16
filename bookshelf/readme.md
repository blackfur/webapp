curl http://localhost:8080/employee
mvn clean spring-boot:run -Dserver.port=8080
mvn -Dserver.port=8081 spring-boot:run
# devtools
If you want devtools work with spring-boot-maven-plugin, You must 'mvn package' every time You change Your code.
