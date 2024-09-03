package com.task.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException (Long id){
        super("task not found with this id" + id);
    }
}
