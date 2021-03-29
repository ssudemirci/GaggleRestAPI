FROM openjdk:latest

ADD target/gaggle-api.jar gaggle-api.jar

EXPOSE 8085

ENTRYPOINT ["java","-jar","gaggle-api.jar"]





