package com.boot.laptop.exception;

public class LaptopNotFoundException extends RuntimeException {

    String exceptionMsg;

    public LaptopNotFoundException(String exceptionMsg) {
        super(exceptionMsg);
        this.exceptionMsg = exceptionMsg;
    }
}
