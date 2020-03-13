package com.cignex.customer;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	// handler methods
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("index");
		List<Customer> customerList = service.getAllCustomers();
		mav.addObject("customers", customerList);
		return mav;
	}
	
	@RequestMapping("/new")
	public String newCustomerForm(Map<String, Object> model) {
		model.put("customer", new Customer());
		return "new_customer";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("customer") Customer customer) {
		service.saveCustomer(customer);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit")
	public ModelAndView editCustomer(@RequestParam long id) {
		ModelAndView mav = new ModelAndView("edit_customer");
		Customer customer = service.getCustomer(id);
		mav.addObject("customer", customer);
		
		return mav;
	}
	
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam Long id) {
		service.deleteCustomer(id);
		
		return "redirect:/";
	}
	
	@RequestMapping("/search")
	public ModelAndView searchCustomer(@RequestParam String keyword) {
		List<Customer> result = service.searchCustomer(keyword);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("customers", result);
		
		return mav;
	}
}
