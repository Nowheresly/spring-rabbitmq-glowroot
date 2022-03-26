package hello;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HelloCaller {

    private final RestTemplate restTemplate;

    public HelloCaller() {

        this.restTemplate = new RestTemplate();
    }

    @Scheduled(fixedRate = 2000L)
    public void callHello() {
        System.out.println("Calling rest endpoint");
        restTemplate.getForObject("http://localhost:8080/hello", String.class);
    }
}
