package com.kostar.weather_sensor.repositories;


import com.kostar.weather_sensor.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByNameOfSensor(String nameOfSensor);

}