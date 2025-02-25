from openjdk:21
copy target/home-0.0.1-SNAPSHOT.jar home.jar
ENTRYPOINT [ "java", "-jar", "home.jar"]