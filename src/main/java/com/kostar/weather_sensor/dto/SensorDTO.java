package com.kostar.weather_sensor.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    private String nameOfSensor;

    public String getNameOfSensor() {
        return nameOfSensor;
    }

    public void setNameOfSensor(String nameOfSensor) {
        this.nameOfSensor = nameOfSensor;
    }
}
