package com.admin.service;

import com.admin.model.Customer;
import com.admin.repository.CustomerRepository;
import com.admin.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired private CustomerRepository repo;

    public List<Customer> listAll() {
        return (List<Customer>) repo.findAll();
    }

    public void save(Customer customer) {
        repo.save(customer);
    }

    public Customer get(Integer id) throws UserNotFoundException {
        Optional<Customer> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any customers with ID " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any customers with ID " + id);
        }
        repo.deleteById(id);
    }
}
