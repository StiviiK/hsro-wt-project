# Stage 1 - Builder
FROM node:alpine AS builder

# Create build dir
RUN mkdir -p /usr/src/app/build/
WORKDIR /usr/src/app/build/

# Copy package.json
ADD package.json .

# Install depedencys
RUN npm set progress=true && npm config set depth 0 && npm cache clean --force
RUN npm install

# Add files
COPY . .

# Build the angular app
RUN $(npm bin)/ng build --prod --build-optimizer


# Stage 2 - Runner
FROM nginx:alpine

# Remove default html stuff
RUN rm -r -f /usr/share/nginx/html/*

# Add build static html
COPY --from=builder /usr/src/app/build/dist /usr/share/nginx/html

# Copy our default nginx config
COPY nginx/default.conf /etc/nginx/conf.d/

# Run the webserver
CMD ["nginx", "-g", "daemon off;"]
