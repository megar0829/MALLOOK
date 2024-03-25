FROM node:20.11.1-alpine
WORKDIR /app
COPY package*.json /app
RUN npm install
COPY . /app
RUN npm run build
CMD ["npm", "run", "dev"]