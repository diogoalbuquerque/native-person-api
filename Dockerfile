FROM ghcr.io/graalvm/graalvm-ce:latest

ADD . /build
WORKDIR /build

RUN ./mvnw -Pnative -DskipTests clean package

ENTRYPOINT [ "sh", "-c", "./application/target/application" ]

EXPOSE 8080
