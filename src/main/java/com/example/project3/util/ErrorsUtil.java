package com.example.project3.util;

import com.example.project3.models.Measurement;
import com.example.project3.models.Sensor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult, Class<?> clazz){
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for(FieldError error: errors){
            stringBuilder.append(error.getField()).append(" - ")
                    .append(error.getDefaultMessage()).append(";");
        }
        if(clazz.equals(Sensor.class))
            throw new SensorNotCreatedException(stringBuilder.toString());
        else if(clazz.equals(Measurement.class))
            throw new MeasurementNotCreatedException(stringBuilder.toString());
    }
}
