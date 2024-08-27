FROM openjdk:17
COPY target/greet-me.jar greet-me.jar
ENTRYPOINT ["java", "-jar", "/greet-me.jar"]