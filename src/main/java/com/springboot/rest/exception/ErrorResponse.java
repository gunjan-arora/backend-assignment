package com.springboot.rest.exception;

import java.util.List;

import org.springframework.stereotype.Component;

public class ErrorResponse
{
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
 
    public ErrorResponse() {
    }
    //General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    } 
}
