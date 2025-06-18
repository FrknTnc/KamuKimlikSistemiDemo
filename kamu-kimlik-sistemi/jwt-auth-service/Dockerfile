# 1. React projesini build et
FROM node:20-bullseye AS builder
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# 2. Nginx ile statik olarak sun
FROM nginx:1.25.3
COPY --from=builder /app/build /usr/share/nginx/html
EXPOSE 80
