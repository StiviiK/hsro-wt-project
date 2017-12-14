# HSRO - WT Project [![Build Status](https://travis-ci.com/StiviiK/hsro-wt-project.svg?token=Qq3qpzFwq5TTKaWqR5rv&branch=master)](https://travis-ci.com/StiviiK/hsro-wt-project)

## Deployment
The deployment runs automatically with [Travis CI](https://travis-ci.org),
travis automatically builds (on every commit) the Docker-Images and uploads them (on every tag-push) to [GitHub Releases](https://github.com/StiviiK/hsro-wt-project/releases).

## Why docker-compose
`docker-compose` creates and runs two images for the website, the first one is the Frontend with a `nginx` webserver which serves the static html, the seccond is the `playframework` Backend. Running the project as mentioned below, will create two indepentend containers - which will communicate with each other.

## Download
To run the site, you need to download `docker-compose.yml`, `FRONTEND_IMAGE.tar` and `BACKEND_IMAGE.tar` from [GitHub Releases](https://github.com/StiviiK/hsro-wt-project/releases). After downloading the archives, run the following two commands to load the images into your local registry:
- `docker load < FRONTEND_IMAGE.tar`
- `docker load < BACKEND_IMAGE.tar`

## Running
To the run the web image you need `docker-compose`:
- `docker-compose up -d`

## Stopping
Stop the container
- `docker-compose stop`

## Clean up docker-compose
- `docker-compose rm`