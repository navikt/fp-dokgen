#!/usr/bin/env bash
docker build --pull -t navikt/fpdokgen .
docker-compose up -d

export HEALTHCHECK_URL=localhost:8080/actuator/health
export CONTAINER=dokgen_dev
attempt_counter=0
max_attempts=15
printf 'Waiting for server         '
until $(docker run --rm --network container:$CONTAINER appropriate/curl --output /dev/null --silent --head --fail $HEALTHCHECK_URL); do
    if [ ${attempt_counter} -eq ${max_attempts} ];then
      echo "Max attempts reached, failing"
      exit 1
    fi
    printf '.'
    attempt_counter=$(($attempt_counter+1))
    sleep 2
done
printf ' done'
