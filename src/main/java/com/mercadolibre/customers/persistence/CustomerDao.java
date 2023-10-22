package com.mercadolibre.customers.persistence;

import com.mercadolibre.customers.entity.CustomerDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class CustomerDao {
    public List<CustomerDTO> getCustomers() {
        return Stream.of(
                        new CustomerDTO(101, "Mobile", 1, 30000),
                        new CustomerDTO(58, "Book", 4, 2000),
                        new CustomerDTO(205, "Laptop", 1, 150000),
                        new CustomerDTO(809, "headset", 1, 1799))
                .collect(Collectors.toList());
    }
}
