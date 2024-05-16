FROM openjdk:21-slim
EXPOSE 8082
COPY /target/*.jar /app.jar
ENTRYPOINT ["sh","-c","java -jar app.jar"]