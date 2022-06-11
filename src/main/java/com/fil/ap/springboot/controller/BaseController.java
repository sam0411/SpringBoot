package com.fil.ap.springboot.controller;

import com.fil.ap.springboot.constants.ErrorType;
import com.fil.ap.springboot.exception.SystemException;
import com.fil.ap.springboot.service.output.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(
            value = {
                ServletRequestBindingException.class,
                MissingServletRequestParameterException.class,
                MethodArgumentNotValidException.class,
                HttpMediaTypeNotSupportedException.class,
                ConstraintViolationException.class
            }
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleThrowable(Exception exception) {

        LOGGER.warn("Invalid Input", exception);

        ErrorMessage result = new ErrorMessage(ErrorType.REQUEST_INVALID_INPUT.getCode(), ErrorType.REQUEST_INVALID_INPUT.getMessage());

        return result;
    }

    @ExceptionHandler(
            value = {
                    SystemException.class
            }
    )
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleThrowable(SystemException exception) {

        ErrorMessage result = new ErrorMessage(exception.getCode(), exception.getMessage());

        return result;
    }
}
