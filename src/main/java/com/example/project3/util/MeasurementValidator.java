package com.example.project3.util;

import com.example.project3.models.Measurement;
import com.example.project3.services.MeasurementsService;
import com.example.project3.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final MeasurementsService measurementsService;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(MeasurementsService measurementsService, SensorsService sensorsService) {
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if(measurement.getSensor() == null){
            errors.rejectValue("sensor","", "you must specify the sensor from the measurement is coming");
        }
        if(sensorsService.findByName(measurement.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor","","sensor with that name doesn't exist");
        }
    }
}
