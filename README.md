# A simple Cloud Foundry Spring Boot web app

## To run locally:

1. Start up the server component
    ```
    mvn spring-boot:run -Dspring-boot.run.profiles=local
    ```
2. Start up the front end dev server \
    (in another terminal)
    ```
    cd ui && yarn start
    ```

The `local` profile uses basic auth, and the usernames can be found in [users.properties](src/main/resources/env/local/users.properties).

## To push a clean copy of the prod artifact (if testing in a branch):

```
mvn clean package -Pprod
cf push
```
