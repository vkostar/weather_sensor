package com.kostar.weather_sensor.util;


import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


public class ErrorHandler {


   public  static void  returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorsList = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorsList.append(error.getField()).append("-")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage()).append(";");
        }
        throw new MeasurementException(errorsList.toString());
    }
}
