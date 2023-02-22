package webflux.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.model.Customer;

public interface CustomerService {

    Mono<Customer> validateAndGetCustomer(Long id);

    Flux<Customer> getCustomers();

    Mono<Customer> saveCustomer(Customer customer);

    Mono<Void> deleteCustomer(Customer customer);
}