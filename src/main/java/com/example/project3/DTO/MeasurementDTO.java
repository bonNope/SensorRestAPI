package com.example.project3.DTO;

import com.example.project3.models.Sensor;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class MeasurementDTO {
    @NotNull
    @DecimalMax("100.0") @DecimalMin("-100.0")
    private BigDecimal value;

    @NotNull
    private boolean raining;

    private SensorDTO sensorDTO;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
}
