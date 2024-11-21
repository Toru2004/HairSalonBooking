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
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;


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
                // Lưu thông tin vào session
                session.setAttribute("username", user.get().getUsername());
                session.setAttribute("role", user.get().getRole());

                // Tạo phản hồi JSON
                Map<String, String> response = new HashMap<>();
                response.put("username", user.get().getUsername());
                response.put("role", user.get().getRole());

                // Xử lý phân quyền theo role
                String role = user.get().getRole();
                switch (role) {
                    case "admin":
                        response.put("redirect", "/ViewAdmins");
                        break;
                    case "staff":
                        response.put("redirect", "/Staff/staffDashboard");
                        break;
                    case "manager":
                        response.put("redirect", "/manager/managerDashboard");
                        break;
                    default:
                        response.put("redirect", "/page/home"); // Mặc định là user
                }

                return ResponseEntity.ok(response);
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate(); // Xóa toàn bộ dữ liệu trong session
        return "redirect:/page/login"; // Chuyển hướng về trang login
    }


}
