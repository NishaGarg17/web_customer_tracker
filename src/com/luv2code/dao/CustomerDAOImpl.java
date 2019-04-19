package com.luv2code.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// Need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		
		// Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Create the query sort by last name
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// Execute the query and get the result list
		List<Customer> customerList = query.getResultList();
		
		// Return the results
		return customerList;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
	System.out.println("Id is *************** : " + customer.getId());
		// save or update the customer in database
		currentSession.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int customerId) {
		// Get current Session
		Session currentSession = sessionFactory.getCurrentSession();
		// Get the customer from database using ID
		
		return currentSession.get(Customer.class, customerId);
	}

	@Override
	public void deleteCustomer(int customerId) {
		// Get current Session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//Delete the customer
		currentSession.delete(currentSession.get(Customer.class, customerId));
	}

	@Override
	public List<Customer> searchCustomersByName(String theSearchName) {
		 // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query<Customer> theQuery = null;

        // only search by name if theSearchName is not empty
		if(!theSearchName.isEmpty() && theSearchName.trim().length() > 0) {
			theQuery = currentSession.createQuery("from Customer where firstName like :theName or lastName like :theName",Customer.class);
			theQuery.setParameter("theName", "%"+theSearchName+"%");
			
		} else {
			// theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);
		}
		List<Customer> customerList = theQuery.getResultList();
		return customerList;
	}

}
