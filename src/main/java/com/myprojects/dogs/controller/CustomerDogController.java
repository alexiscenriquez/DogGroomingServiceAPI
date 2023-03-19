package com.myprojects.dogs.controller;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Dog_Customer;
import com.myprojects.dogs.models.Dogs;
import com.myprojects.dogs.repository.CustomerDogRepo;
import com.myprojects.dogs.repository.CustomerRepo;
import com.myprojects.dogs.services.Dog_CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CustomerDogController {
    @Autowired
    CustomerDogRepo repo;

    @Autowired
    CustomerRepo cRepo;

    @Autowired
    Dog_CustomerService dcService;

    @GetMapping("/customer-dogs")
    public List<Dog_Customer> getAllCustomersWithDogs(){
        return dcService.getAllCustomersWDogs();
    }
    @GetMapping("/customer-dogs/{custId}")
    public List<Dogs> getDogsForCustomer(@PathVariable int custId) throws ResourceNotFoundException {
       return dcService.getAllDogsByCustomer(custId);
    }

}
