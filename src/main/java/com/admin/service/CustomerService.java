package com.admin.service;

import com.admin.exception.CustomerNotFoundException;
import com.admin.model.Customer;
import com.admin.model.User;
import com.admin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
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

//    public String registerCustomer(Customer customer) throws NoSuchAlgorithmException {
//        // Kiểm tra xem người dùng đã tồn tại chưa
//        UserRepository
//        if (customerRepository.findByEmail(customer.getUser().getEmail()).isPresent()) {
//            return "Username already exists";
//        }
//
//        customer.getUser().setRole("customer");
//
//        // Mã hóa mật khẩu trước khi lưu (dùng MD5, có thể thay thế với bcrypt nếu cần)
////        String encodedPassword = encodePassword(user.getPassword());
////        user.setPassword(encodedPassword);
//
//        // Lưu người dùng vào cơ sở dữ liệu
//        customerRepository.save(customer);
//        return "User registered successfully";
//    }

}
