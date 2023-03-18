package com.myprojects.dogs.repository;

import com.myprojects.dogs.models.Dog_Customer;
import com.myprojects.dogs.models.Dogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerDogRepo extends JpaRepository<Dog_Customer,Integer> {
    @Query("select d.dog from Dog_Customer d where d.customer.id=?1")
    List<Dogs> getDogsByCustomer(int customerId);
}
