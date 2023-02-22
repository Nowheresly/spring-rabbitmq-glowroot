package webflux.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import webflux.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CustomerResponse> getCustomers() {
        return customerService.getCustomers().map(customerMapper::toCustomerResponse);
    }

    @GetMapping("/{id}")
    public Mono<CustomerResponse> getCustomer(@PathVariable Long id) {
        return customerService.validateAndGetCustomer(id).map(customerMapper::toCustomerResponse);
    }

    @PostMapping
    public Mono<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        Customer customer = customerMapper.toCustomer(createCustomerRequest);
        return customerService.saveCustomer(customer).map(customerMapper::toCustomerResponse);
    }

    @PatchMapping("/{id}")
    public Mono<CustomerResponse> updateCustomer(@PathVariable Long id,
                                                 @Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return customerService.validateAndGetCustomer(id)
                .doOnSuccess(customer -> {
                    customerMapper.updateCustomerFromRequest(updateCustomerRequest, customer);
                    customerService.saveCustomer(customer).subscribe();
                })
                .map(customerMapper::toCustomerResponse);
    }

    @DeleteMapping("/{id}")
    public Mono<CustomerResponse> deleteCustomer(@PathVariable Long id) {
        return customerService.validateAndGetCustomer(id)
                .doOnSuccess(customer -> customerService.deleteCustomer(customer).subscribe())
                .map(customerMapper::toCustomerResponse);
    }
}