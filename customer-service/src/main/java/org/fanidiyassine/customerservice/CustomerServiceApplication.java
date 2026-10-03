package org.fanidiyassine.customerservice;

import org.fanidiyassine.customerservice.config.CustomerConfigParams;
import org.fanidiyassine.customerservice.entities.Customer;
import org.fanidiyassine.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository  customerRepository) {
        return args -> {
            customerRepository.save(new Customer(null, "Mohamed", "med@gmail.com"));
            customerRepository.save(Customer.builder().name("Yassine").email("yassine@gmail.com").build());
            customerRepository.save(Customer.builder().name("Imane").email("imane@gmail.com").build());
        };
    }

}
