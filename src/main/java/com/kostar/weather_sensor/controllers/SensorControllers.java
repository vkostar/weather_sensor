package com.kostar.weather_sensor.controllers;


import com.kostar.weather_sensor.dto.SensorDTO;
import com.kostar.weather_sensor.models.Sensor;
import com.kostar.weather_sensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorControllers {


    SensorService sensorService;


    @Autowired
    public SensorControllers(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public Sensor registrateSensor(@RequestBody SensorDTO sensorDTO) {
        Sensor sensor = sensorService.registrate(sensorDTO);
        return sensor;
    }


}
