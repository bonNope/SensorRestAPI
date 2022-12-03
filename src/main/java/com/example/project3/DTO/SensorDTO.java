package com.example.project3.DTO;

import com.example.project3.models.Measurement;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class SensorDTO {
    @NotEmpty(message = "the sensor must have a name")
    @Size(min = 3, max = 30, message = "name should be between 3 and 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
