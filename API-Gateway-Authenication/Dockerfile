FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/API-GateWay-Authentication.jar api-gateway-auth.jar
ENTRYPOINT ["java","-jar","api-gateway-auth.jar"]