package com.admin.controller;

import com.admin.model.Customer;
import com.admin.exception.CustomerNotFoundException;
import com.admin.service.CustomerService;
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

    // Display all customers
    @GetMapping("/manageCustomers")
    public String showCustomerList(Model model) {
        List<Customer> listCustomers = customerService.listAll();
        model.addAttribute("listCustomers", listCustomers);
        return "admin/manageCustomers";
    }

    // Show form to add a new customer
    @GetMapping("/manageCustomers/new")
    public String showNewForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Add New Customer");
        return "admin/customer_form";
    }

    // Save customer information
    @PostMapping("/manageCustomers/save")
    public String saveCustomer(Customer customer, RedirectAttributes ra) {
        customerService.save(customer);
        ra.addFlashAttribute("message", "The customer has been saved successfully.");
        return "redirect:/manageCustomers";
    }

    // Show form to edit an existing customer
    @GetMapping("/manageCustomers/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Customer customer = customerService.get(id);
            model.addAttribute("customer", customer);
            model.addAttribute("pageTitle", "Edit Customer (ID: " + id + ")");
            return "admin/customer_form";
        } catch (CustomerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageCustomers";
        }
    }

    // Delete customer by ID
    @GetMapping("/manageCustomers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            customerService.delete(id);
            ra.addFlashAttribute("message", "The customer ID " + id + " has been deleted.");
        } catch (CustomerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/manageCustomers";
    }
}
