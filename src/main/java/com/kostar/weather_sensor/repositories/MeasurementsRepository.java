package com.kostar.weather_sensor.repositories;


import com.kostar.weather_sensor.models.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
}
