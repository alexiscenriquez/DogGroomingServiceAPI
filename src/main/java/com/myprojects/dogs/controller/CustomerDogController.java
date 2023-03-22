package com.myprojects.dogs.controller;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Dog_Customer;
import com.myprojects.dogs.models.Dogs;
import com.myprojects.dogs.repository.CustomerDogRepo;
import com.myprojects.dogs.repository.CustomerRepo;
import com.myprojects.dogs.services.Dog_CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/customer-dogs")
    public ResponseEntity<?>createCustomerDog(@RequestBody @Valid Dog_Customer dc){
        Dog_Customer created=dcService.createCustomerDog(dc);
        if(created==null){
           return  ResponseEntity.status(400).body("Could not create dog");
        }
        return ResponseEntity.status(201).body(created);
    }

}
