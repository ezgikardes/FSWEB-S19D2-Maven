package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.exception.AddressException;
import com.workintech.s18d4.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            throw new AddressException("Address list is empty", HttpStatus.NOT_FOUND);
        }
        return addressRepository.findAll();
    }

    @Override
    public Address findById(long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            return optionalAddress.get();
        } else {
            throw new AddressException("Address cannot be found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Address save(Address address) {
        if(address == null){
            throw new AddressException("Address cannot be null", HttpStatus.BAD_REQUEST);
        }
        return addressRepository.save(address);
    }

    @Override
    public Address delete(long id) {
        Address addressToDelete = findById(id);
        addressRepository.delete(addressToDelete);
        return addressToDelete;
    }

}
