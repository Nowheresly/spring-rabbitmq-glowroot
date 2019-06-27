package hello;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    CustomerRepository customerRepository;

    @RabbitListener(queues = Application.queueName)
    public void receiver(String message) throws InterruptedException {

        System.out.println("Receiving " + message);

        customerRepository.findAll();

        if(Math.random() > .9d) {
            Thread.sleep(5000L);
        }
    }
}
