package com.kostar.weather_sensor.controllers;


import com.kostar.weather_sensor.dto.SensorDTO;
import com.kostar.weather_sensor.models.Sensor;
import com.kostar.weather_sensor.services.SensorService;

import com.kostar.weather_sensor.util.MeasurementErrorResponse;
import com.kostar.weather_sensor.util.MeasurementException;
import com.kostar.weather_sensor.util.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import static com.kostar.weather_sensor.util.ErrorHandler.returnErrorsToClient;

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
    public ResponseEntity<HttpStatus> registrateSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        Sensor sensorToAdd = sensorService.convertToSensorAndEnrich(sensorDTO);
        sensorValidator.validate(sensorToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);

        }
        sensorService.registrate(sensorDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }


}
