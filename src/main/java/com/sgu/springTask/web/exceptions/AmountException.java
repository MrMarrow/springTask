package com.sgu.springTask.web.exceptions;

public class AmountException extends RuntimeException {

    public AmountException() {
        super("Not enough amount on account");
    }
}
