# from the mysql image in the Docker Hub registry create the schemas defined in the "schema.sql" file and import the data from the "data.sql" file
# base build command: docker build -t match-db:0.01 -f Dockerfile --build-arg EXPOSED_PORT=3306 --build-arg MYSQL_ROOT_PASSWORD=root .
# run command: docker run -d -p 3306:3306 --name match-db -e MYSQL_ROOT_PASSWORD=root match-db:0.01
ARG EXPOSED_PORT=3306
ARG MYSQL_ROOT_PASSWORD=root

FROM mysql:8.0-debian

# this mysql image will execute those sql scripts in alphabetical order,
# so we need to prefix the file names with numbers in order to control the execution order
COPY schema.sql /docker-entrypoint-initdb.d/01_schema.sql
COPY data.sql /docker-entrypoint-initdb.d/02_data.sql

ENV MYSQL_ROOT_PASSWORD $MYSQL_ROOT_PASSWORD
EXPOSE $EXPOSED_PORT
