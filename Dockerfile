#基础镜像通过java8来的
FROM openjdk:8-jre-alpine

VOLUME /tmp

EXPOSE 8080

ADD web-api/target/web-api.jar app.jar
ENTRYPOINT ["nohup","java","-Xms512m","-Xmx512m","-Xmn512m","-Dspring.profiles.active=local","-jar","/app.jar"]
