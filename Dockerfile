FROM openjdk:11.0.3-jdk-slim-stretch
ADD ./target/muzix-service-0.0.1-SNAPSHOT.jar /app/muzix/muzix-service-0.0.1-SNAPSHOT.jar
WORKDIR app/muzix
ENTRYPOINT ["java","-jar","muzix-service-0.0.1-SNAPSHOT.jar"]