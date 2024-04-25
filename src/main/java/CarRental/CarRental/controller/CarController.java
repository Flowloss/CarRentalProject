package CarRental.CarRental.controller;

import CarRental.CarRental.model.Car;
import CarRental.CarRental.service.CarService;
import CarRental.CarRental.service.CarServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    CarServiceInterface carService;

    @GetMapping("/api/v1/cars")
    public ResponseEntity<List<Car>> getAllCar() {
        return ResponseEntity.ok(carService.getCar());
    }

    @PostMapping("/api/v1/addCar")
    public ResponseEntity<Car> addNewCar(@Validated @RequestBody Car car) {
        return ResponseEntity.ok(carService.addCar(car));
    }

    @PutMapping("/api/v1/updateCar/{id}")
    public ResponseEntity<Car> updateCar(@Validated @RequestBody Car car, @PathVariable int id) {
        return ResponseEntity.ok(carService.updateCar(car, id));
    }

    @DeleteMapping("/api/v1/deleteCar/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable int id) {
        return ResponseEntity.ok(carService.deleteCar(id));
    }
}
