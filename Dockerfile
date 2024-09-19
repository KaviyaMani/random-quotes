FROM amazoncorretto:17-alpine-jdk
COPY target/greet-me.jar greet-me.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/greet-me.jar"]
