package com.example.project3.controllers;

import com.example.project3.DTO.MeasurementDTO;
import com.example.project3.DTO.MeasurementsResponse;
import com.example.project3.models.Measurement;
import com.example.project3.services.MeasurementsService;
import com.example.project3.services.SensorsService;
import com.example.project3.util.MeasurementNotCreatedException;
import com.example.project3.util.MeasurementErrorResponse;
import com.example.project3.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.project3.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, SensorsService sensorsService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult){
        Measurement newMeasurement = convertToMeasurement(measurementDTO);
        measurementValidator.validate(newMeasurement, bindingResult);
        if(bindingResult.hasErrors())
            returnErrorsToClient(bindingResult, newMeasurement.getClass());

        measurementsService.save(newMeasurement);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public MeasurementsResponse getMeasurements(){
        return new MeasurementsResponse(measurementsService.findAll().stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public String RainyDaysCount(){
        return "count of rainy days is " + measurementsService.countRainingDays();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
