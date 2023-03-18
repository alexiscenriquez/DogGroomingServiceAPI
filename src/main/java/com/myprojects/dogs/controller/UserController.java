package com.myprojects.dogs.controller;

import com.myprojects.dogs.models.User;
import com.myprojects.dogs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/user")
    public List<User> getUsers(){
        return repo.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable int id){
        Optional<User> found=repo.findById(id);
        if(found.isEmpty()){
            return ResponseEntity.status(400).body("User not found");
        }
        return ResponseEntity.status(200).body(found.get());
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        user.setId(null);
        user.setPassword((encoder.encode(user.getPassword())));
        User created=repo.save(user);
        return ResponseEntity.status(200).body(created);
    }
}
