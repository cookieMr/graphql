# GraphQL implementation

TypeScript code of this project is a part of me doing a Udemy Course 
[AWS AppSync & Amplify with React & GraphQL - Complete Guide](https://www.udemy.com/course/aws-appsync-amplify-with-react-graphql-course/).

Java code of this project is a part of me extending the knowledge on GraphQL and I used this Youtube Playlist to understand Java implementation: [Spring Boot GraphQL Tutorial - Full Course](https://www.youtube.com/playlist?list=PLiwhu8iLxKwL1TU0RMM6z7TtkyW-3-5Wi). The author has this course on [Github](https://github.com/philip-jvm/learn-spring-boot-graphql).

Docker files are an additional parts that make this repo run smoothly.

# Starting up GraphQL

To run all docker containers execute `docker-compose up --build` comman in the root direcotry. This will run four containers:
1. MongoDB that will be accessible on default port `27017`.
1. Mongo-Express, a web GUI to interact with MongoDB.
1. Node App with two GraphQL implementations (on two different URLs).
1. A Java Microservice that exposes:
   * few REST endpoints,
   * the main GraphQL endpoint,
   * Playground endpoint
   * and Voyager endpoint.

To stop all of these containers run `docker-compose down`. And the subsequent start of these containers can be faster if run without `--build` option since all of these containers are already built. Execute just `docker-compose up`.

## Connecting to Mongo DB

First, the easy way, is to open a web page at [localhost:8081](http://localhost:8081) which is an exposed Mongo-Express page to mange Mongo database.

Second way is to connect directly to a Mongo DB (e.g. with the VS Code) use the following connection string:
```
mongodb://mongo_user:my_very_secret_mongo_password@127.0.0.1:27017/graph_ql_db?authSource=admin
```

## Interacting with GraphQL

The Node App has two URLs. One uses an internal variables (in TypeScript code) to store data. The second URL uses the mentioned Mongo DB to store data. These URLs are:
 * http://localhost:4000/graphql
 * http://localhost:4000/graphql_mongo

The Java Microservice has two URLs related to GraphQL. One URL is expose documentation and a place to play with GraphQL queries and mutations. The other URL is to visualize relations between objects. Data are stored in the same Mongo DB as Node App uses.
 * http://localhost:8888/playground
 * http://localhost:8888/voyager

## Interacting with REST Endpoints

These are a Spring Boot Microservice endpoints and they can be used either by Postman or via Swagger page avaliable at localhost:
 * http://localhost:8888/swagger-ui.html

These REST endpoints provides the same functionality as GraphQL endpoint in Java Microservice. Data are stored in the same Mongo DB as GraphQL Java endpoint a d Node App use.
