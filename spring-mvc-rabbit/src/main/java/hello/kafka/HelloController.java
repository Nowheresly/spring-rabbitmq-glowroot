package hello.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/hello/{name}")
    public ResponseEntity<String> getHello(@PathVariable("name") String name) throws Throwable {
        System.out.println("rest endpoint called name="+name);
        List<Customer> user = customerRepository.findByLastName(name);

        if(Math.random() > .8d) {
            long pauseTime = (long)(Math.random() * 5000d);
            System.out.println("pausing " + pauseTime);
            Thread.sleep(pauseTime);
        }
        if(Math.random() > .9d) {
            throw new Throwable();
        }

        return ResponseEntity.ok("Hello "+name);
    }
}
