package com.kostar.weather_sensor.dto;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class SensorDTO {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    private String sensor_name;


    public String getSensor_name() {
        return sensor_name;
    }

    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
    }
}
