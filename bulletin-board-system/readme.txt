curl http://localhost:8080

copy /Y target/*.war g:\apache-tomcat-9.0.17\webapps\

mvn jetty:run
mvn clean jetty:run -Djetty.port=8080
http://localhost:8080/static/index.html
http://localhost:8080/index/
http://localhost:8080/
