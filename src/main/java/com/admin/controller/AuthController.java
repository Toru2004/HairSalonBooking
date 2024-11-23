package com.admin.controller;

import com.admin.model.Customer;
import com.admin.model.User;
import com.admin.service.CustomerService;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;



@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    // Đăng ký người dùng
    @PostMapping("/signup")
    public String registerUser(@RequestParam String username, @RequestParam String email,
                               @RequestParam String phone, @RequestParam String password, Model model) {
        try {
//            User user = new User();
            Customer customer = new Customer();

            if (customer.getUser() == null) {
                customer.setUser(new User()); // Khởi tạo đối tượng User nếu chưa có
            }

            customer.getUser().setUsername(username);
            customer.getUser().setEmail(email);
            customer.getUser().setPhoneNumber(phone);
            customer.getUser().setPassword(password); // Mật khẩu chưa mã hóa

            String result = userService.registerCustomer(customer);
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
                // Kiểm tra nếu tài khoản bị vô hiệu hóa
                if (!user.get().isEnabled()) {
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Your account has been disabled. Please contact support.");
                    return ResponseEntity.status(403).body(error); // HTTP 403: Forbidden
                }

                // Lưu thông tin vào session
                session.setAttribute("id", user.get().getId());
                session.setAttribute("username", user.get().getUsername());
                session.setAttribute("role", user.get().getRole());

                // Tạo phản hồi JSON
                Map<String, String> response = new HashMap<>();
                response.put("id", user.get().getId().toString());
                response.put("username", user.get().getUsername());
                response.put("role", user.get().getRole());

                // Xử lý phân quyền theo role
                String role = user.get().getRole().toLowerCase(); // Đảm bảo role là chữ thường
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
                    case "stylist":
                        response.put("redirect", "/stylist/stylistDashboard");
                        break;
                    default:
                        response.put("redirect", "/page/home"); // Mặc định là user
                }

                return ResponseEntity.ok(response);
            } else {
                // Trả về lỗi nếu đăng nhập không thành công
                Map<String, String> error = new HashMap<>();
                error.put("error", "Invalid email or password");
                return ResponseEntity.status(401).body(error); // HTTP 401: Unauthorized
            }
        } catch (NoSuchAlgorithmException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error during password encryption.");
            return ResponseEntity.status(500).body(error); // HTTP 500: Internal Server Error
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate(); // Xóa toàn bộ dữ liệu trong session

        // Xóa cookies liên quan nếu cần (ví dụ: để làm sạch session hoặc token)
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);  // Thiết lập thời gian sống của cookie là 0, để xóa cookie
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/page/login"; // Chuyển hướng về trang login
    }



}
