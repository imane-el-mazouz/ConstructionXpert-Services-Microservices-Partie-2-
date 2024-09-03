package com.task.exception;

public class TaskNotFoundException extends Throwable {
    public TaskNotFoundException(String id){
        super("task with this id not found " + id);
    }
}
