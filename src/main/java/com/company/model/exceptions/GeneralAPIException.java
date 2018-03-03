package com.company.model.exceptions;

import com.company.model.web.ErrorResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralAPIException extends AbstractAPIException {


    private String error;
    public GeneralAPIException(String error) {
        this.error = error;
    }

    @Override
    public ErrorResponse getErrorResponse(){
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("Error", new ArrayList<>());
        errors.get("Error").add(error);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errors);
        return errorResponse;
    }

    @Override
    public String getMessage() {
        return error;
    }
}