package com.admin.service;

import com.admin.model.Customer;
import com.admin.repository.CustomerRepository;
import com.admin.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // List all customers
    public List<Customer> listAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    // Save or update a customer
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    // Get a customer by ID
    public Customer get(Integer id) throws CustomerNotFoundException {
        Optional<Customer> result = customerRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new CustomerNotFoundException("Could not find any customer with ID " + id);
    }

    // Delete a customer by ID
    public void delete(Integer id) throws CustomerNotFoundException {
        Long count = customerRepository.countById(id);
        if (count == null || count == 0) {
            throw new CustomerNotFoundException("Could not find any customer with ID " + id);
        }
        customerRepository.deleteById(id);
    }
}
