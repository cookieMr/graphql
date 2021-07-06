import cors from "cors";
import express from "express";
import mongoose from "mongoose";
import { graphqlHTTP } from "express-graphql";
import { graphQLSchema } from "./schema/graphql_schema";
import { mongoQLSchema } from "./schema/mongodb_graphql_schema";

const port = 4000;

const mongoUser = "mongo_user";
const mongoPassword = "my_very_secret_mongo_password";
const mongoDb = "graph_ql_db";
const mongodbConStr = "mongodb://" + mongoUser + ":" + mongoPassword + "@docker-mongo:27017/" + mongoDb + "?authSource=admin";

const app = express();

mongoose.connect(
    mongodbConStr,
    {
        useNewUrlParser: true,
        useUnifiedTopology: true
    },
    (err) => {
        if (err) {
            console.log("Something went wrong: " + err);
        }
    });
mongoose.connection.once("open", () => console.log("The connection to MongoDB is up and running!"));

app.use(cors());

app.use('/graphql', graphqlHTTP({
    graphiql: true,
    schema: graphQLSchema
}));

app.use('/graphql_mongo', graphqlHTTP({
    graphiql: true,
    schema: mongoQLSchema
}));

app.listen(port, () => {
    console.log("Listening for request on my awesome port: " + port);
});
