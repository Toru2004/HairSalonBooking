package com.admin.controller;

import com.admin.model.User;
import com.admin.exception.UserNotFoundException;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;  // Dịch vụ quản lý người dùng

    // Hiển thị danh sách tất cả người dùng
    @GetMapping("/manageUsers")
    public String showUserList(HttpServletRequest request, Model model) {
        List<User> listUsers = userService.listAll(); // Lấy danh sách người dùng từ dịch vụ
        model.addAttribute("listUsers", listUsers); // Đưa danh sách vào model để hiển thị trong view

        // Lấy vai trò từ session
        String role = (String) request.getSession().getAttribute("role");

        // Kiểm tra nếu không phải staff thì chuyển hướng
        if (role == null || !role.equals("admin")) {
            return "redirect:/page/login"; // Chuyển hướng đến trang Access Denied
        }
        return "admin/manageUsers"; // Trả về view manageUsers.html
    }

    // Hiển thị form để thêm người dùng mới
    @GetMapping("/manageUsers/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User()); // Tạo đối tượng User mới cho form
        model.addAttribute("pageTitle", "Add New User"); // Thiết lập tiêu đề trang
        return "admin/user_form"; // Trả về view user_form.html để thêm người dùng
    }

    // Lưu thông tin người dùng
    @PostMapping("/manageUsers/save")
    public String saveUser(User user, RedirectAttributes ra) {
        try {
            if (user.getId() != null) { // Nếu có ID thì thực hiện cập nhật
                User existingUser = userService.get(user.getId());
                existingUser.setUsername(user.getUsername());
                existingUser.setEmail(user.getEmail());
                existingUser.setPhoneNumber(user.getPhoneNumber());
                existingUser.setPassword(user.getPassword());
                existingUser.setEnabled(user.isEnabled());
                userService.save(existingUser); // Lưu bản ghi đã chỉnh sửa
            } else {
                // Nếu không có ID, thêm mới
                userService.save(user);
            }
            ra.addFlashAttribute("message", "The user has been saved successfully."); // Thông báo thành công
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "Failed to save the user: " + e.getMessage()); // Thông báo lỗi
        }
        return "redirect:/manageUsers"; // Quay lại danh sách người dùng
    }

    // Hiển thị form để chỉnh sửa thông tin người dùng
    @GetMapping("/manageUsers/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = userService.get(id); // Lấy thông tin người dùng theo ID
            model.addAttribute("user", user); // Đưa thông tin người dùng vào model để hiển thị trong form
            model.addAttribute("pageTitle", "Edit User"); // Thiết lập tiêu đề trang
            return "admin/user_form"; // Trả về view user_form.html để chỉnh sửa người dùng
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage()); // Thông báo lỗi nếu không tìm thấy người dùng
            return "redirect:/manageUsers"; // Chuyển hướng về trang danh sách người dùng
        }
    }

    // Xóa người dùng theo ID
    @GetMapping("/manageUsers/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            userService.delete(id); // Xóa người dùng theo ID
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted."); // Thông báo xóa thành công
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage()); // Thông báo lỗi nếu không tìm thấy người dùng
        }
        return "redirect:/manageUsers"; // Chuyển hướng về trang danh sách người dùng
    }
}