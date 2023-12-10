#
# Build stage
#
FROM maven:3.6.3-openjdk-17-slim AS build
COPY src /server/unamano/src
COPY pom.xml /server/unamano
RUN mvn -f /server/unamano/pom.xml clean package


#
# Package stage
#
FROM openjdk:17
MAINTAINER tesla
COPY --from=build /server/unamano/target/unamano-0.0.1-SNAPSHOT.jar  /usr/local/lib/unamano-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/unamano-app.jar"]

