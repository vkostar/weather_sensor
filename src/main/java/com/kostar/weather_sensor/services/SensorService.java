package com.kostar.weather_sensor.services;

import com.kostar.weather_sensor.dto.SensorDTO;
import com.kostar.weather_sensor.models.Measurements;
import com.kostar.weather_sensor.models.Sensor;
import com.kostar.weather_sensor.repositories.MeasurementsRepository;
import com.kostar.weather_sensor.repositories.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SensorService {
    SensorRepository sensorRepository;
    MeasurementsRepository measurementsRepository;
    ModelMapper modelMapper;


    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper, MeasurementsRepository measurementsRepository) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
        this.measurementsRepository = measurementsRepository;
    }

    public Sensor registrate(SensorDTO sensorDTO) {

        Sensor sensor = sensorRepository.save(convertToSensorAndEnrich(sensorDTO));

        return sensor;
    }

    public Sensor convertToSensorAndEnrich(SensorDTO sensorDTO) {
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        return sensor;
    }

    public Optional<Sensor> check(Sensor sensor) {

        return sensorRepository.findByNameOfSensor(sensor.getNameOfSensor());

    }

    public List<Measurements> findAll() {

        return  measurementsRepository.findAll();
    }
}
