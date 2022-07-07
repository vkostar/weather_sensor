package com.kostar.weather_sensor.util;

import com.kostar.weather_sensor.dto.SensorDTO;
import com.kostar.weather_sensor.models.Sensor;
import com.kostar.weather_sensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorService.check(sensor).isPresent()) {
            errors.rejectValue("nameOfSensor", "", "this name is already exist. Try to rename a sensor");
        }
    }
}
