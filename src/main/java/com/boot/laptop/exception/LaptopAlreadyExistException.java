package com.boot.laptop.exception;

public class LaptopAlreadyExistException extends RuntimeException {

    String exceptionMsg;

    public LaptopAlreadyExistException(String msg) {
        super(msg);
        this.exceptionMsg = msg;
    }
}