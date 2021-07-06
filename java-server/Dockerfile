FROM gradle:7.1.1-jdk11

COPY . /app
WORKDIR /app

RUN sh ./gradlew clean build -x test && \
    pwd && ls && \
    ls -la ./build/libs

EXPOSE 8888:8888

CMD [ "java", "-jar", "./build/libs/Java GraphQL Microservice-0.1.jar" ]
