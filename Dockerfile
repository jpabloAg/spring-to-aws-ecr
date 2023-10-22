FROM openjdk:17
EXPOSE 8080
ADD target/customers-service.jar customers-service.jar
ENTRYPOINT ["java","-jar","/customers-service.jar"]