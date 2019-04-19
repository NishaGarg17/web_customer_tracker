package com.luv2code.dao;

import java.util.List;

import com.luv2code.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomersByName(String theSearchName);
}
	