package com.project.Variant11.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_brand", nullable = false)
    private String brand;

    @Column(name = "car_model", nullable = false)
    private String model;

    @Column(name = "manufacture_year", nullable = false)
    private int year;

    @Column(name = "car_price", nullable = false)
    private double price;
}
