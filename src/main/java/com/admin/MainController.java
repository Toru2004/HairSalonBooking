package com.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    @GetMapping("")
    public String showHomePage() {
        return "home"; // This should correspond to home.html
    }

    @GetMapping("/page/{pageName}")
    public String showPage(@PathVariable String pageName) {
        return pageName; // This assumes that the `pageName` corresponds to the template file name
    }
}

