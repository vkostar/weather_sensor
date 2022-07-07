package com.kostar.weather_sensor.services;


import com.kostar.weather_sensor.dto.MeasurementsDTO;
import com.kostar.weather_sensor.models.Measurements;
import com.kostar.weather_sensor.models.Sensor;
import com.kostar.weather_sensor.repositories.MeasurementsRepository;
import com.kostar.weather_sensor.repositories.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class MeasurementService {
    MeasurementsRepository measurementsRepository;
    SensorRepository sensorRepository;
    ModelMapper modelMapper;

    @Autowired
    public MeasurementService(MeasurementsRepository measurementsRepository, SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.measurementsRepository = measurementsRepository;
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Sensor> findSensor(Measurements measurements) {

        return sensorRepository.findByNameOfSensor(measurements.getSensor().getNameOfSensor());
    }

    public void addMeasurements(Measurements measurements) {
        measurementsRepository.save(measurements);


    }

    public Measurements convertAndEnrich(MeasurementsDTO measurementsDTO) {
        Measurements measurementsToAdd = modelMapper.map(measurementsDTO, Measurements.class);
        Sensor sensor = sensorRepository.findByNameOfSensor(measurementsDTO.getSensor().getNameOfSensor()).get();
        measurementsToAdd.setSensor(sensor);
        measurementsToAdd.setDate(new Date());
        return measurementsToAdd;
    }
}
