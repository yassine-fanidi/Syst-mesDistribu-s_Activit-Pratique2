package org.fanidiyassine.billingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.fanidiyassine.billingservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServiceRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customer-service", fallbackMethod = "getDefaultCustomer")
    Customer findByCustomerById(@PathVariable Long id);

    default Customer getDefaultCustomer(Long id, Exception exception){
        exception.printStackTrace();
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("default customer");
        customer.setEmail("default@gmail.com");
        return customer;
    }
}
