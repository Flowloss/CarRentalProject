package CarRental.CarRental.controller;

import CarRental.CarRental.model.Car;
import CarRental.CarRental.service.CarService;
import CarRental.CarRental.service.CarServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CarController {
    @Autowired
    CarServiceInterface carService;

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        logger.info("Fetching all cars");
        List<Car> cars = carService.getCar();
        logger.info("Returning {} cars", cars.size());
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/addcar")
    public ResponseEntity<Car> addNewCar(@Validated @RequestBody Car car) {
        logger.info("Adding new car: {}", car);
        Car addedCar = carService.addCar(car);
        logger.info("Car added successfully: {}", addedCar);
        return ResponseEntity.ok(addedCar);
    }

    @PutMapping("/updatecar/{id}")
    public ResponseEntity<Car> updateCar(@Validated @RequestBody Car car, @PathVariable int id) {
        logger.info("Updating car with ID: {}", id);
        Car updatedCar = carService.updateCar(car, id);
        if (updatedCar != null) {
            logger.info("Car updated successfully: {}", updatedCar);
        } else {
            logger.warn("Car with ID {} not found", id);
        }
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/deletecar/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable int id) {
        logger.info("Deleting car with ID: {}", id);
        boolean deleted = carService.deleteCar(id);
        if (deleted) {
            logger.info("Car with ID {} deleted successfully", id);
        } else {
            logger.warn("Car with ID {} not found", id);
        }
        return ResponseEntity.ok(deleted);
    }
}
