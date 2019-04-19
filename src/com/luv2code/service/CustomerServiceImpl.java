package com.luv2code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.dao.CustomerDAO;
import com.luv2code.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	// Need to inject CustomerDAO
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {
		return customerDAO.getCustomer(customerId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerId) {
		customerDAO.deleteCustomer(customerId);
		
	}

	@Override
	@Transactional
	public List<Customer> searchCustomersByName(String theSearchName) {
		return customerDAO.searchCustomersByName(theSearchName);
	}
	

}
