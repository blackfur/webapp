curl http://localhost:8080

copy /Y target/*.war g:\apache-tomcat-9.0.17\webapps\

mvn jetty:run
mvn clean jetty:run -Djetty.port=8080
mvn jetty:run -Djetty.port=8080
http://localhost:8080/static/index.html
http://localhost:8080/index/
http://localhost:8080/

bin\mysqld --user=root --console
bin\mysql -u root -p1234.abcd
create database bookshelf
use bookshelf
create table if not exists books(title varchar(128) not null, type int) engine=innodb;
insert into books values("Utopia for Realists", 0);
create table user(id MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT, nickname varchar(128), password varchar(32), primary key (id));
insert into user values(32, "Jenkins");
insert into user values(16, "Albert");
alter table user add column password varchar(32);
update user set password = "1234.abcd" where id = 16;
create table posts(id MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,title varchar(32), author varchar(32), createTime varchar(16), category varchar(16), PRIMARY KEY (id));
alter table posts add column (content varchar(1024));

${ELASTICSEARCH_HOME}\config\elasticsearch.yml
cluster.name: suicidegame
g:\elasticsearch-2.0.0\README.textile
* Run @bin/elasticsearch@ on unix, or @bin\elasticsearch.bat@ on windows.
our data is stored at ${ELASTICSEARCH_HOME}\data\suicidegame
# None of the configured nodes are available
Use elasticsearch-2.0.0

create table employee(PersonID int, LastName varchar(255), FirstName varchar(255), City varchar(255), Date datetime(6));
INSERT INTO employee (PersonID, LastName, FirstName, City, Date) VALUES ('4005','Kallis','Jaques','Cape Town','2016-05-23 16:12:03.568810');
INSERT INTO employee (PersonID, LastName, FirstName, City, Date) VALUES ('4004','Baron','Richard','Cape Town','2016-05-23 16:12:04.370460');
INSERT INTO employee (PersonID, LastName, FirstName, City, Date) VALUES ('4003','McWell','Sharon','Cape Town','2016-05-23 16:12:06.874801');

logstash.bat -f logstash.conf
