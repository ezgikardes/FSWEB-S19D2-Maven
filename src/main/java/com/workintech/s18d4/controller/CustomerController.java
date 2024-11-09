package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponse> findAll(){
        List<Customer> customers = customerService.findAll();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponse customerResponse = new CustomerResponse(
                    customer.getId(),
                    customer.getEmail(),
                    customer.getSalary()
            );
            customerResponses.add(customerResponse);
        }
        return customerResponses;
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer){
        customerService.save(customer);
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @RequestBody Customer customer){
        customerService.update(id, customer);
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
    }

    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
    }
}
