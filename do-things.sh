docker-compose -p sandbox build
docker-compose -p sandbox up -d
docker-compose -p sandbox scale jslave=3