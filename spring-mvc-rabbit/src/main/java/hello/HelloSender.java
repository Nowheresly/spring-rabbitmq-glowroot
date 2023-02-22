package hello;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {

    private final RabbitTemplate rabbitTemplate;

    public HelloSender(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 2000L)
    public void sendHello() {
        System.out.println("Sending message");
        rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
    }
}
