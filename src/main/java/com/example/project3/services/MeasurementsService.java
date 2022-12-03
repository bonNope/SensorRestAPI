package com.example.project3.services;

import com.example.project3.models.Measurement;
import com.example.project3.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public void save(Measurement measurement){
        measurementsRepository.save(measurement);
    }

    public int countRainingDays(){
        return measurementsRepository.countAllByRaining(true);
    }

    public List<Measurement> findAll(){
        return measurementsRepository.findAll();
    }

    private void enrichMeasurement(Measurement measurement){
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());

        measurement.setCreatedAt(LocalDateTime.now());
    }

}
