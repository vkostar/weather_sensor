package com.kostar.weather_sensor.dto;

import com.kostar.weather_sensor.models.Sensor;

import javax.validation.constraints.*;


public class MeasurementsDTO {

    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;

    @NotNull
    private boolean raining;
    //    @NotEmpty(message = "Name should not be empty")
    SensorDTO sensor;

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }


}
