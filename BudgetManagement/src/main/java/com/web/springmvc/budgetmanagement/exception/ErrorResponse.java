package com.web.springmvc.budgetmanagement.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private Date timestamp;
    private int status;
    private String path;
    private String message;
    private String error;

}
