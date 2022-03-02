package com.example.bankinformationsystem.utils;

public class UserException extends Exception {

    String exception;
    public UserException(String exception){
        this.exception = exception;
    }

    @Override
    public String toString(){
        return exception;
    }

}
