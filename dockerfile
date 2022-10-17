from openjdk:17-alpine
MAINTAINER github/mmarcosab
COPY ./target/key-processor-0.0.1-SNAPSHOT.jar /app/key-processor-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "key-processor-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080