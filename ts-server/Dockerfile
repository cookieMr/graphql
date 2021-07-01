FROM node:alpine

COPY . /app
WORKDIR /app

RUN npm config set fund false && \
    npm install -g npm typescript && \
    npm install cors lodash mongoose express express-graphql graphql --save && \
    npm install @types/cors @types/lodash @types/mongoose @types/express @types/express-graphql @types/graphql --save-dev && \
    tsc

EXPOSE 4000

CMD [ "node", "./dist/app.js" ]
