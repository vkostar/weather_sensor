package com.kostar.weather_sensor.controllers;


import com.kostar.weather_sensor.dto.MeasurementResponse;
import com.kostar.weather_sensor.dto.MeasurementsDTO;
import com.kostar.weather_sensor.models.Measurements;
import com.kostar.weather_sensor.services.MeasurementService;
import com.kostar.weather_sensor.services.SensorService;
import com.kostar.weather_sensor.util.ErrorHandler;
import com.kostar.weather_sensor.util.MeasureValidator;
import com.kostar.weather_sensor.util.MeasurementErrorResponse;
import com.kostar.weather_sensor.util.MeasurementException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.kostar.weather_sensor.util.ErrorHandler.returnErrorsToClient;


@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    MeasurementService measurementService;
    MeasureValidator measureValidator;
    SensorService sensorService;
    ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementService measurementService, MeasureValidator measureValidator, SensorService sensorService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measureValidator = measureValidator;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> registrateMeasurements(@RequestBody @Valid MeasurementsDTO measurementsDTO, BindingResult bindingResult) {

        Measurements measurementsToAdd = measurementService.convertAndEnrich(measurementsDTO);
        measureValidator.validate(measurementsToAdd, bindingResult);
        if (bindingResult.hasErrors()) {

            returnErrorsToClient(bindingResult);

        }
        measurementService.addMeasurements(measurementsToAdd);


        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }

    @GetMapping()
    public MeasurementResponse getAllMeasurements() {

        return new MeasurementResponse(sensorService.findAll()
                .stream().map(this::convertToMeasurementsDTO).collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {

        return sensorService.findAll().stream().filter(Measurements::isRaining).count();

    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }


    public MeasurementsDTO convertToMeasurementsDTO(Measurements measurements) {

        return modelMapper.map(measurements, MeasurementsDTO.class);
    }

}
