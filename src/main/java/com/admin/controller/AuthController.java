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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<Map<String, String>> loginUser(@RequestParam String email,
                                                         @RequestParam String password,
                                                         HttpSession session) {
        try {
            Optional<User> user = userService.loginUser(email, password);
            if (user.isPresent()) {
                // Lưu username vào session
                session.setAttribute("username", user.get().getUsername());

                // Tạo phản hồi JSON trả về frontend
                Map<String, String> response = new HashMap<>();
                response.put("username", user.get().getUsername());
                response.put("role", user.get().getRole());

                return ResponseEntity.ok(response); // Trả về JSON với thông tin người dùng
            } else {
                // Trả về lỗi nếu đăng nhập không thành công
                Map<String, String> error = new HashMap<>();
                error.put("error", "Invalid email or password");
                return ResponseEntity.status(401).body(error);
            }
        } catch (NoSuchAlgorithmException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error during password encryption.");
            return ResponseEntity.status(500).body(error);
        }
    }



}
