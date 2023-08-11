FROM openjdk:17-alpine

WORKDIR /app

RUN apk add git maven wget

RUN git clone https://github.com/timerdar/authService

RUN mvn clean install -f authService

RUN wget -O /app/authService/jolokia.jar https://repo1.maven.org/maven2/org/jolokia/jolokia-jvm/1.7.2/jolokia-jvm-1.7.2.jar

CMD ["java", "-javaagent:/app/authService/jolokia.jar=host=0.0.0.0,port=8778", "-jar", "/app/authService/target/authService-0.0.1-SNAPSHOT.jar"]
