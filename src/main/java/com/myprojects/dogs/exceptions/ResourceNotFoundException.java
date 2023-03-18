package com.myprojects.dogs.exceptions;

public class ResourceNotFoundException extends Exception{
private static final long serialVersionID=1L;

    public ResourceNotFoundException(String resource,int id) {
        super(resource+ " with id "+id+ " was not found");
    }

    public ResourceNotFoundException(String resource, String name){
      super(resource +"  "+name+ " was not found");
    }


}
