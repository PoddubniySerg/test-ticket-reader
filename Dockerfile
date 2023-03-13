FROM amazoncorretto:19

ADD assets/tickets.json assets/tickets.json

ENTRYPOINT ["java", "-jar", "app.jar"]

ADD target/tickets-reader-0.0.1-SNAPSHOT.jar /app.jar