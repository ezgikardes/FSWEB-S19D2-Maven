package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping
    public List<Customer> findAll () {
            return customerService.findAll();
    }


    @GetMapping("/{id}")
    public Customer findById(@PathVariable long id){
        return customerService.find(id);
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer){
        Customer saved = customerService.save(customer);
        return new CustomerResponse(saved.getId(), saved.getEmail(), saved.getSalary());
    }


    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable long id){
        Customer customer = customerService.find(id);
        return new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary());
    }
}
