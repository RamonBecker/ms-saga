FROM amazoncorretto:21
COPY target/inventory-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "app.jar"]