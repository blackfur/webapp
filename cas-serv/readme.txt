git checkout -b 6.0 origin/6.0
install jdk-11.0.3_linux-x64_bin.tar.gz
./build.sh clean run
@set JRE_HOME=g:\Java\jre1.8.0_201

https://localhost:8443/cas
casuser
Mellon

mysql -u root -p bookshelf < f:\workspace\eugenp\cas\cas-server\src\main\resources\create_test_db_and_users_tbl.sql
show databases;
use test;
show tables;
describe user;
