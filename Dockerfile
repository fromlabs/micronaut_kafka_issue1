FROM openjdk:14-alpine
COPY target/issue1-*.jar issue1.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "issue1.jar"]