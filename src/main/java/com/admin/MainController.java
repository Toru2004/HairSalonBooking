package com.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class MainController {
    @GetMapping("")
    public String showHomePage(Model model) {
        model.addAttribute("title", "Trang Chủ"); // Tiêu đề cho trang chủ
        return "view/pages/home"; // Chạy home.html
    }

    @GetMapping("/page/{pageName}")
    public String showPage(@PathVariable String pageName, Model model) {
        String title;
        switch (pageName) {
            case "home":
                title = "Home";
                break;
            case "aboutUs":
                title = "About Us";
                break;
            case "contact":
                title = "Contact";
                break;
            case "services":
                title = "Services";
                break;
            case "stylists":
                title = "Stylists";
                break;
            case "bookNow":
                title = "Book Now";
                break;
            case "handbook":
                title = "Handbook";
                break;
            default:
                title = "Not Found!"; // Tiêu đề mặc định
                break;
        }
        model.addAttribute("title", title);
        return "view/pages/" + pageName; // Chạy "pageName".html
    }
}


