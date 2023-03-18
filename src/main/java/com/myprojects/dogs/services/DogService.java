package com.myprojects.dogs.services;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Dogs;
import com.myprojects.dogs.repository.DogsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

@Autowired
DogsRepo repo;

  public List<Dogs> getAllDogs(){
        return repo.findAll();
    }
public Dogs getDogByID(Integer id)throws ResourceNotFoundException {
Optional<Dogs> dog=repo.findById(id);
if(dog.isEmpty()){
   throw new ResourceNotFoundException("Dog",id.intValue());
}
    return dog.get();
}
public Dogs getDogByName(String name) throws ResourceNotFoundException{
    Optional<Dogs> dog=repo.findByName(name);
    if(dog.isEmpty()){
        throw new ResourceNotFoundException("Dog",name);
    }
    return dog.get();
}

public  List<Dogs> getDogByBreed(String breed) throws ResourceNotFoundException{
      List<Dogs> dogs=repo.findByBreed(breed);
      if(dogs.isEmpty()){
          throw new ResourceNotFoundException("Dog",breed);
      }
      return dogs;
}
public Dogs createDog(Dogs dog){
      dog.setId(null);
    Dogs created=repo.save(dog);
    if(created==null) {
        return null;
    }
    return created;
}

public Dogs UpdateDog(Dogs dog)throws ResourceNotFoundException{
   //   Dogs toUpdate=repo.findById(dog.getId()).get();
    boolean exists=repo.existsById(dog.getId().intValue());
    Dogs updated;
    if(exists) {
        updated = repo.save(dog);
        return updated;
    }
    else throw new ResourceNotFoundException("Dog",dog.getId().intValue());

}


public Dogs deleteDog(int id) throws ResourceNotFoundException{
      boolean exists=repo.existsById(id);
Dogs toDelete=getDogByID(id);
      if(exists){
          repo.deleteById(id);
          return toDelete;
      }else throw new ResourceNotFoundException("Dog",id);
}
}