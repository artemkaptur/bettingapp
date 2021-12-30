FROM openjdk:15

WORKDIR /app

COPY ./target/universal/stage .

EXPOSE 8883

CMD ["./bin/bettingapp"]