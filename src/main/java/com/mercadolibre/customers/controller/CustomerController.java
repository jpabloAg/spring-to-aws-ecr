package com.mercadolibre.customers.controller;

import com.mercadolibre.customers.entity.Customer;
import com.mercadolibre.customers.persistence.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Value("${spring.application.environment}")
    private String env;

    @Autowired
    private ICustomerRepository customerRepository;

    @GetMapping()
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id){
        Optional<Customer> customerOptional = customerRepository.findById(id);

        return customerOptional.map(value -> ResponseEntity
                .status(HttpStatus.OK)
                .body(value)).orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null));
    }

    @PostMapping()
    public ResponseEntity<List<Customer>> saveCustomers(@RequestBody List<Customer> customers){
        List<Customer> customersSave = customerRepository.saveAll(customers);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customersSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomers(@PathVariable("id") Long id){
        customerRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("OBJETO ELIMINADO DE LA BASE DE DATOS " + id);
    }

    @GetMapping("/env")
    public ResponseEntity<String> getEnv(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(env);
    }

    @GetMapping("/ip")
    public  ResponseEntity<String> getIpAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Dirección IP del servidor:" + ipAddress);
        } catch (UnknownHostException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
