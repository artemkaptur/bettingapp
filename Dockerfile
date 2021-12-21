FROM openjdk:15

WORKDIR /app

COPY ./target/universal/stage .

EXPOSE 8080

CMD ["./bin/bettingapp"]