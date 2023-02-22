package webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import webflux.model.Customer;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
}