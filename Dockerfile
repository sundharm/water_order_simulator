FROM openjdk:8
ADD target/RestWaterSImulator-0.0.1-SNAPSHOT.jar RestWaterSImulator-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","RestWaterSImulator-0.0.1-SNAPSHOT.jar"]