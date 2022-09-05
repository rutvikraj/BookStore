FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/Eureka_Server-0.0.1-SNAPSHOT.jar eureka-server.jar
ENTRYPOINT ["java","-jar","eureka-server.jar"]