#!/bin/sh

# Espera a que los servicios de backend estén completamente iniciados
while ! curl -s do_mining_api_1:8080/api/v1/ping > /dev/null; do
    echo "Waiting for do_mining_api_1 to start..."
    sleep 1
done

while ! curl -s do_mining_api_2:8080/api/v1/ping > /dev/null; do
    echo "Waiting for do_mining_api_2 to start..."
    sleep 1
done

# Inicia Nginx
nginx -g 'daemon off;'