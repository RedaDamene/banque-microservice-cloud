package dev.damene.customerservice;

import dev.damene.customerservice.config.GlobalConfig;
import dev.damene.customerservice.entities.Customer;
import dev.damene.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer customer = Customer.builder()
                    .firstName("Reda")
                    .lastName("Damene")
                    .email("reda@gmail.com")
                    .build();
            customerRepository.save(customer);

            Customer customer1 = Customer.builder()
                    .firstName("Jean")
                    .lastName("Cerien")
                    .email("jean@gmail.com")
                    .build();
            customerRepository.save(customer1);
        };
    }
}
