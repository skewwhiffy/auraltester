package com.skewwhiffy.auraltester.repository;

import java.util.List;

import com.skewwhiffy.auraltester.dto.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}
