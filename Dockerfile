FROM openjdk:8
EXPOSE 8080
ADD target/greet-me.jar greet-me.jar
ENTRYPOINT ["java", "-jar", "/greet-me.jar"]