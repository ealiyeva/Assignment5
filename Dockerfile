FROM openjdk:11
EXPOSE 8081
ADD target/micronaut.jar micronaut.jar
ENTRYPOINT ["java","-jar","/micronaut.jar"]