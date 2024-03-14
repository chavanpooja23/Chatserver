FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM maven:3.8.3-openjdk-17-slim
COPY --FROM=build /target/ChatserverApplication-0.0.1-SNAPSHOT.jar ChatserverApplication.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ChatserverApplication.jar"]