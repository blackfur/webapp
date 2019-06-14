%JAVA_HOME%\jre\lib\javafx.properties
javafx.runtime.verion=2.2.3
mvn install:install-file -Dfile=jfxrt.jar -DgroupId=com.oracle -DartifactId=javafx -Dpackaging=jar -Dversion=2.2.3

java -jar target\suicidegamepc-1.0-SNAPSHOT.jar
