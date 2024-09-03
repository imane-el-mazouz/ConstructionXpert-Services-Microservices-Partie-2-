package com.resource.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(Long id ){
        super("resource already exists with this id : " + id);
    }
}
