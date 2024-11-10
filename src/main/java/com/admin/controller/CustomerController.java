package com.admin.controller;

import com.admin.model.Customer;
import com.admin.exception.UserNotFoundException;
import com.admin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired private CustomerService service;

    @GetMapping("/manageCustomers")
    public String showCustomerList(Model model) {
        List<Customer> listCustomers = service.listAll();
        model.addAttribute("listCustomers", listCustomers);

        return "manageCustomers";
    }

    @GetMapping("/manageCustomers/new")
    public String showNewForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Add New Customer");
        return "customer_form";
    }

    @PostMapping("/manageCustomers/save")
    public String saveCustomer(Customer customer, RedirectAttributes ra) {
        service.save(customer);
        ra.addFlashAttribute("message", "The customer has been saved successfully.");
        return "redirect:/manageCustomers";
    }

    @GetMapping("/manageCustomers/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Customer customer = service.get(id);
            model.addAttribute("customer", customer);
            model.addAttribute("pageTitle", "Edit Customer (ID: " + id + ")");

            return "customer_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageCustomers";
        }
    }

    @GetMapping("/manageCustomers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The customer ID " + id + " has been deleted.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/manageCustomers";
    }
}
