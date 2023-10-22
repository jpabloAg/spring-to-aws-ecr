package com.mercadolibre.customers.controller;

import com.mercadolibre.customers.entity.CustomerDTO;
import com.mercadolibre.customers.persistence.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Value("${spring.application.environment}")
    private String env;

    @Autowired
    private CustomerDao customerDao;

    @GetMapping()
    public ResponseEntity<List<CustomerDTO>> getCustomers(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDao.getCustomers());
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
