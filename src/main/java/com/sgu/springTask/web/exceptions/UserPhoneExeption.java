package com.sgu.springTask.web.exceptions;

public class UserPhoneExeption extends RuntimeException {

    public UserPhoneExeption() {
        super("Incorrect phone or user is not exist");
    }
}
