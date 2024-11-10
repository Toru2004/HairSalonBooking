package com.admin.controller;

import com.admin.model.Customer;
import com.admin.service.CustomerService;
import com.admin.service.UserService;
import com.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    // Hiển thị danh sách khách hàng
    @GetMapping("/manageCustomers")
    public String showCustomerList(Model model) {
        List<Customer> customers = customerService.listAll(); // Lấy danh sách khách hàng
        model.addAttribute("customers", customers); // Truyền danh sách khách hàng vào view
        return "manageCustomers"; // Trả về view manageCustomers.html
    }

    // Hiển thị form thêm mới khách hàng
    @GetMapping("/manageCustomers/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer()); // Tạo đối tượng mới cho Customer
        model.addAttribute("users", userService.listAll()); // Truyền danh sách người dùng
        return "customer_form"; // Trả về form thêm mới khách hàng
    }

    // Lưu thông tin khách hàng
    @PostMapping("/manageCustomers/save")
    public String saveCustomer(Customer customer) {
        customerService.save(customer); // Lưu thông tin khách hàng
        return "redirect:/manageCustomers"; // Chuyển hướng về trang danh sách khách hàng sau khi lưu
    }

    // Xóa khách hàng
    @GetMapping("/manageCustomers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id) {
        customerService.delete(id); // Xóa khách hàng theo id
        return "redirect:/manageCustomers"; // Chuyển hướng về trang danh sách khách hàng sau khi xóa
    }
}
