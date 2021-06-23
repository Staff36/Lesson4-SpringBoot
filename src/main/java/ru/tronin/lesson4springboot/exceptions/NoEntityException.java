package ru.tronin.lesson4springboot.exceptions;

public class NoEntityException extends RuntimeException {
    public NoEntityException(String message) {
        super(message);
    }
}
