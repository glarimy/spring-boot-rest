1. Build the package (from the directory where pom.xml is present)
mvn clean package

2. Dockerize (from the directory where Dockerfile is present)
docker build -t glarimy-cisco-library .

3. Tag the image
docker tag glarimy-cisco-library:latest <username>/glarimy-cisco-library:latest

4. Push the image to DockerHub
docker login (if not logged in already)
docker push <username>/glarimy-cisco-library:latest

5. Deploy and run the containers (from the directory where docker-compose.yml is present)
docker-compose up