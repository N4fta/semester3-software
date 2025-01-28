FROM node:20-alpine

WORKDIR /app

COPY ./dist/ .

RUN npm i -g serve

EXPOSE 3000

CMD [ "serve", "-s", "." ]