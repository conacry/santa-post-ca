FROM adoptopenjdk:11-jre-hotspot
RUN mkdir -p /opt/santa-post-ca
COPY /target/santa-post-ca-*.jar /opt/santa-post-ca/santa-post-ca-app.jar
WORKDIR /opt/santa-post-ca
ENTRYPOINT java -jar santa-post-ca-app.jar