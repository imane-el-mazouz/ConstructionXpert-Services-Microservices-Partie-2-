package com.resource.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Long id){
        super("resource with this id not found : " + id);
    }
}
