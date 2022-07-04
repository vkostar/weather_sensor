package com.kostar.weather_sensor.services;

import com.kostar.weather_sensor.dto.SensorDTO;
import com.kostar.weather_sensor.models.Sensor;
import com.kostar.weather_sensor.repositories.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SensorService {
    SensorRepository sensorRepository;
    ModelMapper modelMapper;


    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public Sensor registrate(SensorDTO sensorDTO) {

        Sensor sensor = sensorRepository.save(convertToSensorAndEnrich(sensorDTO));

        return sensor;
    }

    private Sensor convertToSensorAndEnrich(SensorDTO sensorDTO) {
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        return sensor;
    }
}
