package com.admin.service;

import com.admin.exception.CustomerNotFoundException;
import com.admin.model.Customer;
import com.admin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired private CustomerRepository customerRepository;

    public List<Customer> listAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer get(Integer id) throws CustomerNotFoundException {
        Optional<Customer> result = customerRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new CustomerNotFoundException("Could not find any customers with ID " + id);
    }

    public void delete(Integer id) throws CustomerNotFoundException {
        Long count = customerRepository.countById(id);
        if (count == null || count == 0) {
            throw new CustomerNotFoundException("Could not find any customers with ID " + id);
        }
        customerRepository.deleteById(id);
    }
}
