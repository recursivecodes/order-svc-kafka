FROM openjdk:14-alpine
COPY build/libs/order-svc-*-all.jar order-svc.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "order-svc.jar"]