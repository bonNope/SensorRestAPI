package com.example.project3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @Column(name = "data_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull
    @DecimalMax("100.0") @DecimalMin("-100.0")
    //@Size(min = -100, max = 100, message = "temperature can`t be more than 100 and less than -100 degrees celsius")
    private BigDecimal value;

    @Column(name = "raining")
    @NotNull
    private boolean raining;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    public Measurement() {
    }

    public Measurement(BigDecimal value, boolean raining) {
        this.value = value;
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", createdAt=" + createdAt +
                ", sensor=" + sensor +
                '}';
    }
}
