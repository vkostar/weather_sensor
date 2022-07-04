package com.kostar.weather_sensor.models;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    @Column(name = "sensor_name")
    private String sensor_name;

    @OneToMany(mappedBy = "sensor")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    List<Measurements> listOfMeasure;

    public Sensor() {
    }

    public Sensor(String sensor_name, List<Measurements> listOfMeasure) {
        this.sensor_name = sensor_name;
        this.listOfMeasure = listOfMeasure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensor_name() {
        return sensor_name;
    }

    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
    }

    public List<Measurements> getListOfMeasure() {
        return listOfMeasure;
    }

    public void setListOfMeasure(List<Measurements> listOfMeasure) {
        this.listOfMeasure = listOfMeasure;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", sensor_name='" + sensor_name + '\'' +
                ", listOfMeasure=" + listOfMeasure +
                '}';
    }
}
