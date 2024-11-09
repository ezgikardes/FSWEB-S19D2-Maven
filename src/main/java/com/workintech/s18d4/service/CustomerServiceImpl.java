package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()){
            return null;
            //TODO exception
        }
        return customers;
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
           return optionalCustomer.get();
        }
        //TODO exception
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        if(customer == null){
            //TODO exception
            return null;
        }
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer update(Long id, Customer customer) {
        if (customer == null){
            return null;
            //TODO exception
        }
        customerRepository.save(customer);
        return findById(id);
    }

    @Override
    public Customer delete(Long id) {
        return findById(id);
    }

}
