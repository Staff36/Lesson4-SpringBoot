package ru.tronin.lesson4springboot.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tronin.lesson4springboot.exceptions.NoEntityException;

@ControllerAdvice
public class DefaultAdvice {
    @ExceptionHandler(NoEntityException.class)
    public String handleNoEntityException(NoEntityException e){
        return "products/notFound";
    }
}
