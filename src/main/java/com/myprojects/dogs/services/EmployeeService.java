package com.myprojects.dogs.services;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Dogs;
import com.myprojects.dogs.models.Employee;
import com.myprojects.dogs.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo repo;

    public List<Employee>getAllEmployees(){
        return repo.findAll();
    }

    public Employee findEmployeeById(int id)throws ResourceNotFoundException {
        Optional<Employee> found=repo.findById(id);
        if(found.isEmpty()){
            throw new ResourceNotFoundException("Employee",id);
        }
        return found.get();
    }

    public List<Dogs> findDogsByEmployee(int id) throws ResourceNotFoundException {
      Employee employee=  findEmployeeById(id);
       return repo.findDogsByEmployee(employee.getId());
    }

    public Employee addNewEmployee(Employee emp){
        emp.setId(null);
        repo.save(emp);
        return emp;
    }

    public Employee updateEmployee(Employee emp)throws ResourceNotFoundException {

        boolean exists = repo.existsById(emp.getId());
        if (!exists) {
            throw new ResourceNotFoundException("Employee", emp.getId());
        }
        return repo.save(emp);
    }

    public Boolean deleteEmployee(int id) throws ResourceNotFoundException{
        boolean found=repo.existsById(id);
        if(!found){
        throw new ResourceNotFoundException("Employee",id);
        }
        repo.deleteById(id);
         return true;
    }
}
