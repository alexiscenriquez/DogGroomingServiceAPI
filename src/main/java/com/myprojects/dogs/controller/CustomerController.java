package com.myprojects.dogs.controller;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Customer;
import com.myprojects.dogs.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class CustomerController {
    @Autowired
    CustomerRepo repo;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return repo.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?>getCustomerById(@PathVariable int id)throws ResourceNotFoundException {
        Optional<Customer> found=repo.findById(id);
        if(found.isEmpty()){
            throw new ResourceNotFoundException("Customer",id);
        }
        return ResponseEntity.status(200).body(found);
    }
}
