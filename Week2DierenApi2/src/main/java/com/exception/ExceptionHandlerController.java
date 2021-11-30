package com.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


@RestControllerAdvice
public class ExceptionHandlerController
{
//    @ResponseStatus(value = HttpStatus.CONFLICT,
//            reason = "Data integrity violation")
//    @ExceptionHandler({DataIntegrityViolationException.class, DataNotFoundException.class})
//    public void conflict()
//    {
//
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public String runtimeHandler(RuntimeException ex)
    {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public String dataNotFoundHandler(DataNotFoundException ex)
    {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(MalformedInformationException.class)
    public String malformedInformationException(MalformedInformationException ex)
    {
        return ex.getMessage();
    }


//
//    @ExceptionHandler(Exception.class)
//    public String handleError(HttpServletRequest req, RuntimeException ex) {
//
//        return "Error";
//    }
}
