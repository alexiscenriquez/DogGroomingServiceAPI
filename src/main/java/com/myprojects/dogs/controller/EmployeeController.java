package com.myprojects.dogs.controller;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Dogs;
import com.myprojects.dogs.models.Employee;
import com.myprojects.dogs.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return service.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) throws ResourceNotFoundException{
        return service.findEmployeeById(id);
    }

    @GetMapping("/employees/dogs/{id}")
    public List<Dogs> getDogsByEmployee(@PathVariable int id) throws ResourceNotFoundException{
        return service.findDogsByEmployee(id);
    }

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody  Employee emp){
           Employee created= service.addNewEmployee(emp);
            if(created==null){
                return ResponseEntity.status(400).body("Could Not Create Employee");
            }
            return ResponseEntity.status(200).body(created);
    }

    @PutMapping("/employees")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee emp )throws ResourceNotFoundException{
    Employee updated=service.updateEmployee(emp);
    if(updated==null){
        return ResponseEntity.status(400).body("Employee could not be updated");
    }
    return   ResponseEntity.status(200).body(updated);
    }

//    @DeleteMapping("/employees/{id}")
//    public ResponseEntity<?> deleteEmployee(@PathVariable int id) throws ResourceNotFoundException{
//    boolean deleted=service.deleteEmployee(id);
//    if(deleted){
//        return  ResponseEntity.status(200).body("Employee Deleted");
//    }
//    return ResponseEntity.status(400).body("Employee Not Deleted");
//    }
}
