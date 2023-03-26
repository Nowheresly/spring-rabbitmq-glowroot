# spring-rabbitmq-glowroot
This project is a sample spring-boot app using rabbitmq aiming at testing glowroot rabbitmq support

## How to get started?

* First, start rabbitmq, cassandra and glowroot using the `docker-compose.yml` file.
Run:

`docker-compose up -d`

At this stage, by browsing http://localhost:4000, you should reach the glowroot console (this may take some time).

* then, build the project. Run:

`./gradlew clean build`

* Finally, run the application with glowroot agent. Run:

```
java  \
      -javaagent:path/to/glowroot.jar \
      -Dglowroot.agent.id=spring-rabbitmq \
      -Dglowroot.collector.address=localhost:8181 \
      -Dglowroot.conf.dir=/path/to/glowroot/confdir \
      -Dglowroot.log.dir=/path/to/glowroot/logdir \
      -Dglowroot.tmp.dir=/path/to/glowroot/tmpdir \
      -jar ./build/libs/spring-rabbitmq-glowroot-0.1.0.jar
```

This application has cron that will send messages into rabbitmq every 2 seconds.
The message is read, a database access is performed and we randomly wait for 5 seconds to simulate slow response times.


keytool -genseckey -alias myKey -keypass changeit -storepass changeit -keyalg AES -keysize 256 -keystore defaultStore.keystore -storetype  JCEKS