package com.myprojects.dogs.services;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Customer;
import com.myprojects.dogs.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo repo;

    public List<Customer> getAllCustomers(){
        return repo.findAll();
    }

    public Customer getCustomerById(int id)throws ResourceNotFoundException {
        Optional<Customer> found=repo.findById(id);
        if(found.isEmpty()){
            throw new ResourceNotFoundException("Customer",id);
        }
        return found.get();
    }
}
