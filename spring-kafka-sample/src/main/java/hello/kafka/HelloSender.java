package hello.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public HelloSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 2000L)
    public void sendHello() {
        System.out.println("Sending message");
        kafkaTemplate.send("someTopic", "Hello from Kafka!");
    }

}