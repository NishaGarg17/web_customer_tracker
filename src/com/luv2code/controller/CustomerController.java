package com.luv2code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.entity.Customer;
import com.luv2code.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	// Need to inject Customer DAO
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		// get the customer list from customerDao
		List<Customer> customerList = customerService.getCustomers();
		
		// add the customerList to Model
		theModel.addAttribute("customerList", customerList);
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// create model attribute to bind form data
		Customer customer = new Customer();
		theModel.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId, Model theModel) {
		// get the customer from the database
		Customer theCustomer = customerService.getCustomer(customerId);
		
		// Set customer as model attribute to pre populate the form
		theModel.addAttribute("customer", theCustomer);
		// Send over to our form
		return "customer-form";
	}
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int customerId, Model theModel) {
		// delete the customer
		customerService.deleteCustomer(customerId);
		// get the customer list from customerDao
		List<Customer> customerList = customerService.getCustomers();
		
		// add the customerList to Model
		theModel.addAttribute("customerList", customerList);
		
		// Send over to our form
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		
		// search customers from the Service
		List<Customer> customerList = customerService.searchCustomersByName(theSearchName);
		
		// add the customerList to Model
		theModel.addAttribute("customerList", customerList);
		
		// Send over to our form
		return "list-customers";
	}
}
