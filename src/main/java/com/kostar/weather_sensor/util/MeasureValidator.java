package com.kostar.weather_sensor.util;

import com.kostar.weather_sensor.dto.MeasurementsDTO;
import com.kostar.weather_sensor.dto.SensorDTO;
import com.kostar.weather_sensor.models.Measurements;
import com.kostar.weather_sensor.services.MeasurementService;
import com.kostar.weather_sensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasureValidator implements Validator {

    MeasurementService measurementService;

    @Autowired
    public MeasureValidator(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementsDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurements measurements = (Measurements) target;
        if (measurementService.findSensor(measurements).isEmpty()) {
            errors.rejectValue("sensor", "", "this sensor doesn't exist. try to registrate a sensor");
        }
    }
}
