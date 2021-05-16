FROM adoptopenjdk/openjdk11:latest
ENV JAVA_OPTS=""
VOLUME /tmp
ADD target/predict.jar app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]