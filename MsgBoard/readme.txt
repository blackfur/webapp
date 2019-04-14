curl http://localhost:8080

copy /Y target/*.war g:\apache-tomcat-9.0.17\webapps\

mvn jetty:run
mvn -Djetty.port=8080 jetty:run
http://localhost:8080/static/index.html
http://localhost:8080/index/
