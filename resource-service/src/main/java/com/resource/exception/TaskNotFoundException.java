package com.resource.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException (String id){
        super("task with this id not found" + id);
    }
}
