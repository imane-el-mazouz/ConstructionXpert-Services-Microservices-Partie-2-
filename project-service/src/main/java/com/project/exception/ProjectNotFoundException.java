package com.project.exception;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String id){
        super("project with this id not found " + id);
    }
}
