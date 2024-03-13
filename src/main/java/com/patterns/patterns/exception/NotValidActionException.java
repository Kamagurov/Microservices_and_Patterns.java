package com.patterns.patterns.exception;

public class NotValidActionException extends RuntimeException {

    public NotValidActionException(String message) {
        super(String.format("Чувак такого action(a) - %s нет", message));
    }
}