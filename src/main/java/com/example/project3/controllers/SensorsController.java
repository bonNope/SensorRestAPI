package com.example.project3.controllers;

import com.example.project3.DTO.SensorDTO;
import com.example.project3.models.Sensor;
import com.example.project3.services.SensorsService;
import com.example.project3.util.SensorErrorResponse;
import com.example.project3.util.SensorNotCreatedException;
import com.example.project3.util.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.project3.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorsController(SensorsService sensorsService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult){
        Sensor newSensor = convertToSensor(sensorDTO);
        sensorValidator.validate(newSensor, bindingResult);

        if(bindingResult.hasErrors())
            returnErrorsToClient(bindingResult, newSensor.getClass());

        sensorsService.save(newSensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e){
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
