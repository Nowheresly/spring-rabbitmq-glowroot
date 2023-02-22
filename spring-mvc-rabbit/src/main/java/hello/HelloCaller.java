package hello;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.MediaSize;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class HelloCaller {

    final Set<String> NAMES = new HashSet<>();
    private final RestTemplate restTemplate;

    public HelloCaller() {
        NAMES.add("john");
        NAMES.add("paul");
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(fixedRate = 2000L)
    public void callHello() {
        System.out.println("Calling rest endpoint");
        String name = NAMES.stream().skip(new Random().nextInt(NAMES.size())).findFirst().orElse(null);
        restTemplate.getForObject("http://localhost:8080/hello/"+name, String.class);
    }
}
