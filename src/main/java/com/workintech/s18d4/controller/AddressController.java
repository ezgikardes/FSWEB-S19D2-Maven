package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> findAll(){
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address findById(@PathVariable Long id){
        return addressService.findById(id);
    }

    @PostMapping
    public Address save(@RequestBody Address address){
        return addressService.save(address);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable Long id, @RequestBody Address address){
        return addressService.update(id, address);
    }

    @DeleteMapping("/{id}")
    public Address delete(@PathVariable Long id, Address address){
        return addressService.delete(id, address);
    }




}
