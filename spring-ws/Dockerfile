FROM openjdk:8
# this new image will be create from parent image = openjdk:8(stable)

# Create app directory inside docker image
WORKDIR /usr/app


COPY  target/spring-ws.jar  ./

#optimisation possible : https://spring.io/guides/topicals/spring-boot-docker

EXPOSE 8080

CMD ["java","-jar","spring-ws.jar" ]