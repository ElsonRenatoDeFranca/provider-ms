FROM sensedia/openjdk17-base:latest
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/provider-ms.jar
ADD ${JAR_FILE} provider-ms.jar
ENTRYPOINT ["java","-jar","provider-ms.jar"]