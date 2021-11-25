package com.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


@ControllerAdvice
public class ExceptionHandlerController
{
    @ResponseStatus(value = HttpStatus.CONFLICT,
            reason = "Data integrity violation")
    @ExceptionHandler({DataIntegrityViolationException.class, DataNotFoundException.class})
    public void conflict()
    {

    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {
        return "databaseError";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
