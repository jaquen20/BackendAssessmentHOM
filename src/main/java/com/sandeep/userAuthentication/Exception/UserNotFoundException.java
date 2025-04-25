package com.sandeep.userAuthentication.Exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String s) {
        super("user not found with username: "+ s);
    }
}
