package hello.kafka;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    CustomerRepository customerRepository;

    @RabbitListener(queues = Application.queueName)
    public void receiver(String message) throws Throwable {

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
