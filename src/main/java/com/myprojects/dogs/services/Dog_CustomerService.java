package com.myprojects.dogs.services;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Customer;
import com.myprojects.dogs.models.Dog_Customer;
import com.myprojects.dogs.models.Dogs;
import com.myprojects.dogs.repository.CustomerDogRepo;
import com.myprojects.dogs.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Dog_CustomerService {
    @Autowired
    CustomerDogRepo repo;

    @Autowired
    CustomerRepo cRepo;

    public List<Dogs> getAllDogsByCustomer(int id)throws ResourceNotFoundException {
        Optional<Customer> found=cRepo.findById(id);
        if(found.isEmpty()){
            throw new ResourceNotFoundException("Customer",id);
        }
        return repo.getDogsByCustomer(id);
    }

    public Dog_Customer createCustomerDog( Dog_Customer dc) {
        if(dc==null){
            return null;
        }
      dc.setId(null);

        return repo.save(dc);
    }

    public List<Dog_Customer> getAllCustomersWDogs(){
        return repo.findAll();
    }
}
