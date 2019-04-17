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
create table user(id int, nickname varchar(128));
insert into user values(32, "Jenkins");
insert into user values(16, "Albert");
alter table user add column password varchar(32);
update user set password = "1234.abcd" where id = 16;
