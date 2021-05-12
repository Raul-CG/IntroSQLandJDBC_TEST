
# INTRO SQL AND JDBC
Explanation and task for SQL and JDBC.

## Open docker
docker run --name IntroSQLandJDBC -e MYSQL_ROOT_PASSWORD=changeIt -p 3306:3306 -d mysql:8.0.19

## MySQL script execution to create DataTable, user connection and privileges.
CREATE DATABASE IntroSQLandJDBC;

CREATE USER 'user'@'%' IDENTIFIED BY 'changeIt';

GRANT ALL PRIVILEGES ON IntroSQLandJDBC.* TO 'user'@'%';