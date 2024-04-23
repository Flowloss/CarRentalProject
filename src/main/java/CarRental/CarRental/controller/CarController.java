package CarRental.CarRental.controller;

import CarRental.CarRental.model.Car;
import CarRental.CarRental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("addcar")
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable int id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
    }
}
