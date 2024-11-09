package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public List<Address> findAll() {
        List<Address> addresses = addressRepository.findAll();
        if (addresses.isEmpty()){
            //TODO
            return null;
        }
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            return optionalAddress.get();
        } else {
            //TODO exception
            return null;
        }
    }

    @Override
    public Address save(Address address) {
        if(address == null){
            return null;
            //TODO exception
        }
        return addressRepository.save(address);
    }

    @Override
    public Address update(Long id, Address address) {
        if (address == null){
            //TODO exception
            return null;
        }
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            addressRepository.save(address);
        }
        return address;
    }

    @Override
    public Address delete(Long id, Address address) {
        if (address == null){
            return null;
            //TODO exception
        }
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            return addressRepository.save(address);
        }
        return null;
        //TODO exception
    }


}
