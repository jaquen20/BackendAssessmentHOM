package com.sandeep.userAuthentication.Exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message){
        super("task with id: "+ message +"not found");
    }
}
