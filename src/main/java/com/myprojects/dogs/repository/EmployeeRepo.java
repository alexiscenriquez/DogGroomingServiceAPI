package com.myprojects.dogs.repository;

import com.myprojects.dogs.models.Dogs;
import com.myprojects.dogs.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    @Query("select e.dogs from Employee e where e.id=?1")
List<Dogs> findDogsByEmployee(int id);
}
