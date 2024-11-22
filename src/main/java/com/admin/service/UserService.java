package com.admin.service;

import com.admin.model.Customer;
import com.admin.model.User;
import com.admin.repository.CustomerRepository;
import com.admin.repository.UserRepository;
import com.admin.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Mã hóa mật khẩu thủ công
    private String encodePassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with ID " + id);
        }
        userRepository.deleteById(id);
    }
    public String registerCustomer(Customer customer) throws NoSuchAlgorithmException {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (userRepository.findByEmail(customer.getUser().getEmail()).isPresent()) {
            return "Username already exists";
        }

        // Thiết lập mặc định role cho người dùng
        User user = customer.getUser();
        user.setRole("customer");

        // Mã hóa mật khẩu trước khi lưu (dùng MD5, có thể thay thế với bcrypt nếu cần)
//        String encodedPassword = encodePassword(user.getPassword());
//        user.setPassword(encodedPassword);
        customer.setUser(user);
        // Lưu người dùng vào cơ sở dữ liệu
        customerRepository.save(customer);
        return "User registered successfully";
    }

    public Optional<User> loginUser(String email, String password) throws NoSuchAlgorithmException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // Kiểm tra mật khẩu sau khi mã hóa
//            String encodedPassword = encodePassword(password);
            if (password.equals(user.get().getPassword())) {
                return user;
            }
        }
        return Optional.empty();
    }
}
