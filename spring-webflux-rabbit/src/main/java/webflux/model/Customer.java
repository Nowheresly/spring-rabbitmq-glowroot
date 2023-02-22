package webflux.model;

import org.springframework.data.annotation.Id;

public class Customer {

    @Id
    private Long id;

    private String name;
    private String email;
    private String city;
    private String street;
    private String number;
}