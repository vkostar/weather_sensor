package com.kostar.weather_sensor.repositories;


import com.kostar.weather_sensor.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {



}
