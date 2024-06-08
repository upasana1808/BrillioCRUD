FROM openjdk:8
EXPOSE 8080
ADD target/SpringJPAApp-0.0.1-SNAPSHOT.jar SpringJPAApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/SpringJPAApp-0.0.1-SNAPSHOT.jar"]