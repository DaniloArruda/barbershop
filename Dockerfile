FROM amazoncorretto:11-alpine-jdk
LABEL maintainer="DaniloArruda"
COPY target/barbershop-0.0.1-SNAPSHOT.jar barbershop-0.0.1.jar
ENTRYPOINT ["java","-jar","/barbershop-0.0.1.jar"]