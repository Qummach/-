package com.project.Variant11.controller;

import com.project.Variant11.dto.CarDTO;
import com.project.Variant11.model.Car;
import com.project.Variant11.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    @Operation(summary = "Создать автомобиль", description = "Добавление нового автомобиля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Автомобиль успешно создан",
                    content = @Content(schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе")
    })
    public ResponseEntity<Car> createCar(@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.createCar(carDTO));
    }

    @GetMapping
    @Operation(summary = "Получить список всех автомобилей", description = "Возвращает список всех автомобилей.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список автомобилей успешно получен")
    })
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить автомобиль по ID", description = "Возвращает информацию об автомобиле по его ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Автомобиль найден",
                    content = @Content(schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "404", description = "Автомобиль не найден")
    })
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные автомобиля", description = "Обновляет информацию об автомобиле.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Автомобиль успешно обновлен",
                    content = @Content(schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "404", description = "Автомобиль не найден")
    })
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.updateCar(id, carDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автомобиль", description = "Удаляет автомобиль по его ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Автомобиль успешно удален"),
            @ApiResponse(responseCode = "404", description = "Автомобиль не найден")
    })
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
