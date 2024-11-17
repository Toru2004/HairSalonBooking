package com.admin.controller;

import com.admin.model.User;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Đăng ký người dùng
    @PostMapping("/signup")
    public String registerUser(@RequestParam String username, @RequestParam String email,
                               @RequestParam String phone, @RequestParam String password, Model model) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPhoneNumber(phone);
            user.setPassword(password); // Mật khẩu chưa mã hóa

            String result = userService.registerUser(user);
            model.addAttribute("message", result);

            return "view/pages/signup"; // Trở lại trang đăng ký với thông báo
        } catch (NoSuchAlgorithmException e) {
            model.addAttribute("error", "Error while encoding password");
            return "view/pages/signup"; // Nếu có lỗi trong việc mã hóa mật khẩu
        }
    }

    // Đăng nhập người dùng
    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            Optional<User> user = userService.loginUser(email, password);
            if (user.isPresent()) {
                // Lưu username vào session
                model.addAttribute("message", "Login successful");
                if ("admin".equals(user.get().getRole())) {
                    return "redirect:/manageUsers"; // Chuyển hướng đến trang admin nếu là admin
                } else {
                    return "view/pages/home"; // Chuyển hướng đến trang chủ nếu không phải admin
                }
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "view/pages/login"; // Trở lại trang đăng nhập với lỗi
            }
        } catch (NoSuchAlgorithmException e) {
            model.addAttribute("error", "Error while encoding password");
            return "view/pages/login"; // Trở lại trang đăng nhập nếu có lỗi mã hóa
        }
    }
}
