package com.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {


    @GetMapping("/manager/managerDashboard")
    public String managerDashboard(Model model) {

        return "manager/managerDashboard";
    }
}
