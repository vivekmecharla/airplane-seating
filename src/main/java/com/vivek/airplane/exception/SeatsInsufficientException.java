package com.vivek.airplane.exception;

public class SeatsInsufficientException extends Throwable {
    public SeatsInsufficientException(String message) {
        System.out.println(message);
    }
}
