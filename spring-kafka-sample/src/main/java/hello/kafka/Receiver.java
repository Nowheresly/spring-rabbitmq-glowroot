package hello.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @Autowired
    CustomerRepository customerRepository;

    @KafkaListener(topics = "someTopic")
    public void execute(final GenericMessage<String> message) throws Throwable {

        System.out.println("Receiving " + message);

        customerRepository.findAll();

        if(Math.random() > .8d) {
            long pauseTime = (long)(Math.random() * 5000d);
            System.out.println("pausing " + pauseTime);
            Thread.sleep(pauseTime);
        }
        if(Math.random() > .9d) {
            throw new Throwable();
        }
    }
}