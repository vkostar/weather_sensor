package com.kostar.weather_sensor.controllers;


import com.kostar.weather_sensor.dto.SensorDTO;
import com.kostar.weather_sensor.models.Sensor;
import com.kostar.weather_sensor.services.SensorService;

import com.kostar.weather_sensor.util.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sensors")
public class SensorControllers {

    SensorService sensorService;
    SensorValidator sensorValidator;

    @Autowired
    public SensorControllers(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public String registrateSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().toString();

        }
        Sensor sensor = sensorService.registrate(sensorDTO);
        return "registration is finished successful " + sensor;
    }


}
