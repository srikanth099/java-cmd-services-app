package com.jsp.CustomerDataManagement9.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.jsp.CustomerDataManagement9.Entity.Customer;
import com.jsp.CustomerDataManagement9.Repository.CustomerRepository;

@Repository // used to @componentScan//extra layer annotation
public class CustomerDAO {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

	public Customer saveCustomer(Customer customer) {
		return repository.save(customer);
	}

//	public Customer saveAllCustomers(Customer customer) {
//		try {
//			Session session = sessionFactory.openSession();
//			Transaction transaction = session.beginTransaction();
//			session.save(customer);
//			transaction.commit();
//			return customer;
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return customer;
//	}

	public List<Customer> getAllCustomerData() {
		return repository.findAll();
	}

	public Customer getCustomerDataById(int id) {
		return repository.findById(id).orElse(//new Customer(0,null,null));
				 new Customer(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.name()));
	}

	public Customer deleteCustomerDataById(int id) {
		Customer customer = getCustomerDataById(id);
		if (customer.getId() != 404) {
			repository.deleteById(id);
		}
		return customer;

	}

	public List<Customer> getCustomerByName(String name) {
		return repository.findByName(name);
	}

	public List<Customer> validateCustomer(String name, String email) {
		// it accepts two parameters whether it is same it execute other wise it
		return repository.validateCustomer(name, email);
	}

}
//optional stort one object 
//this class used like a layer of service class and repository(objeect) interface