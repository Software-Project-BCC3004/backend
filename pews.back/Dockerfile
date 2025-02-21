FROM openjdk:17
LABEL authors="gabri"
WORKDIR /projeto/pews
COPY target/pews.back-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/projeto/pews/app.jar"]