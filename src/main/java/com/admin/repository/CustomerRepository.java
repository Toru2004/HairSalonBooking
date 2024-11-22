package com.admin.repository;

import com.admin.model.Customer;
import com.admin.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    public Long countById(Integer id);
}
