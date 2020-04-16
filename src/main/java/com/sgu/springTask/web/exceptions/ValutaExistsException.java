package com.sgu.springTask.web.exceptions;

public class ValutaExistsException extends RuntimeException {

    public ValutaExistsException() {
        super("This valuta is incorrect or not exists");
    }
}
