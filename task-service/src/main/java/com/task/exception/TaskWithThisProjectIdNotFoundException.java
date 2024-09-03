package com.task.exception;

public class TaskWithThisProjectIdNotFoundException extends RuntimeException{
    public TaskWithThisProjectIdNotFoundException (String projectId){
        super("Project with ID " + projectId + " does not exist.");
    }
}
