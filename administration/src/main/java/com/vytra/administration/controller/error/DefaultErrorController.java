package com.vytra.administration.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DefaultErrorController implements ErrorController {


    @RequestMapping(value = "error", method = RequestMethod.GET)
    @ExceptionHandler(Exception.class)
    public ResponseEntity renderErrorPage(HttpServletRequest httpRequest) {

        int nroError = getErrorCode(httpRequest);

        switch (nroError) {
            case 400: {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            case 401: {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            case 404: {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            case 405: {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
            case 500: {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            default: {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}