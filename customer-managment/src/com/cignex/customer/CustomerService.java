package com.cignex.customer;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository repo;

	// getAll customers
	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}

	// save customer
	public void saveCustomer(Customer customer) {
		repo.save(customer);
	}

	// get Customer
	public Customer getCustomer(Long id) {
		Optional<Customer> customer = repo.findById(id);
		return customer.get();
	}

	// delete customer
	public void deleteCustomer(Long id) {
		repo.deleteById(id);
	}
	
	// search customer
	public List<Customer> searchCustomer(String keyword) {
		return repo.search(keyword);
	}
}
