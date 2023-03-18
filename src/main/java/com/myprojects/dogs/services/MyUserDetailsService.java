package com.myprojects.dogs.services;

import com.myprojects.dogs.models.User;
import com.myprojects.dogs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
@Autowired
    UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> found=repo.findByUsername(username);
        if(found.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return new MyUserDetails(found.get());
    }
}
