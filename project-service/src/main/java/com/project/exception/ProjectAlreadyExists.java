package com.project.exception;

public class ProjectAlreadyExists extends RuntimeException {
    public ProjectAlreadyExists(Long id){
        super("project with this id already exists" + id);
    }
}
