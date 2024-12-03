package com.project.Variant11.service;

import com.project.Variant11.dto.CarDTO;
import com.project.Variant11.model.Car;
import com.project.Variant11.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(CarDTO carDTO) {
        Car car = new Car();
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setPrice(carDTO.getPrice());
        return carRepository.save(car);
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car updateCar(Long id, CarDTO carDTO) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            updateEntityFromDTO(car, carDTO);
            return carRepository.save(car);
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    private void updateEntityFromDTO(Car car, CarDTO carDTO) {
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setPrice(carDTO.getPrice());
    }
}
