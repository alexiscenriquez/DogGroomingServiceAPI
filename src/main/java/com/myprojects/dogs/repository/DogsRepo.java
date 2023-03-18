package com.myprojects.dogs.repository;

import com.myprojects.dogs.models.Dogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogsRepo extends JpaRepository<Dogs,Integer> {

     List<Dogs> findByBreed(String breed);
     Optional<Dogs> findById(Integer id);

     Optional<Dogs> findByName(String name);
}
