package com.admin.service;

import com.admin.model.Customer;
import com.admin.model.User;
import com.admin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired private CustomerRepository customerRepo;

    public List<Customer> listAll() {
        return (List<Customer>) customerRepo.findAll();
    }

    public void save(Customer customer) {
        customerRepo.save(customer);
    }

    public Customer get(Integer id) {
        Optional<Customer> result = customerRepo.findById(id);
        return result.orElse(null);  // Return null if not found
    }

    public void delete(Integer id) {
        customerRepo.deleteById(id);
    }
}
