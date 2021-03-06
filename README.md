# HSRO - WT Project [![Build Status](https://travis-ci.org/StiviiK/hsro-wt-project.svg?branch=master)](https://travis-ci.org/StiviiK/hsro-wt-project)

## Deployment
The deployment runs automatically with [Travis CI](https://travis-ci.org),
travis automatically builds (on every commit) the Docker-Images and uploads them (on every tag-push) to [GitHub Releases](https://github.com/StiviiK/hsro-wt-project/releases).

## Why docker-compose
`docker-compose` creates and runs four images for the website, the first one is the Frontend with a `nginx` webserver which serves the static html, the seccond is the `playframework` Backend. The third and the fourth containers are the Database and PhpMyAdmin for administration. Running the project as mentioned below, will create four indepentend containers - which will communicate with each other.

## Download
To run the site, you need to download `docker-compose.yml`, `FRONTEND_IMAGE.tar` and `BACKEND_IMAGE.tar` from [GitHub Releases](https://github.com/StiviiK/hsro-wt-project/releases). After downloading the archives, run the following two commands to load the images into your local registry:
- `docker load < FRONTEND_IMAGE.tar`
- `docker load < BACKEND_IMAGE.tar`

## Running
To the run the web image you need `docker-compose`:
- `docker-compose up -d`

### Known issues
On the first run of `docker-compose up` the backened sometimes will crash.
Just wait for all other services (containers) to be started completely, then `Strg + C` out of the command and run the following command:
- `docker ps -a`

You should see a list of all containers. There should be an container with the image `stiviik/hsro-wt-project/backend`, get the container name and run the following command and replace `<CONTAINER>` with the name you got:

- `docker rm <CONTAINRR>`

If you retry the `docker-compose up` now, everything should run without an container to crash.

## Stopping
Stop the services
- `docker-compose stop`

## Clean up docker-compose
- `docker-compose rm`
