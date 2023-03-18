package com.myprojects.dogs.controller;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Customer;
import com.myprojects.dogs.models.Dog_Customer;
import com.myprojects.dogs.models.Dogs;
import com.myprojects.dogs.repository.CustomerDogRepo;
import com.myprojects.dogs.repository.CustomerRepo;
import com.myprojects.dogs.repository.DogsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class CustomerDogController {
    @Autowired
    CustomerDogRepo repo;

    @Autowired
    CustomerRepo cRepo;

    @Autowired
    DogsRepo dRepo;

    @GetMapping("/customer-dogs")
    public List<Dog_Customer> getAllCustomersWithDogs(){
        return repo.findAll();
    }
    @GetMapping("/customer-dogs/{custId}")
    public List<Dogs> getDogsForCustomer(@PathVariable int custId) throws ResourceNotFoundException {
        Optional<Customer> found=cRepo.findById(custId);
        if(found.isEmpty()){
            throw new ResourceNotFoundException("Customer",custId);
        }
        return repo.getDogsByCustomer(custId);
    }

}
