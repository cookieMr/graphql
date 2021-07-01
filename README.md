# GraphQL implementation

TypeScript code of this project is a part of me doing a Udemy Course 
[AWS AppSync & Amplify with React & GraphQL - Complete Guide](https://www.udemy.com/course/aws-appsync-amplify-with-react-graphql-course/).

Docker files are an additional part that makes this repo run smoothly.

# Starting up GraphQL

To run all docker containers execute `docker-compose up --build` comman in the root direcotry. This will run two containers. First will be a mongo DB, second container will be a Node app.

## Connecting to mongo DB

To connect to a mondo DB (e.g. with the VS Code) use the following connection string:
```
mongodb://mongo_user:my_very_secret_mongo_password@127.0.0.1:27017/graph_ql_db?authSource=admin
```

## Interacting with GraphQL via GraphiQL

The Node App has two URLs. One uses an internal variable (in TypeScript code) to store data. The second URL uses the mentioned Mongo DB to store data. These URLs are:
 * http://localhost:4000/graphql
 * http://localhost:4000/graphql_mongo
