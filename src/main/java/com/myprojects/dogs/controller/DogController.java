package com.myprojects.dogs.controller;

import com.myprojects.dogs.exceptions.ResourceNotFoundException;
import com.myprojects.dogs.models.Dogs;
import com.myprojects.dogs.services.DogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Dogs", description = "the API for managing dogs")
@RequestMapping("/api")
@RestController
public class DogController {
    @Autowired
    DogService service;


    @Operation(summary = "Get all the Dogs in the Dog table")
    @GetMapping("/dogs")
    public List<Dogs> getAllDogs(){
        return service.getAllDogs();
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dog has been found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dogs.class) ) ),
            @ApiResponse(responseCode = "404", description = "Dog was not found",
                    content = @Content )
    })

    @Operation(summary = "Get a dog by its ID")
    @GetMapping("/dogs/{id}")
    public Dogs getDogByID(@PathVariable Integer id){
        try {
            return service.getDogByID(id);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dog has been found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dogs.class) ) ),
            @ApiResponse(responseCode = "404", description = "Dog was not found",
                    content = @Content )
    })
    @Operation(summary = "Get a dog by its name")
    @GetMapping("/dogs/name/{name}")
    public Dogs getDogByName(@PathVariable String name){
        try {
            return service.getDogByName(name);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dog has been found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Dogs.class) ) ),
            @ApiResponse(responseCode = "404", description = "Dog was not found",
                    content = @Content )
    })
    @Operation(summary = "Get a dog by its breed")
    @GetMapping("/dogs/breed/{breed}")
    public List<Dogs> getDogsByBreed(@PathVariable String breed){
        try {
            return service.getDogByBreed(breed);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Create a dog entry")
    @PostMapping("/dogs/")
            public ResponseEntity<?> createDogEntry(@Valid @RequestBody Dogs dog){
            Dogs created= service.createDog(dog);
            if(created!=null)
                return ResponseEntity.status(200).body(created);
            else return ResponseEntity.status(400).body(null);
            }
    @Operation(summary = "Update a dog entry")
    @PutMapping("/dogs/")
    public ResponseEntity<?> updateDogEntry(@Valid @RequestBody Dogs dog){
        Dogs updated= null;
        try {
            updated = service.UpdateDog(dog);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(updated!=null)
            return ResponseEntity.status(200).body(updated);
        else return ResponseEntity.status(400).body(null);

    }
//    @Operation(summary = "Delete a dog entry")
//    @DeleteMapping("/dogs/{id}")
//    public ResponseEntity<?>deleteDogEntry(@PathVariable int id) throws ResourceNotFoundException {
//
//            Dogs deleted = service.deleteDog(id);
//            if(deleted!=null) {
//                return ResponseEntity.status(200).body(deleted);
//            }
//        else {
//               throw new ResourceNotFoundException("Dog",id);
//
//            }
//
//    }
}
