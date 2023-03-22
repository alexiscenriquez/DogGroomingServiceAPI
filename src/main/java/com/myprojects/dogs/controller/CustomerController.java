package com.myprojects.dogs.controller;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Customer;
import com.myprojects.dogs.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CustomerController {
    @Autowired
    CustomerService service;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable int id)throws ResourceNotFoundException {
     return service.getCustomerById(id);
    }
    @PostMapping("/customers")
    public ResponseEntity<?> createCustomerEntry(@Valid @RequestBody Customer cust){
        Customer created=service.addCustomer(cust);

//        RestTemplate restTemplate=new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Customer> request = new HttpEntity<>(cust, headers);
//        ResponseEntity<String> response=restTemplate.postForEntity("http://localhost:8080/api/customers",request, String.class);
//        if(response.getStatusCode()!= HttpStatus.valueOf(200)){
//            return ResponseEntity.status(400).body("Post request failed");
//        }
        return ResponseEntity.status(200).body(created);
    }
}
