package com.admin.controller;

import com.admin.exception.CustomerNotFoundException;
import com.admin.model.Customer;
import com.admin.service.CustomerService;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    // Hiển thị danh sách tất cả khách hàng
    @GetMapping("/manageCustomers")
    public String showCustomerList(Model model) {
        List<Customer> listCustomers = customerService.listAll(); // Lấy danh sách tất cả khách hàng
        model.addAttribute("listCustomers", listCustomers); // Đưa danh sách khách hàng vào model để hiển thị trong view
        return "admin/manageCustomers"; // Trả về view manageCustomers.html
    }

    // Hiển thị form để thêm khách hàng mới
    @GetMapping("/manageCustomers/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer()); // Tạo đối tượng Customer mới để sử dụng trong form
        model.addAttribute("pageTitle", "Add New Customer"); // Thiết lập tiêu đề trang
        return "admin/customer_form"; // Trả về view customer_form.html để thêm mới khách hàng
    }

    // Lưu thông tin khách hàng
    @PostMapping("/manageCustomers/save")
    public String saveCustomer(Customer customer, RedirectAttributes ra) {
        customer.getUser().setRole("customer");
        customerService.save(customer); // Lưu thông tin khách hàng vào cơ sở dữ liệu
        ra.addFlashAttribute("message", "The customer has been saved successfully."); // Thông báo lưu thành công
        return "redirect:/manageCustomers"; // Chuyển hướng về trang danh sách khách hàng
    }

    // Hiển thị form để chỉnh sửa thông tin khách hàng
    @GetMapping("/manageCustomers/edit/{id}")
    public String showEditCustomerForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Customer customer = customerService.get(id); // Lấy thông tin khách hàng theo ID
            model.addAttribute("customer", customer); // Đưa thông tin khách hàng vào model để hiển thị trong form
            model.addAttribute("pageTitle", "Edit Customer (ID: " + id + ")"); // Thiết lập tiêu đề trang

            return "admin/customer_form";  // Trả về view customer_form.html để chỉnh sửa khách hàng
        } catch (CustomerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage()); // Thông báo lỗi nếu không tìm thấy khách hàng
            return "redirect:/manageCustomers"; // Chuyển hướng về trang danh sách khách hàng
        }
    }

    // Xóa khách hàng theo ID
    @GetMapping("/manageCustomers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            customerService.delete(id); // Xóa khách hàng theo ID
            ra.addFlashAttribute("message", "The customer ID " + id + " has been deleted."); // Thông báo xóa thành công
        } catch (CustomerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage()); // Thông báo lỗi nếu không tìm thấy khách hàng
        }
        return "redirect:/manageCustomers"; // Chuyển hướng về trang danh sách khách hàng
    }
}
